package pt.impresa.liferay.content.service;

import com.liferay.portal.kernel.bean.PortletBeanLocatorUtil;
import com.liferay.portal.kernel.util.ReferenceRegistry;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The utility for the impresa content local service. This utility wraps {@link pt.impresa.liferay.content.service.impl.ImpresaContentLocalServiceImpl} and is the primary access point for service operations in application layer code running on the local server.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpresaContentLocalService
 * @see pt.impresa.liferay.content.service.base.ImpresaContentLocalServiceBaseImpl
 * @see pt.impresa.liferay.content.service.impl.ImpresaContentLocalServiceImpl
 * @generated
 */
public class ImpresaContentLocalServiceUtil {
    private static ImpresaContentLocalService _service;

    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify this class directly. Add custom service methods to {@link pt.impresa.liferay.content.service.impl.ImpresaContentLocalServiceImpl} and rerun ServiceBuilder to regenerate this class.
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

    /**
    * GET CONTENTS
    */
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

    /**
    * GET CONTENT DETAILS
    */
    public static java.lang.String getExternalContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.String externalSystemId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return getService()
                   .getExternalContentDetailsJSON(publication, articleId,
            externalSystemId, contentTypes, fields, relations);
    }

    public static java.lang.String getContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return getService()
                   .getContentDetailsJSON(publication, articleId, contentTypes,
            fields, relations);
    }

    /**
    * GET RELATED CONTENT
    */
    public static java.lang.String getRelatedContentsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.Integer maxArticles, java.lang.Integer page,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return getService()
                   .getRelatedContentsJSON(publication, articleId, maxArticles,
            page, fields, contentTypes, relations);
    }

    public static void clearService() {
        _service = null;
    }

    public static ImpresaContentLocalService getService() {
        if (_service == null) {
            InvokableLocalService invokableLocalService = (InvokableLocalService) PortletBeanLocatorUtil.locate(ClpSerializer.getServletContextName(),
                    ImpresaContentLocalService.class.getName());

            if (invokableLocalService instanceof ImpresaContentLocalService) {
                _service = (ImpresaContentLocalService) invokableLocalService;
            } else {
                _service = new ImpresaContentLocalServiceClp(invokableLocalService);
            }

            ReferenceRegistry.registerReference(ImpresaContentLocalServiceUtil.class,
                "_service");
        }

        return _service;
    }

    /**
     * @deprecated
     */
    public void setService(ImpresaContentLocalService service) {
    }
}
