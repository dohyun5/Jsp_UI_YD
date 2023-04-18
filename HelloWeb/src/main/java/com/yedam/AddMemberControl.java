package com.yedam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class AddMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		String fname = req.getParameter("fname");
		String lname = req.getParameter("lname");
		String email = req.getParameter("email");
		String job = req.getParameter("job");
		String hdate = req.getParameter("hdate");
		
		Employee emp = new Employee();
		emp.setFirstName(fname);
		emp.setLastName(lname);
		emp.setEmail(email);
		emp.setJobId(job);
		emp.setHireDate(hdate);
		
		EmpDAO dao = new EmpDAO();
		try {
			if(dao.insertEmployee(emp)) {
				resp.sendRedirect("main.do");
			}else {
				resp.sendRedirect("addMemberForm.do");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}	

}
