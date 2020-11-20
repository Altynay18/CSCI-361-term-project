package com.example.demo.data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.demo.data.Room.RoomId;

@Entity
@Table(name="booking")
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer booking_id;
	
	@Column(name = "booking_date")
	private Date booking_date;

	private Date check_in;
	
	private Date check_out;
	
	private Integer period;
	
	private Integer bill;
	
	@JoinColumns({
		@JoinColumn(name="room_number", referencedColumnName="room_number"),
		@JoinColumn(name="room_type_name", referencedColumnName="room_type_name"),
		@JoinColumn(name="hotel_id", referencedColumnName="hotel_id")
	})
	@ManyToOne
    private Room room;
	
	@JoinColumn(name="guest_id", referencedColumnName="guest_id")
	@ManyToOne
    private Guest guest;
	
	public Integer getBookingId() {
		return booking_id;
	}

	public void setBookingId(Integer booking_id) {
		this.booking_id = booking_id;
	}
	
	public Date getFromDate() {
		return this.check_in;
	}
	
	public void setFromDate(Date d) {
		this.check_in = d;
	}
	
	public Date getToDate() {
		return this.check_out;
	}
	
	public void setToDate(Date d) {
		this.check_out = d;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room id) {
		this.room = id;
	}
	
	public Guest getGuest() {
		return this.guest;
	}
	
	public void setGuest(Guest g) {
		this.guest = g;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking that = (Booking) o;
        return booking_id.equals(that.booking_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(booking_id);
    }

	public Integer getBill() {
		return bill;
	}

	public void setBill(Integer bill) {
		this.bill = bill;
	}

	public Date getBookingDate() {
		return booking_date;
	}

	public void setBookingDate(Date date) {
		this.booking_date = date;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}
    
}



