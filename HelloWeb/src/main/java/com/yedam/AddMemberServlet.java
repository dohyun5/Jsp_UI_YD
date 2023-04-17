package com.yedam;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

@WebServlet("/addMemberServlet")
public class AddMemberServlet extends HttpServlet{
	
	//생성자, init, service
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		
		String fname = req.getParameter("fname"); // input name = "fname" 
		String lname = req.getParameter("lname"); // input name = "lname"
		String email = req.getParameter("email"); // input name = "email"
		String job = req.getParameter("job"); // input name = "job"
		String hire = req.getParameter("hdate"); // input name = "hdate"
		
		Employee emp = new Employee();
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(job);
		emp.setHireDate(hire);
		
		EmpDAO dao = new EmpDAO();
		boolean result = dao.insertEmployee(emp); //insertEmployee -> boolean 형
		
		if(result) {
			resp.sendRedirect("empList");
		}else {
			resp.sendRedirect("addForm.html");
		}
		
		
		
		
		
//		try {
//			Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","hr");
//			//쿼리를 조회하고 처리 결과를 가지고 오는 PreparedStatement객체를 사용
//			String sql = "insert into employees (employee_id,last_name,email,hire_date,job_id,first_name)\r\n"
//					+ "values(employees_seq.nextval,?,?,?,?,?)";
//			PreparedStatement psmt = conn.prepareStatement(sql);
//			psmt.setString(1, lname);
//			psmt.setString(2, email);
//			psmt.setString(3, hire);
//			psmt.setString(4, job);
//			psmt.setString(5, fname);
//			
//			int r = psmt.executeUpdate();
//			System.out.println("처리된 건수:" + r);
//			
//			resp.sendRedirect("empList");//form -> addMemberServlet 
//			
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		System.out.println("fname :" + fname + ",lname : "+ lname +",email :");
//		
	
	}
	
}
