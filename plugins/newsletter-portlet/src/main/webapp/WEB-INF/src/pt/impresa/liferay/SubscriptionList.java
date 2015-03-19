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
	
	private static final String SUBSCRIPTION_EDIT_URL = "/html/subscription/edit.jsp";
	
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
			e.printStackTrace();
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
	public void deleteSubscription(ActionRequest request, ActionResponse response) throws PortalException, SystemException{
		int id = ParamUtil.getInteger(request, "id");
		SubscriptionLocalServiceUtil.deleteSubscription(id);
		PortletSession portletSession = request.getPortletSession();
		processSearch(null, portletSession);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void multiDeleteSubscription(ActionRequest request, ActionResponse response) throws PortalException, SystemException{
		String[] ids = request.getParameterValues("rowIds");
		
		for (String id : ids)
			 SubscriptionLocalServiceUtil.deleteSubscription(Long.valueOf(id));
		
		PortletSession portletSession = request.getPortletSession();
		processSearch(null, portletSession);
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void editSubscription(ActionRequest request, ActionResponse response) throws PortalException, SystemException{
		int id = ParamUtil.getInteger(request, "id");
		pt.impresa.liferay.model.Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(id);
		request.setAttribute("object", subscription);
		response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
	}
	
	/**
	 * Faz a actualização na base de dados da subscrição seleccionada.
	 * @param request
	 * @param response
	 * @throws PortalException
	 * @throws SystemException
	 */
	public void updateSubscription(ActionRequest request, ActionResponse response) throws PortalException, SystemException{
		Long id = ParamUtil.getLong(request, "id");
		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		
		List<String> errors = ValidateSubscription.validate(name, email, id);
		pt.impresa.liferay.model.Subscription subscription = SubscriptionLocalServiceUtil.getSubscription(id);
		subscription.setName(name);
		subscription.setEmail(email);

		if(errors.isEmpty()){
			SubscriptionLocalServiceUtil.updateSubscription(subscription);
			SessionMessages.add(request, "subscription-success");
		}else{
			for (String error : errors) 
				SessionErrors.add(request, error);
			
			request.setAttribute("object", subscription);
			response.setRenderParameter("jspPage", SUBSCRIPTION_EDIT_URL);
		}
		
		PortletSession portletSession = request.getPortletSession();
		processSearch(null, portletSession);
	}
	
	
	public void uploadCsv(ActionRequest request, ActionResponse response) throws IOException, SystemException, PortalException {
		UploadPortletRequest uploadRequest = PortalUtil.getUploadPortletRequest(request);
		ThemeDisplay display = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		InputStream inputStream = uploadRequest.getFileAsStream("file");
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
					errorMsg = "Dados inválidos";
					valid = false;
					break;
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
		}else{
			request.setAttribute("error-msg", String.format("%s na linha %d", errorMsg, rowNumber));
			SessionErrors.add(request, "searchsubscription-error");
		}
		
		PortletSession portletSession = request.getPortletSession();
		processSearch(null, portletSession);
	}
	
	/**
	 * 
	 */
	@SuppressWarnings("unchecked")
	public void serveResource(ResourceRequest request, ResourceResponse response) throws IOException ,javax.portlet.PortletException {
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
        PrintWriter writer = response.getWriter();
        writer.print(content);
        writer.flush();
        writer.close();
	}
}
