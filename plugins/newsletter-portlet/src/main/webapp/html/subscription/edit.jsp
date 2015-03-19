<%@page import="pt.impresa.liferay.model.Subscription"%>
<%@include file="/html/init.jsp"%>

<%
	Subscription sub = (Subscription)request.getAttribute("object");
%>

<liferay-ui:error key="subscription-edit-error" message="subscription-edit-error"/>
<liferay-ui:error key="subscription-error-email-empty" message="subscription-error-email-empty"/>
<liferay-ui:error key="subscription-error-email-invalid" message="subscription-error-email-invalid"/>
<liferay-ui:error key="subscription-error-email-exists" message="subscription-error-email-exists"/>
<liferay-ui:error key="subscription-error-name-empty" message="subscription-error-name-empty"/>

<portlet:actionURL name="updateSubscription" var="action_url"></portlet:actionURL>
<portlet:actionURL name="searchSubscription" var="cancel_url"></portlet:actionURL>

<liferay-ui:header title="subscriptionlist-edit-title" backURL="<%=cancel_url%>"/>
<aui:form action="<%=action_url%>">
	<aui:input name="id" type="hidden" value="<%=sub.getPrimaryKey()%>"></aui:input>
	<aui:input name="name" label="subscription-name" type="text" value="<%=sub.getName()%>"></aui:input>
	<aui:input name="email" label="subscription-email" type="text" value="<%=sub.getEmail()%>"></aui:input>
	<aui:button-row>
		<aui:button type="submit" value="subscription-save" />
	</aui:button-row>
</aui:form>