<%@page import="java.util.LinkedList"%>
<%@page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.FIELDS.TEMPLATES"%>
<%@page import="java.util.List"%>
<%@page import="pt.impresa.liferay.portlet.content.config.FieldConfiguration"%>
<%@page import="pt.impresa.liferay.content.utils.ImpresaContentUtils"%>
<%@page import="pt.impresa.liferay.content.service.model.ImpresaContent"%>
<%@page import="com.liferay.portal.theme.ThemeDisplay"%>
<%@page import="com.liferay.portal.kernel.util.WebKeys"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<% 
	Object media = request.getAttribute("media");
	if(media != null ) {
		FieldConfiguration fc = (FieldConfiguration) request.getAttribute("fieldConfiguration");
		if(media instanceof ImpresaContent) {
			ImpresaContent mediaArticle = ((ImpresaContent)media);

			//When it is a teaser (image/videoPlayer) for listings
			if(fc != null && fc.getFieldTemplate().equalsIgnoreCase(TEMPLATES.IMAGE)) {
				String imageUrl = mediaArticle.getImageSrc();
				if(imageUrl != null) {
					String imageSrc = ImpresaContentUtils.getImageUrl(request, imageUrl, fc.getImageVersion());
					pageContext.setAttribute( "imageSrc", imageSrc );
					pageContext.setAttribute( "imageAlt", mediaArticle.getImageAlt() );
                    pageContext.setAttribute( "articlePageUrl", mediaArticle.getArticlePageUrl() );
				}
			} else if(fc != null && fc.getFieldTemplate().equalsIgnoreCase(TEMPLATES.VIDEO_PLAYER)) {
				String videoUrl = null;
				if(mediaArticle.getVideoUrls() != null && !mediaArticle.getVideoUrls().isEmpty()) {
					videoUrl = mediaArticle.getVideoUrls().get("video.net");
				} else if(mediaArticle.getMediaAbove() != null && !mediaArticle.getMediaAbove().isEmpty()) {
					if(mediaArticle.getMediaAbove().get(0).getVideoUrls() != null && !mediaArticle.getMediaAbove().get(0).getVideoUrls().isEmpty()) {
						videoUrl = mediaArticle.getMediaAbove().get(0).getVideoUrls().get("video.net");
					}
				} else if(mediaArticle.getMediaWrapped() != null && !mediaArticle.getMediaWrapped().isEmpty()) {
					if(mediaArticle.getMediaWrapped().get(0).getVideoUrls() != null && !mediaArticle.getMediaWrapped().get(0).getVideoUrls().isEmpty()) {
						videoUrl = mediaArticle.getMediaWrapped().get(0).getVideoUrls().get("video.net");
					}
				} else if(mediaArticle.getMediaBelow()!= null && !mediaArticle.getMediaBelow().isEmpty()) {
					if(mediaArticle.getMediaBelow().get(0).getVideoUrls() != null && !mediaArticle.getMediaBelow().get(0).getVideoUrls().isEmpty()) {
						videoUrl = mediaArticle.getMediaBelow().get(0).getVideoUrls().get("video.net");
					}
				}
				pageContext.setAttribute( "videoSrc", videoUrl );
			}
		} else if(media instanceof List) {
			//When it is a picturerel
			if(((List)media).size() == 1) {
				//with one element
				ImpresaContent mediaArticle = (ImpresaContent)((List<ImpresaContent>)media).get(0);
				if(mediaArticle.getContentType() != null && mediaArticle.getContentType().equalsIgnoreCase("picture")) {
					String imageSrc = ImpresaContentUtils.getImageUrl(request, mediaArticle.getImageSrc(), fc.getImageVersion());
					pageContext.setAttribute( "imageSrc", imageSrc );
					pageContext.setAttribute( "imageAlt", mediaArticle.getImageAlt() );
				} else if(mediaArticle.getContentType() != null && mediaArticle.getContentType().equalsIgnoreCase("videoimpresa")) {
					pageContext.setAttribute( "videoSrc", mediaArticle.getVideoUrls().get("video.net"));
				}
			} else {
				//with more than one element
				List<ImpresaContent> pictures = new LinkedList<ImpresaContent>();
				for(ImpresaContent mediaArticle : (List<ImpresaContent>)media) {
					if(mediaArticle.getContentType() != null && mediaArticle.getContentType().equalsIgnoreCase("picture")) {
						if(mediaArticle.getImageSrc() != null) {
							//Fetch full complete image url
							String imageSrc = ImpresaContentUtils.getImageUrl(request, mediaArticle.getImageSrc(), fc.getImageVersion());
							mediaArticle.setImageSrc(imageSrc);
							pictures.add(mediaArticle);
						}
					}
				}
				pageContext.setAttribute("pictures", pictures);
			}
		}
	}

