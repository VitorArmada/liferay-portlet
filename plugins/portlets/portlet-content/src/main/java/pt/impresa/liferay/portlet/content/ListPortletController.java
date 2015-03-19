package pt.impresa.liferay.portlet.content;

import java.io.IOException;
import java.util.List;
import javax.portlet.PortletException;
import javax.portlet.PortletPreferences;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.servlet.http.HttpServletRequest;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.CONTENT.DEFAULT;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.GENERAL;
import pt.impresa.liferay.config.ImpresaConfigurationConstants.WIDGET.LIST.PAGINATION;
import pt.impresa.liferay.content.service.ImpresaContentLocalServiceUtil;
import pt.impresa.liferay.content.service.model.GetContentResponse;
import pt.impresa.liferay.content.service.model.ImpresaContent;
import pt.impresa.liferay.content.service.model.ImpresaContentConstants;
import pt.impresa.liferay.content.utils.ImpresaContentUtils;
import pt.impresa.liferay.portlet.content.config.ContentPortletConfiguration;
import pt.impresa.log.ILog;
import pt.impresa.log.ILoggerInterface;
import pt.impresa.serialization.JSONSerializer;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.model.Group;
import com.liferay.portal.model.Layout;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.PortletPreferencesFactoryUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;
import pt.impresa.liferay.config.ImpresaConfigurationConstants;
import pt.impresa.liferay.exception.CustomFieldNotFoundException;

public class ListPortletController extends MVCPortlet {
    
    private static final ILoggerInterface logger = ILog.get(ListPortletController.class);

