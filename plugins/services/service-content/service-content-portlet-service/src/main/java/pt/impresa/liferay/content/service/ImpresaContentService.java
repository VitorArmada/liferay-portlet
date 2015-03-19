package pt.impresa.liferay.content.service;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.jsonwebservice.JSONWebService;
import com.liferay.portal.kernel.transaction.Isolation;
import com.liferay.portal.kernel.transaction.Propagation;
import com.liferay.portal.kernel.transaction.Transactional;
import com.liferay.portal.service.BaseService;
import com.liferay.portal.service.InvokableService;

/**
 * The interface for the impresa content remote service.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see ImpresaContentServiceUtil
 * @see pt.impresa.liferay.content.service.base.ImpresaContentServiceBaseImpl
 * @see pt.impresa.liferay.content.service.impl.ImpresaContentServiceImpl
 * @generated
 */
@JSONWebService
@Transactional(isolation = Isolation.PORTAL, rollbackFor =  {
    PortalException.class, SystemException.class}
)
public interface ImpresaContentService extends BaseService, InvokableService {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never modify or reference this interface directly. Always use {@link ImpresaContentServiceUtil} to access the impresa content remote service. Add custom service methods to {@link pt.impresa.liferay.content.service.impl.ImpresaContentServiceImpl} and rerun ServiceBuilder to automatically copy the method declarations to this interface.
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

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public java.lang.String getNewsletterURl(java.lang.String code);
}
