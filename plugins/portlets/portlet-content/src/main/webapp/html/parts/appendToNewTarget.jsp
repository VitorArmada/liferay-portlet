<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL.DEFAULT"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<%
    ContentPortletConfiguration widgetConfiguration = 
            (ContentPortletConfiguration)request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<script type="text/javascript">
	head.ready(function(){
		var thisPortlet = 'div[id*="p_p_id_<%= PortalUtil.getPortletId(request) %>"]';
		var newTargetId = '#<%= widgetConfiguration.getString(GENERAL.NEW_TARGET_ID) %>';
		var newTargetAnimDuration = "<%= widgetConfiguration.getInt(GENERAL.NEW_TARGET_ANIM_DURATION, DEFAULT.NEW_TARGET_ANIM_DURATION) %>";
		var newTargetAnimDelay = '<%= widgetConfiguration.getInt(GENERAL.NEW_TARGET_ANIM_DELAY, DEFAULT.NEW_TARGET_ANIM_DELAY) %>';
		
		//$(thisPortlet).css({display:"none"});
		//$(thisPortlet).appendTo(newTargetId).show(500);
		
		$(newTargetId).css({display:"none"});
		$(thisPortlet).appendTo(newTargetId);
		
		setTimeout(function(){
			$(newTargetId).slideDown(newTargetAnimDuration);
		}, newTargetAnimDelay);
		
	});
</script>