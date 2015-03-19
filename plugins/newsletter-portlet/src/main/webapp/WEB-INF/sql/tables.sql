create table NL_Subscription (
	subscriptionId LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userName VARCHAR(75) null,
	createDate DATE null,
	modifiedDate DATE null,
	name VARCHAR(75) null,
	email VARCHAR(75) null
);