package com.yedam;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/delMemberServlet")
public class DelMemberServlet extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		String empId = req.getParameter("empId");
//		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
			//String sql = "delete from employees where employee_id = ?";
			
			
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, empId);
//			
//			psmt.executeUpdate();
			String empId = req.getParameter("empId");
			
			EmpDAO dao = new EmpDAO();
			boolean result = dao.deleteEmployee(Integer.parseInt(empId));
			
			
			if(result) {
				resp.sendRedirect("empList");
			}else {
				resp.sendRedirect("delForm.html");
			}
			
			//resp.sendRedirect("empList");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		
		
		
		
		
		
		
		
	}
	
	
	
	
}
