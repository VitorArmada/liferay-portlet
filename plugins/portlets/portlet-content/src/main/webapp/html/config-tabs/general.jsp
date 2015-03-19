<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects/>

<%
	ContentPortletConfiguration impresaConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />general">
	<input name="<portlet:namespace /><%= Constants.CMD%>" type="hidden" value="<%= Constants.CMD%>"/>
	<input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE%>" type="hidden" value="<%= GENERAL.MODULE_NAME%>" />

	<div id="contentSourceModel">

		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/selectbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= GENERAL.DISPLAY%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= GENERAL.DISPLAY_ALWAYS%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= GENERAL.DISPLAY_ARTICLE_PAGE%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= GENERAL.DISPLAY_SECTION_PAGE%>"></liferay-util:param>
					<liferay-util:param name="label" value="Display"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer hideConfigDiv">
			<div class="first">
				<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= GENERAL.APPEND_TO_NEW_TARGET%>"></liferay-util:param>
					<liferay-util:param name="label" value="Append To New Target"></liferay-util:param>
				</liferay-util:include>
			</div>
			<div class="last">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= GENERAL.NEW_TARGET_ID%>"></liferay-util:param>
					<liferay-util:param name="label" value="New Target Id"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
				</liferay-util:include>
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= GENERAL.NEW_TARGET_ANIM_DURATION%>"></liferay-util:param>
					<liferay-util:param name="label" value="Animation Duration (ms)"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputShortestSize"></liferay-util:param>
				</liferay-util:include>
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= GENERAL.NEW_TARGET_ANIM_DELAY%>"></liferay-util:param>
					<liferay-util:param name="label" value="Animation Delay (ms)"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputShortestSize"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>

	</div>

	<input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>general);"/>
</form>