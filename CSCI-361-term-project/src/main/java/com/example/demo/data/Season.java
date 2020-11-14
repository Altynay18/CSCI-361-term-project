package com.example.demo.data;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
@SuppressWarnings("serial")
@Entity // This tells Hibernate to make a table out of this class
@Table(name="season")
public class Season implements Serializable {
	  @Id
	  @Column(name="name")
	  private String season_name;
	  @Column(name = "start_date")
	  private Date start_date;
	  @Column(name = "end_date")
	  private Date end_date;
	  @Column(name = "rate")
	  private double rate;
	  
	  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	  @JoinTable(
	          name = "hotel_has_season",
	          joinColumns = {@JoinColumn(name = "season_name")},
	          inverseJoinColumns = {@JoinColumn(name = "hotel_id")}
	  )

	  private Set<Hotel> hotels;
	  
	  
	  public Set<Hotel> getHotels() {
	        return hotels;
	    }
	  
//	  
	  public void addHotel(Hotel hotel) {
	        this.hotels.add(hotel);
	        hotel.getSeasons().add(this);
	  }
//	  
//	  public void removeHotel(Hotel hotel) {
//		  this.getHotels().remove(hotel);
//	      hotel.getSeasons().remove(this);
//	    }
//	  
//	   public void removeHotels() {
//	        for (Hotel hotel : new HashSet<>(hotels)) {
//	            removeHotel(hotel);
//	     }
//	    }

	  
	  
	  public String getSeasonName() {
		  return this.season_name;
	  }
	  public void setSeasonName(String name) {
		  this.season_name = name;
	  }
	  
	  public Date getStartDate() {
		  return this.start_date;
	  }
	  
	  public void setStartDate(Date date) {
		  this.start_date = date;
	  }
	  
	  public Date getEndDate() {
		  return this.end_date;
	  }
	  
	  public void setEndDate(Date date) {
		  this.end_date = date;
	  }
	  
	  public void setRate(double rate1) {
		   this.rate = rate1;
	  }
	  
	  public double getRate() {
		  return this.rate;
	  }


	  
}
