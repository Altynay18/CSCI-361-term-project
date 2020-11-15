package com.example.demo.data;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="e_schedule")
public class Schedule {
	
	@Id
	@Column(name = "employee_id")
	private Integer employee_id;

	public Integer getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

	private Time from_hour;
	private Time to_hour;
	private String weekdays;
	private Integer salary_per_hour;
	private Integer salary_per_month;
	
	@OneToOne
	@MapsId
	@JoinColumn(name="employee_id", referencedColumnName="employee_id")
    private Employee employee;
	
	
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	public Time getFrom() {
		return from_hour;
	}
	public void setFrom(Time from) {
		this.from_hour = from;
	}
	public Time getTo() {
		return to_hour;
	}
	public void setTo(Time to) {
		this.to_hour = to;
	}
	public String getWeekdays() {
		return weekdays;
	}
	public void setWeekdays(String weekdays) {
		this.weekdays = weekdays;
	}
	public Integer getSalary_per_hour() {
		return salary_per_hour;
	}
	public void setSalary_per_hour(Integer salary_per_hour) {
		this.salary_per_hour = salary_per_hour;
	}
	public Integer getSalary_per_month() {
		return salary_per_month;
	}
	public void setSalary_per_month(Integer salary_per_month) {
		this.salary_per_month = salary_per_month;
	}
	
	public List<String> getFullWeekdays() {
		List<String> weekdayss = new ArrayList<>();
		
		for ( int i = 0; i<weekdays.length(); i++ ) {
			if (weekdays.charAt(i) == 'M') {
				weekdayss.add("Monday");
			} else if (weekdays.charAt(i) == 'T') {
				weekdayss.add("Tuesday");
			} else if (weekdays.charAt(i) == 'W') {
				weekdayss.add("Wednesday");
			} else if (weekdays.charAt(i) == 'R') {
				weekdayss.add("Thursday");
			} else if (weekdays.charAt(i) == 'F') {
				weekdayss.add("Friday");
			} else if (weekdays.charAt(i) == 'S') {
				weekdayss.add("Sunday");
			} else {
				weekdayss.add("Saturday");
			}
		}
		return weekdayss;
	}
	
	public int getWorkedDays() {
		int res = 0;
		List<String> weekdays = getFullWeekdays();
		LocalDate now = LocalDate.now(); 
		LocalDate then = LocalDate.of(now.getYear(), now.getMonth(), 1);
		for(String s : weekdays) {
		    LocalDate first = then.with(TemporalAdjusters.next(DayOfWeek.valueOf(s.toUpperCase())));
		    LocalDate last = now.with(TemporalAdjusters.previous(DayOfWeek.valueOf(s.toUpperCase())));
		    res += (int) ChronoUnit.WEEKS.between(first, last) + 1;
		}
		
		return res;
	}
	
	public LocalDate getCurDate() {
		return LocalDate.now();
	}
	
	public int hourPerDay() {
	//	int res = Math.abs(getTo_schedule() - getFrom_schedule());

		Duration p = Duration.between(getFrom().toLocalTime(), getTo().toLocalTime());
		int res = p.toHoursPart();
		if (res < 0) {
			res += 24;
		}
		return res;
	}
	
	public int totalSalary() {
		
		return getSalary_per_hour()*hourPerDay()*getWorkedDays();
	}
	
}
