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

		String query = "SELECT Name, DriverID, Round((VoteAllValue / VoteCount) ,2) as VoteValue "
				+ "FROM driver ORDER BY VoteValue DESC";
		rs = stmt.executeQuery(query);
		System.out.println(query);
		// declare json array
		JSONObject ori = new JSONObject();
		JSONArray arr = new JSONArray();

		while (rs.next()) {
			// declare json object
			JSONObject obj = new JSONObject();

			// add data into json object
			obj.put("Name", rs.getString("Name"));
			obj.put("DriverID", rs.getString("DriverID"));
			obj.put("VoteValue", rs.getString("VoteValue"));
			
			// add json object into json array
			arr.add(obj);
		}
		
		ori.put("result", arr);
		
		out.println(ori);out.println(ori);

		stmt.close();
		conn.close();

	} catch (ClassNotFoundException e) {
		e.printStackTrace();
	} catch (SQLException e) {
		e.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	}
%>