<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="org.apache.commons.lang3.StringEscapeUtils"%>

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
			configuredValue = StringEscapeUtils.escapeHtml4(String.valueOf(val));
		}
	}
%>

<label class="<%= labelClass %>"><%= label %></label>
<input type="text" 
       name="<portlet:namespace /><%= fieldName %>" 
       value="<%= configuredValue %>" 
       class="<%= cssClass %>" />


