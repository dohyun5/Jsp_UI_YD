package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class FrontController extends HttpServlet{
	
	
	//key & value 저장할 수 있는 컬렉션. Map
	Map<String, Control> map;
	
	public FrontController() {
		System.out.println("FrontController() Call.");
		map = new HashMap<>();
		
	}
	
	@Override
	public void init() throws ServletException {
		System.out.println("init() Call.");
		map.put("/main.do", new MainControl());
		map.put("/first.do", new FirstControl());
		map.put("/second.do", new SecondControl());
		//사원정보 상세페이지(getMember.jsp)
		map.put("/getMember.do", new GetMemberControl());
		map.put("/modifyMember.do", new ModifyMemberControl());
		map.put("/addMemberForm.do", new AddMemberFormControl());
		map.put("/addMember.do", new AddMemberControl());
		map.put("/deleteMember.do", new DeleteMemberControl());
		map.put("/loginForm.do", new LoginFormControl());
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		req.setCharacterEncoding("utf-8");//요청정보에 한글 !
		//resp.setContentType("text/html;charset=UTF-8"); HTML에 한글표시 경우
		
		System.out.println("service() Call.");
		//http://localhost:8081/HelloWeb/first.do 포트번호뒤가 uri값
		String uri = req.getRequestURI();// /HelloWeb/first.do
		String context = req.getContextPath();// 서블릿에서 context : HelloWeb(프로젝트이름)
		String page = uri.substring(context.length());
		
		System.out.println(page);
		System.out.println(map.get(page));
		
		Control control = map.get(page);
		control.exec(req, resp);
		
	
	}
	
	@Override
	public void destroy() {
		System.out.println("destroy() Call.");
	}
	
	
	
	
}
