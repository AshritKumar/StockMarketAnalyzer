<%@ page import="stockanalysis.*"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>Top Gainers</title>
</head>
<body>
<jsp:useBean id="currentdetails" class="stockanalysis.CurrentDetails" /> 
<%
Connection con = GetDBConnection.getConnection("STOCKT_NSE");
Statement stmt = con.createStatement();
String query ="SELECT * FROM gainlose where percent_chng > 0 order by percent_chng desc";
ResultSet rs = stmt.executeQuery(query);
while(rs.next()){
	String ticker =  rs.getString(1);
	double chng = rs.getDouble(2);
	double percentchng = rs.getDouble(3);
	String query1 = "SELECT companyname from TNC WHERE TICKER = '"+ticker+"'";
	ResultSet rs1 = stmt.executeQuery(query1);
	String companyName = new String();
	if(rs1.next())
	 companyName = rs1.getString(1);
	
	 %>
<h3><%=companyName %> &ensp; <%=ticker %></h3> &ensp;  <img class="arrow" src="up.jpg"/>   
<h3 style="color: green"><%=currentdetails.close %> &nbsp; <%=chng %> &nbsp;(<%=percentchng %>%)</h3>
<% } %>
</body>
</html> 
