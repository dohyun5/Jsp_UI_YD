<%@page import="com.yedam.domain.Employee"%>
<%@page import="java.util.List"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>

	<%
		/* EmpDAO dao = new EmpDAO();
		List<Employee> list =  dao.getEmpList(); */
		List<Employee> list = (List<Employee>) request.getAttribute("listInfo");// Attribute반환타입 : Object  따라서 받아올 타입으로 변환. 
		
		String fname = (String) request.getAttribute("reqInfo");//logincontrol참고
		String lname = (String) session.getAttribute("sesInfo");//logincontrol참고
		
	%>
	<p>Request: <%=fname %></p>
	<p>Session: <%=lname %></p>
	<table class="table">
		<thead>
			<tr><th>사원번호</th><th>이름</th><th>이메일</th></tr>
		</thead>
		<tbody>
		<% for(Employee emp : list) {%>
			<tr>
				<td><a href="getMember.do?empId=<%=emp.getEmployeeId() %>"><%=emp.getEmployeeId() %></a></td>
				<td><a href="modifyMember.do?empId=<%=emp.getEmployeeId() %>"><%=emp.getFirstName() %></a></td>
				<td><%=emp.getEmail() %></td>
			</tr>
		<% }%>	
		</tbody>
	</table>
	<jsp:include page="footer.jsp"></jsp:include>
