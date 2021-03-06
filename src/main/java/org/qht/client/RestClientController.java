package org.qht.client;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qht.boot.rest.config.BeanConfiguration;
import com.qht.boot.rest.model.Person;
import com.qht.boot.rest.model.PersonList;

@Controller // without @Response body the return will be the logical name of the view eg. personPage.jsp
@Import(BeanConfiguration.class)// since it is in different package
public class RestClientController {

	@Autowired
	RestTemplate template;
	
	@RequestMapping(value="/getAll", method=RequestMethod.GET)
	public String getAllPerson(Model model) {
		
		//1. set the media type
		List<MediaType> mediaTypeList = new ArrayList<MediaType>();
		mediaTypeList.add(MediaType.APPLICATION_XML);
		
		//2. set http headers
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(mediaTypeList);
		
		//3. Create the HttpEntity
		HttpEntity<Person> entity = new HttpEntity<Person>(headers);
		
		//4. SEnd the request to the server
		ResponseEntity<PersonList> responseEntity 
		 			= template.exchange("http://localhost:8080/persons", HttpMethod.GET, entity, PersonList.class);
		
		//5. Get the data from the response
//		System.out.println(responseEntity.getBody().getData());
		
		model.addAttribute("persons", responseEntity.getBody().getData());
		
		return "personPage";
	}
	
	
	/**************** To add Person Object *********************************/

	/**
	 * Retrieves the JSP add page to add Person details and then from form
	 * invoke the
	 */
	@RequestMapping(value = "/person/add", method = RequestMethod.GET)
	public String getAddPage(Model model) {
		// Create new Person and add to model
		// This is the formBackingOBject
		model.addAttribute("personAttribute", new Person());

		// This will resolve to /WEB-INF/JSP/addpage.jsp
		return "addpage";
	}
	
	/**
	 * Sends a new record to the REST provider based on the information
	 * submitted from the JSP add page.
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addPerson(@ModelAttribute("personAttribute") Person person) {
		// Prepare acceptable media type
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

		// Prepare header
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);

		// Pass the new person and header
		HttpEntity<Person> entity = new HttpEntity<Person>(person, headers);

		// Send the request as POST
		try {
			ResponseEntity<Person> result = template.exchange(
					"http://localhost:8080/person", HttpMethod.POST, entity, Person.class);
		} catch (Exception e) {
			System.out.println("Exception from Post:" + e);
		}

		// This will resolve to /WEB-INF/jsp/resultpage.jsp
		return "resultpage";
	}
	
	

}
