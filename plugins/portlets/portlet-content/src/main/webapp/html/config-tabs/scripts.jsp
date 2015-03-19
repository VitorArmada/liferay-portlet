<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="com.liferay.portal.kernel.util.Constants"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects/>

<%
	ContentPortletConfiguration widgetConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />scripts">
	<input name="<portlet:namespace /><%= Constants.CMD%>" type="hidden" value="<%= Constants.CMD%>"/>
	<input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE%>" type="hidden" value="<%= SCRIPTS.MODULE_NAME%>" />

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_CAROUSEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Display as a Carousel"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Item Selector (by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.CAROUSEL_ITEM_SELECTOR%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textarea.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Parameters:"></liferay-util:param>
				<liferay-util:param name="labelClass" value="displayAsBlock"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.CAROUSEL_PARAMETERS%>"></liferay-util:param>
				<liferay-util:param name="rows" value="5"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
			</liferay-util:include>
			<a class="externalLink" href="http://alloyui.com/api/classes/Carousel.html" target="_blank">[ Go To Plugin Site ]</a>
		</div>
	</div>

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_ELLIPSIS%>"></liferay-util:param>
				<liferay-util:param name="label" value="Use Ellipsis"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Item Selector (by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.ELLIPSIS_ITEM_SELECTOR%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<a class="externalLink" href="hhttp://pvdspek.github.com/jquery.autoellipsis/" target="_blank">[ Go To Plugin Site ]</a>
		</div>
	</div>

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_CUSTOMSCROLLBAR%>"></liferay-util:param>
				<liferay-util:param name="label" value="Use CustomScrollbar"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Item Selector (by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.CUSTOMSCROLLBAR_ITEM_SELECTOR%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textarea.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Parameters:"></liferay-util:param>
				<liferay-util:param name="labelClass" value="displayAsBlock"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.CUSTOMSCROLLBAR_PARAMETERS%>"></liferay-util:param>
				<liferay-util:param name="rows" value="5"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
			</liferay-util:include>
			<a class="externalLink" href="http://manos.malihu.gr/jquery-custom-content-scroller/" target="_blank">[ Go To Plugin Site ]</a>
		</div>
	</div>

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_TOGGLEVIEW%>"></liferay-util:param>
				<liferay-util:param name="label" value="Use Toggle View"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Default View Label:"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.TOGGLEVIEW_DEFAULTVIEW%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Alternative View Label:"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.TOGGLEVIEW_ALTERNATIVEVIEW%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_INFINITESCROLL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Use Infinite Scroll"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<%--
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
				<liferay-util:param name="label" value="Item Selector (by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.INFINITESCROLL_ITEM_SELECTOR %>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
				<liferay-util:param name="label" value="Items To Append(by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.INFINITESCROLL_ITEMS_TO_APPEND %>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			--%>
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="More Results Label:"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.INFINITESCROLL_MORE_RESULTS_LABEL%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textarea.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Parameters:"></liferay-util:param>
				<liferay-util:param name="labelClass" value="displayAsBlock"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.INFINITESCROLL_PARAMETERS%>"></liferay-util:param>
				<liferay-util:param name="rows" value="5"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
			</liferay-util:include>
			<a class="externalLink" href="https://github.com/paulirish/infinite-scroll" target="_blank">[ Go To Plugin Site ]</a>
		</div>
	</div>

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.USE_MASONRY%>"></liferay-util:param>
				<liferay-util:param name="label" value="Use Masonry"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Item Selector (by class):"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.MASONRY_ITEM_SELECTOR%>"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textarea.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="label" value="Parameters:"></liferay-util:param>
				<liferay-util:param name="labelClass" value="displayAsBlock"></liferay-util:param>
				<liferay-util:param name="fieldName" value="<%= SCRIPTS.MASONRY_PARAMETERS%>"></liferay-util:param>
				<liferay-util:param name="rows" value="5"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
			</liferay-util:include>
			<a class="externalLink" href="http://masonry.desandro.com/" target="_blank">[ Go To Plugin Site ]</a>
		</div>
	</div>

	<input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>scripts);"/>

</form>