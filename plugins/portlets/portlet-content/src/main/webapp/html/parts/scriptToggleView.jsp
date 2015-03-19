<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS.DEFAULT"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<%
    ContentPortletConfiguration widgetConfiguration = 
            (ContentPortletConfiguration)request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<script type="text/javascript">
	//head.js( "/js/lib/jquery.cookie.js");
</script>

<div class="toggleViewButtons">
	<div class="toggleToDefaultView activeView" onclick="PortletCommon.VisualEffects.toggleView(this);">
		<span><%= widgetConfiguration.getString(SCRIPTS.TOGGLEVIEW_DEFAULTVIEW, DEFAULT.TOGGLEVIEW_DEFAULTVIEW) %></span>
	</div>
	<div class="toggleToAlternativeView" onclick="PortletCommon.VisualEffects.toggleView(this);">
		<span><%= widgetConfiguration.getString(SCRIPTS.TOGGLEVIEW_ALTERNATIVEVIEW, DEFAULT.TOGGLEVIEW_ALTERNATIVEVIEW) %></span>
	</div>
</div>