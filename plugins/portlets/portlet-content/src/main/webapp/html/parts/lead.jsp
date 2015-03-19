<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty article.lead}">
	<div class="leadContainer">
	<%
		ContentPortletConfiguration widgetConfiguration 
				= (ContentPortletConfiguration) request.getAttribute( WIDGET.WIDGET_CONFIGURATION );
		String contentSource = widgetConfiguration.getString(CONTENT.CONTENT_SOURCE, CONTENT.CONTENT_SECTION);
		if(contentSource != null && contentSource.equalsIgnoreCase( CONTENT.CONTENT_DETAIL )) {
			%>
				<h2>${article.lead}</h2>
			<%
		} else {
			%>
				<h4>${article.lead}</h4>
			<%
		}
	%>
	</div>
</c:if>