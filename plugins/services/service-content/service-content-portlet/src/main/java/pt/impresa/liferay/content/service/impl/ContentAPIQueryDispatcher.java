package pt.impresa.liferay.content.service.impl;

import pt.impresa.api.content.Content;
import pt.impresa.api.content.EPagination;
import pt.impresa.api.content.EQuery;
import pt.impresa.api.content.ERequest;
import pt.impresa.api.content.EResponse;
import pt.impresa.api.content.Exception_Exception;
import pt.impresa.impl.content.ContentServiceClient;
import pt.impresa.impl.content.ContentServiceClientUtils;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;

import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;

public class ContentAPIQueryDispatcher {

	private static final ILoggerInterface logger = ILog.get(ContentAPIResultFetcher.class);
	private static Content impresaContentServiceClientPort = null;

	private synchronized static Content getImpresaContentServiceClientPort() throws MalformedURLException {
		if (impresaContentServiceClientPort == null) {
			ContentServiceClient impresaContentServiceClient = new ContentServiceClient();
			impresaContentServiceClientPort = impresaContentServiceClient.getPort();
		}
		return impresaContentServiceClientPort;
	}

	private static EResponse executeImpresaQuery(EQuery query) {
		EResponse response = null;

		ERequest request = new ERequest();
		request.setQuery(query);

		try {
			Content port = getImpresaContentServiceClientPort();
			long start = System.currentTimeMillis();
			response = port.request(request);
			long ellapsed = System.currentTimeMillis() - start;

			logger.debug("Content API Query took " + ellapsed + "ms");
			if (response != null && (response.getQuery() == null || response.getQuery().getOutcome() == null)) {
				response = null;
			}
		} catch (Exception_Exception e) {
			logger.error(e);
		} catch (Exception e) {
			logger.error(e);
			impresaContentServiceClientPort = null;
		}

		return response;
	}

	private static EQuery buildEQuery(String publication, String section, String articleId, String externalSystemId, boolean includeSubsections, String groupId, int maxArticles, List<String> contentTypes, List<String> fields, Integer page, String searchKeywords, boolean searchByUri, boolean getKeywords, boolean getHomeSection, List<String> relations, Integer depth) {

		EQuery query = new EQuery();

		// Add Content UID
		query.setContentUID(ContentServiceClientUtils.createUID
				( publication
				, section != null ? section : externalSystemId
				, groupId != null ? groupId : articleId
				, externalSystemId != null
				, searchByUri ) );

		// Add Fields to fetch
		if (fields == null || fields.isEmpty()) {
			fields = ImpresaContentConstants.FIELD.DEFAULT.DETAILS;
		}
		String[] fieldsArr = new String[fields.size()];
		ContentServiceClientUtils.addFields(query, fields.toArray(fieldsArr));

		// Add Content Types to fetch
		if (contentTypes == null || contentTypes.isEmpty()) {
			contentTypes = ImpresaContentConstants.TYPE.DEFAULT.GENERAL_TYPE;
		}
		String[] contentTypesArr = new String[contentTypes.size()];
		ContentServiceClientUtils.setContentTypes(query, contentTypes.toArray(contentTypesArr));

		// Max Articles
		query.setLimit(maxArticles);

		// Include Subsections
		query.setIncludeChildren(includeSubsections);

		// Search
		query.setTextSearch(searchKeywords);

		// Pagination
		if (page != null) {
			EPagination pagination = new EPagination();
			pagination.setPageIndex(page);
			pagination.setPageLimit(maxArticles);
			query.setPagination(pagination);
		}
		query.setDepth(depth);

		// Keywords
		query.setGetKeywords(getKeywords);

		// Home Section
		query.setGetHomeSection(getHomeSection);

		// Relations
		if (relations == null || relations.isEmpty()) {
			relations = Arrays.asList(ImpresaContentConstants.RELATION.TEASERREL, ImpresaContentConstants.RELATION.PICTUREREL, ImpresaContentConstants.RELATION.PICTUREREL_1, ImpresaContentConstants.RELATION.PICTUREREL_2);
		}
		String[] relationsArr = new String[relations.size()];
		ContentServiceClientUtils.setRelations(query, relations.toArray(relationsArr));

		return query;
	}

	public static EResponse getContents(String publication, String section, boolean includeSubsections, String groupId, int maxArticles, Integer page, String textSearch, List<String> fields, List<String> contentTypes, List<String> relations) {

		// Set default fields for a common listing
		if (fields == null) {
			fields = ImpresaContentConstants.FIELD.DEFAULT.LIST;
		}

		EQuery contentDetailsQuery = buildEQuery(publication, section, null, null, includeSubsections, groupId, maxArticles, contentTypes, fields, page, textSearch, false, true, true, relations, 1);

		EResponse response = executeImpresaQuery(contentDetailsQuery);

		return response;
	}

	public static EResponse getContentDetails(String publication, String articleId, String externalSystemId, boolean searchByUri, List<String> fields, List<String> contentTypes, List<String> relations) {

		// Set default fields for a common listing
		if (fields == null) {
			fields = ImpresaContentConstants.FIELD.DEFAULT.DETAILS;
		}

		EQuery contentDetailsQuery = buildEQuery(publication, null, articleId, externalSystemId, false, null, 1, contentTypes, fields, null, null, searchByUri, true, true, relations, 1);

		EResponse response = executeImpresaQuery(contentDetailsQuery);

		return response;
	}

	public static EResponse getRelatedContents(String publication, String articleId, boolean searchByUri, Integer page, int maxArticles, List<String> fields, List<String> contentTypes, List<String> relations) {

		// Set default fields for a common listing
		if (fields == null) {
			fields = ImpresaContentConstants.FIELD.DEFAULT.LIST;
		}

		EQuery relatedContentsQuery = buildEQuery(publication, null, articleId, null, false, null, maxArticles, contentTypes, fields, page, null, true, true, true, relations, 2);

		EResponse response = executeImpresaQuery(relatedContentsQuery);

		return response;

	}

}
