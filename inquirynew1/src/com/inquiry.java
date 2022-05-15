package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement; 

public class inquiry 
{ //A common method to connect to the DB
		private Connection connect(){ 
			
						Connection con = null; 
						
						try{ 
								Class.forName("com.mysql.jdbc.Driver"); 
 
								//Provide the correct details: DBServer/DBName, username, password 
								con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inquiry_database", "root", ""); 
						} 
						catch (Exception e) {
							e.printStackTrace();
							} 
						
						return con; 
			} 
		
		
		public String insertinquiry(String code, String name, String price, String desc){ 
			
					String output = ""; 
					
					try
					{ 
						Connection con = connect(); 
						
						if (con == null) 
						{
							return "Error while connecting to the database for inserting."; 
							
						} 
						// create a prepared statement
						
						String query = " insert into inquirys (`inquiryID`,`inquiryCode`,`inquiryName`,`inquiryPrice`,`inquiryDescription`)"+" values (?, ?, ?, ?, ?)"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, 0); 
						preparedStmt.setString(2, code); 
						preparedStmt.setString(3, name); 
						preparedStmt.setDouble(4, Double.parseDouble(price)); 
						preparedStmt.setString(5, desc); 
						// execute the statement
 
						preparedStmt.execute(); 
						con.close(); 
						
						String newinquirys = readinquirys(); 
						output = "{\"status\":\"success\",\"data\":\""+newinquirys+"\"}"; 
					} 
					
					catch (Exception e) 
					{ 
						output = "{\"status\":\"error\", \"data\":\"Error while inserting the inquiry.\"}"; 
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
		
		
		
		public String readinquirys() 
		{ 
			String output = ""; 
			try
			{ 
				Connection con = connect(); 
		 if (con == null) 
		 { 
		 return "Error while connecting to the database for reading."; 
		 } 
		 // Prepare the HTML table to be displayed
		 output = "<table border=\"1\" class=\"table\"><tr><th>inquiry Code</th>"
		 		+ "<th>inquiry Name</th><th>inquiry Price</th>"
		 		+ "<th>inquiry Description</th>"
		 		+ "<th>Update</th>"
		 		+ "<th>Remove</th></tr>"; 
		
		 String query = "select * from inquirys"; 
		 Statement stmt = con.createStatement(); 
		 ResultSet rs = stmt.executeQuery(query); 
		 // iterate through the rows in the result set
		 while (rs.next()) 
		 { 
		 String inquiryID = Integer.toString(rs.getInt("inquiryID")); 
		 String inquiryCode = rs.getString("inquiryCode"); 
		 String inquiryName = rs.getString("inquiryName"); 
		 String inquiryPrice = Double.toString(rs.getDouble("inquiryPrice")); 
		 String inquiryDescription = rs.getString("inquiryDescription"); 
		 // Add into the HTML table
		 output += "<tr><td><input id='hidinquiryIDUpdate' name='hidinquiryIDUpdate' type='hidden' value='"+inquiryID+"'>"+inquiryCode+"</td>"; 
		 output += "<td>" + inquiryName + "</td>"; 
		 output += "<td>" + inquiryPrice + "</td>"; 
		 output += "<td>" + inquiryDescription + "</td>"; 
		 // buttons
		 output += "<td><input name='btnUpdate' type='button' value='Update' "
				 + "class='btnUpdate btn btn-secondary' data-inquiryid='" + inquiryID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove' "
				 + "class='btnRemove btn btn-danger' data-inquiryid='" + inquiryID + "'></td></tr>"; 
		 
		 } 
		 con.close(); 
		 // Complete the HTML table
		 output += "</table>"; 
		 } 
		 
		catch (Exception e) 
		 { 
		 output = "Error while reading the inquirys."; 
		 System.err.println(e.getMessage()); 
		 } 
		return output; 
		}

			
			
			public String updateinquiry(String ID, String code, String name, String price, String desc){ 
				
					String output = ""; 
					
					try{ 
							Connection con = connect(); 
							if (con == null){
								return "Error while connecting to the database for updating.";
								} 
							// create a prepared statement
							String query = "UPDATE inquirys SET inquiryCode=?,inquiryName=?,inquiryPrice=?,inquiryDescription=? WHERE inquiryID=?"; 
							PreparedStatement preparedStmt = con.prepareStatement(query); 
							// binding values
							preparedStmt.setString(1, code); 
							preparedStmt.setString(2, name); 
							preparedStmt.setDouble(3, Double.parseDouble(price)); 
							preparedStmt.setString(4, desc); 
							preparedStmt.setInt(5, Integer.parseInt(ID)); 
							// execute the statement
							preparedStmt.execute(); 
							con.close(); 
							String newinquirys = readinquirys(); 
							output = "{\"status\":\"success\",\"data\":\""+newinquirys+"\"}"; 

					} 
					
					catch (Exception e){ 
						
						output = "{\"status\":\"error\",\"data\":\"Error while updating the inquiry.\"}"; 

						System.err.println(e.getMessage()); 
						
					} 
					
					return output; 
			} 
			
			
			public String deleteinquiry(String inquiryID){ 
				
					String output = ""; 
					
					try{ 
						Connection con = connect(); 
						
						if (con == null){
							return "Error while connecting to the database for deleting."; 
							} 
						// create a prepared statement
						String query = "delete from inquirys where inquiryID=?"; 
						PreparedStatement preparedStmt = con.prepareStatement(query); 
						// binding values
						preparedStmt.setInt(1, Integer.parseInt(inquiryID)); 
						// execute the statement
						preparedStmt.execute(); 
						con.close(); 
						String newinquirys = readinquirys(); 
						 output = "{\"status\":\"success\",\"data\":\""+newinquirys+"\"}"; 

					} 
					
					catch (Exception e){ 
						output = "{\"status\":\"error\",\"data\":\"Error while deleting the inquiry.\"}";
						System.err.println(e.getMessage()); 
					} 
					return output; 
			} 
			
			
			
			
} 
