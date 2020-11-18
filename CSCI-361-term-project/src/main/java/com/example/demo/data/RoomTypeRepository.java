package com.example.demo.data;

import java.util.Set;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.RoomType.RoomTypeId;

public interface RoomTypeRepository extends CrudRepository<RoomType, RoomTypeId> {
	
	@Query("select h.room_type_id.room_type_name from RoomType h" )
    TreeSet<String> findRoomTypesOnly();
	
	@Query("select h.room_type_id.room_type_name from RoomType h where h.room_type_id.hotel_id in ?1" )
	TreeSet<String> findRoomTypeByHotelId(Set<Integer> hotelId);
}
