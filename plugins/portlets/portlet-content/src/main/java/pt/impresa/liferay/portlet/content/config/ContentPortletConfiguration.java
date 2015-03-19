package pt.impresa.liferay.portlet.content.config;

import java.util.LinkedList;
import java.util.List;
import pt.impresa.liferay.config.ImpresaConfiguration;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET;

public class ContentPortletConfiguration extends ImpresaConfiguration {
	
	public ContentPortletConfiguration() { }
	
	private List<FieldGroupConfiguration> fieldGroups;

	public List<FieldGroupConfiguration> getFieldGroups() {
		if(fieldGroups == null) {
			fieldGroups = new LinkedList<FieldGroupConfiguration>();
		}
		return fieldGroups;
	}

	public void setFieldGroups(List<FieldGroupConfiguration> fieldGroups) {
		this.fieldGroups = fieldGroups;
	}
    
    public void parseAndSetCheckBox(String key, String checkboxValue) {
        set(key, WIDGET.CHECKBOX_ON.equalsIgnoreCase(checkboxValue));
    }
	
}
