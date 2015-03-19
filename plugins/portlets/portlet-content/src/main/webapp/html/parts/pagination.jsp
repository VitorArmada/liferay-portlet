<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="pt.impresa.liferay.content.service.model.ImpresaPagination"%>
<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ page import="com.liferay.portal.kernel.portlet.LiferayWindowState"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<%
	ContentPortletConfiguration widgetConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>

<c:set var="currentPage" value="${pagination.currentPage}" />
<c:set var="totalPages" value="${pagination.totalPages}" />
<c:set var="lastPage" value="${totalPages - 1}" />
<c:set var="previousPage" value="${currentPage - 1}" />
<c:set var="nextPage" value="${currentPage + 1}" />


<div class="paginationContainer <%= widgetConfiguration.getString(PAGINATION.CSS_CLASS, PAGINATION.DEFAULT.CSS_CLASS)%>">
	<div class="paginationControls">
		<%-- showFirstPageLink  --%>		
		<c:set var="showFirstPageLink" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_FIRST_PAGE_LINK, false)%>" />
		<c:if test="${showFirstPageLink}">
			<c:choose>
				<c:when test="${showFirstPageLink and currentPage != 0}">
					<portlet:renderURL var="firstPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
						<portlet:param name="jspPage" value="/html/view.jsp" />
						<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="0" />
					</portlet:renderURL>
					<div class="firstPage" onClick="PortletCommon.Content.goToPage('<%= firstPageURL %>', '<%= PortalUtil.getPortletId(request)%>', PortletCommon.VisualEffects.fadeInFadeOutReplace);">
						<span><%= widgetConfiguration.getString(PAGINATION.FIRST_PAGE_LABEL)%></span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="firstPage disabled">
						<span><%= widgetConfiguration.getString(PAGINATION.FIRST_PAGE_LABEL)%></span>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<%-- showPreviousPageLink --%>
		<c:set var="showPreviousPageLink" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_PREVIOUS_PAGE_LINK, false)%>" />
		<c:if test="${showPreviousPageLink}">
			<c:choose>
				<c:when test="${showPreviousPageLink and currentPage != 0}">
					<portlet:renderURL var="previousPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
						<portlet:param name="jspPage" value="/html/view.jsp" />
						<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="${previousPage}" />
					</portlet:renderURL>
					<div class="previousPage" onClick="PortletCommon.Content.goToPage('<%= previousPageURL %>', '<%= PortalUtil.getPortletId(request)%>', PortletCommon.VisualEffects.fadeInFadeOutReplace);">
						<span><%= widgetConfiguration.getString(PAGINATION.PREVIOUS_PAGE_LABEL)%></span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="previousPage disabled">
						<span><%= widgetConfiguration.getString(PAGINATION.PREVIOUS_PAGE_LABEL)%></span>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<%-- pageNumberContainer --%>
		<c:set var="maxPageLinks" value="<%= widgetConfiguration.getInt(PAGINATION.MAX_PAGE_LINKS, PAGINATION.DEFAULT.MAX_PAGE_LINKS)%>" />
		<c:if test="${maxPageLinks != 0}">
			<div class="pageNumberContainer">
				<c:set var="module" value="${currentPage % maxPageLinks}" />
				<c:set var="firstNumber" value="${currentPage - module}" />
				<c:set var="lastNumber" value="${firstNumber + maxPageLinks - 1}" />
				<c:forEach var="i" begin="${firstNumber}" end="${lastNumber}">
					<portlet:renderURL var="specificPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
						<portlet:param name="jspPage" value="/html/view.jsp" />
						<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="${i}" />
					</portlet:renderURL>
					<c:set var="showPageNumber" value="${i + 1}" />
					<div class="pageNumberItem ${currentPage == i ? 'current' : ''}" onClick="PortletCommon.Content.goToPage('<%= specificPageURL%>', '<%= PortalUtil.getPortletId(request)%>', PortletCommon.VisualEffects.fadeInFadeOutReplace);">
						<span>${showPageNumber}</span>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<%-- showNextPageLink --%>
		<c:set var="showNextPageLink" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_NEXT_PAGE_LINK, false)%>" />
		<c:if test="${showNextPageLink}">
			<c:choose>
				<c:when test="${showNextPageLink and currentPage != lastPage}">
					<portlet:renderURL var="nextPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
						<portlet:param name="jspPage" value="/html/view.jsp" />
						<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="${nextPage}" />
					</portlet:renderURL>
					<div class="nextPage" onClick="PortletCommon.Content.goToPage('<%= nextPageURL %>', '<%= PortalUtil.getPortletId(request)%>', PortletCommon.VisualEffects.fadeInFadeOutReplace);">
						<span><%= widgetConfiguration.getString(PAGINATION.NEXT_PAGE_LABEL)%></span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="nextPage disabled">
						<span><%= widgetConfiguration.getString(PAGINATION.NEXT_PAGE_LABEL)%></span>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

		<%-- showLastPageLink --%>
		<c:set var="showLastPageLink" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_LAST_PAGE_LINK, false)%>" />
		<c:if test="${showLastPageLink}">
			<c:choose>
				<c:when test="${showLastPageLink and currentPage != lastPage}">
					<portlet:renderURL var="lastPageURL" windowState="<%= LiferayWindowState.EXCLUSIVE.toString()%>">
						<portlet:param name="jspPage" value="/html/view.jsp" />
						<portlet:param name="<%= PAGINATION.CURRENT_PAGE%>" value="${lastPage}" />
					</portlet:renderURL>
					<div class="lastPage" onClick="PortletCommon.Content.goToPage('<%= lastPageURL %>', '<%= PortalUtil.getPortletId(request)%>', PortletCommon.VisualEffects.fadeInFadeOutReplace);">
						<span><%= widgetConfiguration.getString(PAGINATION.LAST_PAGE_LABEL)%></span>
					</div>
				</c:when>
				<c:otherwise>
					<div class="lastPage disabled">
						<span><%= widgetConfiguration.getString(PAGINATION.LAST_PAGE_LABEL)%></span>
					</div>
				</c:otherwise>
			</c:choose>
		</c:if>

	</div>
	<c:set var="showPaginationSummary" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_PAGINATION_SUMMARY, false)%>" />
	<c:if test="${showPaginationSummary}">
		<c:set var="paginationSummary" value="<%= widgetConfiguration.getString(PAGINATION.PAGINATION_SUMMARY_LABEL)%>" />
		<c:set var="currentPageWrapper" value="<%= PAGINATION.DEFAULT.CURRENT_PAGE_WRAPPER%>" />
		<c:set var="totalPagesWrapper" value="<%= PAGINATION.DEFAULT.TOTAL_PAGES_WRAPPER%>" />
		<c:set var="paginationSummaryReplace" value="${fn:replace(paginationSummary, currentPageWrapper, nextPage)}" />
		<c:set var="paginationSummaryReplace" value="${fn:replace(paginationSummaryReplace, totalPagesWrapper, totalPages)}" />
		<div class="paginationSummary">
			<span>${paginationSummaryReplace}</span>
		</div>
	</c:if>

</div>