package pt.impresa.liferay.content.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseLocalService;
import com.liferay.portal.service.InvokableLocalService;

/**
 * The interface for the impresa content local service.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpresaContentLocalServiceUtil
 * @see pt.impresa.liferay.content.service.base.ImpresaContentLocalServiceBaseImpl
 * @see pt.impresa.liferay.content.service.impl.ImpresaContentLocalServiceImpl
 * @generated
 */
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ImpresaContentLocalService extends BaseLocalService,
    InvokableLocalService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpresaContentLocalServiceUtil} to access the impresa content local service. Add custom service methods to {@link pt.impresa.liferay.content.service.impl.ImpresaContentLocalServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
     */

    /**
    * Returns the Spring bean ID for this bean.
    *
    * @return the Spring bean ID for this bean
    */
    public java.lang.String getBeanIdentifier();

    /**
    * Sets the Spring bean ID for this bean.
    *
    * @param beanIdentifier the Spring bean ID for this bean
    */
    public void setBeanIdentifier(java.lang.String beanIdentifier);

    public java.lang.Object invokeMethod(java.lang.String name,
        java.lang.String[] parameterTypes, java.lang.Object[] arguments)
        throws java.lang.Throwable;

    /**
    * GET CONTENTS
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getContentsJSON(java.lang.String publication,
        java.lang.String section, boolean includeSubsections,
        java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws java.io.IOException;

    /**
    * GET CONTENT DETAILS
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getExternalContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.String externalSystemId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getContentDetailsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> relations) throws java.io.IOException;

    /**
    * GET RELATED CONTENT
    */
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getRelatedContentsJSON(
        java.lang.String publication, java.lang.String articleId,
        java.lang.Integer maxArticles, java.lang.Integer page,
        java.util.List<java.lang.String> fields,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> relations) throws java.io.IOException;
}
