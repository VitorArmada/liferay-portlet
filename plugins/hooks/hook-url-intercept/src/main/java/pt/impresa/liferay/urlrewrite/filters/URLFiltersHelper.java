package pt.impresa.liferay.urlrewrite.filters;

import com.liferay.portal.kernel.util.StringPool;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLFiltersHelper {

    //Constants
    public static final String LEGACY_SECTION_NAME = "legacy";
    public static final String ARCHIVE_SECTION_NAME = "arquivo";
    public static final String BINARY = "BINARY";
    public static final String ALTERNATES = "ALTERNATES";
    public static final String REGEX_BASE_LIFERAY_URL = "web";
    public static final String REGEX_DATE = "([0-9]{4})/([0-9]{2})/([0-9]{2})";
    public static final String REGEX_ARTICLE_NAME = "([a-zA-Z0-9\\-\\_\\.]+)";
    public static final String REGEX_IMAGE_VERSION = "([a-zA-Z0-9\\-\\_]+)";
    public static final String REGEX_SECTIONS = "((([a-zA-Z0-9\\-\\_]+)/)+)";
    public static final String REGEX_PUBLICATION_NAME = "([a-zA-Z]+)";
    public static final String REGEX_ARTICLE_ID = "([0-9]{4})-([0-9]{2})-([0-9]{2})-([a-zA-Z0-9\\-\\_\\.]+)";


    public static final String LIFERAY_PUBLIC_PAGES_BASE_PATH = StringPool.SLASH + REGEX_BASE_LIFERAY_URL;


	private static final Pattern baseLiferayUrlPattern;
	private static final Pattern dateUrlPattern;
	private static final Pattern articleNameUrlPattern;
	private static final Pattern imageVersionUrlPattern;
    private static final Pattern articleIdUrlPattern;
	
	static {
        /* Regex matching patterns shall be compiled once in this static block */
	
		StringBuilder regexBuilder = new StringBuilder();
		
		//Build base Liferay URL Pattern
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_BASE_LIFERAY_URL);
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_PUBLICATION_NAME);
		baseLiferayUrlPattern = Pattern.compile(regexBuilder.toString());
		
		regexBuilder = new StringBuilder();
		
		//Build datePart Url
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_DATE);
		regexBuilder.append(StringPool.SLASH);
		dateUrlPattern = Pattern.compile(regexBuilder.toString());
		
		regexBuilder = new StringBuilder();
		
		//Build article name Url
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_DATE);
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_ARTICLE_NAME);
		articleNameUrlPattern = Pattern.compile(regexBuilder.toString());
		
		regexBuilder = new StringBuilder();
		
		//Build imageVersion Url
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(ALTERNATES);
		regexBuilder.append(StringPool.SLASH);
		regexBuilder.append(REGEX_IMAGE_VERSION);
		regexBuilder.append(StringPool.SLASH);
		imageVersionUrlPattern = Pattern.compile(regexBuilder.toString());

        //Build articleIdUrl

        regexBuilder = new StringBuilder();

        regexBuilder.append(StringPool.SLASH);
        regexBuilder.append(REGEX_ARTICLE_ID);
        articleIdUrlPattern = Pattern.compile(regexBuilder.toString());
	}

    public static String fetchLiferayPublicationName(String uri) {
        String publicationName = null;
        if( uri != null && uri.startsWith(LIFERAY_PUBLIC_PAGES_BASE_PATH) ) {
            String tempPublicationName = uri.substring(LIFERAY_PUBLIC_PAGES_BASE_PATH.length());
            tempPublicationName = tempPublicationName.replaceFirst(StringPool.SLASH, "");
            int indexOfSlash = tempPublicationName.indexOf(StringPool.SLASH);
            if(indexOfSlash > -1 && indexOfSlash < tempPublicationName.length()) {
                tempPublicationName = tempPublicationName.substring(0, indexOfSlash);
                publicationName = tempPublicationName;
            }
        }
        return publicationName;
    }

    public static String fetchLiferaySectionPart(String uri) {

        String sectionPartUrl = null;
        Matcher baseLiferayUrlMatcher = baseLiferayUrlPattern.matcher(uri);

        if(baseLiferayUrlMatcher.find()) {
            int baseUrlLastIndex = baseLiferayUrlMatcher.end();
            String tempSectionPartUri = uri.substring(baseUrlLastIndex);

            int lastIndexOfSlash = tempSectionPartUri.lastIndexOf(StringPool.SLASH);
            if(lastIndexOfSlash > 0 && lastIndexOfSlash < tempSectionPartUri.length() ) {
                tempSectionPartUri = tempSectionPartUri.substring(0, lastIndexOfSlash);
                sectionPartUrl = tempSectionPartUri;
            }

        }

        return sectionPartUrl;

    }

    public static String fetchLiferayArticlePart(String uri) {
        String articlePartUrl = uri.substring(uri.lastIndexOf(StringPool.SLASH));
        return articlePartUrl;
    }

	public static String fetchEscenicSectionPart(String uri) {
        
		String sectionPartUrl = uri;
		Matcher baseLiferayUrlMatcher = baseLiferayUrlPattern.matcher(sectionPartUrl);
		if(baseLiferayUrlMatcher.find()) {
			int baseUrlLastIndex = baseLiferayUrlMatcher.end();
			sectionPartUrl = sectionPartUrl.substring(baseUrlLastIndex);
		}

        Matcher dateMatcher = dateUrlPattern.matcher(sectionPartUrl);
        if(dateMatcher.find()){
            int lastIndex = dateMatcher.start();
            sectionPartUrl = sectionPartUrl.substring(0,lastIndex);
            sectionPartUrl = sectionPartUrl + StringPool.SLASH;
        }
		
		return sectionPartUrl;
		
    }
    
    public static String fetchEscenicDatePart(String uri) {
        
		StringBuilder append = new StringBuilder();
        
		Matcher dateMatcher = dateUrlPattern.matcher(uri);
        if(dateMatcher.find()) {
            for(int i = 1; i <= dateMatcher.groupCount(); i++) {
                append.append(dateMatcher.group(i));
                append.append(StringPool.DASH);
            }
        }
        return append.toString();
		
    }
    
    public static String fetchEscenicArticleNamePart(String uri) {
		
        StringBuilder append = new StringBuilder();
		
        Matcher articleNameMatcher = articleNameUrlPattern.matcher(uri);
        if(articleNameMatcher.find()) {
            append.append(articleNameMatcher.group(4));
        }
        return append.toString();
		
    }
    
    public static String fetchEscenicImageVersionPart(String uri, String imageVersionQueryParam) {
        StringBuilder append = new StringBuilder();
        
		Matcher imageVersionMatcher = imageVersionUrlPattern.matcher(uri);
        if(imageVersionMatcher.find()) {
            append.append(StringPool.QUESTION);
			append.append(imageVersionQueryParam);
			append.append(StringPool.EQUAL);
            append.append(imageVersionMatcher.group(1));
        }
        return append.toString();
    }

    public static void setPermanentRedirect(String redirectURL, ServletResponse servletResponse) {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String newURL = httpResponse.encodeRedirectURL(redirectURL);
        httpResponse.setStatus(HttpServletResponse.SC_MOVED_PERMANENTLY);
        httpResponse.setHeader("Location", newURL);
        httpResponse.setHeader("Connection", "close");
    }

    public static boolean matchesLiferayArticleUrl(String uri) {
        return articleIdUrlPattern.matcher(uri).find();
    }
    
}
