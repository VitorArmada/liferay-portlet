<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT"%>
<%@page import="com.liferay.portal.model.Portlet"%>
<%@page import="com.liferay.portal.model.PortletInfo"%>
<%@page import="java.util.Enumeration"%>
<%@page import="com.liferay.portal.service.persistence.PortletUtil"%>
<%@page import="com.liferay.portal.kernel.util.LocaleUtil"%>
<%@page import="com.liferay.portal.kernel.language.LanguageUtil"%>
<%@page import="com.liferay.portlet.portletconfiguration.util.PortletConfigurationUtil"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="java.util.List" %>
<%@ page import="javax.portlet.PortletPreferences" %>
<%@ page import="com.liferay.portal.kernel.util.ParamUtil" %>
<%@ page import="com.liferay.portal.kernel.util.Validator" %>
<%@ page import="com.liferay.portlet.PortletPreferencesFactoryUtil" %>
<%@ page import="pt.impresa.liferay.content.service.model.ImpresaContent" %>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration" %>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.FIELDS.TEMPLATES" %>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION" %>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL"%>
<%@ page import="pt.impresa.liferay.content.service.model.GetContentResponse" %>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.FIELDS"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.FieldGroupConfiguration"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.FieldConfiguration"%>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/util" prefix="liferay-util" %>

<%
	GetContentResponse getContentResponse = (GetContentResponse) request.getAttribute(LIST.CONTENT);
	ContentPortletConfiguration widgetConfiguration = (ContentPortletConfiguration) request.getAttribute(WIDGET.WIDGET_CONFIGURATION);
%>
<portlet:defineObjects />

<%--
    Digital Mushroom #1 (Create a hidden field with class portlet-hidden, so that a ninja jquery is able to hide it)
--%>
<%
	String portletTitle = null;
	Enumeration<String> prefNames = portletPreferences.getNames();
	while (prefNames.hasMoreElements()) {
		String portletNamePref = prefNames.nextElement();
		if (portletNamePref.startsWith("portletSetupTitle")) {
			portletTitle = portletPreferences.getValue(portletNamePref, null);
			break;
		}
	}
%>
<input type="hidden" class="portlet-title" value="<%= portletTitle %>" />
<c:set var="portletDisabled" value="<%= request.getAttribute(WIDGET.DISABLED)%>" />
<c:if test="${portletDisabled}">
	<input type="hidden" class="portlet-hidden" value="true" />
</c:if>
<%--
    End Digital Mushroom #1
--%>

<%-- Define some consts in EL for syntatic sugar (Bitchs know you are suck) --%>
<c:set var="titleConst" value="<%= TEMPLATES.TITLE%>" scope="request" />
<c:set var="leadConst" value="<%= TEMPLATES.LEAD%>" scope="request" />
<c:set var="readMoreConst" value="<%= TEMPLATES.READMORE%>" scope="request" />
<c:set var="bodyConst" value="<%= TEMPLATES.BODY%>" scope="request" />
<c:set var="publishedDate" value="<%= TEMPLATES.PUBLISHED_DATE%>" scope="request" />
<c:set var="share" value="<%= TEMPLATES.SHARE%>" scope="request" />
<c:set var="sectionName" value="<%= TEMPLATES.SECTION_NAME%>" scope="request" />
<c:set var="contentType" value="<%= TEMPLATES.CONTENT_TYPE%>" scope="request" />
<c:set var="articleType" value="<%= TEMPLATES.ARTICLE_TYPE%>" scope="request" />
<c:set var="authorConst" value="<%= TEMPLATES.AUTHOR%>" scope="request" />
<c:set var="keywordsConst" value="<%= TEMPLATES.KEYWORDS%>" scope="request" />
<c:set var="videoPlayerConst" value="<%= TEMPLATES.VIDEO_PLAYER%>" scope="request" />
<c:set var="imageConst" value="<%= TEMPLATES.IMAGE%>" scope="request" />
<c:set var="mediaAboveConst" value="<%= TEMPLATES.MEDIA_ABOVE%>" scope="request" />
<c:set var="mediaWrappedConst" value="<%= TEMPLATES.MEDIA_WRAPPED%>" scope="request" />
<c:set var="mediaBelowConst" value="<%= TEMPLATES.MEDIA_BELOW%>" scope="request" />


