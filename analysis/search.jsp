<!DOCTYPE html>

<%@page import="java.util.Date"%>
<%@page import="java.text.*"%>
<html>
  
  <head data-gwd-animation-mode="quickMode">
    <title>Search</title>
    	<link rel="stylesheet" type="text/css" href="search.css">
  </head>
   <body>
    <div class="cdiv"><h2 class="cname"> <%=request.getAttribute("cname") %> (<%=session.getAttribute("ticker").toString().toUpperCase() %>)
    <span style="color:olive;font-family:monospace;font-size:14px">NSE</span>
    </h2> 
   </div>
    
      
     <img class="graph" src="http://chart.finance.yahoo.com/t?s=<%=session.getAttribute("ticker") %>"/>
     <div class="details">
     current day details:<br>
      date: <%=request.getAttribute("cdate") %><br>
     <%double prevclose =   (Double)(request.getAttribute("pclose"));
       double currentclose = (Double)request.getAttribute("cclose");
       double raise;
       DecimalFormat df = new DecimalFormat("#.###");
        if(currentclose-prevclose>0){
        	
        	raise =  Double.valueOf(df.format((currentclose-prevclose)));
        	
        	double perchn = (raise/prevclose)*100;
        	 double perchng =  Double.valueOf(df.format(perchn));
        %>
        <span style="font-family: fantasy;font: 500; font-size: larger;"><%=currentclose %></span>
         <img class="arrow" src="up.jpg"/>&emsp; &nbsp;
        <span style="color:green;font-weight:1000; font-size: 18px "><%=raise %> (<%=perchng %>%)</span>
        <%
        }
        else{
        	raise = Double.valueOf(df.format(prevclose-currentclose));
        	double perchng =  Double.valueOf(df.format((raise/prevclose)*100));%>
        	
        	<span style="font-family: fantasy;font-weight:1000; font-size:18px;"><%=currentclose %></span>
        	<img class="arrow" src="down.png"/>&emsp; &nbsp;
        	<span style="color:red;font-weight:500; font-size: 18px "><%=raise %> (<%=perchng %>%)</span>
        	<%}%>
    <table border="1" class="table">
    <tr>
    <th>prev close</th>
    <td><%=request.getAttribute("pclose") %></td>
    </tr>
    <tr>
    <th>open</th>
    <td><%=request.getAttribute("copen") %></td>
    </tr>
    <tr>
    <th>days range</th>
    <td><%=request.getAttribute("lowest") %> - <%=request.getAttribute("highest") %></td>
    </tr>
    <tr>
    <th>highest</th>
    <td><%=request.getAttribute("highest") %></td>
    </tr>
    <tr>
    <th>volume</th>
    <td><%=request.getAttribute("todayVol") %></td>
    </tr>
    <tr>
    <th>lowest</th>
    <td><%=request.getAttribute("lowest") %></td>
    </tr>
    <tr>
    <th>current vol</th>
    <td><%=request.getAttribute("cvolume") %></td>
    </tr>
    
    </table>
     
     <br>
     <br>
     prev day: <%=request.getAttribute("prevdate")%>
     <table border="1" draggable="true" class="table">
     <tr>
     <th>previous close: </th>
     <td><%=request.getAttribute("pclose") %></td>
     </tr>
     <tr>
     <th>previous open: </th>
     <td><%=request.getAttribute("popen") %></td>
     </tr>
     
     <tr>
     <th>previous max: </th>
     <td><%=request.getAttribute("pmax") %></td>
     </tr>
     <tr>
     <th>previous min: </th>
     <td><%=request.getAttribute("pmin") %></td>
     </tr>
     <tr>
     <th>previous volume: </th>
     <td><%=request.getAttribute("pvolume") %></td>
     </tr>
    </table>
    </div>
     
  </body>

</html>