package com.yedam;

import java.util.List;

public interface TodoMapper {
	public List<ToDoVO> todoList();
	public int removeTodo(int todoNo);
	public int updateTodo(ToDoVO vo);
	public int addTodo(ToDoVO vo);
	
	
}
