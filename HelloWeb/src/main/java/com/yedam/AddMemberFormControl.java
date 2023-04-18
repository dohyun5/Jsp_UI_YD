package com.yedam;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddMemberFormControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/addMemberForm.jsp");
		
		try {
			rd.forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
