package pt.impresa.liferay.content.utils;

import javax.servlet.http.HttpServletRequest;

import pt.impresa.liferay.utils.ImpresaLiferayUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.StringPool;
import java.net.URI;
import java.net.URISyntaxException;
import pt.impresa.liferay.exception.CustomFieldNotFoundException;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;

public class ImpresaContentUtils {

	private static final String PUBLICATION_UNIQUE_NAME = "publicationUniqueName";

	private static final String SECTION_UNIQUE_NAME = "rootSectionUniqueName";
	
	private static final String IMAGES_CDN_BASE_URL = "imagesCDNBaseUrl";
	
	private static final ILoggerInterface logger = ILog.get(ImpresaContentUtils.class);

	public static String getCurrentPublicationName(HttpServletRequest request) throws PortalException, SystemException, CustomFieldNotFoundException {
		return ImpresaLiferayUtils.getCustomFieldValue(ImpresaLiferayUtils.CustomFieldSource.SITE, request, PUBLICATION_UNIQUE_NAME);
	}
	
	public static String getRootSectionUniqueName(HttpServletRequest request) throws PortalException, SystemException, CustomFieldNotFoundException{
		return ImpresaLiferayUtils.getCustomFieldValue(ImpresaLiferayUtils.CustomFieldSource.SITE, request, SECTION_UNIQUE_NAME);
	}
	
	public static String getImagesCDNBaseUrl(HttpServletRequest request) throws PortalException, SystemException, CustomFieldNotFoundException {
		return ImpresaLiferayUtils.getCustomFieldValue(ImpresaLiferayUtils.CustomFieldSource.SITE, request, IMAGES_CDN_BASE_URL);
	}
	
	public static String getImageUrl(HttpServletRequest request, String imageUrl, String imageVersion) throws PortalException, SystemException, CustomFieldNotFoundException {
		String result = null;
		try {
			
			String scheme = null;
			String authority = null;
			try {
				
				String imagesCDNBaseURL = getImagesCDNBaseUrl(request);
				URI imagesCDNBaseURI = new URI(imagesCDNBaseURL);
				scheme = imagesCDNBaseURI.getScheme();
				authority = imagesCDNBaseURI.getAuthority();
			
			} catch (CustomFieldNotFoundException ex) {
				logger.error("Could not found images CDN Base Path. Site has no images");
			}
			
			String currentPublicationName = getCurrentPublicationName(request);
			StringBuilder pathBuilder = new StringBuilder(StringPool.SLASH);
			pathBuilder.append(currentPublicationName);
			pathBuilder.append(StringPool.SLASH);
			pathBuilder.append(imageUrl);
			String path = pathBuilder.toString();
			
			String query = null;
			if(imageVersion != null) {
				String imageVersionQueryParam = "v";
				StringBuilder queryBuilder = new StringBuilder(imageVersionQueryParam);
				queryBuilder.append(StringPool.EQUAL);
				queryBuilder.append(imageVersion);
				query = queryBuilder.toString();
			}

			URI imageVersionURI = new URI 
					( scheme
					, authority
					, path
					, query
					, null
					);
			
			result = imageVersionURI.toString();
			
		} catch (URISyntaxException ex) {
			ILog.get().error("Failed to fetch imageVersion URL : ", ex);
		}
		
		return result;
	}

}
