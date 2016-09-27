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

		//String query = "SELECT Round((VoteAllValue / VoteCount), 1) AS VoteValue From driver";

		String query = "SELECT Round( AVG( Round((VoteAllValue / VoteCount) ,2) ) ,2 ) as AvgVoteValue "
				+ "FROM driver";

		System.out.println(query);
		rs = stmt.executeQuery(query);
		// declare json array
		JSONObject ori = new JSONObject();
		JSONArray arr = new JSONArray();

		while (rs.next()) {
			// declare json object
			JSONObject obj = new JSONObject();

			// add data into json object
			obj.put("AvgVoteValue", rs.getString("AvgVoteValue"));

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