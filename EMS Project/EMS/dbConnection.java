package EMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class dbConnection {
	private Connection conn = null;
	private Statement stmt = null;
	
	public dbConnection() {
		try{	
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:C:\\MYSQLDB\\db.db");

		}catch(Exception e){

			System.out.println(e.toString());

		}
	}
	
	public boolean createNewTables() {
		
			try {
	        	 stmt = conn.createStatement();
	        	 String user_drop = "DROP TABLE users;";
	        	 String bookings_drop = "DROP TABLE bookings;";
	        	 String customers_drop = "DROP TABLE customers;";
	        	 String venues_drop = "DROP TABLE venues;";
	        	 
	        	 String user = "CREATE TABLE IF NOT EXISTS users (\n"  
	 	                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
	 	                + " name text NOT NULL,\n"  
	 	                + " email text NOT NULL,\n"
	 	               	+ " password text NOT NULL\n"
	 	                + ");";
	        	 String bookings = "CREATE TABLE IF NOT EXISTS bookings (\n"  
	  	                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
	  	                + " customer_cnic text NOT NULL,\n" 
	  	                + " customer_name text NOT NULL,\n" 
	  	                + " venue_id integer NOT NULL,\n" 
	  	                + " venue_name text NOT NULL\n" 
	  	                + ");";
	        	 String customers = "CREATE TABLE IF NOT EXISTS customers (\n"  
	   	                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
	   	                + " name text NOT NULL,\n"  
	   	                + " cnic text NOT NULL\n"
	   	                + ");";
	        	 String venues = "CREATE TABLE IF NOT EXISTS venues (\n"  
	  	                + " id integer PRIMARY KEY AUTOINCREMENT,\n"  
	  	                + " name text NOT NULL,\n"  
	  	                + " capacity integer\n"  
	  	                + ");";
	        	 
	        	 
//	        	stmt.execute(user_drop);
//	        	stmt.execute(bookings_drop);
//	        	stmt.execute(customers_drop);
//	        	stmt.execute(venues_drop);
//	        	 
				stmt.execute(user);
				stmt.execute(bookings);
				stmt.execute(customers);
				stmt.execute(venues);
				
				conn.close();
				stmt.close();
				return true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
			}
			
		
		return false;
	}

	public boolean checkUser(String email, String pass) {
		try {
       	 stmt = conn.createStatement();
       	 String sql = "SELECT * FROM users where email='"+email+"' and password='"+pass+"'";  
       	 ResultSet rs  = stmt.executeQuery(sql);  
        
        // loop through the result set  
	        while (rs.next()) {
	        	conn.close();
	        	stmt.close();
	        	return true;  
	        }  
	        
		}
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
		
		return false;
	}
	
	public boolean insertUser(String name,String email,String password) {
		 String sql = "INSERT INTO users(name, email, password) VALUES(?,?,?)";  
	        try{
				
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, name);  
	            pstmt.setString(2, email);  
	            pstmt.setString(3, password);  
	            pstmt.executeUpdate();
	            conn.close();
	            pstmt.close();
	            return true;
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return false;
	}

	public void listcustomers() {
		
		try {
	       	 stmt = conn.createStatement();
	       	 String sql = "SELECT * FROM customers";  
	       	 ResultSet rs  = stmt.executeQuery(sql); 
	       	System.out.println("Id" +  "\t" +   
	                   "Name" + "\t" +  
	                   "CNIC");  
		        while (rs.next()) {  
		        	 System.out.println(rs.getInt("id") +  "\t" +   
                             rs.getString("name") + "\t" +  
                             rs.getString("cnic"));    
		        }  
		      
		        rs.close();   
		 
			}
			catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        } 
	}
	
	public void listusers() {
				
				try {
					if(conn.isClosed()) {
						new dbConnection();
					}
			       	 stmt = conn.createStatement();
			       	 String sql = "SELECT * FROM users";  
			       	 ResultSet rs  = stmt.executeQuery(sql); 
			       	 System.out.println("Id" +  "\t" +   
			                   "Name" + "\t" +
			                   "Email" + "\t" +
			                   "Pass");  
				        while (rs.next()) {  
				        	 System.out.println(rs.getInt("id") +  "\t" +   
		                             rs.getString("name") + "\t" + 
		                             rs.getString("email") + "\t" + 
		                             rs.getString("password"));    
				        }  
				        conn.close();
				        rs.close();
				       
					}
					catch (SQLException e) {  
			            System.out.println(e.getMessage());  
			        } 
			}
	
	public boolean addcustomers(String name, String cnic) {
		 String sql = "INSERT INTO customers(name, cnic) VALUES(?,?)";  
	        try{
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, name);  
	            pstmt.setString(2, cnic);  
	            pstmt.executeUpdate();
	            conn.close();
	            pstmt.close();
	            return true;
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return false;
	}
	
	public boolean addvenues(String name, int capacity) {
		 String sql = "INSERT INTO venues(name, capacity) VALUES(?,?)";  
	        try{
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, name);  
	            pstmt.setInt(2, capacity);  
	            pstmt.executeUpdate();
	            conn.close();
	            pstmt.close();
	            return true;
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return false;
	}
	
	public boolean addbooking(String cus_name, String cus_cnic,int venue_id,String venue_name) {
		 String sql = "INSERT INTO bookings(customer_name, customer_cnic,venue_id,venue_name) VALUES(?,?,?,?)";  
	        try{
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, cus_name);
	            pstmt.setString(2, cus_cnic);
	            pstmt.setInt(3, venue_id);
	            pstmt.setString(4, venue_name);
	          
	            pstmt.executeUpdate();
	            conn.close();
	            pstmt.close();
	            return true;
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return false;
	}
	
	
	public void listvenues() {
		try {
	       	 stmt = conn.createStatement();
	       	 String sql = "SELECT * FROM venues";  
	       	 ResultSet rs  = stmt.executeQuery(sql);  
	       	 System.out.println("Id" +  "\t" +   
                   "Name" + "\t" +  
                   "Capacity");  
		        while (rs.next()) {  
		        	 System.out.println(rs.getInt("id") +  "\t" +   
                             rs.getString("name") + "\t" +  
                             rs.getInt("capacity"));    
		        }  
		        //conn.close();
		        rs.close();
		   
		 
			}
			catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        } 
	}
	
	public void listbookings() {
		try {
	       	 stmt = conn.createStatement();
	       	 String sql = "SELECT * FROM bookings";  
	       	 ResultSet rs  = stmt.executeQuery(sql);  
	       	 System.out.println("Id" +  "\t" +   
                   "Cus Name" + "\t" +  
                   "CNIC" + "\t" +  
                   "Venue" + "\t");  
		        while (rs.next()) {  
		        	 System.out.println(rs.getInt("id") +  "\t" +   
                             rs.getString("customer_name") + "\t" +  
                             rs.getString("customer_cnic") + "\t" +  
                             rs.getInt("venue_name") 
		        			);    
		        }  
		        //conn.close();
		        rs.close();
		   
		 
			}
			catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        } 
	}
	
	public boolean deletecustomers(int id) {
		 String sql = "Delete from customers where id = "+id;  
	        try{
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.executeUpdate();
	            return true;
	        } catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        }  
	        return false;
	}
}
