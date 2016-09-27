
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
		
		//최근 공지사항 가져옴.
		String query = "select Contents, DATE_FORMAT(UpdatedDate , '%Y년 %c월 %d일') as UpdatedDateFC from notice where noticeID = (select MAX(noticeID) from notice)"; 
		rs = stmt.executeQuery(query);
       System.out.println(query);
       // declare json array
       JSONObject ori = new JSONObject();
       JSONArray arr = new JSONArray();
       
       while(rs.next()) {
          // declare json object
          JSONObject obj = new JSONObject();
          
          // add data into json object
          obj.put("Contents", rs.getString("Contents"));
          obj.put("UpdatedDate", rs.getString("UpdatedDateFC"));
          
          // add json object into json array
          arr.add(obj);
       }
       
       ori.put("result", arr);	out.print(ori);
       
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