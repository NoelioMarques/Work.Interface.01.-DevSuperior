package model.services;

import java.time.Duration;

import Entities.CarRental;
import Entities.Invoice;

public class RentalService {
	private Double pricePerHour;
	private Double pricePerDay; 
	
	private TaxService taxService;

	public RentalService(Double pricePerHour, Double pricePerDay,TaxService taxService) {
		this.pricePerHour = pricePerHour;
		this.pricePerDay = pricePerDay;
		this.taxService = taxService;
	} 
	
	public void processInvoice(CarRental carRental) {
		
		double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes() ;
		double hours = minutes/60;
		
		double basicpayment; 
		if(hours <= 12) {
			basicpayment = pricePerHour * Math.ceil(hours);
			
		} else {
			basicpayment = pricePerDay * Math.ceil(hours/24);
		}
		
		double tax = taxService.tax(basicpayment);
		
		carRental.setInvoice(new Invoice(basicpayment, tax));
	}
	
	
}
