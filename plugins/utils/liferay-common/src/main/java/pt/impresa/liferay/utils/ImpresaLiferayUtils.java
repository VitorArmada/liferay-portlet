package pt.impresa.liferay.utils;

import java.io.Serializable;

import javax.servlet.http.HttpServletRequest;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import pt.impresa.liferay.exception.CustomFieldNotFoundException;

public class ImpresaLiferayUtils {
	
	public static enum CustomFieldSource {
		SITE,
		PAGE
	}
	
	/**
	 * Search for a custom field in the following order: Page -> SITE
	 */
	public static String getCustomFieldValue(HttpServletRequest request, String fieldName) throws PortalException, SystemException, CustomFieldNotFoundException {
		String customFieldValue;
		try {
			customFieldValue = getCustomFieldValue(CustomFieldSource.PAGE, request, fieldName);
		} catch (CustomFieldNotFoundException ex) {
			customFieldValue = getCustomFieldValue(CustomFieldSource.SITE, request, fieldName);
		}
		return customFieldValue;
	}
	
	/**
	 * Search for a custom field given the source
	 */
	public static String getCustomFieldValue(CustomFieldSource source, HttpServletRequest request, String fieldName) throws PortalException, SystemException, CustomFieldNotFoundException {
		String value;
		switch(source) {
			case PAGE :
				value = getCustomFieldValueFromPage(request, fieldName);
				break;
			case SITE :
			default:
				value = getCustomFieldValueFromSite(request, fieldName);
				break;
		}
		if(value == null) {
			throw new CustomFieldNotFoundException(source, fieldName);
		}
		return value;
	}
	
	private static String getCustomFieldValueFromSite(HttpServletRequest request, String fieldName) throws PortalException, SystemException {
		String result = null;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Serializable got = themeDisplay.getLayout().getGroup().getExpandoBridge().getAttribute(fieldName);
		if (got != null) {
			result = got.toString();
		}
		return result;
	}
	
	private static String getCustomFieldValueFromPage(HttpServletRequest request, String fieldName) throws PortalException, SystemException {
		String result = null;
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		Serializable got = themeDisplay.getLayout().getExpandoBridge().getAttribute(fieldName);
		if (got != null) {
			result = got.toString();
		}
		return result;
	}

	public static String getPortalURL(HttpServletRequest request) {
		ThemeDisplay themeDisplay = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		return themeDisplay.getPortalURL();
	}

}
