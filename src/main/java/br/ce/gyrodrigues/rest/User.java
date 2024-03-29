package br.ce.gyrodrigues.rest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name ="user")
@XmlAccessorType(XmlAccessType.FIELD)


public class User {
	
	 
	private Long id;
	
	
	private String name;
	private Integer age;
	private Double salary;
	
	public User() {
		
	}
	
	
	public User(String name, Integer age) {
		super();
		this.name = name;
		this.age = age;
	}
	

	public void setId(long id) {
		this.id = id;
	}
	
	
	public String getName() {
		return name;
		
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public long getId() {
		return id;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", age=" + age + ", salary=" + salary + "]";
	}
}
