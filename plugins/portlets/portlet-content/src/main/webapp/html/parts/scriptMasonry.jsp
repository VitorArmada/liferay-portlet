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
					(ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>
<script type="text/javascript">
	//head.js( "/js/lib/jquery.masonry.min.js");
	head.ready(function() {
		//Initialize Masonry
		$('div[id*="p_p_id_<%= PortalUtil.getPortletId(request)%>"] .listContainer').masonry({
			itemSelector: ".<%= widgetConfiguration.getString(SCRIPTS.MASONRY_ITEM_SELECTOR, DEFAULT.MASONRY_ITEM_SELECTOR)%>",
			<%= widgetConfiguration.getString(SCRIPTS.MASONRY_PARAMETERS, DEFAULT.MASONRY_PARAMETERS)%>
		});
		var masonryReload = function() {
			//receives a set of jquery objects
			$portletListContainer = $('div[id*="p_p_id_<%= PortalUtil.getPortletId(request)%>"] .listContainer');
			if($portletListContainer.hasClass("masonry")) {
				$portletListContainer.masonry("reload");
			} else {
				$portletListContainer.masonry({
					itemSelector: ".<%= widgetConfiguration.getString(SCRIPTS.MASONRY_ITEM_SELECTOR, DEFAULT.MASONRY_ITEM_SELECTOR)%>",
					<%= widgetConfiguration.getString(SCRIPTS.MASONRY_PARAMETERS, DEFAULT.MASONRY_PARAMETERS)%>
				});
			}
		};
		//Register on events that require masonry reload
		Liferay.on(PortalCommon.Events.INFINITE_SCROLL, masonryReload);
		Liferay.on(PortalCommon.Events.TOGGLE_VIEW, masonryReload);
	});
</script>
