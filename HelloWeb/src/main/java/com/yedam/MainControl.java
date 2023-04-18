package com.yedam;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class MainControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
			//페이지 재 지정.
			//resp.sendRedirect();//parameter값을 넘길수 없다.
			EmpDAO dao = new EmpDAO();
			List<Employee> list =  dao.getEmpList();
			
			req.setAttribute("listInfo", list);
			
			
			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/empList.jsp");//.forward(req, resp); //페이지 재지정 이동할 
			rd.forward(req, resp); //(요청정보, 응답정보)
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
