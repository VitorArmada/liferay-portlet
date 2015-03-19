package pt.impresa.liferay.content.search;

import java.io.IOException;
import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import pt.impresa.date.DateUtils;
import pt.impresa.liferay.content.ServiceContentPortletConstants;
import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;
import pt.impresa.liferay.content.service.model.GetContentResponse;
import pt.impresa.liferay.content.service.model.ImpresaContent;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants;
import pt.impresa.liferay.content.utils.ContentOpenSearchUtils;
import pt.impresa.liferay.content.utils.ImpresaContentUtils;
import pt.impresa.liferay.exception.CustomFieldNotFoundException;
import pt.impresa.liferay.utils.ImpresaLiferayUtils;
import pt.impresa.log.ILog;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.BaseOpenSearchImpl;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.uuid.PortalUUIDUtil;

public class ContentOpenSearch extends BaseOpenSearchImpl {
	
	private static final String TITLE = "Resultados de Pesquisa";
	
	private static final String IMAGE_VERSION = "?v=w220h138";
	
	
	@Override
	public String search(HttpServletRequest request, long groupId, long userId, String keywords, int startPage, int itemsPerPage, String format) throws SearchException {
		String result = null;	
		try {	
			String publication = ImpresaContentUtils.getCurrentPublicationName(request);
			String rootSectionUniqueName = ImpresaContentUtils.getRootSectionUniqueName(request);
				
			GetContentResponse contentResponse = ImpresaContentLocalServiceUtil.getContents
						( publication
						, rootSectionUniqueName
						, true
						, null
						, itemsPerPage
						, Collections.singletonList(ImpresaContentConstants.TYPE.NEWS)
						, null
						, startPage
						, keywords
						, null);
			result = ContentOpenSearchUtils.getStringFromDoc(createDoc(contentResponse, ImpresaLiferayUtils.getPortalURL(request), publication, keywords, startPage, itemsPerPage));
		
		} catch (IOException ex) {
			ILog.get().error(ex);
		} catch (ParserConfigurationException e) {
			ILog.get().error(e);
		} catch (PortalException e) {
			ILog.get().error(e);
		} catch (SystemException e) {
			ILog.get().error(e);
		} catch (CustomFieldNotFoundException ex) {
			ILog.get().error(ex);
		}
	
		return result;
	}
	
	private Document createDoc(GetContentResponse contentResponse, String portalURL, String publication, String keywords, int startPage, int itemsPerPage) throws ParserConfigurationException{
		Document doc =  DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		
		Element root = ContentOpenSearchUtils.createNamespace(doc, "feed");
		doc.appendChild(root);
		ContentOpenSearchUtils.setNamespace(ContentOpenSearchUtils.OS_NAMESPACE, root);
		
		ContentOpenSearchUtils.addElement(doc, root, "title", TITLE, ContentOpenSearchUtils.NO_NAMESPACE);
		ContentOpenSearchUtils.addElement(doc, root, "updated", DateUtils.DATETIME.toISO8601(new Date()), ContentOpenSearchUtils.NO_NAMESPACE);
		
		Element author = ContentOpenSearchUtils.createElement(doc, root, "author", ContentOpenSearchUtils.NO_NAMESPACE);
		ContentOpenSearchUtils.addElement(doc, author, "name", portalURL, ContentOpenSearchUtils.NO_NAMESPACE);
		
		ContentOpenSearchUtils.addElement(doc, root, "id", "urn:uuid:" + PortalUUIDUtil.generate(), ContentOpenSearchUtils.NO_NAMESPACE);
		int total = contentResponse.getPagination().getTotalPages()*Math.min(itemsPerPage,contentResponse.getContent().size());
		ContentOpenSearchUtils.addElement(doc, root, "totalResults", String.valueOf(total), ContentOpenSearchUtils.OS_NAMESPACE);
		int startIndex = contentResponse.getPagination().getCurrentPage()*itemsPerPage;
		ContentOpenSearchUtils.addElement(doc, root, "startIndex", String.valueOf(startIndex), ContentOpenSearchUtils.OS_NAMESPACE);
		ContentOpenSearchUtils.addElement(doc, root, "itemsPerPage", String.valueOf(itemsPerPage), ContentOpenSearchUtils.OS_NAMESPACE);
		
		Element query = ContentOpenSearchUtils.createElement(doc, root, "Query", ContentOpenSearchUtils.OS_NAMESPACE);
		query.setAttribute("role", "request");
		query.setAttribute("searchTerms", keywords);
		query.setAttribute("startPage", String.valueOf(startPage));	
		
		if (contentResponse.getContent() != null) {
			for (ImpresaContent content : contentResponse.getContent()) {
				Element entry = ContentOpenSearchUtils.createElement(doc, root, "entry", ContentOpenSearchUtils.NO_NAMESPACE);	
				ContentOpenSearchUtils.addElement(doc, entry, "title", content.getTitle(), ContentOpenSearchUtils.NO_NAMESPACE);	
				
				Element entryLink = ContentOpenSearchUtils.createElement(doc, entry, "link", ContentOpenSearchUtils.NO_NAMESPACE);
				entryLink.setAttribute("href", content.getArticlePageUrl());				
				
				ContentOpenSearchUtils.addElement(doc, entry, "id", "urn:uuid:" + PortalUUIDUtil.generate(), ContentOpenSearchUtils.NO_NAMESPACE);
				ContentOpenSearchUtils.addElement(doc, entry, "updated", content.getPublishedDate(), ContentOpenSearchUtils.NO_NAMESPACE);
				
				ContentOpenSearchUtils.addElement(doc, entry, "logo", ServiceContentPortletConstants.SEARCH.CDN_URL+publication+"/"+content.getImageSrc()+IMAGE_VERSION, 
						ContentOpenSearchUtils.NO_NAMESPACE);		
				
				ContentOpenSearchUtils.addElement(doc, entry, "section", content.getSectionName(), ContentOpenSearchUtils.NO_NAMESPACE);
				
				ContentOpenSearchUtils.addElement(doc, entry, "pubDate", 
						ContentOpenSearchUtils.getSearchDateFormat(DateUtils.ISO8601.getDate(content.getPublishedDate()))+", "+ ContentOpenSearchUtils.getPublishedTimeFromDateTime((content.getPublishedDate())), 
						ContentOpenSearchUtils.NO_NAMESPACE);
				
				ContentOpenSearchUtils.addElement(doc, entry, "summary", content.getLead(), ContentOpenSearchUtils.NO_NAMESPACE);
				ContentOpenSearchUtils.addElement(doc, entry, "tags", "", ContentOpenSearchUtils.NO_NAMESPACE);
			} 
		}	
		return doc;
	}
	
	
}
