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

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * This class is used by SOAP remote services.
 *
 * @author    srtab
 * @generated
 */
public class SubscriptionSoap implements Serializable {
	public static SubscriptionSoap toSoapModel(Subscription model) {
		SubscriptionSoap soapModel = new SubscriptionSoap();

		soapModel.setSubscriptionId(model.getSubscriptionId());
		soapModel.setCompanyId(model.getCompanyId());
		soapModel.setGroupId(model.getGroupId());
		soapModel.setUserName(model.getUserName());
		soapModel.setCreateDate(model.getCreateDate());
		soapModel.setModifiedDate(model.getModifiedDate());
		soapModel.setName(model.getName());
		soapModel.setEmail(model.getEmail());

		return soapModel;
	}

	public static SubscriptionSoap[] toSoapModels(Subscription[] models) {
		SubscriptionSoap[] soapModels = new SubscriptionSoap[models.length];

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModel(models[i]);
		}

		return soapModels;
	}

	public static SubscriptionSoap[][] toSoapModels(Subscription[][] models) {
		SubscriptionSoap[][] soapModels = null;

		if (models.length > 0) {
			soapModels = new SubscriptionSoap[models.length][models[0].length];
		}
		else {
			soapModels = new SubscriptionSoap[0][0];
		}

		for (int i = 0; i < models.length; i++) {
			soapModels[i] = toSoapModels(models[i]);
		}

		return soapModels;
	}

	public static SubscriptionSoap[] toSoapModels(List<Subscription> models) {
		List<SubscriptionSoap> soapModels = new ArrayList<SubscriptionSoap>(models.size());

		for (Subscription model : models) {
			soapModels.add(toSoapModel(model));
		}

		return soapModels.toArray(new SubscriptionSoap[soapModels.size()]);
	}

	public SubscriptionSoap() {
	}

	public long getPrimaryKey() {
		return _subscriptionId;
	}

	public void setPrimaryKey(long pk) {
		setSubscriptionId(pk);
	}

	public long getSubscriptionId() {
		return _subscriptionId;
	}

	public void setSubscriptionId(long subscriptionId) {
		_subscriptionId = subscriptionId;
	}

	public long getCompanyId() {
		return _companyId;
	}

	public void setCompanyId(long companyId) {
		_companyId = companyId;
	}

	public long getGroupId() {
		return _groupId;
	}

	public void setGroupId(long groupId) {
		_groupId = groupId;
	}

	public String getUserName() {
		return _userName;
	}

	public void setUserName(String userName) {
		_userName = userName;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getModifiedDate() {
		return _modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		_modifiedDate = modifiedDate;
	}

	public String getName() {
		return _name;
	}

	public void setName(String name) {
		_name = name;
	}

	public String getEmail() {
		return _email;
	}

	public void setEmail(String email) {
		_email = email;
	}

	private long _subscriptionId;
	private long _companyId;
	private long _groupId;
	private String _userName;
	private Date _createDate;
	private Date _modifiedDate;
	private String _name;
	private String _email;
}