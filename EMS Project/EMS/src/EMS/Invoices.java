package EMS;

import java.util.Scanner;

public class Invoices {
	int type = 0,venue_id,invoice_id;
	String cus_cnic,cus_name,venue_name;
	dbConnection db = new dbConnection();
	Scanner sc = new Scanner(System.in);
	
	public Invoices() {
		System.out.println("--------------------------------------------");
		System.out.println("-----------------INVOICES-------------------");
		System.out.println("--------------------------------------------");
		System.out.println("Press 1 to list all invoices");
		System.out.println("Press 2 for invoice Receipt");
		System.out.println("Press 3 to Dashboard");
		type= sc.nextInt();
		System.out.println();
		
		if(type==1) {
			listinvoices();
		}else if(type==2) {
			checkInvoice();
		}else if(type==3) {
			Dashboard dashboard = new Dashboard();
		}
	}
	
	public void listinvoices(){
		db.listbookings();
		new Invoices();
	}
	
	public void checkInvoice(){
		System.out.println("Please Provide Invoice ID:");
		invoice_id = sc.nextInt();
		db.checkInvoice(invoice_id);
		
	}


	public static void showreceipt(int id, String cnic,String name, int venue_id, String venue_name, int total_persons,int total_amount,String event_date){
		
		if(id==-1) {
			System.out.println("No Invoice Found !");
		}else {
			System.out.println("************************************INVOICE*****************************************");
			System.out.println("************************************************************************************");
			System.out.println("*                                                                                  *");
			System.out.println("*                                                                                  *");
			System.out.println("*	Invoice No: "+id+"                                                             *");
			System.out.println("*                                                                                  *");
			System.out.println("*	Customer CNIC: "+cnic+"                                                        *");
			System.out.println("*                                                                                  *");
			System.out.println("*	Customer Name: "+name+"                                                        *");                        
			System.out.println("*                                                                                  *");
			System.out.println("*	Venue Name: "+venue_name+"	                                                   *");
			System.out.println("*                                                                                  *");
			System.out.println("*	Total Persons Event: "+total_persons+"                                         *");
			System.out.println("*                                                                                  *");
			System.out.println("*	Event Date: "+event_date+"                                                     *");
			System.out.println("*                                                                                  *");
			System.out.println("*                                                                                  *");
			System.out.println("*                                                                                  *");
			System.out.println("*                                                                                  *");
			System.out.println("*                                            Total Amount: "+total_amount+" 	   *");
			System.out.println("*                                                                                  *");
			System.out.println("************************************************************************************");
		}
		
		new Invoices();
	}
	
}
