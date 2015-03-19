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
            (ContentPortletConfiguration)request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>
<script type="text/javascript">
	//head.js( "/js/lib/jquery.mCustomScrollbar.concat.min.js");
	
    head.ready(function(){
		
		var thisPortlet = 'div[id*="p_p_id_<%= PortalUtil.getPortletId(request) %>"] .<%= widgetConfiguration.getString(SCRIPTS.CUSTOMSCROLLBAR_ITEM_SELECTOR, DEFAULT.CUSTOMSCROLLBAR_ITEM_SELECTOR) %>';
		
		$(thisPortlet).mCustomScrollbar({
		
			callbacks: {
				whileScrolling:function(){
					// Top Button
					if (mcs.topPct === 0) {
						$(thisPortlet).find('.mCSB_buttonTop').addClass('disabled',250);
					} else {
						$(thisPortlet).find('.mCSB_buttonTop').removeClass('disabled',250);
					}
					// Bottom Button
					if (mcs.topPct === 100) {
						$(thisPortlet).find('.mCSB_buttonBottom').addClass('disabled',250);
					} else {
						$(thisPortlet).find('.mCSB_buttonBottom').removeClass('disabled',250);
					}
					// Left Button
					if (mcs.leftPct === 0) {
						$(thisPortlet).find('.mCSB_buttonLeft').addClass('disabled',250);
					} else {
						$(thisPortlet).find('.mCSB_buttonLeft').removeClass('disabled',250);
					}
					// Right Button
					if (mcs.leftPct === 100) {
						$(thisPortlet).find('.mCSB_buttonRight').addClass('disabled',250);
					} else {
						$(thisPortlet).find('.mCSB_buttonRight').removeClass('disabled',250);
					}
				}
			},

			<%= widgetConfiguration.getString(SCRIPTS.CUSTOMSCROLLBAR_PARAMETERS, DEFAULT.CUSTOMSCROLLBAR_PARAMETERS) %>
		});
					
		//Disable Top and Left Buttons
		$(thisPortlet).find('.mCSB_buttonTop').addClass('disabled');
		$(thisPortlet).find('.mCSB_buttonLeft').addClass('disabled');
		
    });
</script>
