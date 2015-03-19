<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS.DEFAULT"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<%
    ContentPortletConfiguration widgetConfiguration = 
            (ContentPortletConfiguration)request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<div class="followLinkContainer">
	<a href="<%= widgetConfiguration.getString(PAGINATION.FOLLOW_LINK) %>"><span><%= widgetConfiguration.getString(PAGINATION.FOLLOW_LINK_LABEL) %></span></a>
</div>