<%@include file="/html/init.jsp"%>

<% 
	ResultRow row = (ResultRow) request.getAttribute(WebKeys.SEARCH_CONTAINER_RESULT_ROW); 
	String id = row.getPrimaryKey();
%>

<liferay-ui:icon-menu>
	<portlet:actionURL name="editSubscription" var="editURL" >
		<portlet:param name="id" value="<%=id%>"/>
	</portlet:actionURL>
	<liferay-ui:icon image="edit" label="Editar" url="<%=editURL%>"/>
	
	<portlet:actionURL name="deleteSubscription" var="deleteURL" >
		<portlet:param name="id" value="<%=id%>"/>
	</portlet:actionURL>
	<liferay-ui:icon-delete url="<%=deleteURL%>"/>
</liferay-ui:icon-menu>