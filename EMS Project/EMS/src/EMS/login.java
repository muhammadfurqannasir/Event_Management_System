package EMS;

import java.util.Scanner;

public class login {

	Scanner sc;
	private int type;
	private String email,pass;
	dbConnection db = new dbConnection();
	
	public login() {
		sc = new Scanner(System.in);
		System.out.println("--------------------------------------------");
		System.out.println("-----WELCOME TO EVENT MANAGEMENT SYSTEM-----");
		System.out.println("--------------------------------------------");
		loginmethod();
		
	}
	
	private void loginmethod() {
		System.out.println("--------------------------------------------");
		System.out.println("--------------------------------------------");
		System.out.println("Press 1 to Login");
		System.out.println("Press 2 to SignUp");
		type = sc.nextInt();
		System.out.println("-------------------");
		switch(type) {
			case 1:
				checkLogin();
				break;
				
			case 2:
				SignUp signup = new SignUp();
				break;
		}
	}
	
	private void checkLogin() {
		System.out.println("User Email: ");
		email = sc.next();
		System.out.println("User Password: ");
		pass = sc.next();
		if(db.checkUser(email, pass)==true) {
			Dashboard dashboard = new Dashboard();
		}else {
			System.out.println("Wrong Id & Password");
			loginmethod();
		}
	}
}
