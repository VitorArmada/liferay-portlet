package pt.impresa.liferay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import pt.impresa.liferay.model.impl.SubscriptionImpl;
import pt.impresa.liferay.service.SubscriptionLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class SubscriptionList
 */
public class SubscriptionList extends MVCPortlet {
	
	private static final String SUBSCRIPTIONLIST_SUCCESS_KEY = "subscriptionlist-success";
	private static final String SUBSCRIPTIONLIST_SEARCH_ERROR = "subscriptionlist-search-error";
	private static final String SUBSCRIPTIONLIST_ERROR_KEY = "subscriptionlist-error";
	private static final String SUBSCRIPTIONLIST_IMPORT_ERROR = "subscriptionlist-import-error";
	private static final String SUBSCRIPTIONLIST_EDIT_SUCCESS = "subscriptionlist-edit-success";
	private static final String SUBSCRIPTIONLIST_EDIT_ERROR = "subscriptionlist-edit-error";
	private static final String SUBSCRIPTIONLIST_SEARCH_UPDATE_ERROR = "subscriptionlist-search-update-error";
	private static final String SUBSCRIPTIONLIST_DELETE_SUCCESS = "subscriptionlist-delete-success";
	private static final String SUBSCRIPTIONLIST_DELETE_ERROR = "subscriptionlist-delete-error";
	private static final String SUBSCRIPTION_EDIT_URL = "/html/subscription/edit.jsp";
	private static final Object SUBSCRIPTIONLIST_EXPORT_ERROR = "subscriptionlist-export-error";
	private static final Object SUBSCRIPTIONLIST_IMPORT_SUCCESS = "subscriptionlist-import-success";
	
