package EMS;

import java.sql.*;


public class main {
	public static void main(String[] args) {
		dbConnection dbconn = new dbConnection();
		
		if(dbconn.createNewTables()==true) {
			login logn = new login();
		}
		
	}

}
