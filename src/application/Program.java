package application;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Scanner;

import Entities.CarRental;
import Entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

public class Program {

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		System.out.println("Enter rental details");
		System.out.println();
		System.out.print("Car Model: ");
		String carModel = read.nextLine();
		System.out.print("Withdrawal: (dd/MM/yyyy  hh:mm)");
		LocalDateTime start = LocalDateTime.parse(read.nextLine(), fmt);
		System.out.print("Devolution (dd/MM/yyyy  hh:mm)");
		LocalDateTime finish = LocalDateTime.parse(read.nextLine(), fmt);
		
		CarRental cr = new CarRental(start, finish,new Vehicle(carModel));
		
		System.out.print("Enter the Hour Price: ");
		Double pricePerHour = read.nextDouble();
		System.out.print("Enter the Day Price: ");
		Double pricePerDay = read.nextDouble();
		
		RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
		rentalService.processInvoice(cr);
		
		System.out.println("Invoice");
		System.out.println("Basic Payment: " + cr.getInvoice().getBasicPayment());
		System.out.println("TAX: " + cr.getInvoice().getTax());
		System.out.println("Total Payment: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
	}

}
