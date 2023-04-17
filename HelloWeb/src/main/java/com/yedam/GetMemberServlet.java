package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/searchMember")	
public class GetMemberServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		String empId = req.getParameter("empId");
		EmpDAO dao = new EmpDAO();
		Employee result = dao.getEmp(Integer.parseInt(empId));
		
		if(result != null) {
			out.print("<table border = '1'>");
			out.print("<tr>");
			out.print("<th>사원번호</th>");
			out.print("<td>"+ result.getEmployeeId()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>fname</th>");
			out.print("<td>"+ result.getFirstName()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>lname</th>");
			out.print("<td>"+ result.getLastName()+"</td>");
			out.print("</tr>");
			out.print("<tr>");
			out.print("<th>email</th>");
			out.print("<td>"+ result.getEmail()+"</td>");
			out.print("</tr>");
			out.print("</table>");
			out.print("<hr>");
			out.print("<a href='empList'>목록으로</a>");
			
			
			//resp.sendRedirect("empList");
		}else {
			resp.sendRedirect("searchForm.html");
		}
		
		
	}
	
}
