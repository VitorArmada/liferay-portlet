package pt.impresa.liferay.exception;

import com.liferay.portal.kernel.util.StringPool;
import pt.impresa.liferay.utils.ImpresaLiferayUtils;

public class CustomFieldNotFoundException extends Exception {

	public CustomFieldNotFoundException(ImpresaLiferayUtils.CustomFieldSource source, String fieldName) {
		super(String.format("Error getting customField %s from %s", fieldName, source != null ? source.toString() : StringPool.QUESTION));
	}
	
	public CustomFieldNotFoundException(String fieldName) {
		super(String.format("Error getting customField %s from %s", fieldName));
	}
	
}
