
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@page import="java.sql.*"%>

<%@page import="org.json.simple.JSONObject"%>
<%@page import="org.json.simple.JSONArray"%>


<%
	request.setCharacterEncoding("utf-8");

	Connection conn = null;
	Statement stmt = null;
	ResultSet rs = null;

	try {
		// mysql connecter
		Class.forName("com.mysql.jdbc.Driver");

		// mysql address & id & password
		String url = "jdbc:mysql://163.180.117.131:3306/whenbusdb";

		// mysql connection
		conn = DriverManager.getConnection(url, "root", "root");
		stmt = conn.createStatement();
		
		String BusName = request.getParameter("BusName");
		// mysql query
		String query = "SELECT NaverMapURL FROM bus WHERE BusName = '"+BusName+"'";
		System.out.println(query);
		
		rs = stmt.executeQuery(query);
		// declare json array
		JSONObject ori = new JSONObject();
		JSONArray arr = new JSONArray();

		while (rs.next()) {
			// declare json object
			JSONObject obj = new JSONObject();

			// add data into json object
			obj.put("NaverMapURL", rs.getString("NaverMapURL"));

			// add json object into json array
			arr.add(obj);
		}

		ori.put("result", arr);
		out.print(ori);

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