package com.qht.boot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.qht.boot.rest.model.Person;
import com.qht.boot.rest.model.PersonList;
import com.qht.boot.rest.service.PersonService;

@RestController
public class RestProviderController {

	@Autowired
	PersonService personService; 
	
	@RequestMapping(value="/persons", method=RequestMethod.GET, headers= "Accept=application/xml,applicaiton/json")
	public PersonList getPerson() {
		
		PersonList personList = new PersonList();
		List<Person> persons = personService.getAll();
		personList.setData(persons);
		
		return personList;		

	}
}
