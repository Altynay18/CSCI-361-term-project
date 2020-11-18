package com.example.demo.data;
import java.util.Iterator;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

public interface GuestRepository extends CrudRepository<Guest, String>{
	
	@Query("select g from Guest g where g.email=?1")
	Optional<Guest> findByEmail(String email);

}
