package com.yedam;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


@WebServlet("/addTodo.json")
public class addTodo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public addTodo() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String todoTitle = request.getParameter("todoTitle");
		ToDoVO vo = new ToDoVO();
		vo.setTodoTitle(todoTitle);
		
		TodoService service = new TodoServiceImpl();
		Gson gson = new GsonBuilder().create();
		Map<String, Object> map = new HashMap<>();
		
		if(service.addTodo(vo)) {
			map.put("retCode", "Success");
			map.put("retVal", "No");
			
		}else {
			map.put("retCode", "Fail");
			map.put("retVal", "nono");
		}
		
		
		doGet(request, response);
	}

}
