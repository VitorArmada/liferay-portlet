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

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />content">
	<input name="<portlet:namespace /><%= Constants.CMD%>" type="hidden" value="<%= Constants.CMD%>"/>
	<input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE%>" type="hidden" value="<%= CONTENT.MODULE_NAME%>" />

	<div id="contentSourceModel">

		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/selectbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.CONTENT_SOURCE%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= CONTENT.CONTENT_SECTION%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= CONTENT.CONTENT_RELATED%>"></liferay-util:param>
					<liferay-util:param name="options" value="<%= CONTENT.CONTENT_DETAIL%>"></liferay-util:param>
					<liferay-util:param name="label" value="Content Source"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.HIDE_ON_CONTENT%>"></liferay-util:param>
					<liferay-util:param name="label" value="Hide on Details display"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.PUBLICATION%>"></liferay-util:param>
					<liferay-util:param name="label" value="Publication"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputLongSize"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.SECTION%>"></liferay-util:param>
					<liferay-util:param name="label" value="Section"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputLongSize"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.ARTICLE%>"></liferay-util:param>
					<liferay-util:param name="label" value="Article"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputLongSize"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.GROUP_ID%>"></liferay-util:param>
					<liferay-util:param name="label" value="Group Id (if left empty content source will be Latest, otherwise it will be Desked)"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputLongSize"></liferay-util:param>
				</liferay-util:include>
				<label></label>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.INCLUDE_SUBSECTIONS%>"></liferay-util:param>
					<liferay-util:param name="label" value="Search in subsections"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<label>Content Type</label>
				<%
					String contentType = "";
					if (impresaConfiguration != null) {
						ArrayList<String> contentTypes = (ArrayList<String>) impresaConfiguration.get(CONTENT.CONTENT_TYPE);
						if (contentTypes != null) {
							StringBuilder sb = new StringBuilder();
							for (String ct : contentTypes) {
								sb.append(ct + ",");
							}
							contentType = sb.toString();
						}
					}
				%>
				<c:set var="contentType" value="<%= contentType%>" />
				<input type="text" name="<portlet:namespace /><%= CONTENT.CONTENT_TYPE%>" value="${contentType}" class="inputLongSize" />
			</div>
		</div>
		<div class="configContainer">
			<div class="first">
				<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
					<liferay-util:param name="fieldName" value="<%= CONTENT.MAX_ARTICLES%>"></liferay-util:param>
					<liferay-util:param name="label" value="Max Articles"></liferay-util:param>
					<liferay-util:param name="cssClass" value="inputShortestSize"></liferay-util:param>
				</liferay-util:include>
			</div>
		</div>

	</div>

	<input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>content);"/>
</form>