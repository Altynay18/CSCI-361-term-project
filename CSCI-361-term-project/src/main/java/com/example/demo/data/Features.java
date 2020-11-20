package com.example.demo.data;

import java.io.Serializable;
import java.sql.Date;
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

import com.example.demo.data.Dayoff.DayoffId;
import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
@Table(name="hotelfeatures")
public class Features implements Serializable {
	
	@SuppressWarnings("serial")
	@Embeddable
	public static class FeatureId implements Serializable {
	   
		@Column(name = "hotel_id")
		private Integer hotel_id;
		
		@Column(name = "feature")
		private String feature;   // corresponds to primary key type of Hotel
	   
		

		public Integer getHotel_id() {
			return hotel_id;
		}

		public void setHotel_id(Integer hotel_id) {
			this.hotel_id = hotel_id;
		}

		public String getFeature() {
			return feature;
		}

		public void setFeature(String feature) {
			this.feature = feature;
		}

		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        FeatureId that = (FeatureId) o;
	        return hotel_id.equals(that.hotel_id) &&
	        		feature == that.feature;
	    }

	    @Override
	    public int hashCode() {
	        return Objects.hash(hotel_id, feature);
	    }

	}

    @EmbeddedId
    private FeatureId feature_id;

    public FeatureId getFeature_id() {
		return feature_id;
	}

	public void setFeature_id(FeatureId feature_id) {
		this.feature_id = feature_id;
	}

	@JsonManagedReference
    @MapsId("hotel_id")
    @JoinColumn(name="hotel_id", referencedColumnName="hotel_id")
    @ManyToOne
    private Hotel hotel;

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
        Features that = (Features) o;
        return feature_id.equals(that.feature_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(feature_id);
    }
}
