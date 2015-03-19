package pt.impresa.liferay.portlet.content.config;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletConfig;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import pt.impresa.liferay.config.ImpresaConfigurationAction;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.FIELDS;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SCRIPTS;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.SOCIAL;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;
import pt.impresa.serialization.JSONSerializer;

public class ContentPortletConfigurationAction extends ImpresaConfigurationAction {
    
    private static final String KEY_CONFIG_JSP = "/html/config.jsp";
    private static final ILoggerInterface logger = ILog.get(ContentPortletConfigurationAction.class);
    
    private static final Map<String,Class<?>> socialNetworksFields = new HashMap<String,Class<?>>();
    private static final Map<String,Class<?>> paginationFields = new HashMap<String,Class<?>>();
    private static final Map<String,Class<?>> contentFields = new HashMap<String,Class<?>>();
    private static final Map<String,Class<?>> fieldsFields = new HashMap<String,Class<?>>();
    private static final Map<String,Class<?>> scriptsFields = new HashMap<String,Class<?>>();
    private static final Map<String,Class<?>> generalFields = new HashMap<String,Class<?>>();
        
				public ContentPortletConfigurationAction() {

								//Configure general fields
								generalFields.put(GENERAL.DISPLAY, String.class);
								generalFields.put(GENERAL.APPEND_TO_NEW_TARGET, Boolean.class);
								generalFields.put(GENERAL.NEW_TARGET_ID, String.class);
								generalFields.put(GENERAL.NEW_TARGET_ANIM_DURATION, Integer.class);
								generalFields.put(GENERAL.NEW_TARGET_ANIM_DELAY, Integer.class);

								//Configure content fields
								contentFields.put(CONTENT.PUBLICATION, String.class);
								contentFields.put(CONTENT.SECTION, String.class);
								contentFields.put(CONTENT.ARTICLE, String.class);
								contentFields.put(CONTENT.MAX_ARTICLES, Integer.class);
								contentFields.put(CONTENT.CONTENT_TYPE, List.class);
								contentFields.put(CONTENT.GROUP_ID, String.class);
								contentFields.put(CONTENT.INCLUDE_SUBSECTIONS, Boolean.class);
								contentFields.put(CONTENT.HIDE_ON_CONTENT, Boolean.class);
								contentFields.put(CONTENT.CONTENT_SOURCE, String.class);

								//Configure scripts fields
								scriptsFields.put(SCRIPTS.USE_CAROUSEL, Boolean.class);
								scriptsFields.put(SCRIPTS.CAROUSEL_ITEM_SELECTOR, String.class);
								scriptsFields.put(SCRIPTS.CAROUSEL_PARAMETERS, String.class);
								scriptsFields.put(SCRIPTS.USE_ELLIPSIS, Boolean.class);
								scriptsFields.put(SCRIPTS.ELLIPSIS_ITEM_SELECTOR, String.class);
								scriptsFields.put(SCRIPTS.USE_CUSTOMSCROLLBAR, Boolean.class);
								scriptsFields.put(SCRIPTS.CUSTOMSCROLLBAR_ITEM_SELECTOR, String.class);
								scriptsFields.put(SCRIPTS.CUSTOMSCROLLBAR_PARAMETERS, String.class);
								scriptsFields.put(SCRIPTS.USE_TOGGLEVIEW, Boolean.class);
								scriptsFields.put(SCRIPTS.TOGGLEVIEW_DEFAULTVIEW, String.class);
								scriptsFields.put(SCRIPTS.TOGGLEVIEW_ALTERNATIVEVIEW, String.class);
								scriptsFields.put(SCRIPTS.USE_MASONRY, Boolean.class);
								scriptsFields.put(SCRIPTS.MASONRY_ITEM_SELECTOR, String.class);
								scriptsFields.put(SCRIPTS.MASONRY_PARAMETERS, String.class);
								scriptsFields.put(SCRIPTS.USE_INFINITESCROLL, Boolean.class);
								scriptsFields.put(SCRIPTS.INFINITESCROLL_ITEM_SELECTOR, String.class);
								scriptsFields.put(SCRIPTS.INFINITESCROLL_ITEMS_TO_APPEND, String.class);
								scriptsFields.put(SCRIPTS.INFINITESCROLL_MORE_RESULTS_LABEL, String.class);
								scriptsFields.put(SCRIPTS.INFINITESCROLL_PARAMETERS, String.class);

								//Configure pagination fields
								paginationFields.put(PAGINATION.SHOW_BOTTOM_PAGINATION, Boolean.class);
								paginationFields.put(PAGINATION.CSS_CLASS, String.class);
								paginationFields.put(PAGINATION.SHOW_FIRST_PAGE_LINK, Boolean.class);
								paginationFields.put(PAGINATION.FIRST_PAGE_LABEL, String.class);
								paginationFields.put(PAGINATION.SHOW_PREVIOUS_PAGE_LINK, Boolean.class);
								paginationFields.put(PAGINATION.PREVIOUS_PAGE_LABEL, String.class);
								paginationFields.put(PAGINATION.SHOW_NEXT_PAGE_LINK, Boolean.class);
								paginationFields.put(PAGINATION.NEXT_PAGE_LABEL, String.class);
								paginationFields.put(PAGINATION.SHOW_LAST_PAGE_LINK, Boolean.class);
								paginationFields.put(PAGINATION.LAST_PAGE_LABEL, String.class);
								paginationFields.put(PAGINATION.SHOW_PAGINATION_SUMMARY, Boolean.class);
								paginationFields.put(PAGINATION.PAGINATION_SUMMARY_LABEL, String.class);
								paginationFields.put(PAGINATION.MAX_PAGE_LINKS, Integer.class);
								paginationFields.put(PAGINATION.SHOW_FOLLOW_LINK, Boolean.class);
								paginationFields.put(PAGINATION.FOLLOW_LINK, String.class);
								paginationFields.put(PAGINATION.FOLLOW_LINK_LABEL, String.class);

								//Configure fields fields
								fieldsFields.put(KEY_CONFIG_JSP, String.class);

								//Configure socialNetworks fields
								socialNetworksFields.put(SOCIAL.USE_FACEBOOK_LIKE, Boolean.class);
								socialNetworksFields.put(SOCIAL.PUBLISH_META_TAGS, Boolean.class);
								socialNetworksFields.put(SOCIAL.USE_TWITTER_TWEET, Boolean.class);
								socialNetworksFields.put(SOCIAL.USE_GOOGLEPLUS_PLUSONE, Boolean.class);
								socialNetworksFields.put(SOCIAL.USE_GOOGLEPLUS_SHARE, Boolean.class);
								socialNetworksFields.put(SOCIAL.FACEBOOK_LIKE_CONFIG, String.class);
								socialNetworksFields.put(SOCIAL.TWITTER_TWEET_CONFIG, String.class);
								socialNetworksFields.put(SOCIAL.GOOGLEPLUS_PLUSONE_CONFIG, String.class);
								socialNetworksFields.put(SOCIAL.GOOGLEPLUS_SHARE_CONFIG, String.class);

				}

