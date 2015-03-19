package pt.impresa.liferay.content.service.impl;

import java.io.IOException;
import java.util.List;

import pt.impresa.api.content.EResponse;
import pt.impresa.liferay.content.service.base.ImpresaContentServiceBaseImpl;
import pt.impresa.liferay.content.service.model.GetContentResponse;

import com.liferay.portal.kernel.jsonwebservice.JSONWebService;

/**
 * The implementation of the impresa content remote service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link pt.impresa.liferay.content.service.ImpresaContentService} interface.
 *
 * <p>
 * This is a remote service. Methods of this service are expected to have security checks based on the propagated JAAS credentials because this service can be accessed remotely.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see pt.impresa.liferay.content.service.base.ImpresaContentServiceBaseImpl
 * @see pt.impresa.liferay.content.service.ImpresaContentServiceUtil
 */
public class ImpresaContentServiceImpl extends ImpresaContentServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link pt.impresa.liferay.content.service.ImpresaContentServiceUtil} to access the impresa content remote service.
     */
	@JSONWebService(value = "get-contents-json", method = "GET")
	public String getContentsJSON
    ( String publication
    , String section
    , boolean includeSubsections
    , String groupId
    , int maxArticles
    , List<String> contentTypes
    , List<String> fields
    , Integer page
    , String searchKeywords
	, List<String> relations ) throws IOException {

return impresaContentLocalService.getContentsJSON(publication, section, includeSubsections, groupId, maxArticles, contentTypes, fields, page, searchKeywords, relations);

}
	@JSONWebService(value = "get-content-response", method = "GET")
	public GetContentResponse getContents
    ( String publication
    , String section
    , boolean includeSubsections
    , String groupId
    , int maxArticles
    , List<String> contentTypes
    , List<String> fields
    , Integer page
    , String searchKeywords
	, List<String> relations ) throws IOException {

GetContentResponse result = new GetContentResponse();
	
EResponse getContentsResponse = ContentAPIQueryDispatcher.getContents
		( publication
		, section
		, includeSubsections
		, groupId
		, maxArticles
		, page
		, searchKeywords
		, fields
		, contentTypes
		, relations);

if(getContentsResponse != null) {	
	
	result.setContent(
			ContentAPIResultFetcher.packageImpresaContents(getContentsResponse.getQuery().getOutcome()));
	result.setPagination(
			ContentAPIResultFetcher.packageImpresaPagination(getContentsResponse.getQuery().getPagination()));
	
}

return result;
}
	
	@JSONWebService(value = "get-newsletter-url", method = "GET")
	public String getNewsletterURl(String code){
		return NewsletterServiceEndpoint.getNewsletterUrl(code);
	}

}
