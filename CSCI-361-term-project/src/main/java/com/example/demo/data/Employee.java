package com.example.demo.data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.OffsetTime;
import java.time.Period;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Table(name="employee")
public class Employee {
	
	@Id
	private Integer employee_id;
	
	private String name;
	private String second_name;
	private String email;
	private String password;
	private String phone;
	private String employee_type;

	@JoinColumn(name="hotel_id", referencedColumnName="hotel_id")
	@ManyToOne
    private Hotel hotel;
	
	@OneToMany(mappedBy = "employee")
	private Set<Dayoff> dayoffs;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmployee_type() {
		return employee_type;
	}

	public void setEmployee_type(String employee_type) {
		this.employee_type = employee_type;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSecond_name() {
		return second_name;
	}

	public void setSecond_name(String second_name) {
		this.second_name = second_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel h) {
		hotel = h;
	}

}


