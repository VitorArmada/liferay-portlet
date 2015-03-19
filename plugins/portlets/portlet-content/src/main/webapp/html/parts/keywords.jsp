<%@ page import="com.liferay.util.portlet.PortletProps"%>
<%@ page import="com.liferay.portal.service.PortletLocalServiceUtil"%>
<%@ page import="com.liferay.portal.model.Portlet"%>
<%@ page import="com.liferay.portal.kernel.util.WebKeys"%>
<%@ page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@ page import="com.liferay.portal.service.LayoutLocalServiceUtil"%>
<%@ page import="com.liferay.portal.util.PortalUtil"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>

<portlet:defineObjects />

<div class="keywordsContainer">
				<div class="label">Palavras-chave</div>
				<div class="tagsList">
								<c:forEach var="tag" items="${article.keywords}">
												<div class="tag">${tag}</div>
								</c:forEach>
				</div>
</div>