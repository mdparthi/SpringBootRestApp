package com.qht.boot.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

import io.swagger.annotations.ApiModelProperty;

@XmlRootElement(name="person")
public class Person {

	@ApiModelProperty(notes="id will be unique")
	private Long id;
	@ApiModelProperty(notes="first should be 6 chars max")
	private String firstName;
	private String lastName;
	private Double money;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	
	@Override
	public String toString() {
		
	  return this.firstName + "  " + this.getLastName();	
		
	}
	
	
}
