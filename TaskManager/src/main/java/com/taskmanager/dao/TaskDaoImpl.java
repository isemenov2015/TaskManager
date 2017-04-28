package com.taskmanager.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.taskmanager.model.Todo;

@Repository
public class TaskDaoImpl implements TaskDao {
	private SessionFactory sessionFactory;
	private int todoId = 0;
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public List<Todo> retrieveTodos(String user, boolean hideDone) {
		Session session = this.sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Todo> todoList = (List<Todo>) session.createQuery("from Todo").list();
		List<Todo> filteredTodoList = new ArrayList<Todo>();
		for (Todo todo : todoList)
			if (todo.getName().equals(user))
				if (!hideDone || !todo.isDone())
					filteredTodoList.add(todo);
		return filteredTodoList;
	}

	public synchronized void addTodo(String user, String desc, Date targetDate,
			boolean isDone) {
		Session session = this.sessionFactory.getCurrentSession();
		session.save(new Todo(++todoId, user, desc, targetDate, isDone));
	}

	public void deleteTodo(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Todo todo = (Todo) session.load(Todo.class, new Integer(id));
		if (todo != null)
			session.delete(todo);
		session.flush();
	}

	public Todo retrieveTodo(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Todo todo = (Todo) session.load(Todo.class, new Integer(id));
		return todo;
	}
	
	@Transactional
	public void updateTodo(Todo todo) {
		Session session = this.sessionFactory.openSession();
		session.saveOrUpdate(todo);
		session.flush();
	}

	public void markDone(int id) {
		Session session = this.sessionFactory.getCurrentSession();
		Todo todo = (Todo) session.load(Todo.class, new Integer(id));
		todo.setDone(true);
		session.saveOrUpdate(todo);
		session.flush();
	}
}
