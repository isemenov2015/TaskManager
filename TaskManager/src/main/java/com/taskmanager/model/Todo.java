package com.taskmanager.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Proxy;

@Entity
@Table(name="todo")
@Proxy(lazy = false)
public class Todo {
	
	private int id;
	private String name;
	
	@Size(min=6, message="Please, enter no less than 6 characters")
	private String desc;
	private Date targetDate;
	private boolean isDone;
	
	public Todo() {}
	
	public Todo(int id, String name, String desc, Date targetDate,
			boolean isDone) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
		this.targetDate = targetDate;
		this.isDone = isDone;
	}
	
	//getters and setters
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Column(name="name")
	public String getName() {
		return this.name;
	}
	public void setName(String user) {
		this.name = user;
	}
	@Column(name="description")
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	@Column(name="targetDate")
	public Date getTargetDate() {
		return targetDate;
	}
	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}
	@Column(name="isDone")
	public boolean isDone() {
		return isDone;
	}
	public void setDone(boolean isDone) {
		this.isDone = isDone;
	}
	@Override
	public String toString() {
		return String.format(
				"Todo [id=%s, user=%s, desc=%s, targetDate=%s, isDone=%s]", id,
				name, desc, targetDate, isDone);
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Todo other = (Todo) obj;
		if (id != other.id)
			return false;
		return true;
	}
}
