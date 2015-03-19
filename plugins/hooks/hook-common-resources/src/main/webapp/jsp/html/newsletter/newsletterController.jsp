<%@page import="java.io.FileNotFoundException"%>
<%@page import="com.liferay.portal.kernel.json.JSONSerializable"%>
<%@page import="com.liferay.portal.kernel.json.JSONSerializer"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="com.liferay.portal.util.Portal"%>
<%@page import="com.liferay.portal.kernel.util.Base64"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.URLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortletMode"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayPortlet"%>
<%@page import="com.liferay.portal.kernel.servlet.LiferayFilter"%>
<%@page import="org.apache.http.impl.DefaultHttpClientConnection"%>
<%@page import="org.apache.http.HttpHost"%>
<%@page import="com.liferay.portal.kernel.json.JSONException"%>
<%@page import="javax.servlet.jsp.tagext.TryCatchFinally"%>
<%@page import="com.liferay.portal.kernel.json.JSONArray"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONFactory"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.lang.Exception"%>
<%@page import="com.liferay.portal.model.Contact"%>
<%@page import="java.util.LinkedList" %>
<%@page import="java.util.List" %>
<%@page import="java.util.Map" %>

<%@page contentType="text/html; charset=utf-8" %>
<%@page trimDirectiveWhitespaces="true" %>

	<%!
		private String clearJsonString(String jsonString){
			return jsonString.substring(1,jsonString.length()-1).replace("\\\"", "\"").replace("\\\\\"", "\\\"");
		}
		
		private void setAttributeContentList(JSONArray jsonArray, String attrName, HttpServletRequest request, String publicationAttr) throws JSONException, MalformedURLException, IOException{
			for (int i = 0; i < jsonArray.length(); i++) {
					List<JSONObject> impresaContentList = new LinkedList<JSONObject>(); 
					JSONObject child = jsonArray.getJSONObject(i);
					int results = child.isNull("results")?1:child.getInt("results");
					String groupId = child.getString("groupId").isEmpty()?null:child.getString("groupId");
					
					String jsonString = getJsonContentString(child.getString("publication"), child.getString("section"), groupId, results, "%5Bnews%5D",request);
					String finalJsonSring = clearJsonString(jsonString);
					
					JSONObject contents = JSONFactoryUtil.createJSONObject(finalJsonSring);

					if(contents.getJSONArray("content")!=null){
						JSONArray contentArray = contents.getJSONArray("content");

						for(int j=0; j < contentArray.length(); j++){
							impresaContentList.add(contentArray.getJSONObject(j));
						}
						request.setAttribute(attrName+i, impresaContentList);
						request.setAttribute(publicationAttr+i, child.getString("publication")+"/");
					}else{				
						request.setAttribute("isQueryNull", true);
						break;
					}		 		
				}  
		    }
		
		private String getJsonContentString(String publication, String section, String groupId, int results, String contentTypes, HttpServletRequest request) throws MalformedURLException, IOException{
			String groupIdParameter = groupId==null?"-groupID=":"groupId="+groupId;
			
			String urlString = PortalUtil.getPortalURL(request)+"/service-content-portlet/api/jsonws/impresacontent/get-contents-json?publication="+publication+
					"&section="+section+"&includeSubsections=true&"+groupIdParameter+"&maxArticles="+results+"&contentTypes="+contentTypes+
					"&-fields=&page=0&-searchKeywords=&-relations=";

			URL url = new URL(urlString);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.connect();
			   
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer jsonContentString = new StringBuffer("");
			while ((inputLine = in.readLine()) != null){
				jsonContentString.append(inputLine); 
			}
			in.close();
			
			return jsonContentString.toString();
		}
	%>
	
   <%

        if("ilike".equals(request.getParameter("cock"))){
            request.setAttribute("isQueryNull", true);
            return;
        }


   		try{
   			request.setAttribute("newsletterUrl", request.getRequestURL());
   			request.setAttribute("w220h138", "?v=w220h138");
   			request.setAttribute("w220h335", "?v=w220h335");
   			request.setAttribute("cdnurl", "http://images.cdn.impresa.pt/");
   			request.setAttribute("isQueryNull", false);
 			
   			String jsonObjectString = clearJsonString(request.getAttribute("config").toString());;
			JSONObject parentJObject = JSONFactoryUtil.createJSONObject(jsonObjectString);
			JSONArray jsonArrayLatest = parentJObject.getJSONArray("newsletterContent");
			
			if(jsonArrayLatest!=null){
				setAttributeContentList(jsonArrayLatest, "contents", request, "publication");
				String newsletterView = parentJObject.getString("view")+".jsp";
					
				if(!(Boolean)request.getAttribute("isQueryNull")){
					try{
						if(request.getParameter("subject")==null){
							%> <jsp:include page="<%= newsletterView %>"></jsp:include> <%
						}else{
							%> <jsp:include page="newsletterSubject.jsp"></jsp:include> <%
						}
					}catch(Exception e){
						e.printStackTrace();
						request.setAttribute("errorMessage", "View not found");
						%> <jsp:include page="newsletterError.jsp"></jsp:include> <%
					}
				}else{
					request.setAttribute("errorMessage", "No Contents Found");
					%> <jsp:include page="newsletterError.jsp"></jsp:include> <%
				}
			}else{
				request.setAttribute("errorMessage", "JSON Error");
				%> <jsp:include page="newsletterError.jsp"></jsp:include> <%
			}
   		}catch(JSONException e){
   			e.printStackTrace();
   			request.setAttribute("errorMessage", "JSON Error");
			%> <jsp:include page="newsletterError.jsp"></jsp:include> <%
   		}catch(MalformedURLException e){
   			e.printStackTrace();
   		}catch(IOException e){
   			e.printStackTrace();
   		}
   %>
