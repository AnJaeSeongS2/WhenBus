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
    	String BusName = request.getParameter("BusName");
    	String StartTime = request.getParameter("StartTime");
    	String IsWeekend = request.getParameter("IsWeekend");
    	
    	String EndTime = StartTime; //search 마지막 종료 시간
    	if( Character.getNumericValue(StartTime.charAt(1))>= 6 ){
    	 	EndTime =  String.valueOf( (Character.getNumericValue(StartTime.charAt(0))+1 ) ) ;
    		EndTime= EndTime+( String.valueOf( (Character.getNumericValue(StartTime.charAt(1))-6 )));
    	}
    	else{
    		EndTime =  String.valueOf( StartTime.charAt(0) ) ;
    		EndTime = EndTime+ ( String.valueOf( (Character.getNumericValue(StartTime.charAt(1))+4 )));
    
    	}
    	EndTime = EndTime + StartTime.substring(2, 5);
    	//endtime 확인가능.
    	
    	
    	// query
    	String query = " SELECT TIME_FORMAT( StartTime, '%k시 %i분') as StartTimeFC, BusName, CarPlateName, IsCrossRoad, Name, DriverID, Round(VoteAllValue / VoteCount, 2) as VoteValue "
    					+ "FROM schedule s NATURAL JOIN driver d NATURAL JOIN car c NATURAL JOIN bus b "
    					+ "WHERE b.BusName = '"+BusName+"' AND s.StartTime BETWEEN '"+StartTime+"' AND '"+EndTime+"' AND s.IsWeekend = '"+IsWeekend+"' "
    					+ "ORDER BY StartTime ASC;";
    	System.out.println(query);
    					rs = stmt.executeQuery(query);
    	
    	JSONObject ori = new JSONObject();
        JSONArray arr = new JSONArray();
    	
    	while(rs.next()) {
    		 JSONObject obj = new JSONObject();
    		 
    		 obj.put("StartTime", rs.getString("StartTimeFC"));
    		 obj.put("BusName", rs.getString("BusName"));
    		 obj.put("CarPlateName", rs.getString("CarPlateName"));
    		 obj.put("IsCrossRoad", rs.getString("IsCrossRoad"));
    		 obj.put("Name", rs.getString("Name"));
    		 obj.put("DriverID", rs.getString("DriverID"));
    		 obj.put("VoteValue", rs.getString("VoteValue"));
    		 
    		 arr.add(obj);
    	}
    	
    	ori.put("result",arr);	out.println(ori);
    	
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