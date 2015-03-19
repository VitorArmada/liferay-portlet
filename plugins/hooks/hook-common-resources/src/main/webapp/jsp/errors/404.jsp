<%
	response.setStatus(HttpServletResponse.SC_NOT_FOUND);
	response.sendRedirect("/erro");
%>