    @Override
    public void render(RenderRequest request, RenderResponse response) throws PortletException, IOException {
        
        try {
            
            PortletPreferences preferences = request.getPreferences();
			
            String portletResource = ParamUtil.getString(request, "portletResource");
            if (Validator.isNotNull(portletResource)) {
                preferences = PortletPreferencesFactoryUtil.getPortletSetup(request, portletResource);
            }
            
            
            ContentPortletConfiguration config;
            String configStr = preferences.getValue(WIDGET.CONFIG_JSON, null);
            if(configStr != null) {
                
                config = JSONSerializer.fromJSON(configStr, ContentPortletConfiguration.class);
                
                if(config != null) {
					
					String contentJSON = (String)request.getAttribute(CONTENT.IMPRESA_CONTENT);
					String contentId = (String)request.getAttribute(CONTENT.IMPRESA_CONTENT_ID);
					
					//Find out if the portlet is to be displayed or not
					String display = config.getString(GENERAL.DISPLAY,GENERAL.DISPLAY_ALWAYS);
					if( ( contentJSON != null && !display.equalsIgnoreCase(GENERAL.DISPLAY_SECTION_PAGE) ) || 
						( contentJSON == null && !display.equalsIgnoreCase(GENERAL.DISPLAY_ARTICLE_PAGE) ) ) {
						
						//Find out what type of widget we want to display
						String contentSource = config.getString(CONTENT.CONTENT_SOURCE, CONTENT.CONTENT_SECTION);
						if(contentSource != null) {
							
							//For article details we dont need nothing
                            String publicationName = config.getString(CONTENT.PUBLICATION);
                            if(publicationName==null||publicationName.trim().isEmpty()){
                            	publicationName = ImpresaContentUtils.getCurrentPublicationName(PortalUtil.getHttpServletRequest(request));
                            }
                            String section = config.getString(CONTENT.SECTION);
                            List<String> contentTypes = config.getList(CONTENT.CONTENT_TYPE);
                            Integer maxArticles = config.getInt(CONTENT.MAX_ARTICLES, CONTENT.DEFAULT.MAX_ARTICLES);
                            Boolean includeSubSections = config.getBoolean(CONTENT.INCLUDE_SUBSECTIONS, CONTENT.DEFAULT.INCLUDE_SUBSECTIONS);
                            String groupId = config.getString(CONTENT.GROUP_ID);
                            Boolean usePagination = config.getBoolean(PAGINATION.SHOW_BOTTOM_PAGINATION, false);
                            Integer page = ParamUtil.getInteger(request, PAGINATION.CURRENT_PAGE, DEFAULT.CURRENT_PAGE);

                            if(contentSource.equalsIgnoreCase(CONTENT.CONTENT_RELATED)){
                                //Related articles
                                contentJSON = ImpresaContentLocalServiceUtil.getRelatedContentsJSON
                                        ( publicationName
                                        , contentId
                                        , maxArticles
                                        , usePagination ? page : null
                                        , null
                                        , contentTypes != null && !contentTypes.isEmpty() ? contentTypes : null
                                        , ImpresaContentConstants.RELATION.STORY_RELATIONS );

                            } else if(contentSource.equalsIgnoreCase(CONTENT.CONTENT_SECTION)){
                                //Normal articles
                                contentJSON = ImpresaContentLocalServiceUtil.getContentsJSON( publicationName
                                        , section != null && ! section.isEmpty() ? section : null
                                        , includeSubSections
                                        , groupId != null && !groupId.isEmpty() ? groupId : null
                                        , maxArticles
                                        , contentTypes != null && !contentTypes.isEmpty() ? contentTypes : null
                                        , null
                                        , usePagination ? page : null
                                        , null
                                        , null );

							}
							
							//Publish render properties
							if(contentJSON != null) {
								GetContentResponse content = JSONSerializer.fromJSON(contentJSON, GetContentResponse.class);
								
								//Publish metatags in case of article details
								if(content.getContent() != null && !content.getContent().isEmpty()) {
									
									if(content.getContent().size() > maxArticles) {
										content.setContent(content.getContent().subList(0, maxArticles));
									}
									
									if(contentSource.equalsIgnoreCase(CONTENT.CONTENT_DETAIL)) {
										
										ThemeDisplay td = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
										if(td != null) {
											Layout l = td.getLayout();
											if(l !=  null) {
												Group g = GroupLocalServiceUtil.getGroup(l.getGroupId());
												String name = g.getName();
												
												HttpServletRequest httpServletRequest = PortalUtil.getOriginalServletRequest(PortalUtil.getHttpServletRequest(request));
												ImpresaContent article = content.getContent().get(0);
												
												StringBuilder sb = new StringBuilder(article.getTitle());
												sb.append(StringPool.SPACE);
												sb.append(StringPool.DASH);
												sb.append(StringPool.SPACE);
												sb.append(name);
												
												PortalUtil.setPageTitle(sb.toString(), httpServletRequest);
										
												httpServletRequest.setAttribute( ImpresaConfigurationConstants.ARTICLE.URL
														, PortalUtil.getCurrentCompleteURL(httpServletRequest) );
												//We don't need this meta tags shit to stop the site from loading so try catch in your head
												try {
													httpServletRequest.setAttribute( ImpresaConfigurationConstants.ARTICLE.IMAGE_URL
															, ImpresaContentUtils.getImageUrl( httpServletRequest, article.getImageSrc(), null) );
												} catch (PortalException ex) {
													logger.error( ex );
												} catch (SystemException ex) {
													logger.error( ex );
												} catch (CustomFieldNotFoundException ex) {
													logger.error( ex );
												}

												httpServletRequest.setAttribute( WebKeys.PAGE_DESCRIPTION, article.getLead() );
												httpServletRequest.setAttribute( WebKeys.PAGE_KEYWORDS, article.getKeywords() );
												httpServletRequest.setAttribute( WebKeys.PAGE_TITLE, article.getTitle() );
												
											}
										}
									}
								}
								
								request.setAttribute(WIDGET.WIDGET_CONFIGURATION, config);
								request.setAttribute(LIST.CONTENT, content);
								request.setAttribute(CONTENT.PUBLICATION_NAME, publicationName);
							}
						}
                    } else {
                        request.setAttribute(WIDGET.DISABLED, true);
                    }
				}
            }
            
        } catch (PortalException ex) {
            logger.error(ex);
        } catch (SystemException ex) {
            logger.error(ex);
        } catch (CustomFieldNotFoundException ex) {
			logger.error(ex);
		}
        
        super.render(request, response);
                    
    }
}
