package EMS;

import java.util.Scanner;


public class Users extends Human {
	private int type = 0,user_id;
	private String name,email,pass;
	dbConnection db = new dbConnection();
	Scanner sc = new Scanner(System.in);
	

	public Users() {
		type = getType("User");
		if(type==1) {
			getList();
		}else if(type==2) {
			addusers();
		}else if(type==3) {
			deleteuser();
		}else {
			Dashboard dashboard = new Dashboard();
		}
	}
	
	private void addusers() {
		System.out.println("-------------------");
		System.out.println("Enter Name");
		name = sc.next();
		System.out.println("Enter Email");
		email = sc.next();
		System.out.println("Enter Password");
		pass = sc.next();
		if(name=="" || email=="" || pass=="") {
			System.out.println("Please Provide all details !");
			addusers();
		}else {
			if(addHuman(name,email,pass)==true) {
				System.out.println("Added Successfully !");
				System.out.println("-------------------");
				System.out.println("-------------------");
				new Users();
			}
		}
		System.out.println("----------------");
		
	}
	
	private void deleteuser() {
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
		db.listusers();
		new Users();
		
	}

	@Override
	boolean deleteHuman(int id) {
		return db.deletecustomers(1);
	}
	
}

	




