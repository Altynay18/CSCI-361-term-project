package com.example.demo.data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

public interface GuestRepository extends CrudRepository<Guest, Integer>{

}
