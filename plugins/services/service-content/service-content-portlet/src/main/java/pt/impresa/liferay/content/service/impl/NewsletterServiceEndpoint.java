package pt.impresa.liferay.content.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

import pt.impresa.api.newsletter.INewsletter;
import pt.impresa.api.newsletter.Newsletter;
import pt.impresa.api.newsletter.NewsletterServiceEndpointService;
import pt.impresa.application.ApplicationSettings;
import pt.impresa.liferay.content.service.ImpresaLiferayContentServiceConstants;
import pt.impresa.log.ILog;
import pt.impresa.service.client.ImpresaServiceLocator;

public class NewsletterServiceEndpoint {
	
	private static Newsletter newsletterPort = null;
	private static final ApplicationSettings settings = new ApplicationSettings(); 
	
	
	private static synchronized Newsletter getNewsletterServicePort(){
		if (newsletterPort==null) {
			try {
				String newsletterServiceUrl = settings.getString(ImpresaLiferayContentServiceConstants.PROPERTY.SERVICE_NEWSLETTER_URL);
				ImpresaServiceLocator locator = new ImpresaServiceLocator();
				newsletterPort = locator.findService(newsletterServiceUrl, NewsletterServiceEndpointService.class).getNewsletterPort();
			} catch (InstantiationException e) {
				ILog.get().error(e);
			} catch (IllegalAccessException e) {
				ILog.get().error(e);
			} catch (NoSuchMethodException e) {
				ILog.get().error(e);
			} catch (MalformedURLException e) {
				ILog.get().error(e);
			} catch (InvocationTargetException e) {
				ILog.get().error(e);
			}
		}
		return newsletterPort;
	}
	
	public static String getNewsletterUrl(String code){
		List<INewsletter> newsletter = getNewsletterServicePort().getNewsletterFromCode(code);
		String url = null;
		if (newsletter != null && !newsletter.isEmpty()) {
			url = newsletter.get(0).getUrl();
		}
		return url;
	}
 	
}
