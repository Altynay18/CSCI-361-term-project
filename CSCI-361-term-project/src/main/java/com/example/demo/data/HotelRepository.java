package com.example.demo.data;

import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface HotelRepository extends CrudRepository<Hotel, Integer> {
	
	@Query("select h.country_name from Hotel h" )
    TreeSet<String> findCountriesOnly();
	
	@Query("select h.city from Hotel h" )
	TreeSet<String> findCitiesOnly();
	
	@Query("select h.name from Hotel h" )
	TreeSet<String> findNamesOnly();
	
	@Query("select h.city from Hotel h where h.country_name=?1" )
	TreeSet<String> findCitiesByCountry(String country);
	
	@Query("select h.hotel_id from Hotel h where (h.country_name=?1 or ?1=null) and (h.city=?2 or ?2=null) ")
	TreeSet<Integer> findHotelIdByCountryAndCity(String country, String city);
}

