package EMS;

import java.util.Scanner;

public class Customer extends Human{

	private int type = 0,user_id;
	private String name,cnic;
	dbConnection db = new dbConnection();
	Scanner sc = new Scanner(System.in);
	public Customer() {
		type = getType("Customer");
		
		if(type==1) {
			getList();
		}else if(type==2) {
			addcustomers();
		}else if(type==3) {
			deletecustomers();
		}else {
			Dashboard dashboard = new Dashboard();
		}
	}
	
	
	private void addcustomers() {
		System.out.println("-------------------");
		System.out.println("Enter Customer Name");
		name = sc.next();
		System.out.println("Enter Customer CNIC");
		cnic = sc.next();
		if(name=="" || cnic=="") {
			System.out.println("Please Provide Name and Email !");
			addcustomers();
		}else {
			if(addHuman(name,cnic)==true) {
				System.out.println("Added Successfully !");
				System.out.println("-------------------");
				System.out.println("-------------------");
				new Customer();
			}
		}
		System.out.println("----------------");
		
	}
	
	
	private void deletecustomers() {
		System.out.println("-------------------");
		System.out.println("Enter ID: ");
		user_id = sc.nextInt();
		if(deleteHuman(user_id)) {	
			System.out.println("Deleted Successfully !");
			new Users();
		}else {
			System.out.println("No Id Exists Successfully !");
			new Users();
		}
	}

	@Override
	void getList() {
		db.listcustomers();
		new Customer();
		
	}


	@Override
	boolean deleteHuman(int id) {
		return db.deletecustomers(1);
	}

}
