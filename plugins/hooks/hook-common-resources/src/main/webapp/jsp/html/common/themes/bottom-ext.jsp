<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.StringPool"%>
<%@page import="com.liferay.portal.service.LayoutLocalServiceUtil"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.model.Group"%>
<%@page import="com.liferay.portal.model.Layout"%>
<%@page import="java.util.List"%>

<%
	ThemeDisplay td = (ThemeDisplay)request.getAttribute(WebKeys.THEME_DISPLAY);
	Group g = td.getLayout().getGroup();
	
	//Fetching analytics sneiders
	String analyticsSectionName = (String) td.getLayout().getExpandoBridge().getAttribute("analyticsSectionName");
	String analyticsSubSectionName = (String) td.getLayout().getExpandoBridge().getAttribute("analyticsSubSectionName");
	String sectionName = td.getLayout().getName(td.getLocale());
	String subsectionName = sectionName;
	if(analyticsSectionName != null && !analyticsSectionName.isEmpty()) {
		sectionName = analyticsSectionName;
	} 
	if(analyticsSubSectionName != null && !analyticsSubSectionName.isEmpty()) {
		subsectionName = analyticsSubSectionName;
	}
	String analyticsCompanyName = (String) g.getExpandoBridge().getAttribute("analyticsCompanyName");
	String analyticsSiteName = (String) g.getExpandoBridge().getAttribute("analyticsSiteName");
	String analyticsDomainName = (String) g.getExpandoBridge().getAttribute("analyticsDomainName");
	String analyticsNetscopeCompanyId = (String) g.getExpandoBridge().getAttribute("analyticsNetscopeCompanyId");
	String analyticsNetscopeSiteId = (String) g.getExpandoBridge().getAttribute("analyticsNetscopeSiteId");
	String analyticsSapoWebAnalyticsSiteId = (String) g.getExpandoBridge().getAttribute("analyticsSapoWebAnalyticsSiteId");
	String analyticsGoogleAnalyticsSiteId = (String) g.getExpandoBridge().getAttribute("analyticsGoogleAnalyticsSiteId");
	String analyticsNetscopeV3SiteId = (String) g.getExpandoBridge().getAttribute("analyticsNetscopeV3SiteId");
	
	//Determine if is in homepage
	long layoutId = g.getDefaultPublicPlid();
	Layout l = LayoutLocalServiceUtil.getLayout(layoutId);
	String homePageUrl = l.getFriendlyURL();
	String currentUrl = PortalUtil.getCurrentURL(request);
	String analyticsNetscopeV3Value = null;
	if(currentUrl.equalsIgnoreCase(homePageUrl) || currentUrl.equalsIgnoreCase(StringPool.SLASH)) {
		//valores do bufo do netscope
		analyticsNetscopeV3Value = "Homepage_do_site";
	} else {
		analyticsNetscopeV3Value = PortalUtil.getCurrentURL(request).substring(1);
	}
	
%>


<% if(!td.isSignedIn()) { %>

	<script type="text/javascript">
		head.ready(function() {
			LayoutTabs.configureTabs();
			//TODO: Delete when Someone figures out how to correctly hide portlets
			PortletCommon.VisualEffects.hideDisabledPortlets();

			PortletCommon.VisualEffects.reloadToggle();

			PortalCommon.Ads.initAds();
			//////////////////
			// WEB ANALYTICS
			//////////////////
			WebAnalytics.init({
				platforms : {
					NETSCOPE_2 : {
						WRP_SECTION : '<%= sectionName %>',
						WRP_SUBSECTION : '<%= subsectionName %>',
						WRP_ID : '<%= analyticsNetscopeSiteId %>',
						WRP_CHANNEL : '<%= analyticsSiteName %>',
						WRP_SECTION_GRP : '<%= analyticsCompanyName %>',
						WRP_SUBSECTION_GRP : '<%= analyticsNetscopeSiteId %>',
						WEBO_ID_GROUPE : '<%= analyticsNetscopeCompanyId %>',
						WRP_CONTENT : document.title
					},
					NETSCOPE_3 : {
						identifier : '<%= analyticsNetscopeV3SiteId %>',
						value : '<%= analyticsNetscopeV3Value %>'
					},
					SAPO_ANALYTICS : {
						swakt : '<%= analyticsSapoWebAnalyticsSiteId %>',
						swasection : '<%= sectionName %>',
						swasubsection : '<%= subsectionName %>',
						swasectiongrp : '<%= analyticsCompanyName %>',
						swasubsectiongrp : '<%= analyticsNetscopeSiteId %>',
						swacontent : '<%= analyticsSiteName %>',
						swachannel : document.title
					},
					GOOGLE_ANALYTICS : {
						account : '<%= analyticsGoogleAnalyticsSiteId %>',
						domainName : '<%= analyticsDomainName %>',
						section : '<%= sectionName %>',
						subSection : '<%= subsectionName %>'
					}
				}
			});
			//Register Web Analytics on Liferay's Event Bus
			Liferay.on(PortalCommon.Events.PAGE_VIEW, function(event,data) {
				WebAnalytics.trackAjaxEvent();
			});
			WebAnalytics.trackPageView();
		});
	</script>
	<script src="http://bars.sapo.pt/bsu_v1.1/barra.js?exception=2553&amp;parceiro=true&amp;with_pub=false&amp;width=960px&amp;links_parceiro=true " type="text/javascript"></script>
<% } %>