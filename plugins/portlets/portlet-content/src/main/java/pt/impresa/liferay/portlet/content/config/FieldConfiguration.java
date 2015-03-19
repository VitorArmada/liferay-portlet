package pt.impresa.liferay.portlet.content.config;

public class FieldConfiguration {

	private String fieldTemplate;
	private String fieldName;
	private String cssClass;
	private String imageVersion;
    private String dateFormat;

	public String getFieldTemplate() {
		return fieldTemplate;
	}

	public void setFieldTemplate(String fieldTemplate) {
		this.fieldTemplate = fieldTemplate;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getImageVersion() {
		return imageVersion;
	}

	public void setImageVersion(String imageVersion) {
		this.imageVersion = imageVersion;
	}

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }
	
}
