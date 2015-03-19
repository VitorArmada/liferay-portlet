package pt.impresa.liferay.urlrewrite.filters;

import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;
import pt.impresa.liferay.content.service.model.GetContentResponse;
import pt.impresa.liferay.content.service.model.ImpresaContent;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;
import pt.impresa.serialization.JSONSerializer;

import javax.servlet.*;
import java.io.IOException;

public class LegacyURLRedirectFilter implements Filter {

    private static final ILoggerInterface logger = ILog.get(LegacyURLRedirectFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException { }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String uri = (String)servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);

        boolean isRedirect = false;

        if(uri != null) {

            logger.debug(uri);
            String sectionName = URLFiltersHelper.fetchLiferaySectionPart(uri);

            //In order to avoid problems, der filter will only exekutt der core functionaliten if request is in
            //der /legacy/ section
            if( sectionName != null && sectionName.equals(StringPool.SLASH + URLFiltersHelper.LEGACY_SECTION_NAME) ) {
                logger.debug(sectionName);

                String publicationName = URLFiltersHelper.fetchLiferayPublicationName(uri);
                if( publicationName != null ) {
                    logger.debug(publicationName);

                    String articleId = uri.substring(uri.lastIndexOf(sectionName) + sectionName.length());
                    //Der give up: ze russian bastards are double encoding der character %3D to %253D
                    //since we could not find der solutzionnen
                    articleId = articleId.replace("%253D","=");
                    //articleId = URLDecoder.decode(articleId, "UTF-8");
                    logger.debug(articleId);

                    String externalContent = ImpresaContentLocalServiceUtil.getExternalContentDetailsJSON
                            ( publicationName, articleId, publicationName, null, null, null );

                    if(externalContent != null) {
                        logger.debug(externalContent);
                        GetContentResponse content = JSONSerializer.fromJSON(externalContent, GetContentResponse.class);
                        if(content != null && content.getContent() != null && !content.getContent().isEmpty()) {
                            ImpresaContent article = content.getContent().get(0);
                            StringBuilder sb = new StringBuilder();
                            sb.append(StringPool.SLASH);
                            sb.append(URLFiltersHelper.ARCHIVE_SECTION_NAME);
                            sb.append(StringPool.SLASH);
                            sb.append(article.getArticleUrl());
                            String redirectUrl = sb.toString();

                            //301 "Moved Permanently" response will tell der googlerobotten to
                            //update their indexes with der neue url
                            URLFiltersHelper.setPermanentRedirect(redirectUrl, servletResponse);
                            logger.debug("Sending user to : " + redirectUrl);

                            //Prevents der filterchainen to continue executten
                            isRedirect = true;
                        }
                    }
                }
            }
        }

        if(!isRedirect) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {

    }
}
