package com.example.hackerrank;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hackerrank.entities.PersonEntity;
import com.example.hackerrank.services.PersonService;

@RestController
@RequestMapping("persons")
public class PersonController {

	@Autowired
	PersonService personService;
	
	@GetMapping("/get/{id}")
	public ResponseEntity<PersonEntity> getPersonById(@PathVariable int id) {
		
		PersonEntity entity = this.personService.getById(id);
		return ResponseEntity.ok().body(entity);
		
	}

	@GetMapping("/getallpersons")
	public ResponseEntity<List<PersonEntity>> getAllPersons() {

		try {

			return ResponseEntity.ok(personService.getAllPersons());

		} catch (Exception e) {

			System.out.println("Exception message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@PutMapping("/saveperson")
	public ResponseEntity<String> savePerson(@RequestBody List<PersonEntity> persons) {

		try {

			personService.savePerson(persons);
			return ResponseEntity.ok("Person saved successfully");

		} catch (Exception e) {

			System.out.println("Exception message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}

	}

	@PostMapping("/updateperson")
	public ResponseEntity<String> updatePerson(@RequestBody PersonEntity person) {

		try {

			String message = personService.updatePerson(person);
			return ResponseEntity.status(HttpStatus.OK).body(message);

		} catch (Exception e) {

			System.out.println("Exception message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}

	@DeleteMapping("/deleteperson/{id}")
	public ResponseEntity<String> deletePerson(@PathVariable int id) {

		try {

			personService.deletePerson(id);
			return ResponseEntity.status(HttpStatus.OK).body("Person deleted successfully");

		} catch (Exception e) {

			System.out.println("Exception message: " + e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		}
	}
}