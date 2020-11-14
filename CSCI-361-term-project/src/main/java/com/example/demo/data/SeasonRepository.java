package com.example.demo.data;


import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeasonRepository extends CrudRepository<Season, String> {
	@Query("select h.season_name from Season h" )
    TreeSet<String> findSeasonNamesOnly();
	
}
