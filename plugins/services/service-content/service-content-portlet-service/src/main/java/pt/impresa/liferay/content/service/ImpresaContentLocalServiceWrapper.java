package pt.impresa.liferay.content.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ImpresaContentLocalService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ImpresaContentLocalService
 * @generated
 */
public class ImpresaContentLocalServiceWrapper
    implements ImpresaContentLocalService,
        ServiceWrapper<ImpresaContentLocalService> {
    private ImpresaContentLocalService _impresaContentLocalService;

    public ImpresaContentLocalServiceWrapper(
        ImpresaContentLocalService impresaContentLocalService) {
        _impresaContentLocalService = impresaContentLocalService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _impresaContentLocalService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impresaContentLocalService.setBeanIdentifier(beanIdentifier);
    }

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impresaContentLocalService.invokeMethod(name, parameterTypes,
            arguments);
    }

    /**
    * GET CONTENTS
    */
    public java.lang.String getContentsJSON(java.lang.String publication,
        java.lang.String section, boolean includeSubsections,
        java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentLocalService.getContentsJSON(publication,
            section, includeSubsections, groupId, maxArticles, contentTypes,
            fields, page, searchKeywords, relations);
    }

    public pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentLocalService.getContents(publication, section,
            includeSubsections, groupId, maxArticles, contentTypes, fields,
            page, searchKeywords, relations);
    }

    /**
    * GET CONTENT DETAILS
    */
    public java.lang.String getExternalContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.String externalSystemId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentLocalService.getExternalContentDetailsJSON(publication,
            articleId, externalSystemId, contentTypes, fields, relations);
    }

    public java.lang.String getContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentLocalService.getContentDetailsJSON(publication,
            articleId, contentTypes, fields, relations);
    }

    /**
    * GET RELATED CONTENT
    */
    public java.lang.String getRelatedContentsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.Integer maxArticles, java.lang.Integer page,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentLocalService.getRelatedContentsJSON(publication,
            articleId, maxArticles, page, fields, contentTypes, relations);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ImpresaContentLocalService getWrappedImpresaContentLocalService() {
        return _impresaContentLocalService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedImpresaContentLocalService(
        ImpresaContentLocalService impresaContentLocalService) {
        _impresaContentLocalService = impresaContentLocalService;
    }

    public ImpresaContentLocalService getWrappedService() {
        return _impresaContentLocalService;
    }

    public void setWrappedService(
        ImpresaContentLocalService impresaContentLocalService) {
        _impresaContentLocalService = impresaContentLocalService;
    }
}
