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
//	        	 String user_drop = "DROP TABLE users;";
//	        	 String bookings_drop = "DROP TABLE bookings;";
//	        	 String customers_drop = "DROP TABLE customers;";
//	        	 String venues_drop = "DROP TABLE venues;";
	        	 
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
	  	                + " venue_name text NOT NULL,\n" 
	  	                + " total_persons int NOT NULL,\n" 
	  	                + " total_amount int NOT NULL,\n" 
	  	                + " event_date text NOT NULL\n"
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
	
	
	public void checkInvoice(int id) {
		
		try {
       	 stmt = conn.createStatement();
       	 String sql = "SELECT * FROM bookings where id='"+id+"'";  
       	 ResultSet rs  = stmt.executeQuery(sql);  
       	 int iid=-1,venue_id=0,total_persons=0,total_amount=0;
       	 String customer_cnic="",customer_name="",venue_name="",event_date="";
        // loop through the result set  
	        while (rs.next()) {	        	
	        	iid = rs.getInt("id");
	        	customer_cnic = rs.getString("customer_cnic");
	        	customer_name = rs.getString("customer_name");
	        	venue_id = rs.getInt("venue_id");
	        	venue_name = rs.getString("venue_name");
	        	total_persons = rs.getInt("total_persons");
	        	total_amount = rs.getInt("total_amount");
	        	event_date = rs.getString("event_date");
	        	
	        }
	        
	        Invoices.showreceipt(iid, customer_cnic, customer_name, venue_id, venue_name, total_persons, total_amount, event_date);
	        conn.close();
        	stmt.close();
	       
		}
		catch (SQLException e) {  
            System.out.println(e.getMessage());  
        } 
		
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
	       	 System.out.println("---------------------------------------------");
	       	 System.out.println("Id" +  "\t" +  "\t" + 
	                   "Name" + "\t" +  "\t" +
	                   "CNIC");
	       	 System.out.println("---------------------------------------------");
		        while (rs.next()) {  
		        	 System.out.println(rs.getInt("id") +  "\t" + "\t" +  
                             rs.getString("name") + "\t" +  "\t" +
                             rs.getString("cnic"));    
		        }  
		     System.out.println("---------------------------------------------");
		     System.out.println();
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
			       	System.out.println("---------------------------------------------");
			       	 System.out.println("Id" +  "\t" +   
			                   "Name" + "\t" +
			                   "Email" + "\t" +
			                   "Pass"); 
			       	System.out.println("---------------------------------------------");
				        while (rs.next()) {  
				        	 System.out.println(rs.getInt("id") +  "\t" +   
		                             rs.getString("name") + "\t" + 
		                             rs.getString("email") + "\t" + 
		                             rs.getString("password"));    
				        }  
				        conn.close();
				        System.out.println("---------------------------------------------");
					     System.out.println();
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
	
       
	public boolean addbooking(String cus_name, String cus_cnic,int venue_id,String venue_name,int total_persons,int total_amount,String event_date) {
		 String sql = "INSERT INTO bookings(customer_name, customer_cnic,venue_id,venue_name,total_persons,total_amount,event_date) VALUES(?,?,?,?,?,?,?)";  
	        try{
	            PreparedStatement pstmt = conn.prepareStatement(sql);  
	            pstmt.setString(1, cus_name);
	            pstmt.setString(2, cus_cnic);
	            pstmt.setInt(3, venue_id);
	            pstmt.setString(4, venue_name);
	            pstmt.setInt(5, total_persons);
	            pstmt.setInt(6, total_amount);
	            pstmt.setString(7, event_date);
	            
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
	       	System.out.println("---------------------------------------------");
	       	 System.out.println("Id" +  "\t" +  "\t" + 
                   "Name" + "\t" +  "\t" +
                   "Capacity");  
	       	System.out.println("---------------------------------------------");
		        while (rs.next()) {  
		        	 System.out.println(rs.getInt("id") +  "\t" +"\t" +   
                             rs.getString("name") + "\t" +
                             rs.getInt("capacity"));    
		        }  
		        System.out.println("---------------------------------------------");
			    System.out.println();
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
	       	 System.out.println();
	       	 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	       	 System.out.println("Id" + "\t" + "Customer" + "\t" +  "CNIC" + "\t" + "\t" + "Venue" + "\t"+ "\t"+ "Total Person" + "\t"+ "\t"+ "Total Amount" + "\t"+ "\t"+ "Event Date" + "\t");  
			 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			 while (rs.next()) {  
	        	 System.out.println(rs.getInt("id") + "\t" +
                         rs.getString("customer_name") + "\t" + "\t" +  
                         rs.getString("customer_cnic") + "\t" + "\t" +  
                         rs.getString("venue_name") + "\t" + "\t" +
                         rs.getInt("total_persons") + "\t" + "\t" +
                         rs.getInt("total_amount") + "\t" + "\t" + "\t" +
                         rs.getString("event_date") + "\t"
	        			);    
			 }
			 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			 System.out.println();
		        //conn.close();
			 rs.close();
		   
			}
			catch (SQLException e) {  
	            System.out.println(e.getMessage());  
	        } 
	}
	
	public void listinvoices() {
		try {
	       	 stmt = conn.createStatement();
	       	 String sql = "SELECT * FROM bookings";  
	       	 ResultSet rs  = stmt.executeQuery(sql);  
	       	 System.out.println();
	       	 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
	       	 System.out.println("INV Id" + "\t" + "Customer" + "\t" +  "CNIC" + "\t" + "\t" + "Venue" + "\t"+ "\t"+ "Total Person" + "\t"+ "\t"+ "Total Amount" + "\t"+ "\t"+ "Event Date" + "\t");  
			 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			 while (rs.next()) {  
	        	 System.out.println(rs.getInt("id") + "\t" +
                         rs.getString("customer_name") + "\t" + "\t" +  
                         rs.getString("customer_cnic") + "\t" + "\t" +  
                         rs.getString("venue_name") + "\t" + "\t" +
                         rs.getInt("total_persons") + "\t" + "\t" +
                         rs.getInt("total_amount") + "\t" + "\t" + "\t" +
                         rs.getString("event_date") + "\t"
	        			);    
			 }
			 System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
			 System.out.println();
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
