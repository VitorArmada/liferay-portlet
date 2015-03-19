<%@ page import="com.liferay.portal.util.PortalUtil"%>
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
					(ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<script type="text/javascript">
	//head.js( "/js/lib/jquery.autoellipsis.js");
    head.ready(function(){
		var doEllipsis = function() {
			$portlet = $('div[id*="p_p_id_<%= PortalUtil.getPortletId(request) %>"] .<%= widgetConfiguration.getString(SCRIPTS.ELLIPSIS_ITEM_SELECTOR, DEFAULT.ELLIPSIS_ITEM_SELECTOR) %>');
			$($portlet).ellipsis();
		};
		doEllipsis();
		Liferay.on(PortalCommon.Events.INFINITE_SCROLL, doEllipsis);
	});
</script>