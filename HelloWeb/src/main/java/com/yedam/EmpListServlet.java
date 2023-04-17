package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;


@WebServlet("/empList")
public class EmpListServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		//jdbc 연결 접속 조회
		EmpDAO dao = new EmpDAO();
		List<Employee> list =  dao.getEmpList();
				
					out.print("<table border='1'>");
					out.print("<thead><tr><td>사원번호</td><td>fname</td><td>lname</td><td>email</td><td>JobId</td></tr></thead>");
					out.print("<tbody>");
//					while(rs.next()) {
//						System.out.println("eid: " + rs.getInt("employee_id") + ",fname :" + rs.getString("first_name")+ ",email :" + rs.getString("email")+ ",phone_number :" + rs.getString("phone_number"));
//						//사원번호, fname, lname, email, phone..
//						out.print("<tr><td>"+rs.getInt(1)+"</td>"+"<td>"+rs.getString(2)+"</td>"+"<td>"+rs.getString(3)+"</td>"+"<td>"+rs.getString(4)+"</td>"+"<td>"+rs.getString(5)+"</td></tr>");
//					}
					for(Employee emp : list) {
						//System.out.println("System.out.println(\"eid: \" + rs.getInt(\"employee_id\") + \",fname :\" + rs.getString(\"first_name\")+ \",email :\" + rs.getString(\"email\")+ \",phone_number :\" + rs.getString(\"phone_number\"))");
						out.print("<tr><td><a href='searchMember?emp_id="+emp.getEmployeeId()+"'>"+emp.getEmployeeId()
											+"</a></td><td>"+emp.getFirstName()
											+"</td><td>"+emp.getLastName()
											+"</td><td>"+emp.getEmail()
											+"</td><td>"+emp.getJobId()+"</td></tr>");
					}
					
					out.print("</tbody>");
					out.print("</table>");
		
		
	}
	
//	public static void main(String[] args) {
//		
//		
//		
//	}
//	
	
	
}