				private void configure(String configModule, ActionRequest actionRequest, ContentPortletConfiguration configuration) {

								if(CONTENT.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configureContent(actionRequest, configuration);
								} else if(FIELDS.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configureFields(actionRequest, configuration);
								} else if(PAGINATION.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configurePagination(actionRequest, configuration);
								} else if(SCRIPTS.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configureScripts(actionRequest, configuration);
								} else if(SOCIAL.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configureSocialNetworks(actionRequest, configuration);
								} else if(GENERAL.MODULE_NAME.equalsIgnoreCase(configModule)) {
												configureGeneral(actionRequest, configuration);
								}

				}
    
    private void configureSocialNetworks(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
    
        configureFieldMap(socialNetworksFields, actionRequest, configuration);
        
    }
    
    private void configureGeneral(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
    
        configureFieldMap(generalFields, actionRequest, configuration);
        
    }
    
    private void configurePagination(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
        
        configureFieldMap(paginationFields, actionRequest, configuration);
       
    }
    
    private void configureFields(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
        
        String nGroupsStr = actionRequest.getParameter(FIELDS.NGROUPS);
	    String[] groupIndex = nGroupsStr.split(",");

        List<FieldGroupConfiguration> fieldGroups = new LinkedList<FieldGroupConfiguration>();
        
        for(String i : groupIndex) {

            String groupTemplates = actionRequest.getParameter(FIELDS.GROUP + FIELDS.FIELD_TEMPLATES + i);
            
            if(groupTemplates != null && !groupTemplates.isEmpty()) {
                
                FieldGroupConfiguration fieldGroup = new FieldGroupConfiguration();
                
                String groupCssClass = actionRequest.getParameter(FIELDS.GROUP + FIELDS.CSS_CLASS + i);
                if(groupCssClass != null) {
                    fieldGroup.setCssClass(groupCssClass);
                }
                
                //FIXME: This should be on a per field basis
                String imageVersion = actionRequest.getParameter(FIELDS.GROUP + FIELDS.IMAGE_VERSION + i);
                String dateFormat = actionRequest.getParameter(FIELDS.GROUP + FIELDS.DATE_FORMAT + i);
                
                List<FieldConfiguration> fields = new LinkedList<FieldConfiguration>();
                
                String [] templates = groupTemplates.split(",");
                for(String template:templates) {
                    FieldConfiguration field = new FieldConfiguration();
                    field.setFieldTemplate(template);
                    field.setImageVersion(imageVersion);
                    field.setDateFormat(dateFormat);
                    fields.add(field);
                }
                
                fieldGroup.setFields(fields);
                fieldGroups.add(fieldGroup);
            }
        }
        
        configuration.setFieldGroups(fieldGroups);
        
    }
    
