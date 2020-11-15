package com.example.demo.data;

import java.sql.Date;
import java.util.List;
import java.util.TreeSet;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface DayoffRepository extends CrudRepository<Dayoff, Integer> {
	@Query("select d from Dayoff d where d.dayoff_id.employee_id = ?1 "
			+ "order by d.dayoff_id.day")
	List<Dayoff> findByEmployeeId(Integer id);
	
	@Query("select d from Dayoff d where d.dayoff_id.employee_id = ?1 "
			+ "and month(d.dayoff_id.day) = month(curdate()) "
			+ "order by d.dayoff_id.day")
	List<Dayoff> findThisMonthDayoffs(Integer id);

}
