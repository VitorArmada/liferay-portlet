<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SOCIAL.DEFAULT"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SOCIAL"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
	
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
	
<portlet:defineObjects />
	
<%
	ContentPortletConfiguration widgetConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
	ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
	String baseUrl = td.getPortalURL();
%>
		
<script type="text/javascript">
	//head.js("/js/impresa/socialnetworks/socialnetworks.js");
	head.ready(function() {
		SocialNetworks.loadFacebookApi();
		SocialNetworks.loadTwitterApi();
		SocialNetworks.loadGooglePlusApi();
	});
</script> 
	
<div id="share_<%= PortalUtil.getPortletId(request)%>" class="shareSocialNetworks">
	<%-- Facebook Like --%>
	<c:set var="useFacebookLike" value="<%= widgetConfiguration.getBoolean(SOCIAL.USE_FACEBOOK_LIKE, SOCIAL.DEFAULT.USE_FACEBOOK_LIKE)%>" />
	<c:if test="${useFacebookLike}">
		<div class="shareFacebook">
			<div id="fb-root"></div>
			<div class="fb-like"
				 data-href="<%= baseUrl%>${article.articlePageUrl}"
				 <%= widgetConfiguration.getString(SOCIAL.FACEBOOK_LIKE_CONFIG, SOCIAL.DEFAULT.FACEBOOK_LIKE_CONFIG)%>>
			</div> 
		</div>
	</c:if>
	<%-- Twitter Tweet --%>
	<c:set var="useTwitterTweet" value="<%= widgetConfiguration.getBoolean(SOCIAL.USE_TWITTER_TWEET, SOCIAL.DEFAULT.USE_TWITTER_TWEET)%>" />
	<c:if test="${useTwitterTweet}">
		<div class="shareTwitter">
			<a class="twitter-share-button"
			   href="https://twitter.com/share"
			   data-url="<%= baseUrl%>${article.articlePageUrl}"
			   <%= widgetConfiguration.getString(SOCIAL.TWITTER_TWEET_CONFIG, SOCIAL.DEFAULT.TWITTER_TWEET_CONFIG)%>>
				Tweet
			</a>
		</div>
	</c:if>
	<%-- Google Plus +1 --%>
	<c:set var="useGooglePlusPlusone" value="<%= widgetConfiguration.getBoolean(SOCIAL.USE_GOOGLEPLUS_PLUSONE, SOCIAL.DEFAULT.USE_GOOGLEPLUS_PLUSONE)%>" />
	<c:if test="${useGooglePlusPlusone}">
		<div class="shareGooglePlus">
			<div class="g-plusone"
				 data-url="<%= baseUrl%>${article.articlePageUrl}"
				 <%= widgetConfiguration.getString(SOCIAL.GOOGLEPLUS_PLUSONE_CONFIG, SOCIAL.DEFAULT.GOOGLEPLUS_PLUSONE_CONFIG)%>>
			</div>
		</div>
	</c:if>
	<%-- Google Plus Share --%>
	<c:set var="useGooglePlusShare" value="<%= widgetConfiguration.getBoolean(SOCIAL.USE_GOOGLEPLUS_SHARE, SOCIAL.DEFAULT.USE_GOOGLEPLUS_SHARE)%>" />
	<c:if test="${useGooglePlusShare}">
		<div class="shareGooglePlus">
			<div class="g-plus" 
				 data-url="<%= PortalUtil.getCurrentCompleteURL(request)%>"
				 data-action="share"
				 <%= widgetConfiguration.getString(SOCIAL.GOOGLEPLUS_SHARE_CONFIG, SOCIAL.DEFAULT.GOOGLEPLUS_SHARE_CONFIG)%>>
			</div>
		</div>
	</c:if>
</div>