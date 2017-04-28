package com.taskmanager.dao;

import java.util.Date;
import java.util.List;

import com.taskmanager.model.Todo;

public interface TaskDao {
	public List<Todo> retrieveTodos(String user, boolean hideDone);
	public void addTodo(String name, String desc, Date targetDate, 
			boolean isDone);
	public void deleteTodo(int id);
	public Todo retrieveTodo(int id);
	public void updateTodo(Todo todo);
	public void markDone(int id);
}