%>
<c:if test="${not empty media}">
	
	<%-- media relations --%>
	<c:if test="${not empty mediaCssClass}">
		<div class="${mediaCssClass}">
	</c:if>
	
	<c:choose>
		<%-- standalone images --%>
		<c:when test="${not empty imageSrc}">
			<div class="imageContainer">
                <c:if test="${not empty articlePageUrl}">
                    <a href="${articlePageUrl}">
                </c:if>
                <img src="<c:out value="${imageSrc}" />" alt="<c:out value="${imageAlt}" />">
                <c:if test="${not empty articlePageUrl}">
                    </a>
                </c:if>
			</div>
		</c:when>
		<%-- multiple images --%>
		<c:when test="${not empty pictures}">
			<div class="slideshow-above">
				<c:forEach var="picture" items="${pictures}">
					<img src="<c:out value="${picture.imageSrc}" />" alt="<c:out value="${picture.imageAlt}" />">
				</c:forEach>
			</div>
			<script type="text/javascript">
				//head.js('/js/lib/slides/jquery.slides.min.js');
				head.ready(function() {
					$(function() {
						$(".slideshow-above").slidesjs({
							width: 620,
							height: 395,
							pagination: {
								active: true,
								// [boolean] Create pagination items.
								// You cannot use your own pagination. Sorry.
								effect: "slide"
								// [string] Can be either "slide" or "fade".
							},
							callback: {
								start: function(reloadMrec) {
									$('.stickyMrec iframe').attr('src', function (i, val) { return val; });
									Liferay.fire(PortalCommon.Events.PAGE_VIEW);
								}
							}
						});
					});
				});
			</script>
		</c:when>
		<%-- standalone video --%>
		<c:when test="${not empty videoSrc}">
			<%
				//Vast URL Template
				//TODO: widget field configuration should contain VAST URL (no cpids and shit)

				ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
				String vastZoneId = (String) td.getLayout().getGroup().getExpandoBridge().getAttribute("sapoZoneId");
				String vastZoneCpId = (String) td.getLayout().getGroup().getExpandoBridge().getAttribute("sapoCpId");
				String vastUrl = null;

				StringBuilder sb = new StringBuilder();
				sb.append("http://pub.sapo.pt/vast.php?");
				if(vastZoneId != null && !vastZoneId.isEmpty()) {
					sb.append("zoneid=");
					sb.append(vastZoneId);
					sb.append("&");
				}
				if(vastZoneCpId != null && !vastZoneCpId.isEmpty()) {
					sb.append("cpid=");
					sb.append(vastZoneCpId);
					sb.append("&");
				}
				sb.append("cb=");
				vastUrl = sb.toString();

			%>
			<script type="text/javascript">
				//TODO: Load minified versions of videojs and telefunken
				head.load('/html/js/impresa/video/telefunken-css.css');
				head.load('/html/js/lib/videojs/video-js.min.css');
				/*head.js('/js/lib/videojs/video.min.js',function(){
					head.js('/js/impresa/video/telefunken.js',function(){
						head.js('/js/impresa/video/telefunken-util.js', function(){
							head.js('/js/impresa/video/telefunken-vast.js',
									'/js/impresa/video/telefunken-videojs.js');
						});
					});
				});*/
				head.ready(function(){
					telefunken("videoPlayerWrapper", new TelefunkenVideoJS(), {
						videoUrl : "${videoSrc}",
						techOrder : ["flash","html5"],
						autoplay : true,
						vastUrl : '<%= vastUrl %>' + Math.floor(Math.random() * 987654321),
						swfPath : "/html/common/swf/video-js.swf",
						width : "100%",
						height : "100%",
                        debug : true
					});
				});
			</script>
			<div class="videoContainer">
				<div id="videoPlayerWrapper"></div>
			</div>
		</c:when>
		<%-- multiple videos (not supported) --%>
	</c:choose>
	
	<c:if test="${not empty mediaCssClass}">
		</div>
	</c:if>

</c:if>