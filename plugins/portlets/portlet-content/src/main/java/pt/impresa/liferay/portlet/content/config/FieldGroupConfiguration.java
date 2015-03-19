package pt.impresa.liferay.portlet.content.config;

import java.util.List;

/**
 *
 * @author madoke
 */
public class FieldGroupConfiguration {
	
	private List<FieldConfiguration> fields;
	private String cssClass;

	public List<FieldConfiguration> getFields() {
		return fields;
	}

	public void setFields(List<FieldConfiguration> fields) {
		this.fields = fields;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}
	
}
