<%@page import="java.io.IOException"%>
<%@page import="java.net.MalformedURLException"%>
<%@page import="java.io.InputStreamReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page import="java.net.HttpURLConnection"%>
<%@page import="java.net.URL"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>

<%@page trimDirectiveWhitespaces="true" %>

<%!
private String getNewsletterURL(String code, HttpServletRequest request) throws MalformedURLException, IOException{
	String urlString = PortalUtil.getPortalURL(request)+"/service-content-portlet/api/jsonws/impresacontent/get-newsletter-url?code="+code; 
	URL url = new URL(urlString);
	HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	connection.connect();
	
	BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	String inputLine;
	StringBuffer jsonString = new StringBuffer("");
	while ((inputLine = in.readLine()) != null){
		jsonString.append(inputLine); 
	}
	in.close();
	
	return jsonString.toString();	
}
%>
<% 
	request.setAttribute("config", getNewsletterURL(request.getParameter("code"), request));
	request.setAttribute("code", request.getParameter("code"));
%>
<jsp:include page="jsp/html/newsletter/newsletterController.jsp"></jsp:include>