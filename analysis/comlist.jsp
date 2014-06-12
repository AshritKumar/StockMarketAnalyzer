<%@ page import="stockanalysis.*"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>companies list</title>
<table>
<tr><th>Company Ticker</th><th>Company Name</th></tr>
<%
try{
	
Connection con = GetDBConnection.getConnection("STOCKT_NSE");
Statement stmt = con.createStatement();
String query ="SELECT * FROM TNC";
ResultSet rs = stmt.executeQuery(query);
while(rs.next()){
	String ticker =  rs.getString(1);
	String comName = rs.getString(2);
	request.setAttribute("ticker", ticker);
	

%>
   <tr>
   <td>
  <a href="currentdaydetails" name="ticker"> <%=ticker %>  </a></td>
  <td><%=comName %></td></tr>

	
	<%}
     }
	catch(SQLException se){
	se.printStackTrace();
	}
	%>
	
</table>