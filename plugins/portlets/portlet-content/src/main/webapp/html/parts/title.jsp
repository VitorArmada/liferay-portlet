<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="com.liferay.util.portlet.PortletProps"%>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Portlet"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil"%>
<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<c:if test="${not empty article.title}">
	<div class="titleContainer">
    <%
		ContentPortletConfiguration widgetConfiguration 
				= (ContentPortletConfiguration) request.getAttribute( WIDGET.WIDGET_CONFIGURATION );
		String contentSource = widgetConfiguration.getString( CONTENT.CONTENT_SOURCE, CONTENT.CONTENT_SECTION );
		if(contentSource != null && contentSource.equalsIgnoreCase( CONTENT.CONTENT_DETAIL )) {
			%>
				<h1>${article.title}</h1>
			<%
		} else {
			%>
				<h3>
					<c:choose>
						<c:when test="${not empty article.articlePageUrl}">
							<a href="${article.articlePageUrl}">${article.title}</a>
						</c:when>
						<c:otherwise>
							${article.title}
						</c:otherwise>
					</c:choose>
				</h3>
			<%
		}
	%>
    </div>
</c:if>