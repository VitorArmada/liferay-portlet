
<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SOCIAL"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<portlet:defineObjects/>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />socialNetworks">
    <input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.CMD %>"/>
    <input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE %>" type="hidden" value="<%= SOCIAL.MODULE_NAME %>" />
	<!-- Meta Tags -->
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.PUBLISH_META_TAGS %>"></liferay-util:param>
                <liferay-util:param name="label" value="Publish meta-tags information"></liferay-util:param>
            </liferay-util:include>
		</div>
	</div>
	<!-- Facebook -->
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.USE_FACEBOOK_LIKE %>"></liferay-util:param>
                <liferay-util:param name="label" value="Show Facebook Like button"></liferay-util:param>
            </liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.FACEBOOK_LIKE_CONFIG %>"></liferay-util:param>
                <liferay-util:param name="label" value="Like button tag attributes (HTML)"></liferay-util:param>
                <liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
            </liferay-util:include>
		</div>
	</div>
	<!-- Twitter -->
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.USE_TWITTER_TWEET %>"></liferay-util:param>
                <liferay-util:param name="label" value="Show Twiter Tweet button"></liferay-util:param>
            </liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.TWITTER_TWEET_CONFIG %>"></liferay-util:param>
                <liferay-util:param name="label" value="Tweet button attributes (HTML)"></liferay-util:param>
                <liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
            </liferay-util:include>
		</div>
	</div>
	<!-- Google Plus -->
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.USE_GOOGLEPLUS_PLUSONE %>"></liferay-util:param>
                <liferay-util:param name="label" value="Show Google Plus +1 button"></liferay-util:param>
            </liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.GOOGLEPLUS_PLUSONE_CONFIG %>"></liferay-util:param>
                <liferay-util:param name="label" value="Google Plus +1 button attributes (HTML)"></liferay-util:param>
                <liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
            </liferay-util:include>
		</div>
	</div>
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.USE_GOOGLEPLUS_SHARE %>"></liferay-util:param>
                <liferay-util:param name="label" value="Show Google Plus Share button"></liferay-util:param>
            </liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext() %>">
                <liferay-util:param name="fieldName" value="<%= SOCIAL.GOOGLEPLUS_SHARE_CONFIG %>"></liferay-util:param>
                <liferay-util:param name="label" value="Google Plus Share button attributes (HTML)"></liferay-util:param>
                <liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
            </liferay-util:include>
		</div>
	</div>
        
    <input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>socialNetworks);"/>
	
</form>