package com.example.demo.data;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.JoinColumn;

@Entity // This tells Hibernate to make a table out of this class
@Table(name="hotel")
public class Hotel {
  @Id
//  private int hotel_id;
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="hotel_id")
	private int hotel_id;

  private String country_name;

  private String city;
  
  private String name;

  @OneToMany(mappedBy="hotel")
  private Set<RoomType> roomTypes;
  @JsonBackReference
  @ManyToMany(mappedBy = "hotels", cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)

  private Set<Season> seasons;
//  public void addSeasons(Season season) {
//      seasons.add(season);
//      season.getHotels().add(this);
//  }
//  
  public Set<Season> getSeasons() {
      return seasons;
  }
//  
  
  public int getId() {
    return hotel_id;
  }

  public void setId(int id) {
    this.hotel_id = id;
  }

  public String getCountry() {
    return country_name;
  }

  public void setCountry(String name) {
    this.country_name = name;
  }
  
  public String getHotelName() {
	    return name;
	  }
  

  public String getCity() {
    return city;
  }

  public void setCity(String email) {
    this.city = email;
  }
}

