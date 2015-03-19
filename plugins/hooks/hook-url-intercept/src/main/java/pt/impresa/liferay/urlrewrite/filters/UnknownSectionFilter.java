package pt.impresa.liferay.urlrewrite.filters;

import com.liferay.portal.NoSuchLayoutException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Company;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.service.LayoutLocalServiceUtil;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;

import javax.servlet.*;
import java.io.IOException;
import java.util.List;

public class UnknownSectionFilter implements Filter {

    private static final ILoggerInterface logger = ILog.get(UnknownSectionFilter.class);

    public void init(FilterConfig filterConfig) throws ServletException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String uri = (String)servletRequest.getAttribute(WebKeys.INVOKER_FILTER_URI);
        boolean isRedirect = false;

        //Should we match only article urls ? what about sections that don't exist
        if(uri != null && URLFiltersHelper.matchesLiferayArticleUrl(uri)) {
            String publicationName = StringPool.SLASH + URLFiltersHelper.fetchLiferayPublicationName(uri);
            String sectionName = URLFiltersHelper.fetchLiferaySectionPart(uri);
            if(sectionName != null) {
                logger.debug(sectionName);
                try {
                    List<Company> companyList = CompanyLocalServiceUtil.getCompanies();
                    Company company = companyList.get(0);
                    Group group = GroupLocalServiceUtil.getFriendlyURLGroup(company.getCompanyId(), publicationName);

                    //First check if archive section exists

                    try {
                        Layout archiveSectionLayout = LayoutLocalServiceUtil.getFriendlyURLLayout(group.getGroupId(), false, StringPool.SLASH + URLFiltersHelper.ARCHIVE_SECTION_NAME);
                    } catch (NoSuchLayoutException e) {
                        throw new PortalException("\"arquivo\" section does not exist ",e);
                    }

                    //if we're here, we are safe

                    Layout layout = LayoutLocalServiceUtil.getFriendlyURLLayout(group.getGroupId(), false, sectionName);

                } catch (SystemException e) {
                    logger.error(e);
                } catch (NoSuchLayoutException e) {
                    String articlePart = URLFiltersHelper.fetchLiferayArticlePart(uri);
                    String redirectUrl = StringPool.SLASH + URLFiltersHelper.ARCHIVE_SECTION_NAME + articlePart;
                    URLFiltersHelper.setPermanentRedirect(redirectUrl, servletResponse);
                    logger.debug("redirecting user to " + redirectUrl);
                    isRedirect = true;
                } catch (PortalException e) {
                    logger.error(e);
                }
            }
        }

        if(!isRedirect) {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    public void destroy() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
