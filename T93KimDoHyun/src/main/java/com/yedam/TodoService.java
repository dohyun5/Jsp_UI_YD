package com.yedam;

import java.util.List;

public interface TodoService {
	public List<ToDoVO> todoList();
	public boolean addTodo(ToDoVO vo);
	public boolean removeTodo(int todoNo);
	public boolean modifyTodo(ToDoVO vo);
	
	
	
}
