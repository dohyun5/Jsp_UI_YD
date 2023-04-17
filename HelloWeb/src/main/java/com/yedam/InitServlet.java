package com.yedam;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/initServlet")
public class InitServlet extends HttpServlet{
	
	
	//서블릿 : 서버측에서 실행되는 웹페이지(java코드)
	//		 톰캣 컨테이너가 실행시켜준다. 
	//		 <-> 클라이언트의 요청. url : localhost:8081/HelloWeb/initServlet 
	//.java 확장자 코드들은 .class파일이 컴파일이 된 후 .class파일이 실행
	
	public InitServlet() {
		System.out.println("InitServlet() Call.");
		//최초 한번만 실행
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("init() Call.");
		//최초 한번만 실행
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("service() Call.");
		resp.setContentType("text/html;charset=UTF-8"); //화면에 출력되는 content 한글 표시
		
		//사용자의 화면에 출력(html) -> 출력 스트림 생성 
		PrintWriter out = resp.getWriter(); //출력스트림 생성.
		out.print("initServlet 페이지 입니다 :)");
		out.print("<h3>InitPage</h3>");
		out.print("<a href='index.html'>첫페이지로 이동</a>");
	}
	
	
	
	
	
	
}
