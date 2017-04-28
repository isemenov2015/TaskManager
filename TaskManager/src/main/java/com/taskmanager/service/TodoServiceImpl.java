package com.taskmanager.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.dao.TaskDao;
import com.taskmanager.model.Todo;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {
	private TaskDao taskDao;

	public void setTaskDao(TaskDao taskDao) {
		this.taskDao = taskDao;
	}
	
	public List<Todo> retrieveTodos(String user, boolean hideDone) {
		return this.taskDao.retrieveTodos(user, hideDone);
	}

	public void addTodo(String name, String desc, Date targetDate,
			boolean isDone) {
		this.taskDao.addTodo(name, desc, targetDate, isDone);
	}

	public void deleteTodo(int id) {
		this.taskDao.deleteTodo(id);
	}

	public Todo retrieveTodo(int id) {
		return this.taskDao.retrieveTodo(id);
	}

	public void updateTodo(Todo todo) {
		this.taskDao.updateTodo(todo);
	}

	public void markDone(int id) {
		this.taskDao.markDone(id);
	}
	
}
