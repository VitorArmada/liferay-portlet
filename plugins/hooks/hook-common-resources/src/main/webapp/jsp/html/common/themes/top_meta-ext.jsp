<%@page import="com.liferay.portal.util.PortalUtil"%>
<%@page import="com.liferay.portal.model.Layout"%>
<%@page import="com.liferay.portal.service.GroupLocalServiceUtil"%>
<%@page import="com.liferay.portal.model.Group"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
	String meta_description = null;
	String meta_url = null;
	String meta_siteName = null;
	String meta_type = null;
	String meta_title = null;
	String meta_image_url = null;
    String meta_googleVerification = null;
	String meta_article = (String) request.getAttribute("impresaContent");

	Layout meta_layout = layout;
	Group meta_group = null;
	if(meta_layout != null) {
		long meta_groupId = meta_layout.getGroupId();
		meta_group = GroupLocalServiceUtil.getGroup(meta_groupId);
		meta_siteName = meta_group.getName();
        meta_googleVerification = (String) meta_group.getExpandoBridge().getAttribute("googleVerificationKey");
	}
	
	if(meta_article != null) {
		
		meta_type = "article";
		meta_url = (String) request.getAttribute("articleUrl");
		meta_image_url = (String) request.getAttribute("articleImageUrl");
		meta_description = (String) request.getAttribute(WebKeys.PAGE_DESCRIPTION);
		meta_title = (String) request.getAttribute(WebKeys.PAGE_TITLE);

	} 

	if(meta_type == "article") { %>
	
		<meta property="og:type" content="<%= meta_type %>" />
		
		<meta property="og:site_name" content="<%= meta_siteName %>" />
	
		<meta property="og:title" content="<%= meta_title %>" />
		<meta name="twitter:title" content="<%= meta_title %>" />
	
		<meta property="og:image" content="<%= meta_image_url %>" />
		<meta name="twitter:image" content="<%= meta_image_url %>" />
		
		<meta property="og:url" content="<%= meta_url %>" />
		<meta name="twitter:url" content="<%= meta_url %>" />

		<meta property="og:description" content="<%= meta_description %>" />
		<meta name="twitter:description" content="<%= meta_description %>" />
<%
	}
%>

<%
    if(meta_googleVerification != null) {
%>
    <meta name="google-site-verification" content="<%= meta_googleVerification %>" />
<%
    }
%>
	