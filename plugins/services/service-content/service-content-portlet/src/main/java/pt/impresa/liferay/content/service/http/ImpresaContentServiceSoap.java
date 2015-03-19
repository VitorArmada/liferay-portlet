package pt.impresa.liferay.content.service.http;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import pt.impresa.liferay.content.service.ImpresaContentServiceUtil;

import java.rmi.RemoteException;

/**
 * <p>
 * This class provides a SOAP utility for the
 * {@link pt.impresa.liferay.content.service.ImpresaContentServiceUtil} service utility. The
 * static methods of this class calls the same methods of the service utility.
 * However, the signatures are different because it is difficult for SOAP to
 * support certain types.
 * </p>
 *
 * <p>
 * The benefits of using the SOAP utility is that it is cross platform
 * compatible. SOAP allows different languages like Java, .NET, C++, PHP, and
 * even Perl, to call the generated services. One drawback of SOAP is that it is
 * slow because it needs to serialize all calls into a text format (XML).
 * </p>
 *
 * <p>
 * You can see a list of services at
 * http://localhost:8080/api/secure/axis. Set the property
 * <b>axis.servlet.hosts.allowed</b> in portal.properties to configure
 * security.
 * </p>
 *
 * <p>
 * The SOAP utility is only generated for remote services.
 * </p>
 *
 * @author    Brian Wing Shun Chan
 * @see       ImpresaContentServiceHttp
 * @see       pt.impresa.liferay.content.service.ImpresaContentServiceUtil
 * @generated
 */
public class ImpresaContentServiceSoap {
    private static Log _log = LogFactoryUtil.getLog(ImpresaContentServiceSoap.class);

    public static java.lang.String getContentsJSON(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws RemoteException {
        try {
            java.lang.String returnValue = ImpresaContentServiceUtil.getContentsJSON(publication,
                    section, includeSubsections, groupId, maxArticles,
                    contentTypes, fields, page, searchKeywords, relations);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static pt.impresa.liferay.content.service.model.GetContentResponse getContents(
        java.lang.String publication, java.lang.String section,
        boolean includeSubsections, java.lang.String groupId, int maxArticles,
        java.util.List<java.lang.String> contentTypes,
        java.util.List<java.lang.String> fields, java.lang.Integer page,
        java.lang.String searchKeywords,
        java.util.List<java.lang.String> relations) throws RemoteException {
        try {
            pt.impresa.liferay.content.service.model.GetContentResponse returnValue =
                ImpresaContentServiceUtil.getContents(publication, section,
                    includeSubsections, groupId, maxArticles, contentTypes,
                    fields, page, searchKeywords, relations);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }

    public static java.lang.String getNewsletterURl(java.lang.String code)
        throws RemoteException {
        try {
            java.lang.String returnValue = ImpresaContentServiceUtil.getNewsletterURl(code);

            return returnValue;
        } catch (Exception e) {
            _log.error(e, e);

            throw new RemoteException(e.getMessage());
        }
    }
}
