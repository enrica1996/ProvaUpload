package com.sistemi.informativi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sistemi.informativi.entity.Academy;
import com.sistemi.informativi.service.AcademyService;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

/**
 * 
 * GET http://localhost:8080/api/academies -> all academies GET
 * http://localhost:8080/api/academies/code-number/1089G -> academy by code
 * number (PK) GET http://localhost:8080/api/academies/location/Rome ->
 * academies by location GET
 * http://localhost:8080/api/academies/students-number/17 -> academies by
 * students number > of POST http://localhost:8080/api/academies -> insert
 * academy, JSON PUT http://localhost:8080/api/academies -> update academy, JSON
 * DELETE http://localhost:8080/api/academies/code-number/1089T -> delete
 * academy GET http://localhost:8080/api/academies/location-name/Rome/company1
 * GET http://localhost:8080/api/academies/xml -> all academies but xml POST
 * http://localhost:8080/api/academies/responseEntity -> insert academy, JSON
 * POST http://localhost:8080/api/academies/validation -> insert academy, JSON
 * GET http://localhost:8080/api/academies/hateoas/code-number/1089G -> academy
 * 
 */
@CrossOrigin
@RestController
@RequestMapping("/api/academies")
public class AcademyController {

	@Autowired
	private AcademyService academyService;

	@GetMapping("")
	public List<Academy> findAllAcademies() throws Exception {

		/**
		 * Invochiamo il metodo del service findAllAcademies() che già restituisce una
		 * List<Academy>; Tale List sarà convertita dal Jackson Object Mapper in un JSON
		 */
		return academyService.findAllAcademies();
	}

	/**
	 * @PathVariable comunica al JacksonObjectMapper la necessità di convertire una
	 *               variabile http in una variabile java
	 */
	@GetMapping("/code-number/{codeNumber}")
	public Academy findAcademieByCodeNumber(@PathVariable String codeNumber) throws Exception {

		return academyService.findAcademyByCodeNumber(codeNumber);
	}

	@GetMapping("/location/{location}")
	public List<Academy> findAllAcademiesByLocation(@PathVariable String location) throws Exception {

		return academyService.findAllAcademiesByLocation(location);
	}

	@GetMapping("/students-number/{studentsNumber}")
	public List<Academy> findAllAcademiesByStudentsNumberGreaterThan(@PathVariable int studentsNumber)
			throws Exception {

		return academyService.findAllAcademiesByStudentsNumberGreatenThan(studentsNumber);
	}

	/**
	 * @RequestBody chiede a JacksonObjectMapping di convertire un JSON in ingresso
	 *              in un oggetto java
	 */
	@PostMapping("")
	public Academy saveAcademy(@RequestBody Academy academy) throws Exception {

		return academyService.saveOrUpdateAcademy(academy);
	}

	@PutMapping("")
	public Academy updateAcademy(@RequestBody Academy academy) throws Exception {

		return academyService.saveOrUpdateAcademy(academy);
	}

	@DeleteMapping("/code-number/{codeNumber}")
	public Map<String, Boolean> removeAcademy(@PathVariable String codeNumber) {

		return academyService.removeAcademy(codeNumber);
	}

	@GetMapping("/location-name/{location}/{businessName}")
	public List<Academy> findAllAcademiesByLocationAndBusinessName(@PathVariable String location,
			@PathVariable String businessName) throws Exception {

		return academyService.findAllAcademiesByLocationAndBusinessName(location, businessName);
	}

	// Restituisce xml anzichè json
	@GetMapping(path = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
	public List<Academy> findAllAcademiesXML() throws Exception {

		return academyService.findAllAcademies();
	}

	/**
	 * Esempio metodo che customizza risposta al consumer cambiando il comportamento
	 * di default del Jackson Object Mapper
	 * 
	 * E' necessario che il metodo non ritorni un Object Java Generico (es_ JPA
	 * Entity), ma un oggetto di nome ResponseEntity (API SpringRestful)
	 */

	@PostMapping(path = "/responseEntity")
	public ResponseEntity<?> saveCustomAcademy(@RequestBody Academy academy) throws Exception {

		academyService.saveOrUpdateAcademy(academy);

		Map<String, String> responseMap = new HashMap<>();
		responseMap.put("Operation Satus", "1");
		responseMap.put("Operation Feedback", "Ok");

		/**
		 * Restituendo come ritorno del metodo un oggetto ResponseEntity siamo in grado
		 * di modificare sia l'header che il body della risposta
		 * 
		 * Ciò che viene passato in input come primo argomento al costruttore di
		 * ResponseEntity rappresenta il body della risposta (in questo caso un json che
		 * mappa la struttura dell HashMap responseMap), il secondo argomento
		 * rappresenta l'header della risposta (in qusto caso il codice Rest 201,
		 * anzichè il default Spring 200)
		 */
		return new ResponseEntity<>(responseMap, HttpStatus.CREATED);
	}

	@PostMapping("/validation")
	public Academy saveValidatedAcademy(@Valid @RequestBody Academy academy) throws Exception {

		return academyService.saveOrUpdateAcademy(academy);
	}

	@GetMapping("/hateoas/code-number/{codeNumber}")
	public EntityModel<Academy> findAcademyByCodeNumberHATEOAS(@PathVariable String codeNumber) throws Exception {

		Academy academy = academyService.findAcademyByCodeNumber(codeNumber);

		/**
		 * EntityModel è un API di spring restful utilizzata per inserire all'interno
		 * del JSON di risposta uno o più link
		 */
		EntityModel<Academy> resource = EntityModel.of(academy);

		/**
		 * linkTo è una variabile che rappresenta un hyperlink ad un altra operazione 
		 * quella specificata in questo caso dal metodo findAllAcademies()
		 */
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).findAllAcademies());

		/**
		 * Il withRel può essere usato per conferire il nome visivo all'hyperlink
		 */
        resource.add(linkTo.withRel("all-academies"));

	    return resource;

	}
}
