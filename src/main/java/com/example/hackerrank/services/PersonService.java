package com.example.hackerrank.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hackerrank.entities.PersonEntity;
import com.example.hackerrank.repositories.PersonRepository;

@Service
public class PersonService {

	@Autowired
	PersonRepository personRepo;

	public List<PersonEntity> getAllPersons() {

		List<PersonEntity> ps = this.personRepo.findAll();
		return ps;
	}

	public void savePerson(List<PersonEntity> persons) {

		for (PersonEntity p : persons)
			this.personRepo.save(p);

	}

	public String updatePerson(PersonEntity person) {

		Integer id = person.getId();

		Optional<PersonEntity> oPerson = this.personRepo.findById(id);

		if (oPerson.isPresent()) {

			PersonEntity personToUpdate = oPerson.get();

			personToUpdate.setAge(person.getAge());
			personToUpdate.setFavoriteColour(person.getFavoriteColour());
			personToUpdate.setFirstName(person.getFirstName());
			personToUpdate.setLastName(person.getLastName());
			personToUpdate.setHobby(person.getHobby());

			this.personRepo.save(personToUpdate);

			return "Person updated successfully";

		} else {

			return "Person not found";

		}
	}

	public void deletePerson(int id) {

		this.personRepo.deleteById(id);

	}

	public PersonEntity getById(int id) {

		return this.personRepo.findById(id).get();

	}
}
