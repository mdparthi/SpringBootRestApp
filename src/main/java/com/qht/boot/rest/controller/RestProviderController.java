package com.qht.boot.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	/*
	 * Rest method to add Person object in the service class PersonList
	 */
	@RequestMapping(value = "/person", method = RequestMethod.POST, headers = "Accept=application/json" , consumes="application/json")
	public @ResponseBody Person addPerson(@RequestBody Person person) {
		// Call service to here
		return personService.add(person);
	}
	
	/*
	 * Rest service method to get Person object based on Id
	 */
	@RequestMapping(value = "/person/{id}", method = RequestMethod.GET, headers = "Accept=text/plain,application/xml, application/json")
	public @ResponseBody Person getPerson(@PathVariable("id") Long id) {
		// Call service here
		return personService.get(id);
	}

}
