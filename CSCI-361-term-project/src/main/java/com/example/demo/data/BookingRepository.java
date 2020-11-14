package com.example.demo.data;

import java.sql.Date;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.data.Room.RoomId;

public interface BookingRepository extends CrudRepository<Booking, Integer>{
	
	@Query("select r " + 
			"from Room r " + 
			"where r.room_id in ?3 "
			+ "and r.room_id.room_type_id not in " + 
			"(" + 
			"  select b.room_type.room_type_id " + 
			"  from Booking b " + 
			"  where b.check_out > ?1" + 
			"  and b.check_in < ?2 " + 
			") and " + 
			" r.room_id.room_number not in " + 
			"(" + 
			"  select b.room_number " + 
			"  from Booking b " + 
			"  where b.check_out > ?1" + 
			"  and b.check_in < ?2 " + 
			") "
			+ "order by r.room_type.hotel.country_name, r.room_type.hotel.city")
	Iterable<Room> findAvailableRooms(Date from, Date to, Iterable<RoomId> r);
	
	@Query("select b from Booking b where (b.booking_id=?1 or ?1=null) and "
			+ "(b.guest.guest_id=?2 or ?2=null)"
			+ "and (b.guest.email=?3 or ?3=null)")
	Iterable<Booking> findSpecificBooking(Integer bid, Integer gid, String email);
	
	
	
}
