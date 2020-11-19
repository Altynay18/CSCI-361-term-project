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

	@Query("select h.hotel_id from Hotel h where (h.country_name=?1 or ?1=null) and (h.city=?2 or ?2=null)")
	TreeSet<Integer> findHotelIdByCountryAndCity(String country, String city);

	@Query("select h.hotel_id from Hotel h join h.seasons s where (h.country_name=?1 or ?1=null) and "
			+ "(h.city=?2 or ?2=null) and " +
			"(s.season_name=?3 or ?3=null)")
	TreeSet<Integer> findHotelIdByCountryAndCityAndSeason(String country, String city, String season);

	@Query("select h from Hotel h join h.seasons s where (h.country_name=?1 or ?1=null) and "
			+ "(h.city=?2 or ?2=null) and " +
			"(s.season_name=?3 or ?3=null)")
	Iterable<Hotel> findHotelIdByCountryAndCityAndSeason2(String country, String city, String season);

	@Query("select s.season_name from Season s")
	Iterable<String> findSeasons();

	@Query("select h from Hotel h join h.seasons s where s.season_name = ?1")
	Iterable<Hotel> findAllSeasons(String id);
	
	@Query("select h from Hotel h where(h.name=?1 or ?1=null) and"
			+ "(h.hotel_id=?2 or ?2=null)")
	Iterable<Hotel> findSpecificHotel(String name, Integer hotelid);

//	@Query("select dev from Device dev join dev.authorizedDrivers d where d = ?1")
//	Set<Device> findDeviceByDriver(Driver driver);
}
