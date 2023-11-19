package model.dto;

import java.sql.Date;

public class TodoDTO {
	
	private int id;
	private String todo;
	private Date timeLimit;
	private int priority;
	private int user_id;
	
	public TodoDTO() {}
	
	public TodoDTO(int id, String todo, Date timeLimit, int priority, int user_id) {
		this.id = id;
		this.todo = todo;
		this.timeLimit = timeLimit;
		this.priority = priority;
		this.user_id = user_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Date getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(Date timeLimit) {
		this.timeLimit = timeLimit;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

}
