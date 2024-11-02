package EMS;

import java.util.Scanner;

abstract class Human {
	int type;
	Scanner sc = new Scanner(System.in);
	
	public int getType(String name) {
		System.out.println("---------------------------------------------");
		System.out.println("-----------------"+name+"--------------------");
		System.out.println("---------------------------------------------");
		System.out.println("Press 1 to list "+name);
		System.out.println("Press 2 to add "+name);
		System.out.println("Press 3 to delete "+name);
		System.out.println("Press 4 to Dashboard");
		type= sc.nextInt();
		System.out.println();
		return type;
	}
	
	public boolean addHuman(String name, String email, String pass) {
		// USERS
		dbConnection db = new dbConnection();
		return db.insertUser(name, email, pass);
	}
	
	public boolean addHuman(String name, String cnic) {
		// CUSTOMER
		dbConnection db = new dbConnection();
		return db.addcustomers(name, cnic);
	}
	
	abstract void getList();
	
	abstract boolean deleteHuman(int id);
	
}
