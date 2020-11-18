package com.example.demo.data;

import java.io.Serializable;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="room_type")
public class RoomType implements Serializable {
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class RoomTypeId implements Serializable {
	   
		@Column(name = "room_type_name")
//		@Enumerated(EnumType.STRING)
		private String room_type_name;
		
		@Column(name = "hotel_id")
		private int hotel_id;   // corresponds to primary key type of Hotel
	   
		public String getRoom_type_name() {
			return room_type_name;
		}

		public void setRoom_type_name(String id) {
			this.room_type_name = id;
		}
		
		public Integer getHotel_id() {
			return hotel_id;
		}

		public void setHotel_id(Integer id) {
			this.hotel_id = id;
		}
		
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        RoomTypeId that = (RoomTypeId) o;
	        return room_type_name.equals(that.room_type_name) &&
	        		hotel_id == that.hotel_id;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(room_type_name, hotel_id);
	    }

	}

	
	@EmbeddedId 
	private RoomTypeId room_type_id;

	private Integer price;

	private Integer capacity;
	
	@MapsId("hotel_id")
	@JoinColumn(name="hotel_id", referencedColumnName="hotel_id")
	@ManyToOne
    private Hotel hotel;
	
	@OneToMany(mappedBy = "room_type")
	private Set<Room> rooms;

	public RoomTypeId getRoom_type_id() {
		return room_type_id;
	}

	public void setRoom_type_id(RoomTypeId id) {
		this.room_type_id = id;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer id) {
		this.price = id;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer id) {
		this.capacity = id;
	}
    
	public Hotel getHotel() {
		return hotel;
	}

	public void setHotel(Hotel h) {
		hotel = h;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomType that = (RoomType) o;
        return room_type_id.equals(that.room_type_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_type_id);
    }
    
}

