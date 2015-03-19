package pt.impresa.liferay.content.filters;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import org.apache.http.HttpStatus;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT;
import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;
import pt.impresa.liferay.content.service.model.GetContentResponse;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;
import pt.impresa.serialization.JSONSerializer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentLoadFilter implements Filter {

	private static final ILoggerInterface logger = ILog.get(ContentLoadFilter.class);
	private static final String REGEX_ARTICLE_ID = "([0-9]{4})-([0-9]{2})-([0-9]{2})-([a-zA-Z0-9\\-_\\.]+)";
	private static final String REGEX_BASE_LIFERAY_URL = StringPool.SLASH + "web" + StringPool.SLASH;
	private static Pattern articleIdPattern;


	public void init(FilterConfig filterConfig) {
		articleIdPattern = Pattern.compile(REGEX_ARTICLE_ID);
	}

	public void doFilter
			( ServletRequest servletRequest
					, ServletResponse servletResponse
					, FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest)servletRequest;

		String uri = (String)servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);
		logger.debug(uri);

		Matcher articleIdMatcher = articleIdPattern.matcher(uri);
		if(articleIdMatcher.find()) {

			if(uri.startsWith(REGEX_BASE_LIFERAY_URL)) {
				String noBaseUrl = uri.substring(REGEX_BASE_LIFERAY_URL.length());
				int indexOfFirstSlash = noBaseUrl.indexOf(StringPool.SLASH);
				if(indexOfFirstSlash > 0){
					String publicationName = noBaseUrl.substring(0, indexOfFirstSlash);


					String contentJSON = ImpresaContentLocalServiceUtil.getContentDetailsJSON
							(publicationName, articleIdMatcher.group(), null, null, null);

					if(contentJSON != null) {

						GetContentResponse content = JSONSerializer.fromJSON(contentJSON, GetContentResponse.class);
						if(content != null && content.getContent() != null && !content.getContent().isEmpty()) {

							logger.debug("Loaded article: " + articleIdMatcher.group());

							servletRequest.setAttribute( CONTENT.IMPRESA_CONTENT_ID, articleIdMatcher.group() );
							servletRequest.setAttribute( CONTENT.IMPRESA_CONTENT, contentJSON );

							int indexOfArticleId = uri.indexOf(articleIdMatcher.group());
							String newURI = uri.substring(0, indexOfArticleId-1);

							logger.debug("=> " + newURI);

							httpRequest.getRequestDispatcher(newURI).forward(servletRequest, servletResponse);
							return;
						}
						else{
							HttpServletResponse response = (HttpServletResponse) servletResponse;
							httpRequest.getRequestDispatcher("/erro").forward(servletRequest, servletResponse);
							response.setStatus(HttpStatus.SC_NOT_FOUND);
							// response.setHeader("Location", "/erro");
							// response.setHeader("Connection", "close");
							return;
						}
					}
				}
			}
		}

		filterChain.doFilter(servletRequest, servletResponse);
	}

	public void destroy() {}

}