    private void configureContent(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
        
        configureFieldMap(contentFields, actionRequest, configuration);
        
    }
    
    private void configureScripts(ActionRequest actionRequest, ContentPortletConfiguration configuration) {
       
        configureFieldMap(scriptsFields, actionRequest, configuration);
		
    }
    
    private void configureFieldMap(Map<String,Class<?>> fieldMap, ActionRequest actionRequest
            , ContentPortletConfiguration configuration) {
        
        if(fieldMap != null && !fieldMap.isEmpty()) {
            for(String key : fieldMap.keySet()) {
                String fieldValue = actionRequest.getParameter(key);
				Class<?> type = fieldMap.get(key);
				if(type == String.class) {
					configuration.parseAndSetString(key, fieldValue);
				} else if(type == Boolean.class) {
					configuration.parseAndSetCheckBox(key, fieldValue);
				} else if(type == Integer.class) {
					configuration.parseAndSetInt(key, fieldValue);
				} else if(type == List.class) {
					configuration.parseAndSetList(key, fieldValue);
				}
            }
        }
        
    }
    
    //////////////////////////////////
    // CONFIGURATION ACTION METHODS //
    //////////////////////////////////
    
    public void processAction(PortletConfig portletConfig, ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
        
        loadPreferences(actionRequest);
        
        ContentPortletConfiguration configuration = null;
        String configJSON = loadJSONConfig();
        if(configJSON == null) {
            configuration = new ContentPortletConfiguration();
        } else {
            configuration = JSONSerializer.fromJSON
                    (configJSON, ContentPortletConfiguration.class);
        }
        
        //Fetch Module Being Configured
        String configModule = getConfigModuleName(actionRequest);
        
        //Process configuration module
        configure(configModule, actionRequest, configuration);
        
        configJSON = configuration.toJSON();
        storeJSONConfig(configJSON);
        addSuccessMessage(portletConfig, actionRequest);
    }
    
    public String render(PortletConfig portletConfig, RenderRequest renderRequest, RenderResponse renderResponse) throws Exception {
        
        loadPreferences(renderRequest);
        
        //Fetch Configuration Object
        String configJSON = loadJSONConfig();
        if(configJSON != null) {
            ContentPortletConfiguration configuration =
                    JSONSerializer.fromJSON(configJSON, ContentPortletConfiguration.class);
            renderRequest.setAttribute(WIDGET.CONFIG, configuration);
        }
        
        return KEY_CONFIG_JSP;
    }

}
