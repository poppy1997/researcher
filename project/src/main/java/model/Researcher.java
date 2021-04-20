package model; 
import java.sql.*; 
public class Researcher 
{ //A common method to connect to the DB
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", ""); 
 } 
 catch (Exception e) 
 {e.printStackTrace();} 
 return con; 
 } 
public String insertItem(String code, String name,String project, String price, String desc) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 
 
 // create a prepared statement
 String query = " insert into researcher (`r_id`,`r_code`, `r_name`,`r_project`,`r_project_price`,`r_project_desc`)"
 + " values (1,abc, abc, abc,190,abc)"; 
 PreparedStatement preparedStmt = con.prepareStatement(query); 
 
 // binding values
 preparedStmt.setInt(1, 0); 
 preparedStmt.setString(2, code); 
 preparedStmt.setString(3, name); 
 preparedStmt.setString(4, project); 
 preparedStmt.setDouble(5, Double.parseDouble(price)); 
 preparedStmt.setString(6, desc); 
 
// execute the statement
 preparedStmt.execute(); 
 con.close(); 
 output = "Inserted successfully"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while inserting the item."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 

public String readItems() 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for reading."; } 
 
 // Prepare the html table to be displayed
 output = "<table border='1'><tr><th>Researcher Code</th><th>Researcher Name</th>" +
 "<th>Project</th>" + 
 "<th>Project Description</th>" +
 "<th>Project price</th>" +
 "<th>Update</th><th>Remove</th></tr>"; 
 
 String query = "select * from researcher"; 
 Statement stmt = con.createStatement(); 
 ResultSet rs = stmt.executeQuery(query); 
 
 // iterate through the rows in the result set
 while (rs.next()) 
 { 
 String r_id = Integer.toString(rs.getInt("r_id")); 
 String r_code = rs.getString("r_code"); 
 String r_project = rs.getString("r_project"); 
 String r_project_price = Double.toString(rs.getDouble("r_project_price")); 
 String r_project_desc = rs.getString("r_project_desc"); 
 
 // Add into the HTML table
 output += "<tr><td>" + r_code + "</td>"; 
 output += "<td>" + r_project + "</td>"; 
 output += "<td>" + r_project_price + "</td>"; 
 output += "<td>" + r_project_desc + "</td>"; 
 
 // buttons
 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>" + "<td><form method='post' action='researcher.jsp'>" + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>" + "<input name='r_id' type='hidden' value='" + r_id  + "'>" + "</form></td></tr>"; 
 } 
 con.close(); 
 
 // Complete the HTML table
 output += "</table>"; 
 } 
 catch (Exception e) 
 { 
 output = "Error while reading the items."; 
 System.err.println(e.getMessage()); 
 } 
 return output; 
 } 
public String updateItem(String ID, String code, String name,String project, String price, String desc)
{ 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for updating."; } 
	 // create a prepared statement
	 String query = "UPDATE researcher SET r_code=?,r_name=?,r_project=?,r_project_price=?,r_project_desc=? WHERE r_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setString(1, code); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, project); 
	 preparedStmt.setDouble(4, Double.parseDouble(price)); 
	 preparedStmt.setString(5, desc); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Updated successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while updating researcher details."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	public String deleteItem(String r_id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 
	 // create a prepared statement
	 String query = "delete from researcher where r_id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(r_id)); 
	 
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 output = "Deleted successfully"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while deleting the researcher."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	} 
