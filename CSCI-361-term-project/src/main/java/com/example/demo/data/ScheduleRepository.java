package com.example.demo.data;

import java.util.Optional;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {

	@Query("select h.employee_type from Employee h" )
    TreeSet<String> findTypesOnly();
	
	@Query("select h from Employee h where h.employee_id=?1" )
    Optional<Employee> findEmployee(Integer id);
	
	@Query("select e from Schedule e where (e.employee.employee_id=?1 or ?1=null) "
			+ "and (e.employee.name=?2 or ?2='' or ?2=null) "
	  		+ "and (e.employee.second_name=?3 or ?3='' or ?3=null) " 
			+ "and (e.employee.employee_type=?4 or ?4='' or ?4=null) "
			+ "and (e.employee.hotel.name=?5 or ?5='') or ?5=null")	 
    Iterable<Schedule> findEmployeeList(Integer id, String name, String surname, String type, String hotel);
}
