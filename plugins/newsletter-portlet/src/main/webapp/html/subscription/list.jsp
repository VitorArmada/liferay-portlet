<%@page import="com.liferay.portal.kernel.dao.search.RowChecker"%>
<%@page import="pt.impresa.liferay.model.Subscription"%>
<%@page import="pt.impresa.liferay.service.SubscriptionLocalServiceUtil"%>
<%@include file="/html/init.jsp"%>

<portlet:actionURL name="searchSubscription" var="action_url"></portlet:actionURL>

<liferay-ui:success key="subscriptionlist-success" message="<%=(String)request.getAttribute(\"success-msg\")%>"/>
<liferay-ui:error key="subscriptionlist-error" message="<%=(String)request.getAttribute(\"error-msg\")%>"/>

<% 
	List<Subscription> templist = (List<Subscription>)portletSession.getAttribute("list");

	if(templist == null){
		templist = SubscriptionLocalServiceUtil.getSubscriptions(-1, -1);
		portletSession.setAttribute("list", templist);
	}
	
	Integer count = 0;
	if(templist != null)
		count = templist.size();
%>

<liferay-ui:header title="subscriptionlist-title" />

<aui:form action="<%=action_url%>">
	<% String term = (String)portletSession.getAttribute("term"); %>
	<aui:input name="term" type="text" label="subscriptionlist-search-label" value="<%=term%>"></aui:input>
	<aui:button-row>
		<aui:button type="submit" value="Submeter"></aui:button>
	</aui:button-row>
</aui:form>

<hr/>

<portlet:actionURL var="uploadCsv" name="uploadCsv"/>
<aui:form action="<%= uploadCsv %>" method="post" enctype="multipart/form-data" >
	<aui:input type="file" id="file" name='file' size="50" label="" />
	<script>
		$(document).ready(function(){
			$("input[id$='file']").hide();
			
			$("#fileselector").click(function(){
				$("input[id$='file']").trigger("click");
				return false;
			});
			
			$("input[id$='file']").on("change", function(){
				$(this).closest("form").submit();
			});
		});
	</script>
</aui:form> 

<portlet:actionURL name="multiDeleteSubscription" var="action_multidelete_url"/>
<aui:form action="<%=action_multidelete_url%>">
	
	<% if (count != 0) {%>
		<aui:button-row>
			<aui:button id="fileselector" value="Importar CSV" />
			<portlet:resourceURL var="exportToCsv" id="exportToCsv"/>
	    	<aui:button value="Exportar CSV" onClick="<%=exportToCsv.toString()%>"/>
	    </aui:button-row>
	    <aui:button-row>
			<aui:button type="submit" value="Apagar" onClick="javascript:if(confirm('Tem a certeza que pretende apagar isto?')) return true; else return false;"/>
		</aui:button-row>
	<% }%>
	
	<liferay-ui:search-container delta="10" rowChecker="<%=new RowChecker(renderResponse)%>" emptyResultsMessage="subscriptionlist-empty">
		<liferay-ui:search-container-results>
			<% 
				List<Subscription> list = ListUtil.subList(templist, searchContainer.getStart(), searchContainer.getEnd());
				
				pageContext.setAttribute("results", list);
				pageContext.setAttribute("total", templist.size());
			%>
		</liferay-ui:search-container-results>
		<liferay-ui:search-container-row 
				className="pt.impresa.liferay.model.Subscription" 
				keyProperty="subscriptionId"
				modelVar="item">
				
				<liferay-ui:search-container-column-text property="name"/>
				<liferay-ui:search-container-column-text property="email"/>
				<liferay-ui:search-container-column-jsp path="/html/subscription/maintenance_menu.jsp"/>
				
		</liferay-ui:search-container-row>
		<liferay-ui:search-iterator />
	</liferay-ui:search-container>
</aui:form>

<%--c:forEach items="${list}" var="item">
	<c:out value="${item.name}" /> :  <c:out value="${item.email}" />
</c:forEach--%>