	/**
	 * 
	 * @param request
	 * @param response
	 */
	public void searchSubscription(ActionRequest request, ActionResponse response){
		String term = ParamUtil.getString(request, "term");
		PortletSession portletSession = request.getPortletSession();
		portletSession.setAttribute("term", term);
		try {
			processSearch(term, portletSession);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_SEARCH_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
	
	/**
	 * 
	 * @param term
	 * @param portletSession
	 * @throws SystemException
	 */
	public void processSearch(String term, PortletSession portletSession) throws SystemException {
		List<pt.impresa.liferay.model.Subscription> templist;
		if(term == null)
			templist = SubscriptionLocalServiceUtil.getSubscriptions(-1, -1);
		else
			templist = SubscriptionLocalServiceUtil.findByEmailLike(term);
		portletSession.setAttribute("list", templist);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void deleteSubscription(ActionRequest request, ActionResponse response) {
		int id = ParamUtil.getInteger(request, "id");
		
		try {
			SubscriptionLocalServiceUtil.deleteSubscription(id);
			request.setAttribute("success-msg", SUBSCRIPTIONLIST_DELETE_SUCCESS);
			SessionMessages.add(request, SUBSCRIPTIONLIST_SUCCESS_KEY);
		} catch (PortalException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_DELETE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_DELETE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		} 
		
		try {
			PortletSession portletSession = request.getPortletSession();
			processSearch(null, portletSession);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_SEARCH_UPDATE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void multiDeleteSubscription(ActionRequest request, ActionResponse response) {
		String[] ids = request.getParameterValues("rowIds");
		
		try {
			for (String id : ids)
				SubscriptionLocalServiceUtil.deleteSubscription(Long.valueOf(id));
			request.setAttribute("success-msg", SUBSCRIPTIONLIST_DELETE_SUCCESS);
			SessionMessages.add(request, SUBSCRIPTIONLIST_SUCCESS_KEY);
		} catch (NumberFormatException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_DELETE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		} catch (PortalException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_DELETE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_DELETE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
		
		PortletSession portletSession = request.getPortletSession();
		try {
			processSearch(null, portletSession);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_SEARCH_UPDATE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void editSubscription(ActionRequest request, ActionResponse response) {
		int id = ParamUtil.getInteger(request, "id");

		try {
			pt.impresa.liferay.model.Subscription subscription;
			subscription = SubscriptionLocalServiceUtil.getSubscription(id);
			request.setAttribute("object", subscription);
		} catch (PortalException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_EDIT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_EDIT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
		
		response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
	}
	
	/**
	 * Faz a actualização na base de dados da subscrição seleccionada.
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void updateSubscription(ActionRequest request, ActionResponse response) {
		Long id = ParamUtil.getLong(request, "id");
		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		
		try {
			List<String> errors = ValidateSubscription.validate(name, email, id);
			pt.impresa.liferay.model.Subscription subscription;
			subscription = SubscriptionLocalServiceUtil.getSubscription(id);
			subscription.setName(name);
			subscription.setEmail(email);
			
			request.setAttribute("object", subscription);
			
			if(errors.isEmpty()) {
				SubscriptionLocalServiceUtil.updateSubscription(subscription);
				request.setAttribute("success-msg", SUBSCRIPTIONLIST_EDIT_SUCCESS);
				SessionMessages.add(request, SUBSCRIPTIONLIST_SUCCESS_KEY);
				
			} else {
				for (String error : errors) 
					SessionErrors.add(request, error);
				response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
			}
		} catch (PortalException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_EDIT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
			response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_EDIT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
			response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
		}
		
		try {
			PortletSession portletSession = request.getPortletSession();
			processSearch(null, portletSession);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_SEARCH_UPDATE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
	
	
	public void uploadCsv(ActionRequest request, ActionResponse response) throws SystemException, PortalException {
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		ThemeDisplay display = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		InputStream inputStream;
		try {
			inputStream = uploadRequest.getFileAsStream("file");
			InputStreamReader reader = new InputStreamReader(inputStream);
			BufferedReader buff = new BufferedReader(reader);
			
			List<pt.impresa.liferay.model.Subscription> subscriptions = new ArrayList<pt.impresa.liferay.model.Subscription>();
			boolean valid = true;
			int rowNumber=0;
			String line;
			String errorMsg = null;
			
			while((line=buff.readLine())!=null){
				rowNumber++;
				String[] values = line.split(";");
				if ( values.length!=2 ){
					valid = false;
					errorMsg = "Formato inválido";
					break;
				}else{
					if (rowNumber == 1)
						continue;
					String name = values[0];
					String email = values[1];
					List<String> errors = ValidateSubscription.validate(name, email);
					if ( !errors.isEmpty() ){
						if (errors.contains("subscription-error-email-exists") )
							continue;
						else {
							errorMsg = "Dados inválidos";
							valid = false;
							break;
						}
					}else{
						pt.impresa.liferay.model.Subscription subscription = new SubscriptionImpl();
						subscription.setEmail(email);
						subscription.setName(name);
						subscription.setCompanyId(display.getCompanyId());
						subscription.setGroupId(display.getCompanyGroupId());
						subscriptions.add(subscription);
					}
				}			
			}
			buff.close();
			
			if ( valid ){
				for (pt.impresa.liferay.model.Subscription subscription : subscriptions) {
					SubscriptionLocalServiceUtil.addSubscription(subscription);
				}
				request.setAttribute("success-msg", SUBSCRIPTIONLIST_IMPORT_SUCCESS);
				SessionMessages.add(request, SUBSCRIPTIONLIST_SUCCESS_KEY);
			}else{
				request.setAttribute("error-msg", String.format("%s na linha %d", errorMsg, rowNumber));
				SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
			}
		} catch (IOException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_IMPORT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
		
		try {
			PortletSession portletSession = request.getPortletSession();
			processSearch(null, portletSession);
		} catch (SystemException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_SEARCH_UPDATE_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void serveResource(ResourceRequest request, ResourceResponse response) {
		PortletSession portletSession = request.getPortletSession();
		List<pt.impresa.liferay.model.Subscription> list = (List<pt.impresa.liferay.model.Subscription>) portletSession.getAttribute("list");
		
		StringBuilder sb = new StringBuilder();
		sb.append("Name;Email\n");
		for(pt.impresa.liferay.model.Subscription sub : list){
			sb.append(sub.getName());
			sb.append(';');
			sb.append(sub.getEmail());
			sb.append('\n');
		}
		String content = sb.toString();
        response.setContentType("text/csv");
        response.setProperty("Content-disposition", "attachment; filename=subscriptions.csv");
        PrintWriter writer;
		try {
			writer = response.getWriter();
			writer.print(content);
	        writer.flush();
	        writer.close();
		} catch (IOException e) {
			request.setAttribute("error-msg", SUBSCRIPTIONLIST_EXPORT_ERROR);
			SessionErrors.add(request, SUBSCRIPTIONLIST_ERROR_KEY);
		}
	}
}
