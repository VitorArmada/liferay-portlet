<%@include file="/html/init.jsp"%>

<portlet:actionURL name="addSubscription" var="action_url"></portlet:actionURL>

<liferay-ui:success key="subscription-success" message="subscription-success"/>
<liferay-ui:error key="subscription-error" message="subscription-error"/>
<liferay-ui:error key="subscription-error-email-empty" message="subscription-error-email-empty"/>
<liferay-ui:error key="subscription-error-email-invalid" message="subscription-error-email-invalid"/>
<liferay-ui:error key="subscription-error-email-exists" message="subscription-error-email-exists"/>
<liferay-ui:error key="subscription-error-name-empty" message="subscription-error-name-empty"/>

<h2><liferay-ui:message key="subscription-title"/></h2>
<aui:form action="<%=action_url%>">
	<aui:input name="name" type="text" label="subscription-name"/>
	<aui:input name="email" type="text" label="subscription-email"/>
	<aui:button-row>
		<aui:button  type="submit" value="subscription-submit" />
	</aui:button-row>
</aui:form>