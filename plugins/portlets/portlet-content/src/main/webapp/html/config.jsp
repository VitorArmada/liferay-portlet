<%@page import="com.liferay.util.portlet.PortletProps"%>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfiguration"%>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil"%>
<%@ page import="com.liferay.portal.kernel.util.Validator"%>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil"%>
<%@ page import="javax.portlet.PortletPreferences"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<portlet:defineObjects/>

<!-- CSS -->
<link href="/css/impresaPortletConfig.css" rel="stylesheet" type="text/css" />
<!-- JS -->
<script type="text/javascript">
	head.js("/js/lib/jquery.min.js");
	head.js("/js/lib/jquery-ui.min.js");
	head.js("/js/impresa/configuration/configuration-common.js");
</script>

<!-- WIDGET INFO -->
<div class="widgetInfo">
	<div class="widgetName">Portlet Content</div>
	<div class="jenkinsBuild">Jenkins Build Number : <span><%= PortletProps.get("jenkins.build.number")%></span></div>
</div>
<div style="clear:both;"></div>

<!-- CONFIG -->
<div id="widget_list_config">

	<liferay-ui:success key="success" message="Success!" />

	<div id="configTabs">
		<!-- TABS -->
		<ul>
			<li><a href="#configGeneral">General</a></li>
			<li><a href="#configContent">Content</a></li>
			<li><a href="#configFields">Fields</a></li>
			<li><a href="#configPagination">Pagination</a></li>
			<li><a href="#configMultimedia">Multimedia</a></li>
			<li><a href="#configSocial">Social</a></li>
			<li><a href="#configScripts">Scripts</a></li>
		</ul>
		<!-- CONTENT TAB  -->
		<div id="configGeneral">
			<c:import url="/html/config-tabs/general.jsp" />
		</div>
		<!-- CONTENT TAB  -->
		<div id="configContent">
			<c:import url="/html/config-tabs/content.jsp" />
		</div>
		<!-- VIEW TAB  -->
		<div id="configFields">
			<c:import url="/html/config-tabs/fields.jsp" />
		</div>
		<!-- PAGINATION TAB  -->
		<div id="configPagination">
			<c:import url="/html/config-tabs/pagination.jsp" />
		</div>
		<!-- MULTIMEDIA TAB  -->
		<div id="configMultimedia">
			<c:import url="/html/config-tabs/multimedia.jsp" />
		</div>
		<!-- SOCIAL TAB  -->
		<div id="configSocial">
			<c:import url="/html/config-tabs/socialNetworks.jsp" />
		</div>
		<!-- SCRIPT TAB  -->
		<div id="configScripts">
			<c:import url="/html/config-tabs/scripts.jsp" />
		</div>
		<!-- WIKI BUTTON -->
		<div class="wikiButton">
			<a href="https://docs.google.com/document/d/1xBsgHGXygvteq3y_vSYEsmrPAj_w1qB70q7JiAgCevU/edit?usp=sharing&pli=1" target="_blank">Wiki</a>
		</div>
	</div>
	<script>
		var checkboxes = $('.configContainer .first input[type=checkbox]');
		$(checkboxes).each(function () {
			if ($(this).is(':checked')) {
				$(this).closest('div').parent().closest('div.configContainer').removeClass('hideConfigDiv', 25);
				$(this).closest('div').parent().closest('div.configContainer').addClass('showConfigDiv', 25);
			}
		});
		$('.configContainer .first input[type=checkbox]').change(function () {
			$(this).closest('div').parent().closest('div.configContainer').toggleClass('hideConfigDiv', 25).toggleClass('showConfigDiv', 25);
		});
	</script>

</div>
