package EMS;

import java.util.Scanner;

public class Venues {
	int type = 0;
	String name;
	int capacity;
	dbConnection db = new dbConnection();
	Scanner sc = new Scanner(System.in);
	
	public Venues() {
		System.out.println("---------------------------------------------");
		System.out.println("-------------------Venue----------------------");
		System.out.println("---------------------------------------------");
		System.out.println("Press 1 to list venues");
		System.out.println("Press 2 to add venues");
		System.out.println("Press 3 to delete venues");
		System.out.println("Press 4 to Dashboard");
		type= sc.nextInt();
		System.out.println();
		if(type==1) {
			listvenue();
		}else if(type==2) {
			addvenue();
		}else if(type==3) {
			deletevenue();
		}else {
			Dashboard dashboard = new Dashboard();
		}
	}
	
	public void listvenue() {
		db.listvenues();
		new Venues();
	}
	
	public void addvenue() {
		System.out.println("-------------------");
		System.out.println("Enter Name");
		sc.nextLine();
		name = sc.nextLine();
		System.out.println("Enter Capacity");
		capacity = sc.nextInt();
		if(name=="" || capacity<=0) {
			System.out.println("Please Provide Name and Capacity !");
			addvenue();
		}else {
			if(db.addvenues(name,capacity)==true) {
				System.out.println("Added Successfully !");
				System.out.println("-------------------");
				System.out.println("-------------------");
				new Venues();
			}
		}
		System.out.println("----------------");
		
	}
	
	public void deletevenue() {
		db.deletecustomers(1);
	}

}
