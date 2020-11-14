package com.example.demo.data;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import com.example.demo.data.RoomType.RoomTypeId;

@SuppressWarnings("serial")
@Entity
@Table(name="booking")
public class Booking implements Serializable {
	
//	@SuppressWarnings("serial")
//	@Embeddable
//	public static class BookingId implements Serializable {
//		
//		@Column(name = "booking_id")
//		private Integer booking_id;
//		
//		@Column(name = "guest_id")
//		private Integer guest_id;
//
//		public Integer getBookingId() {
//			return booking_id;
//		}
//
//		public void setBookingId(Integer booking_id) {
//			this.booking_id = booking_id;
//		}
//		
//		public Integer getGuestId() {
//			return guest_id;
//		}
//
//		public void setGuestId(Integer booking_id) {
//			this.guest_id = booking_id;
//		}
//		
//		@Override
//	    public boolean equals(Object o) {
//	        if (this == o) return true;
//	        if (o == null || getClass() != o.getClass()) return false;
//	        BookingId that = (BookingId) o;
//	        return booking_id == that.booking_id &&
//	        		guest_id == that.guest_id;
//	    }
//
//	    @Override
//	    public int hashCode() {
//	        return Objects.hash(booking_id, guest_id);
//	    }
//	}
//	
//	@EmbeddedId 
//	private BookingId booking_id;
//	
//	@MapsId("guest_id")
	@JoinColumn(name="guest_id", referencedColumnName="guest_id")
	@ManyToOne
    private Guest guest;
	
	@Id
//	private Integer booking_id;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="booking_id")
	private Integer booking_id;
	
	@Column(name = "booking_date")
	private Date booking_date;

	private Date check_in;
	
	private Date check_out;
	
	@Column(name = "room_number")
	private Integer room_number;
	
	private Integer bill;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="room_type_name", referencedColumnName="room_type_name"),
		@JoinColumn(name="hotel_id", referencedColumnName="hotel_id")
	})
    private RoomType room_type;
	
	public Integer getBookingId() {
		return booking_id;
	}

	public void setBookingId(Integer booking_id) {
		this.booking_id = booking_id;
	}

//	public BookingId getBookingId() {
//		return this.booking_id;
//	}
//	
//	public void setBookingId(BookingId id) {
//		this.booking_id = id;
//	}
	
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
	
	public Integer getRoomNumber() {
		return room_number;
	}

	public void setRoomNumber(Integer id) {
		this.room_number = id;
	}
	
	public RoomType getRoomType() {
		return room_type;
	}

	public void setRoomType(RoomType id) {
		this.room_type = id;
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

	public void setBookingDate(Date booking_date) {
		this.booking_date = booking_date;
	}
    
}



