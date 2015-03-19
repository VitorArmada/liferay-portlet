<%@page import="pt.impresa.liferay.content.service.model.ImpresaPagination"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS.DEFAULT"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<% 
	ContentPortletConfiguration widgetConfiguration =
		(ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<!-- Page 2 Url -->
<portlet:renderURL var="nextPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
	<portlet:param name="jspPage" value="/html/view.jsp" />
	<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="${pagination.currentPage + 1}" />
</portlet:renderURL>
<!-- Next Page Url Structure -->
<portlet:renderURL var="portletURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
	<portlet:param name="jspPage" value="/html/view.jsp" />
</portlet:renderURL>

<!-- Next Page Selector -->
<div id="infscr-nextSelectorContainer">
	<a id="infscr-nextSelector" class="nextSelector" href="<%= nextPageURL %>"><%= widgetConfiguration.getString(SCRIPTS.INFINITESCROLL_MORE_RESULTS_LABEL)%></a>
</div>

<script type="text/javascript">
	/*head.js("/js/lib/jquery.infinitescroll.min.js", function(){
		head.js( "/js/lib/jquery.infinitescroll.behavior.local.js"
		, "/js/lib/jquery.infinitescroll.behavior.facebook.js"
		, "/js/lib/jquery.infinitescroll.behavior.manual-trigger.js" );
	});*/
	
	
	head.ready(function() {
		
		var portletSelector = 'div[id*="p_p_id_<%= PortalUtil.getPortletId(request)%>"]';
		var portletListContainer = portletSelector +  ' <%= widgetConfiguration.getString(SCRIPTS.INFINITESCROLL_ITEM_SELECTOR, SCRIPTS.DEFAULT.INFINITESCROLL_ITEM_SELECTOR)%>';
		
		$(portletListContainer).infinitescroll({
			itemSelector: "<%= widgetConfiguration.getString(SCRIPTS.INFINITESCROLL_ITEMS_TO_APPEND, SCRIPTS.DEFAULT.INFINITESCROLL_ITEMS_TO_APPEND)%>",
			navSelector: "a.nextSelector:last",
			nextSelector: "a.nextSelector:last",
			appendCallback : true,
			path: function(index) {
				return '<%= portletURL + "&" + PAGINATION.CURRENT_PAGE + "=" %>' + (index - 1);
			},
			<%= widgetConfiguration.getString(SCRIPTS.INFINITESCROLL_PARAMETERS)%>
		}, function() {
			Liferay.fire(PortalCommon.Events.TOGGLE_VIEW,  portletListContainer);
			Liferay.fire(PortalCommon.Events.INFINITE_SCROLL, portletSelector);
			Liferay.fire(PortalCommon.Events.PAGE_VIEW);
		});
		$(portletSelector).addClass("infscr-enabled");
	});
</script>