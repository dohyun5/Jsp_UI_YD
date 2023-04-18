<%@page import="com.yedam.domain.Employee"%>
<%@page import="com.yedam.persistence.EmpDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="menu.jsp"></jsp:include>
<jsp:include page="nav.jsp"></jsp:include>

<%
	//사원번호 조회 -> Employee
		/* String empId = request.getParameter("empId");
		EmpDAO dao = new EmpDAO();
		Employee result = dao.getEmp(Integer.parseInt(empId));
		*/
		Employee emp = (Employee) request.getAttribute("empInfo");
%>

	<form action="modifyMember.do" method="post">
		<table class = 'table'>
		
			<tr><th>사원번호</th><td><input type="text" name="empId" value="<%=emp.getEmployeeId() %>" readonly></td></tr>
			<tr><th>fname</th><td><input type="text" name="fname" value="<%=emp.getFirstName() %>"></td></tr>
			<tr><th>lname</th><td><input type="text" name="lname" value="<%=emp.getLastName() %>"></td></tr>
			<tr><th>email</th><td><input type="text" name="email" value="<%=emp.getEmail() %>"></td></tr>
			<tr><td colspan="2">
				<input type="submit" value="수정">
				<input type="button" value="삭제">
			</td></tr>
			
		</table>
	</form>

	<script>
	//Document Object Model. (DOM)
		let btn = document.querySelector('input[type="button"]');
		console.log(btn);
		btn.onclick = function(){
			//alert("클릭됨.");
			//삭제하는 구문
			let frm = document.querySelector('form');//form이 여러개 있다면 가장 첫번째 form을 가져온다
			frm.action = "deleteMember.do";
			frm.submit();//
			
			
		}
		
	</script>




<jsp:include page="footer.jsp"></jsp:include>