package pt.impresa.liferay.content.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableService;

/**
 * The utility for the impresa content remote service. This utility wraps {@link pt.impresa.liferay.content.service.impl.ImpresaContentServiceImpl} and is the primary access point for service operations in application layer code running on a remote server.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpresaContentService
 * @see pt.impresa.liferay.content.service.base.ImpresaContentServiceBaseImpl
 * @see pt.impresa.liferay.content.service.impl.ImpresaContentServiceImpl
 * @generated
 */
public class ImpresaContentServiceUtil {
    private static ImpresaContentService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link pt.impresa.liferay.content.service.impl.ImpresaContentServiceImpl} and rerun ServiceBuilder to regenerate this class.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public static java.lang.String getBeanIdentifier() {
        return getService().getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public static void setBeanIdentifier(java.lang.String beanIdentifier) {
        getService().setBeanIdentifier(beanIdentifier);
    }

    public static java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return getService().invokeMethod(name, parameterTypes, arguments);
    }

    public static java.lang.String getContentsJSON(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return getService()
                   .getContentsJSON(publication, section, includeSubsections,
            groupId, maxArticles, contentTypes, fields, page, searchKeywords,
            relations);
    }

    public static pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return getService()
                   .getContents(publication, section, includeSubsections,
            groupId, maxArticles, contentTypes, fields, page, searchKeywords,
            relations);
    }

    public static java.lang.String getNewsletterURl(java.lang.String code) {
        return getService().getNewsletterURl(code);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImpresaContentService getService() {
        if (_service == null) {
            InvokableService invokableService = (InvokableService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImpresaContentService.class.getName());

            if (invokableService instanceof ImpresaContentService) {
                _service = (ImpresaContentService) invokableService;
            } else {
                _service = new ImpresaContentServiceClp(invokableService);
            }

            ReferenceRegistry.registerReference(ImpresaContentServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(ImpresaContentService service) {
    }
}
