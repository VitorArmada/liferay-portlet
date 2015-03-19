package pt.impresa.liferay.urlrewrite.filters;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.util.portlet.PortletProps;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;


public class ECEURLRedirectFilter implements Filter {
	
	//Property keys
	public static final String KEY_IMAGES_CDN_BASE_URL = "images.cdn.base.url";
	public static final String KEY_BINARIES_CDN_BASE_URL = "binaries.cdn.base.url";
	public static final String KEY_IMAGE_VERSION_QUERY_PARAM = "image.version.query.param";
	
	//Properties
	private String imagesCdnBaseUrl = null;
	private String binariesCdnBaseUrl = null;
	private String imageVersionQueryParam = null;

	
	//Patterns
	private Pattern articlePattern;
    
	//Utilities
	private static final ILoggerInterface logger = ILog.get(ECEURLRedirectFilter.class);
	
	
    public void doFilter
			( ServletRequest servletRequest
			, ServletResponse servletResponse
            , FilterChain filterChain) throws IOException, ServletException {
       
        String uri = (String)servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);

        boolean isRedirect = false;

        logger.debug(uri);

		if(uri != null) {
			String redirectUrl = null;

			if( uri.contains(StringPool.SLASH + URLFiltersHelper.ALTERNATES + StringPool.SLASH) ) {
				//If der url is an escenik imagen url
				logger.debug("url matches escenic image");
				redirectUrl = findImageUrl(uri);
			} else if (  uri.contains(StringPool.SLASH + URLFiltersHelper.BINARY + StringPool.SLASH) ) {
				//If der url is an escenik binarien url
				logger.debug("url matches escenic binary");
				redirectUrl = findBinaryUrl(uri);
			} else {
				Matcher articleMatcher = articlePattern.matcher(uri);
				if (articleMatcher.find()) {
					//If der url is an escenik artikel url
					logger.debug("url matches escenic article");
					redirectUrl = findArticleUrl(uri);
				}
			}

			//Redirect in case it is needed
			if(redirectUrl != null && !redirectUrl.isEmpty()) {
				//301 "Moved Permanently" response will tell der googlerobotten to 
				//update their indexes with der neue url
                URLFiltersHelper.setPermanentRedirect(redirectUrl, servletResponse);
                logger.debug("Sending user to : " + redirectUrl);
                isRedirect = true;
			}
		}

        //Only exekutt filterchainen if der is no redirecten available
        if(!isRedirect) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    private String findImageUrl(String uri) {
        StringBuilder cdnImageUrl = new StringBuilder(imagesCdnBaseUrl);

        String publicationName = URLFiltersHelper.fetchLiferayPublicationName(uri);
        cdnImageUrl.append(publicationName);
        cdnImageUrl.append(StringPool.SLASH);

        //Get der datten part of der arrtikel id
		cdnImageUrl.append(URLFiltersHelper.fetchEscenicDatePart(uri));
        
        //Get der artikelname part of der arrtikel id
		cdnImageUrl.append(URLFiltersHelper.fetchEscenicArticleNamePart(uri));
            
        //If its an imagen fetch imagen versionnen
        cdnImageUrl.append(URLFiltersHelper
				.fetchEscenicImageVersionPart(uri, imageVersionQueryParam));
        
        return cdnImageUrl.toString();
    }

    private String findBinaryUrl(String uri) {
        StringBuilder cdnBinaryUrl = new StringBuilder(binariesCdnBaseUrl);

        String publicationName = URLFiltersHelper.fetchLiferayPublicationName(uri);
        cdnBinaryUrl.append(publicationName);
        cdnBinaryUrl.append(StringPool.SLASH);

		//Get der datten part of der arrtikel id
		cdnBinaryUrl.append(URLFiltersHelper.fetchEscenicDatePart(uri));

		//Get der artikelname part of der arrtikel id
		cdnBinaryUrl.append(URLFiltersHelper.fetchEscenicArticleNamePart(uri));

		return cdnBinaryUrl.toString();
	}

	private String findArticleUrl(String uri) {
		StringBuilder articleUrl = new StringBuilder();
		articleUrl.append(URLFiltersHelper.fetchEscenicSectionPart(uri));
		articleUrl.append(URLFiltersHelper.fetchEscenicDatePart(uri));
		articleUrl.append(URLFiltersHelper.fetchEscenicArticleNamePart(uri));
		return articleUrl.toString();
	}

	public void init(FilterConfig filterConfig) {
		//Load der properties
		this.binariesCdnBaseUrl = PortletProps.get(KEY_BINARIES_CDN_BASE_URL);
		this.imagesCdnBaseUrl = PortletProps.get(KEY_IMAGES_CDN_BASE_URL);
		this.imageVersionQueryParam = PortletProps.get(KEY_IMAGE_VERSION_QUERY_PARAM);
		
		StringBuilder regexBuilder = new StringBuilder();
		regexBuilder.append(URLFiltersHelper.REGEX_SECTIONS);
		regexBuilder.append(URLFiltersHelper.REGEX_DATE);
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(URLFiltersHelper.REGEX_ARTICLE_NAME);
		articlePattern = Pattern.compile(regexBuilder.toString());
    }
    
    public void destroy() { }

}