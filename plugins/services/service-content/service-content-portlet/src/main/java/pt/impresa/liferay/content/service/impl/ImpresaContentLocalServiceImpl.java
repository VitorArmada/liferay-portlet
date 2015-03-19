package pt.impresa.liferay.content.service.impl;

import java.io.IOException;
import java.util.List;

import pt.impresa.api.content.EResponse;
import pt.impresa.liferay.content.service.base.ImpresaContentLocalServiceBaseImpl;
import pt.impresa.liferay.content.service.model.GetContentResponse;
import pt.impresa.serialization.JSONSerializer;

/**
 * The implementation of the impresa content local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link pt.impresa.liferay.content.service.ImpresaContentLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see pt.impresa.liferay.content.service.base.ImpresaContentLocalServiceBaseImpl
 * @see pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil
 */
public class ImpresaContentLocalServiceImpl
extends ImpresaContentLocalServiceBaseImpl {
    /*
     * NOTE FOR DEVELOPERS:
     *
     * Never reference this interface directly. Always use {@link pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil} to access the impresa content local service.
     */
    
	/*********************************
	 * GET CONTENTS
	 *********************************/
	
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
        
		return JSONSerializer.toJSON 
				( getContents
					( publication
					, section
					, includeSubsections
					, groupId
					, maxArticles
					, contentTypes
					, fields
					, page
					, searchKeywords
					, relations) );

    }
	
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
	
	/*********************************
	 * GET CONTENT DETAILS
	 *********************************/
	
	public String getExternalContentDetailsJSON
			( String publication
			, String articleId
			, String externalSystemId
			, List<String> contentTypes
			, List<String> fields
			, List<String> relations ) throws IOException {
	
		String jsonResult = null;
		
		EResponse getContentsResponse = ContentAPIQueryDispatcher.getContentDetails
				( publication
				, articleId
				, externalSystemId
				, false
				, fields
				, contentTypes
				, relations);
				
		if(getContentsResponse != null) {	
			GetContentResponse result = new GetContentResponse();
			
			result.setContent(
					ContentAPIResultFetcher.packageImpresaContents(getContentsResponse.getQuery().getOutcome()));
			
			jsonResult = JSONSerializer.toJSON(result);
		}
		
        return jsonResult;
	
	} 
	
	public String getContentDetailsJSON
			( String publication
            , String articleId
            , List<String> contentTypes
            , List<String> fields
			, List<String> relations ) throws IOException {
        
		String jsonResult = null;
        
		EResponse getContentsResponse = ContentAPIQueryDispatcher.getContentDetails
				( publication
				, articleId
				, null
				, true
				, fields
				, contentTypes
				, relations);
				
		if(getContentsResponse != null) {	
			GetContentResponse result = new GetContentResponse();
			
			result.setContent(
					ContentAPIResultFetcher.packageImpresaContents(getContentsResponse.getQuery().getOutcome()));
			result.setPagination(
					ContentAPIResultFetcher.packageImpresaPagination(getContentsResponse.getQuery().getPagination()));
			
			jsonResult = JSONSerializer.toJSON(result);
		}
		
        return jsonResult;
    }
    
	/*********************************
	 * GET RELATED CONTENT
	 *********************************/
	
	public String getRelatedContentsJSON
			( String publication
			, String articleId
			, Integer maxArticles
			, Integer page
			, List<String> fields
			, List<String> contentTypes
			, List<String> relations ) throws IOException {
		
		String jsonResult = null;
        
		EResponse getContentsResponse = ContentAPIQueryDispatcher.getRelatedContents
				( publication
				, articleId
				, true
				, null //page
				, 1
				, fields
				, contentTypes
				, relations );
				
		if(getContentsResponse != null) {	
			GetContentResponse result = new GetContentResponse();
			
			result.setContent
					( ContentAPIResultFetcher.packageImpresaContents
						( ContentAPIResultFetcher.getRelatedArticles
							( getContentsResponse, maxArticles ) ) );
			result.setPagination(
					ContentAPIResultFetcher.packageImpresaPagination(getContentsResponse.getQuery().getPagination()));
			
			jsonResult = JSONSerializer.toJSON(result);
		}
		
        return jsonResult;
	
	}
	
   
}
