<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    
    String fieldName = request.getParameter("fieldName");
	String defaultValue = request.getParameter("defaultValue");
	String label = request.getParameter("label");
	String labelClass = request.getParameter("labelClass");
	boolean fieldDefaultValue = Boolean.getBoolean(defaultValue);
		
	ContentPortletConfiguration widgetConfiguration 
			= (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);

	boolean configuredValue = false;
	if(widgetConfiguration != null) {
		configuredValue = widgetConfiguration.getBoolean(fieldName, fieldDefaultValue);
	}
%>



<input type="checkbox" 
	   name="<portlet:namespace /><%= fieldName %>" 
	   <%= (configuredValue) ? "checked" : "" %> />

<label class="<%= labelClass %>"><%= label %></label>

