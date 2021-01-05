package com.project.TabernasSevilla.forms;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.project.TabernasSevilla.domain.Establishment;

import lombok.Data;


@Data
public class BookingForm {
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate placementDate; //IMPORTANTE ES LOCALDATE, NO LOCALDATETIME
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private LocalDate reservationDate;
	private Integer seating;
	private String hourDate;
	
	private String contactPhone;
	
	private String notes;
	private Establishment location;
	
	public Establishment getLocation() {
		return this.location;
	}
	
	public void setLocation(Establishment e) {
		Establishment eso = new Establishment();
		this.location = eso;
	}
	
	//aqui hay que añadir un pedido que es opcional
	// Y LOCATION
	

}
