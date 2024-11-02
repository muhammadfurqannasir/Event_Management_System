package EMS;

import java.util.Scanner;

public class Bookings {
	int type = 0,venue_id,total_person,total_amount;
	String cus_cnic,cus_name,venue_name,event_date;
	dbConnection db = new dbConnection();
	Scanner sc = new Scanner(System.in);
	
	public Bookings() {
		System.out.println("--------------------------------------------");
		System.out.println("-----------------BOOKINGS-------------------");
		System.out.println("--------------------------------------------");
		System.out.println("Press 1 to list booking");
		System.out.println("Press 2 to add booking");
		System.out.println("Press 3 to delete booking");
		System.out.println("Press 4 to Dashboard");
		type= sc.nextInt();
		System.out.println();
		
		if(type==1) {
			listbookings();
		}else if(type==2) {
			addbookings();
		}else if(type==3) {
			//deletecustomers();
		}else if(type==4) {
			Dashboard dashboard = new Dashboard();
		}
	}
	public void listbookings() {
		db.listbookings();
		new Bookings();
	}
	
	public void addbookings() {
		System.out.println("");
		System.out.println("------------------CUSTOMERS-------------------");
		db.listcustomers();
		System.out.println("----------------------------------------------");
		System.out.println("");
		System.out.println("");
		System.out.println("-------------------VENUES---------------------");
		db.listvenues();
		System.out.println("----------------------------------------------");
		
		System.out.println("");
		System.out.println("");
		
		System.out.println("Enter Customer Name(String): ");
		sc.nextLine();
		cus_name = sc.nextLine();
		
		System.out.println("Enter Customer CNIC(String): ");
		cus_cnic = sc.next();
		
		System.out.println("Enter Venue Name(String): ");
		sc.nextLine();
		venue_name = sc.nextLine();
		
		System.out.println("Enter Venue Id(Int): ");
		venue_id = sc.nextInt();
		
		System.out.println("Enter Total Persons(Int): ");
		total_person = sc.nextInt();
		
		System.out.println("Enter Total Amount(Int): ");
		total_amount = sc.nextInt();
		
		System.out.println("Enter Event Date(String)(DD-MM-YYYY): ");
		event_date = sc.next();
		
		if(cus_name=="" || cus_cnic =="" ||venue_name =="" ||venue_id <=0 ||total_person <=0 ||total_amount <=0 ||event_date =="" ) {
			System.out.println("Please Provide All Details !");
			addbookings();
		}else {
			if(db.addbooking(cus_name,cus_cnic,venue_id,venue_name,total_person,total_amount,event_date)==true) {
				System.out.println("Added Successfully !");
				System.out.println("-------------------");
				System.out.println("-------------------");
				new Bookings();
			}
		}
		System.out.println("----------------");
		
	}
	
	public void deletebooking() {
		db.deletecustomers(1);
	}
}
