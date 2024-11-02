package EMS;

import java.util.Scanner;

public class SignUp {
	Scanner sc;
	int type;
	String name,email,pass;
	dbConnection db = new dbConnection();
	public SignUp() {
		sc = new Scanner(System.in);
		createaccount();
		
	}
	
	public void createaccount() {
		System.out.println("--------------------------------------------");
		System.out.println("Please Provide UserName: ");
		name = sc.next();
		System.out.println("Please Provide Email: ");
		email = sc.next();
		System.out.println("Please Provide Password: ");
		pass = sc.next();
		if(name!=""&&email!=""&&pass!="") {
			if(db.insertUser(name, email, pass)) {
				System.out.println("User Added Successfully ! ");
				login logn = new login();
			}else {
				System.out.println("Some Error! Try Again ");
				createaccount();
			}
		}else {
			System.out.println("Please Provide All Details ! ");
			createaccount();
		}
	}

}
