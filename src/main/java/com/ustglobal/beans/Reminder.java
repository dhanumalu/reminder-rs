package com.ustglobal.beans;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Reminder {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String date;
	
	public Reminder() {
		super();
	}

	public Reminder(Long id, String name, String date) {
		super();
		this.id = id;
		this.name = name;
		this.date = date;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
		
}