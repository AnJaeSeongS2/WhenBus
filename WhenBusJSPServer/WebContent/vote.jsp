<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.sql.*"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>

<%
	// language setting
    request.setCharacterEncoding("utf-8");

	// init connection
	Connection conn = null;
	// init statement
	Statement stmt = null;
	// init resultset
    ResultSet rs = null;
    
    try {
    	// mysql connecter
    	Class.forName("com.mysql.jdbc.Driver");
    	
    	// mysql address & id & password
    	String url = "jdbc:mysql://163.180.117.131:3306/WhenBusDB";

    	// mysql connection
    	conn = DriverManager.getConnection(url, "root", "root");
    	stmt = conn.createStatement();
    	
    	// parameters
    	String DriverID = request.getParameter("DriverID");
    	String VoteValue = request.getParameter("VoteValue");
    	
    	System.out.println(DriverID + " " + VoteValue);
    	
    	String query = "UPDATE WhenBusDB.driver SET VoteAllValue = VoteAllValue + '"+VoteValue+"', VoteCount = VoteCount + 1 WHERE driverID = '"+DriverID+"'";
    	stmt.executeUpdate(query);
    	System.out.println(query);
    	stmt.close();
    	conn.close();
    	
    } catch(ClassNotFoundException e) {
    	e.printStackTrace();
    } catch(SQLException e) {
    	e.printStackTrace();
    } catch(Exception e) {
    	e.printStackTrace();
    } 
%>