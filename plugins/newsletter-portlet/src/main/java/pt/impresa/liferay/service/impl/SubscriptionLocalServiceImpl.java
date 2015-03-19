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

package pt.impresa.liferay.service.impl;

import java.util.Date;
import java.util.List;

import pt.impresa.liferay.model.Subscription;
import pt.impresa.liferay.service.base.SubscriptionLocalServiceBaseImpl;

import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.search.Indexable;
import com.liferay.portal.kernel.search.IndexableType;

/**
 * The implementation of the subscription local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link pt.impresa.liferay.service.SubscriptionLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author srtab
 * @see pt.impresa.liferay.service.base.SubscriptionLocalServiceBaseImpl
 * @see pt.impresa.liferay.service.SubscriptionLocalServiceUtil
 */
public class SubscriptionLocalServiceImpl extends SubscriptionLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link pt.impresa.liferay.service.SubscriptionLocalServiceUtil} to access the subscription local service.
	 */
	@Override
	@Indexable(type = IndexableType.REINDEX)
	public Subscription addSubscription(Subscription subscription) throws SystemException {
		
		subscription.setSubscriptionId(counterLocalService.increment(Subscription.class.getName())); //Gera um id
		subscription.setCreateDate(new Date());
		subscription.setModifiedDate(new Date());
		
		return super.addSubscription(subscription);
	}
	
	public List<Subscription> findByEmail(String email) throws SystemException{
		return subscriptionPersistence.findByEmail(email);
	}
	
	public List<Subscription> findAll() throws SystemException{
		return subscriptionPersistence.findAll();
	}
	
	public List<Subscription> findByEmailLike(String email) throws SystemException{
		DynamicQuery query = DynamicQueryFactoryUtil
			.forClass(Subscription.class)
			.add(PropertyFactoryUtil.forName("email").like("%"+email+"%"));
		
		return subscriptionPersistence.findWithDynamicQuery(query);
	}
}