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

package pt.impresa.liferay.model.impl;

import com.liferay.portal.kernel.util.StringBundler;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.model.CacheModel;

import pt.impresa.liferay.model.Subscription;

import java.io.Serializable;

import java.util.Date;

/**
 * The cache model class for representing Subscription in entity cache.
 *
 * @author srtab
 * @see Subscription
 * @generated
 */
public class SubscriptionCacheModel implements CacheModel<Subscription>,
	Serializable {
	@Override
	public String toString() {
		StringBundler sb = new StringBundler(17);

		sb.append("{subscriptionId=");
		sb.append(subscriptionId);
		sb.append(", companyId=");
		sb.append(companyId);
		sb.append(", groupId=");
		sb.append(groupId);
		sb.append(", userName=");
		sb.append(userName);
		sb.append(", createDate=");
		sb.append(createDate);
		sb.append(", modifiedDate=");
		sb.append(modifiedDate);
		sb.append(", name=");
		sb.append(name);
		sb.append(", email=");
		sb.append(email);
		sb.append("}");

		return sb.toString();
	}

	public Subscription toEntityModel() {
		SubscriptionImpl subscriptionImpl = new SubscriptionImpl();

		subscriptionImpl.setSubscriptionId(subscriptionId);
		subscriptionImpl.setCompanyId(companyId);
		subscriptionImpl.setGroupId(groupId);

		if (userName == null) {
			subscriptionImpl.setUserName(StringPool.BLANK);
		}
		else {
			subscriptionImpl.setUserName(userName);
		}

		if (createDate == Long.MIN_VALUE) {
			subscriptionImpl.setCreateDate(null);
		}
		else {
			subscriptionImpl.setCreateDate(new Date(createDate));
		}

		if (modifiedDate == Long.MIN_VALUE) {
			subscriptionImpl.setModifiedDate(null);
		}
		else {
			subscriptionImpl.setModifiedDate(new Date(modifiedDate));
		}

		if (name == null) {
			subscriptionImpl.setName(StringPool.BLANK);
		}
		else {
			subscriptionImpl.setName(name);
		}

		if (email == null) {
			subscriptionImpl.setEmail(StringPool.BLANK);
		}
		else {
			subscriptionImpl.setEmail(email);
		}

		subscriptionImpl.resetOriginalValues();

		return subscriptionImpl;
	}

	public long subscriptionId;
	public long companyId;
	public long groupId;
	public String userName;
	public long createDate;
	public long modifiedDate;
	public String name;
	public String email;
}