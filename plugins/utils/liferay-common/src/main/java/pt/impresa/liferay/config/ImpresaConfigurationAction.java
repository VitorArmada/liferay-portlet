package pt.impresa.liferay.config;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.portlet.ConfigurationAction;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import java.io.IOException;
import javax.portlet.ActionRequest;
import javax.portlet.PortletConfig;
import javax.portlet.PortletPreferences;
import javax.portlet.PortletRequest;
import javax.portlet.ReadOnlyException;
import javax.portlet.ValidatorException;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;

public abstract class ImpresaConfigurationAction implements ConfigurationAction {

	private static final String KEY_PORTLET_RESOURCE = "portletResource";
	private static final ILoggerInterface logger = ILog.get(ImpresaConfigurationAction.class);
	private PortletPreferences preferences;
	
	protected String loadJSONConfig() {
		return preferences.getValue(ImpresaConfigurationConstants.WIDGET.CONFIG_JSON, null);
	}
	
	protected void storeJSONConfig(String JSONConfig) {
		try {
			preferences.setValue(ImpresaConfigurationConstants.WIDGET.CONFIG_JSON, JSONConfig);
			preferences.store();
		} catch (ReadOnlyException ex) {
			logger.error(ex);
		} catch (IOException ex) {
			logger.error(ex);
		} catch (ValidatorException ex){
			logger.error(ex);
		}
	}
	
	protected String getConfigModuleName(ActionRequest ar) {
		return ar.getParameter(ImpresaConfigurationConstants.WIDGET.CONFIG_MODULE);
	}
	
	protected void addSuccessMessage(PortletConfig pc, ActionRequest ar) {
		SessionMessages.add(ar, "success");
        SessionMessages.add(ar, pc.getPortletName() + ".doConfigure");
	}
	
	protected void loadPreferences(PortletRequest pr) throws PortalException, SystemException {
		String portletResource = ParamUtil.getString(pr, KEY_PORTLET_RESOURCE);
		preferences = PortletPreferencesFactoryUtil.getPortletSetup(pr, portletResource);
	}
}
