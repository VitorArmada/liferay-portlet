package pt.impresa.liferay;

import java.util.ArrayList;
import java.util.List;

import pt.impresa.liferay.service.SubscriptionLocalServiceUtil;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.util.Validator;

public class ValidateSubscription {
	
	private static final String SUBSCRIPTION_ERROR_NAME_EMPTY = "subscription-error-name-empty";
	private static final String SUBSCRIPTION_ERROR_EMAIL_EXISTS = "subscription-error-email-exists";
	private static final String SUBSCRIPTION_ERROR_EMAIL_INVALID = "subscription-error-email-invalid";
	private static final String SUBSCRIPTION_ERROR_EMAIL_EMPTY = "subscription-error-email-empty";

	public static List<String> validate(String name, String email) throws SystemException, PortalException {
		return ValidateSubscription.validate(name, email, null);
	}
	
	public static List<String> validate(String name, String email, Long id) throws SystemException, PortalException {
		List<String> errors = new ArrayList<String>();
		
		// email validation
		if( Validator.isNull(email) )
			errors.add(SUBSCRIPTION_ERROR_EMAIL_EMPTY);
		else{
			if( !Validator.isEmailAddress(email) )
				errors.add(SUBSCRIPTION_ERROR_EMAIL_INVALID);
			else{
				if(id==null){
					if(!SubscriptionLocalServiceUtil.findByEmail(email).isEmpty())
						errors.add(SUBSCRIPTION_ERROR_EMAIL_EXISTS);
				}else{
					pt.impresa.liferay.model.Subscription sub = SubscriptionLocalServiceUtil.getSubscription(id);
					if(!sub.getEmail().equals(email))
						if(!SubscriptionLocalServiceUtil.findByEmail(email).isEmpty())
							errors.add(SUBSCRIPTION_ERROR_EMAIL_EXISTS);
				}
			}
		}

		// name validation
		if( Validator.isNull(name) )
			errors.add(SUBSCRIPTION_ERROR_NAME_EMPTY);
		
		return errors;
	}
	
}
