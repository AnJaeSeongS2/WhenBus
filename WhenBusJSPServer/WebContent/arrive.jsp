
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

		// mysql query
		String query = "select CONCAT ( SUBSTR( cast(TIMEDIFF(startTime,curTime() )as char)  , 4,2 ) ,'분 전' ) as DueTime, cast( StartTime AS char) AS startTimeCasted, IsWeekend  , BusName, IsCrossRoad from Schedule s natural join Car c natural join Bus b where cast( SUBTIME(StartTime , curtime()) AS char) > '00:00' and cast( SUBTIME(StartTime , curtime()) AS char) < '01:00' and IsWeekend = (case (dayofweek(curdate())-1)%6 when '0' then 'T' else 'F' end)  order by StartTime asc ";
		rs = stmt.executeQuery(query);

	    System.out.println(query);
		// declare json array
		JSONObject ori = new JSONObject();
		JSONArray arr = new JSONArray();

		while (rs.next()) {
			// declare json object
			JSONObject obj = new JSONObject();

			// add data into json object
			obj.put("DueTime", rs.getString("DueTime"));
			obj.put("StartTime", rs.getString("startTimeCasted"));
			obj.put("IsWeekend", rs.getString("IsWeekend"));
			obj.put("BusName", rs.getString("BusName"));
			obj.put("IsCrossRoad", rs.getString("IsCrossRoad"));

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