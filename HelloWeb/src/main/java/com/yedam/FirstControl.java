package com.yedam;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class FirstControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
			System.out.println("firstcontrol 실행.");
			resp.setContentType("text/html;charset=UTF-8");
			PrintWriter out = null;
			try {
				out= resp.getWriter();
			} catch (Exception e) {
				e.printStackTrace();
			}
			//jdbc 연결 접속 조회
			EmpDAO dao = new EmpDAO();
			List<Employee> list =  dao.getEmpList();
					
						out.print("<table border='1'>");
						out.print("<thead><tr><td>사원번호</td><td>fname</td><td>lname</td><td>email</td><td>JobId</td></tr></thead>");
						out.print("<tbody>");
						for(Employee emp : list) {
							out.print("<tr><td><a href='searchMember?empId="+emp.getEmployeeId()+"'>"+emp.getEmployeeId()
												+"</a></td><td>"+emp.getFirstName()
												+"</td><td>"+emp.getLastName()
												+"</td><td>"+emp.getEmail()
												+"</td><td>"+emp.getJobId()+"</td></tr>");
						}
						out.print("</tbody>");
						out.print("</table>");
		}
			
	}
