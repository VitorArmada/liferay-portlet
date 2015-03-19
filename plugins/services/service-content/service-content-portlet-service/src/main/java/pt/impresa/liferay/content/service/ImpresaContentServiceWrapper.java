package pt.impresa.liferay.content.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link ImpresaContentService}.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ImpresaContentService
 * @generated
 */
public class ImpresaContentServiceWrapper implements ImpresaContentService,
    ServiceWrapper<ImpresaContentService> {
    private ImpresaContentService _impresaContentService;

    public ImpresaContentServiceWrapper(
        ImpresaContentService impresaContentService) {
        _impresaContentService = impresaContentService;
    }

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier() {
        return _impresaContentService.getBeanIdentifier();
    }

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier) {
        _impresaContentService.setBeanIdentifier(beanIdentifier);
    }

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable {
        return _impresaContentService.invokeMethod(name, parameterTypes,
            arguments);
    }

    public java.lang.String getContentsJSON(java.lang.String publication,
        java.lang.String section, boolean includeSubsections,
        java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentService.getContentsJSON(publication, section,
            includeSubsections, groupId, maxArticles, contentTypes, fields,
            page, searchKeywords, relations);
    }

    public pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException {
        return _impresaContentService.getContents(publication, section,
            includeSubsections, groupId, maxArticles, contentTypes, fields,
            page, searchKeywords, relations);
    }

    public java.lang.String getNewsletterURl(java.lang.String code) {
        return _impresaContentService.getNewsletterURl(code);
    }

    /**
     * @deprecated Renamed to {@link #getWrappedService}
     */
    public ImpresaContentService getWrappedImpresaContentService() {
        return _impresaContentService;
    }

    /**
     * @deprecated Renamed to {@link #setWrappedService}
     */
    public void setWrappedImpresaContentService(
        ImpresaContentService impresaContentService) {
        _impresaContentService = impresaContentService;
    }

    public ImpresaContentService getWrappedService() {
        return _impresaContentService;
    }

    public void setWrappedService(ImpresaContentService impresaContentService) {
        _impresaContentService = impresaContentService;
    }
}
