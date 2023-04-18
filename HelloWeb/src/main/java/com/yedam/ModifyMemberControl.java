package com.yedam;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yedam.domain.Employee;
import com.yedam.persistence.EmpDAO;

public class ModifyMemberControl implements Control {

	@Override
	public void exec(HttpServletRequest req, HttpServletResponse resp) {
		
		// View에서 요청한 method를 구분. GET/POST
		if (req.getMethod().equals("GET")) {

			// emp_id 파라미터.
			// mvc패턴으로 처리 | 컨트롤러에서는 DB의 처리 view화면으로 정보를 전송.
			// emp 변수에 조회결과를 담아서 empInfo의 속성으로 modifyMember.jsp 재지정.
			String empId = req.getParameter("empId");
			EmpDAO dao = new EmpDAO();
			Employee emp = dao.getEmp(Integer.parseInt(empId));

			req.setAttribute("empInfo", emp);

			RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/views/modifyMember.jsp");
			try {
				rd.forward(req, resp);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}

		} else if (req.getMethod().equals("POST")) {
			//DB 업데이트 처리 목록으로 이동.
			String empId = req.getParameter("empId");
			String fname = req.getParameter("fname");
			String lname = req.getParameter("lname");
			String email = req.getParameter("email");
			
			Employee emp = new Employee();
			emp.setEmployeeId(Integer.parseInt(empId));
			emp.setFirstName(fname);
			emp.setLastName(lname);
			emp.setEmail(email);
			
			EmpDAO dao = new EmpDAO();
			boolean result = dao.updateEmployee(emp);
			try {
				if(result) {
					resp.sendRedirect("main.do");
				}else {
					resp.sendRedirect("modifyMember.do");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}

	}

}
