package com.yedam;

import java.util.List;

public class TodoServiceImpl implements TodoService{
	TodoMapper mapper = DataSource.getInstance()
			.openSession(true).getMapper(TodoMapper.class);
	@Override
	public List<ToDoVO> todoList() {
		return mapper.todoList();
	}
	@Override
	public boolean addTodo(ToDoVO vo) {
		return mapper.addTodo(vo)==1;
	}
	@Override
	public boolean removeTodo(int todoNo) {
		return mapper.removeTodo(todoNo)==1;
	}
	@Override
	public boolean modifyTodo(ToDoVO vo) {
		return mapper.updateTodo(vo)==1;
	}

	
}
