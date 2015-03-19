<%@page import="com.liferay.portal.kernel.json.JSONFactoryUtil"%>
<%@page import="com.liferay.portal.kernel.json.JSONObject"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="java.text.ParseException"%>
<%@page trimDirectiveWhitespaces="true" %>

<%!
private static String getFinalDateFormat(String sDate){
	String result = null;
	try {
		Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(sDate) ;
		result = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy").format(date);
	} catch (ParseException e) {
		System.err.println(e);
	}
	return result;
}

private static String getDate(String dateTime) {
	if(dateTime!=null){
		int tIndex = dateTime.indexOf('T');
		return tIndex > 0 ? dateTime.substring(0, tIndex) : null;
	}
	return null;
}

private static String getTime(String dateTime){
	if(dateTime!=null){
		int tIndex = dateTime.indexOf('T');
		return (tIndex > 0) ? dateTime.substring(tIndex+1, tIndex+9) : null;
	}
	return null;
}

//Tue May 07 11:28:13 WEST 2013
//2013-05-07T09:56:44+01:00
%>
<% 
	List<JSONObject> list = (List<JSONObject>) request.getAttribute("contents0");
	String sDate = getDate(list.get(0).getString("publishedDate"));	
	String sTime = getTime(list.get(0).getString("publishedDate"));
	String sDateTime = sDate +" "+ sTime;
	
	pageContext.setAttribute("finalDate", getFinalDateFormat(sDateTime));
%>

<?xml version="1.0" encoding="UTF-8" standalone="yes"?>
<newsletter>
<subject>${contents0[0].getString("title")}</subject>
<date>${finalDate}</date>
</newsletter>