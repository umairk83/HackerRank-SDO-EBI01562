package com.example.hackerrank.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.hackerrank.entities.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer>  {
	
	List<PersonEntity> findAll();
}
