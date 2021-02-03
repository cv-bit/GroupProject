package com.cd.dtstanley.carsgroup.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cd.dtstanley.carsgroup.models.Title;


@Repository
public interface TitleRepository extends CrudRepository<Title, Long>{
	List<Title> findAll();
}
//Get these free with CrudRepository
//C - Create
//R - Read
//U - Update
//D – Delete

