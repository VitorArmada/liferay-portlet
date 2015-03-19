<%@ page import="java.util.List"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.FieldConfiguration"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.FieldGroupConfiguration"%>
<%@ page import="pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.FIELDS"%>
<%@ page import="pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET"%>
<%@ page import="com.liferay.portal.kernel.util.Constants" %>

<%@ taglib uri="http://java.sun.com/portlet" prefix="portlet" %>
<%@ taglib uri="http://liferay.com/tld/portlet" prefix="liferay-portlet" %>
<%@ taglib uri="http://liferay.com/tld/ui" prefix="liferay-ui" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://liferay.com/tld/aui" prefix="aui" %>

<portlet:defineObjects/>

<%
    ContentPortletConfiguration widgetConfiguration
            = (ContentPortletConfiguration) request.getAttribute(WIDGET.CONFIG);
%>

<form action="<liferay-portlet:actionURL portletConfiguration="true" />" method="post" name="<portlet:namespace />fields">
    <input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="<%= Constants.CMD %>"/>
    <input name="<portlet:namespace /><%= WIDGET.CONFIG_MODULE %>" type="hidden" value="<%= FIELDS.MODULE_NAME %>" />

    <div class="configContainer">

        <aui:fieldset id="group-fields">
        <%

            int i = 1;
            String fields = "";
            String imageVersion = "";
            String groupCssClass;
            String dateFormat = "";

            List<FieldGroupConfiguration> groups = widgetConfiguration.getFieldGroups();
            if(groups != null && !groups.isEmpty()) {

                for(FieldGroupConfiguration group : groups){

                    groupCssClass = group.getCssClass();

                    if(group.getFields() != null) {
                        for(FieldConfiguration field: group.getFields()) {
                            fields += field.getFieldTemplate() + ",";
                        }
                    }

                    //FIXME: This is supposed to be per field
                    if(group.getFields() != null) {
                        for(FieldConfiguration field: group.getFields()) {
                            imageVersion = field.getImageVersion();
                            dateFormat = field.getDateFormat();
                            break;
                        }
                    }

        %>

            <div class="lfr-form-row lfr-form-row-inline">
                <div class="row-fields">
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.CSS_CLASS + i %>"
                               value="<%= groupCssClass %>" label="<%= FIELDS.CSS_CLASS + i %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.FIELD_TEMPLATES + i %>"
                               value="<%= fields %>" label="<%= FIELDS.FIELD_TEMPLATES + i %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.IMAGE_VERSION + i %>"
                               value="<%= imageVersion %>" label="<%= FIELDS.IMAGE_VERSION + i %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.DATE_FORMAT + i%>"
                               value="<%= dateFormat %>" label="<%= FIELDS.DATE_FORMAT + i %>"/>
                </div>
            </div>

        <%
                ++i;
            }
        }
        else {
        %>

            <div class="lfr-form-row lfr-form-row-inline">
                <div class="row-fields">
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.CSS_CLASS + i %>"
                               value="" label="<%= FIELDS.CSS_CLASS %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.FIELD_TEMPLATES + i %>"
                               value="" label="<%= FIELDS.FIELD_TEMPLATES %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.IMAGE_VERSION + i %>"
                               value="" label="<%= FIELDS.IMAGE_VERSION %>"/>
                    <aui:input type="text" name="<%= FIELDS.GROUP + FIELDS.DATE_FORMAT + i %>"
                               value="" label="<%= FIELDS.DATE_FORMAT %>"/>
                </div>
            </div>
        <%
            }
        %>

        <%--<aui:input type="hidden" name="<%= FIELDS.NGROUPS %>" value="<%= i %>" />--%>
    </div>

    </aui:fieldset>

    <input type="button" class="saveForm" value="Save" onClick="submitForm(document.<portlet:namespace/>fields);"/>

</form>

<aui:script use="liferay-auto-fields">
    var autoFields = new Liferay.AutoFields(
    {
    contentBox: '#<portlet:namespace />group-fields',
    fieldIndexes: '<portlet:namespace /><%= FIELDS.NGROUPS %>'
    }
    ).render();
</aui:script>