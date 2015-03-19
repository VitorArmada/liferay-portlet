/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package pt.impresa.liferay.model;

import com.liferay.portal.model.ModelWrapper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * This class is a wrapper for {@link Subscription}.
 * </p>
 *
 * @author    srtab
 * @see       Subscription
 * @generated
 */
public class SubscriptionWrapper implements Subscription,
	ModelWrapper<Subscription> {
	public SubscriptionWrapper(Subscription subscription) {
		_subscription = subscription;
	}

	public Class<?> getModelClass() {
		return Subscription.class;
	}

	public String getModelClassName() {
		return Subscription.class.getName();
	}

	public Map<String, Object> getModelAttributes() {
		Map<String, Object> attributes = new HashMap<String, Object>();

		attributes.put("subscriptionId", getSubscriptionId());
		attributes.put("companyId", getCompanyId());
		attributes.put("groupId", getGroupId());
		attributes.put("userName", getUserName());
		attributes.put("createDate", getCreateDate());
		attributes.put("modifiedDate", getModifiedDate());
		attributes.put("name", getName());
		attributes.put("email", getEmail());

		return attributes;
	}

	public void setModelAttributes(Map<String, Object> attributes) {
		Long subscriptionId = (Long)attributes.get("subscriptionId");

		if (subscriptionId != null) {
			setSubscriptionId(subscriptionId);
		}

		Long companyId = (Long)attributes.get("companyId");

		if (companyId != null) {
			setCompanyId(companyId);
		}

		Long groupId = (Long)attributes.get("groupId");

		if (groupId != null) {
			setGroupId(groupId);
		}

		String userName = (String)attributes.get("userName");

		if (userName != null) {
			setUserName(userName);
		}

		Date createDate = (Date)attributes.get("createDate");

		if (createDate != null) {
			setCreateDate(createDate);
		}

		Date modifiedDate = (Date)attributes.get("modifiedDate");

		if (modifiedDate != null) {
			setModifiedDate(modifiedDate);
		}

		String name = (String)attributes.get("name");

		if (name != null) {
			setName(name);
		}

		String email = (String)attributes.get("email");

		if (email != null) {
			setEmail(email);
		}
	}

	/**
	* Returns the primary key of this subscription.
	*
	* @return the primary key of this subscription
	*/
	public long getPrimaryKey() {
		return _subscription.getPrimaryKey();
	}

	/**
	* Sets the primary key of this subscription.
	*
	* @param primaryKey the primary key of this subscription
	*/
	public void setPrimaryKey(long primaryKey) {
		_subscription.setPrimaryKey(primaryKey);
	}

	/**
	* Returns the subscription ID of this subscription.
	*
	* @return the subscription ID of this subscription
	*/
	public long getSubscriptionId() {
		return _subscription.getSubscriptionId();
	}

	/**
	* Sets the subscription ID of this subscription.
	*
	* @param subscriptionId the subscription ID of this subscription
	*/
	public void setSubscriptionId(long subscriptionId) {
		_subscription.setSubscriptionId(subscriptionId);
	}

	/**
	* Returns the company ID of this subscription.
	*
	* @return the company ID of this subscription
	*/
	public long getCompanyId() {
		return _subscription.getCompanyId();
	}

	/**
	* Sets the company ID of this subscription.
	*
	* @param companyId the company ID of this subscription
	*/
	public void setCompanyId(long companyId) {
		_subscription.setCompanyId(companyId);
	}

	/**
	* Returns the group ID of this subscription.
	*
	* @return the group ID of this subscription
	*/
	public long getGroupId() {
		return _subscription.getGroupId();
	}

	/**
	* Sets the group ID of this subscription.
	*
	* @param groupId the group ID of this subscription
	*/
	public void setGroupId(long groupId) {
		_subscription.setGroupId(groupId);
	}

	/**
	* Returns the user name of this subscription.
	*
	* @return the user name of this subscription
	*/
	public java.lang.String getUserName() {
		return _subscription.getUserName();
	}

	/**
	* Sets the user name of this subscription.
	*
	* @param userName the user name of this subscription
	*/
	public void setUserName(java.lang.String userName) {
		_subscription.setUserName(userName);
	}

	/**
	* Returns the create date of this subscription.
	*
	* @return the create date of this subscription
	*/
	public java.util.Date getCreateDate() {
		return _subscription.getCreateDate();
	}

	/**
	* Sets the create date of this subscription.
	*
	* @param createDate the create date of this subscription
	*/
	public void setCreateDate(java.util.Date createDate) {
		_subscription.setCreateDate(createDate);
	}

	/**
	* Returns the modified date of this subscription.
	*
	* @return the modified date of this subscription
	*/
	public java.util.Date getModifiedDate() {
		return _subscription.getModifiedDate();
	}

	/**
	* Sets the modified date of this subscription.
	*
	* @param modifiedDate the modified date of this subscription
	*/
	public void setModifiedDate(java.util.Date modifiedDate) {
		_subscription.setModifiedDate(modifiedDate);
	}

	/**
	* Returns the name of this subscription.
	*
	* @return the name of this subscription
	*/
	public java.lang.String getName() {
		return _subscription.getName();
	}

	/**
	* Sets the name of this subscription.
	*
	* @param name the name of this subscription
	*/
	public void setName(java.lang.String name) {
		_subscription.setName(name);
	}

	/**
	* Returns the email of this subscription.
	*
	* @return the email of this subscription
	*/
	public java.lang.String getEmail() {
		return _subscription.getEmail();
	}

	/**
	* Sets the email of this subscription.
	*
	* @param email the email of this subscription
	*/
	public void setEmail(java.lang.String email) {
		_subscription.setEmail(email);
	}

	public boolean isNew() {
		return _subscription.isNew();
	}

	public void setNew(boolean n) {
		_subscription.setNew(n);
	}

	public boolean isCachedModel() {
		return _subscription.isCachedModel();
	}

	public void setCachedModel(boolean cachedModel) {
		_subscription.setCachedModel(cachedModel);
	}

	public boolean isEscapedModel() {
		return _subscription.isEscapedModel();
	}

	public java.io.Serializable getPrimaryKeyObj() {
		return _subscription.getPrimaryKeyObj();
	}

	public void setPrimaryKeyObj(java.io.Serializable primaryKeyObj) {
		_subscription.setPrimaryKeyObj(primaryKeyObj);
	}

	public com.liferay.portlet.expando.model.ExpandoBridge getExpandoBridge() {
		return _subscription.getExpandoBridge();
	}

	public void setExpandoBridgeAttributes(
		com.liferay.portal.service.ServiceContext serviceContext) {
		_subscription.setExpandoBridgeAttributes(serviceContext);
	}

	@Override
	public java.lang.Object clone() {
		return new SubscriptionWrapper((Subscription)_subscription.clone());
	}

	public int compareTo(pt.impresa.liferay.model.Subscription subscription) {
		return _subscription.compareTo(subscription);
	}

	@Override
	public int hashCode() {
		return _subscription.hashCode();
	}

	public com.liferay.portal.model.CacheModel<pt.impresa.liferay.model.Subscription> toCacheModel() {
		return _subscription.toCacheModel();
	}

	public pt.impresa.liferay.model.Subscription toEscapedModel() {
		return new SubscriptionWrapper(_subscription.toEscapedModel());
	}

	@Override
	public java.lang.String toString() {
		return _subscription.toString();
	}

	public java.lang.String toXmlString() {
		return _subscription.toXmlString();
	}

	public void persist()
		throws com.liferay.portal.kernel.exception.SystemException {
		_subscription.persist();
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedModel}
	 */
	public Subscription getWrappedSubscription() {
		return _subscription;
	}

	public Subscription getWrappedModel() {
		return _subscription;
	}

	public void resetOriginalValues() {
		_subscription.resetOriginalValues();
	}

	private Subscription _subscription;
}