<c:set var="widgetConfiguration" value="<%= widgetConfiguration%>" />
<c:set var="getContentResponse" value="<%= getContentResponse%>" />
<c:set var="publicationName" value="<%= request.getAttribute(CONTENT.PUBLICATION_NAME)%>" scope="request" />

<c:choose>
	<c:when test="${not empty widgetConfiguration and not empty getContentResponse}">
		<div class="listContainer">
			
			<c:set var="articles" value="<%= getContentResponse.getContent()%>" />
			<c:set var="pagination" value="<%= getContentResponse.getPagination()%>" scope="request" />
			
			<%-- Iterate Article List --%>
			<c:forEach var="article" items="${articles}" varStatus="loopStatus">
				<%-- Set article vars --%>
				<c:set var="article" value="${article}" scope="request" />
				<c:set var="addclassOddEven" value= "${loopStatus.index % 2 == 0 ? 'odd' : 'even'}" />
				<c:set var="addclassIndexCounter" value="index_${loopStatus.index + 1}" />
				<c:set var="addclassFirstLast" value="${loopStatus.first ? 'first' : ''}" />
				<c:set var="addclassFirstLast" value="${loopStatus.last ? 'last' : addclassFirstLast}" />
				
				<%-- Digital Mushroom #2 ( Create a css class with the selected image version ) --%>
				<%
					String imageVersion = FIELDS.DEFAULT.IMAGE_VERSION;
					if (widgetConfiguration.getFieldGroups() != null) {
						for (FieldGroupConfiguration fgc : widgetConfiguration.getFieldGroups()) {
							if (fgc.getFields() != null) {
								for (FieldConfiguration fc : fgc.getFields()) {
									if (fc.getFieldTemplate().equalsIgnoreCase(TEMPLATES.IMAGE)) {
										ImpresaContent c = (ImpresaContent) pageContext.getAttribute("article");
										if(c.getImageSrc() != null && !c.getImageSrc().isEmpty()) {
											imageVersion = fc.getImageVersion();
										}
									}
								}
							}
						}
					}
				%>
				<%-- End Digital Mushroom# 2 --%>
				
				
				<div class="article ${addclassOddEven} ${addclassIndexCounter} ${addclassFirstLast} IV-<%= imageVersion%> CT-${article.contentType} SUN-${article.sectionUniqueName}" >
					
					<%-- Iterate Field Groups --%>
					<c:forEach var="fieldGroup" items="${widgetConfiguration.fieldGroups}" varStatus="loopGroupStatus">
						<div id="fieldGroup_${loopGroupStatus.index}" class="${fieldGroup.cssClass}">
							
							<%-- Iterate Fields --%>
							<c:forEach var="field" items="${fieldGroup.fields}" varStatus="loopFieldStatus">
								<div id="field_${loopFieldStatus.index}" class="${field.cssClass}">
									
									<%-- Set the field configuration available to other files --%>
									<c:set var="fieldConfiguration" value="${field}" scope="request" />
									
									<c:choose>
										
										<c:when test="${fieldConfiguration.fieldTemplate eq titleConst}">
											<c:import url="/html/parts/title.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq leadConst}">
											<c:import url="/html/parts/lead.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq readMoreConst}">
											<c:import url="/html/parts/readMore.jsp" />
										</c:when>
										
										<%-- Media Relations --%>
										<%-- Image or VideoPlayer (Teasers or default, or picturerels if empty) --%>
										<c:when test="${fieldConfiguration.fieldTemplate eq videoPlayerConst
														or fieldConfiguration.fieldTemplate eq imageConst}">
											<c:set var="media" scope="request" value="${article}" />
											<c:import url="/html/parts/media.jsp" />
										</c:when>
										<%-- Picturerel 0,1,2 --%>
										<c:when test="${fieldConfiguration.fieldTemplate eq mediaAboveConst}">
											<c:set var="media" scope="request" value="${article.mediaAbove}" />
											<c:set var="mediaCssClass" scope="request" value="mediaAbove" />
											<c:import url="/html/parts/media.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq mediaWrappedConst}">
											<c:set var="media" scope="request" value="${article.mediaWrapped}" />
											<c:set var="mediaCssClass" scope="request" value="mediaWrapped" />
											<c:import url="/html/parts/media.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq bodyConst}">
											<c:import url="/html/parts/body.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq mediaBelowConst}">
											<c:set var="media" scope="request" value="${article.mediaBelow}" />
											<c:set var="mediaCssClass" scope="request" value="mediaBelow" />
											<c:import url="/html/parts/media.jsp" />
										</c:when>
										
										
										<c:when test="${fieldConfiguration.fieldTemplate eq publishedDate}">
											<c:import url="/html/parts/publishedDate.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq share}">
											<c:import url="/html/parts/socialNetworks.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq sectionName}">
											<c:import url="/html/parts/sectionName.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq contentType}">
											<c:import url="/html/parts/contentType.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq articleType}">
											<c:import url="/html/parts/articleType.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq authorConst}">
											<c:import url="/html/parts/author.jsp" />
										</c:when>
										<c:when test="${fieldConfiguration.fieldTemplate eq keywordsConst}">
											<c:import url="/html/parts/keywords.jsp" />
										</c:when>
									</c:choose>
								</div>
							</c:forEach>
						</div>
					</c:forEach>
				</div>
				<c:remove var="article" scope="request" />
			</c:forEach>
		</div>

		<%-- Pagination --%>
		<c:set var="showBottomPagination" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_BOTTOM_PAGINATION, false)%>" />
		<c:if test="${showBottomPagination}">
			<c:import url="/html/parts/pagination.jsp" />
		</c:if>

		<%-- Follow Link --%>
		<c:set var="showFollowLink" value="<%= widgetConfiguration.getBoolean(PAGINATION.SHOW_FOLLOW_LINK, false)%>" />
		<c:if test="${showFollowLink}">
			<c:import url="/html/parts/followLink.jsp" />
		</c:if>

		<%-- Use Carousel --%>
		<c:set var="useCarousel" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_CAROUSEL, false)%>" />
		<c:if test="${useCarousel}">
			<c:import url="/html/parts/scriptCarousel.jsp" />
		</c:if>

		<%-- Use Ellipsis --%>
		<c:set var="useEllipsis" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_ELLIPSIS, false)%>" />
		<c:if test="${useEllipsis}">
			<c:import url="/html/parts/scriptEllipsis.jsp" />
		</c:if>

		<%-- Use Custom Scrollbar --%>
		<c:set var="useCustomScrollbar" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_CUSTOMSCROLLBAR, false)%>" />
		<c:if test="${useCustomScrollbar}">
			<c:import url="/html/parts/scriptCustomScrollbar.jsp" />
		</c:if>

		<%-- Use Toggle View --%>		
		<c:set var="useToggleView" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_TOGGLEVIEW, false)%>" />
		<c:if test="${useToggleView}">
			<c:import url="/html/parts/scriptToggleView.jsp" />
		</c:if>

		<%-- Use Masonry --%>		
		<c:set var="useMasonry" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_MASONRY, false)%>" />
		<c:if test="${useMasonry}">
			<c:import url="/html/parts/scriptMasonry.jsp" />
		</c:if>

		<%-- Use Infinite Scroll --%>		
		<c:set var="useInfiniteScroll" value="<%= widgetConfiguration.getBoolean(SCRIPTS.USE_INFINITESCROLL, false)%>" />
		<c:if test="${useInfiniteScroll}">
			<c:import url="/html/parts/scriptInfiniteScroll.jsp" />
		</c:if>

		<%-- Append To New Target --%>
		<c:set var="appendToNewTarget" value="<%= widgetConfiguration.getBoolean(GENERAL.APPEND_TO_NEW_TARGET, false)%>" />
		<c:if test="${appendToNewTarget}">
			<c:import url="/html/parts/appendToNewTarget.jsp" />
		</c:if>
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>