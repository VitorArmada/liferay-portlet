<%@ page import="com.liferay.portal.kernel.util.Constants"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>



<portlet:defineObjects/>

<%
	ContentPortletConfiguration widgetConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />pagination">
	<input name="<portlet:namespace /><%= Constants.CMD%>" type="hidden" value="<%= Constants.CMD%>"/>
	<input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE%>" type="hidden" value="<%= PAGINATION.MODULE_NAME%>" />


	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_BOTTOM_PAGINATION%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Pagination"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.CSS_CLASS%>"></liferay-util:param>
				<liferay-util:param name="label" value="Custom Class:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>			

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_FIRST_PAGE_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show First Page Link"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.FIRST_PAGE_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Fist Page Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_PREVIOUS_PAGE_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Previous Page Link"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.PREVIOUS_PAGE_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Previous Page Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_NEXT_PAGE_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Next Page Link"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.NEXT_PAGE_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Next Page Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_LAST_PAGE_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Last Page Link"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.LAST_PAGE_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Last Page Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>
	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_PAGINATION_SUMMARY%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Pagination Summary"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.PAGINATION_SUMMARY_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Pagination Summary Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputLongestSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>
	<div class="configContainer">
		<div class="first">
			<label>Other Options</label>
		</div>
		<div class="last">
			<%--				
			<label>[Not implemented] Items Per Page:</label>
			<input name="paginationItemsPerPage" type="text" class="inputShortestSize">
			--%>
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.MAX_PAGE_LINKS%>"></liferay-util:param>
				<liferay-util:param name="label" value="Maximum Page Links:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputShortestSize"></liferay-util:param>
			</liferay-util:include>
			<%--
			<label>[Not implemented] Page Links Style:</label>
			<select name="paginationPageLinksStyle" class="inputMediumSize">
							<option value="none">None</option>
							<option value="number">Number</option>
							<option value="dots">Dots</option>
							<option value="complexList">Complex List</option>
			</select>
			<label>[Not implemented] Page Flip Animation:</label>
			<select name="paginationPageFlipAnimation" class="inputMediumSize">
							<option value="none">None</option>
							<option value="number">Fade</option>
			</select>
			--%>
		</div>
	</div>	

	<div class="configContainer hideConfigDiv">
		<div class="first">
			<liferay-util:include page="/html/config-elements/checkbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.SHOW_FOLLOW_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Show Follow Link"></liferay-util:param>
			</liferay-util:include>
		</div>
		<div class="last">
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.FOLLOW_LINK%>"></liferay-util:param>
				<liferay-util:param name="label" value="Link:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
			<liferay-util:include page="/html/config-elements/textbox.jsp" servletContext="<%= this.getServletContext()%>">
				<liferay-util:param name="fieldName" value="<%= PAGINATION.FOLLOW_LINK_LABEL%>"></liferay-util:param>
				<liferay-util:param name="label" value="Link Label:"></liferay-util:param>
				<liferay-util:param name="cssClass" value="inputMediumSize"></liferay-util:param>
			</liferay-util:include>
		</div>
	</div>

	<input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>pagination);"/>

</form>