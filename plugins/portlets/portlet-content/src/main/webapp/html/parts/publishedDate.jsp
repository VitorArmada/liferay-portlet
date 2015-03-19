<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="pt.impresa.date.DateUtils"%>
<%@page import="pt.impresa.log.ILog" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="date" value="${article.publishedDate}" scope="request" />
<c:set var="dateFormat" value="${fieldConfiguration.dateFormat}" scope="request" />

<%
String date = "";
String dateFormat = (String) request.getAttribute("dateFormat");

try 
{
    if(dateFormat != null && !dateFormat.isEmpty()) {
        date = (String) request.getAttribute("date");
        if(date != null && !date.isEmpty()) {
            Date theDate = DateUtils.DATETIME.fromISO8601(date);
            SimpleDateFormat dt = new SimpleDateFormat(dateFormat);
            date = dt.format(theDate);
        }
    }
} catch (Exception ex) {
    ILog.get().error(ex);
}

%>
<div class="dateContainer publishedDate">
    <span><%= date %></span>
</div>