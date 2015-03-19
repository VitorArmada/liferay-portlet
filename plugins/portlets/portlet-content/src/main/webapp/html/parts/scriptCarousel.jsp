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
    head.ready(function(){
        AUI().use('aui-carousel', function(Y) {
            new Y.Carousel({
                contentBox: 'div[id*="p_p_id_<%= PortalUtil.getPortletId(request) %>"]',
                
                itemSelector: '.<%= widgetConfiguration.getString(SCRIPTS.CAROUSEL_ITEM_SELECTOR, DEFAULT.CAROUSEL_ITEM_SELECTOR) %>',
                <%= widgetConfiguration.getString(SCRIPTS.CAROUSEL_PARAMETERS, DEFAULT.CAROUSEL_PARAMETERS) %>
                <%--
                playing: <%= widgetConfiguration.getBoolean(SCRIPTS.CAROUSEL_AUTOPLAY, DEFAULT.CAROUSEL_AUTOPLAY) %>,
                animationTime: <%= widgetConfiguration.getInt(SCRIPTS.CAROUSEL_ANIMATION_DURATION, DEFAULT.CAROUSEL_ANIMATION_DURATION) %>,
                intervalTime: <%= widgetConfiguration.getInt(SCRIPTS.CAROUSEL_INTERVAL, DEFAULT.CAROUSEL_INTERVAL) %>,
                --%>
            }).render();
        });
    });
</script>