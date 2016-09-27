


<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.sql.*"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>


<%

//not used this dummy test code

//not used this dummy test code

//not used this dummy test code

//not used this dummy test code


    request.setCharacterEncoding("utf-8");

	Connection connection = null;
	Statement stmt = null;
    ResultSet rs = null;
    
    System.out.println(request.getParameter("test"));
    
    try {
       // mysql connecter
       Class.forName("com.mysql.jdbc.Driver");
       
       // mysql address & id & password
       String url = "jdbc:mysql://163.180.117.131:3306/whenbusdb";

       // mysql connection
       connection = DriverManager.getConnection(url, "root", "root");
       
       stmt = connection.createStatement();
       
       // mysql query
       String query = "SELECT * FROM Bus";
       
       rs = stmt.executeQuery(query);
       
       // declare json array
       JSONObject ori= new JSONObject();
       JSONArray arr = new JSONArray();
       
       while(rs.next()) {
          // declare json object
          JSONObject obj = new JSONObject();
          
          // add data into json object
          obj.put("busName", rs.getString("BusName"));
          obj.put("IsCrossRoad", rs.getString("IsCrossRoad"));
          
          // add json object into json array
          arr.add(obj);
       }
       
       ori.put("result",arr);	out.println(ori);
       
       stmt.close();
       connection.close();
    } catch(ClassNotFoundException e) {
       out.println("ClassNotFoundException");
    } catch(SQLException e) {
       out.println("SQLException");
    }
    
    catch(Exception e) {
       out.println("f");
    } 
%>