package com.example.demo.data;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.demo.data.RoomType.RoomTypeId;

@Entity
@Table(name="dayoff")
public class Dayoff implements Serializable {
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class DayoffId implements Serializable {
	   
		@Column(name = "employee_id")
		private Integer employee_id;
		
		@Column(name = "day")
		private Date day;   // corresponds to primary key type of Hotel
	   
		
		public Integer getEmployee_id() {
			return employee_id;
		}

		public void setEmployee_id(Integer employee_id) {
			this.employee_id = employee_id;
		}

		public Date getDay() {
			return day;
		}

		public void setDay(Date day) {
			this.day = day;
		}

		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        DayoffId that = (DayoffId) o;
	        return employee_id.equals(that.employee_id) &&
	        		day == that.day;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(employee_id, day);
	    }

	}
	@EmbeddedId 
	private DayoffId dayoff_id;
	
	@ManyToOne
	@MapsId("employee_id")
	@JoinColumn(name="employee_id", referencedColumnName="employee_id")
    private Employee employee;
	
	public DayoffId getDayoff_id() {
		return dayoff_id;
	}

	public void setDayoff_id(DayoffId dayoff_id) {
		this.dayoff_id = dayoff_id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dayoff that = (Dayoff) o;
        return dayoff_id.equals(that.dayoff_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayoff_id);
    }
}
