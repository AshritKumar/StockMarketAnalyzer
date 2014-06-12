<%@ page import="stockanalysis.*"%>
<%@ page import="java.sql.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<title>Top Gainers</title>
<link rel="stylesheet" type="text/css" href="gainers.css">
</head>
<body>
gainers
<jsp:useBean id="currentdetails" class="stockanalysis.CurrentDetails" />
<jsp:useBean id="comname" class="stockanalysis.CompanyName"/>
<%
try{
	
Connection con = GetDBConnection.getConnection("STOCKT_NSE");
Statement stmt = con.createStatement();
String query ="SELECT * FROM gainlose where percent_chng > 0 order by percent_chng desc";
ResultSet rs = stmt.executeQuery(query);
while(rs.next()){
	String ticker =  rs.getString(1);
	double chng = rs.getDouble(2);
	double percentchng = rs.getDouble(3);
	currentdetails.getCurrentDetails(ticker);
	
%>
<table>
<tr>
<td><h4><%=comname.getCompanyName(ticker)%> </h4></td> 
<td><h5> (<%=ticker %>)</h5></td>
<td><img class="arrow" src="up.jpg"/> </td>
<td><h3 style="color: green"><%=currentdetails.close %> &nbsp; <%=chng %> &nbsp;(<%=percentchng %>%)</h3></td>
</tr>
<% }
}
catch(SQLException se){
	se.printStackTrace();
}
%>
</table>
</body>
</html>