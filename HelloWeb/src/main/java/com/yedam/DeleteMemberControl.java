package com.yedam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.persistence.EmpDAO;

public class DeleteMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		String empId = req.getParameter("empId");
		EmpDAO dao = new EmpDAO();
		
		try {
			if (dao.deleteEmployee(Integer.parseInt(empId))) {
				resp.sendRedirect("main.do");
			}else{
				resp.sendRedirect("modifyMember.do?empId="+empId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
