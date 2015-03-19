package pt.impresa.liferay;

import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import pt.impresa.liferay.model.impl.SubscriptionImpl;
import pt.impresa.liferay.service.SubscriptionLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * Portlet implementation class Subscription
 */
public class Subscription extends MVCPortlet {
	
	private static final String SUBSCRIPTION_SUCCESS = "subscription-success";
	private static final String SUBSCRIPTION_ERROR = "subscription-error";

	public void addSubscription(ActionRequest request, ActionResponse response) throws PortalException {
		String name = ParamUtil.getString(request, "name");
		String email = ParamUtil.getString(request, "email");
		
		try {
			List<String> errors = ValidateSubscription.validate(name, email);
			
			if(errors.isEmpty()){
				SubscriptionImpl subscription = createSubscription(request, name, email);
				
				try {
					SubscriptionLocalServiceUtil.addSubscription(subscription);
					SessionMessages.add(request, SUBSCRIPTION_SUCCESS);
				} catch (SystemException e) {
					e.printStackTrace();
					SessionErrors.add(request, SUBSCRIPTION_ERROR, SUBSCRIPTION_ERROR);
				}
			}else{
				for (String error : errors) 
					SessionErrors.add(request, error);
			}
			
		} catch (SystemException e) {
			e.getStackTrace();
			SessionErrors.add(request, SUBSCRIPTION_ERROR, SUBSCRIPTION_ERROR);
		}
	}

	private SubscriptionImpl createSubscription(ActionRequest request, String name, String email) {
		
		ThemeDisplay display = (ThemeDisplay) request.getAttribute(WebKeys.THEME_DISPLAY);
		
		SubscriptionImpl subscription = new SubscriptionImpl();
		subscription.setName(name);
		subscription.setEmail(email);
		subscription.setCompanyId(display.getCompanyId());
		subscription.setGroupId(display.getCompanyGroupId());
		
		return subscription;
	}
	
}
