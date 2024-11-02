package EMS;

import java.util.Scanner;

public class Dashboard {
	int type =0;
	Scanner sc = new Scanner(System.in);
	public Dashboard() {
		System.out.println("--------------------------------------------");
		System.out.println("---------------DASHBOARD--------------------");
		System.out.println("--------------------------------------------");
		System.out.println("Press 1 for Customers");
		System.out.println("Press 2 for Venues");
		System.out.println("Press 3 for Bookings");
		System.out.println("Press 4 for Users");
		System.out.println("Press 5 for Invoices");
		type = sc.nextInt();
		if(type==1) {
			Customer custm = new Customer();
		}else if(type==2) {
			Venues custm = new Venues();
		}else if(type==3) {
			Bookings custm = new Bookings();
		}else if(type==4) {
			Users custm = new Users();
		}else if(type==5) {
			Invoices invoices = new Invoices();
		}
	}

}
