<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    
    String fieldName = request.getParameter("fieldName");
	String cssClass = request.getParameter("cssClass");
	String label = request.getParameter("label");
	String labelClass = request.getParameter("labelClass");
		
	ContentPortletConfiguration widgetConfiguration 
            = (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);

	String configuredValue = "";
	if(widgetConfiguration != null) {
		Object val = widgetConfiguration.get(fieldName);
		if(val != null) {
			configuredValue = String.valueOf(val);
		}
	}
%>

<label class="<%= labelClass %>"><%= label %></label>
<select 
    name="<portlet:namespace /><%= fieldName %>"
    class="<%= cssClass %>">
    <c:set var="configuredValue" value="<%= configuredValue %>" />
    <c:forEach var="option" items="${paramValues.options}">
        <c:choose>
            <c:when test="${option eq configuredValue}">
                <option value="${option}" selected>${option}</option>
            </c:when>
            <c:otherwise>
                <option value="${option}">${option}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>

</select>
			