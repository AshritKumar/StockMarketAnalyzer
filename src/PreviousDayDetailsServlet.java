
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import org.apache.catalina.Session;

import stockanalysis.*;
@WebServlet("/previousdetails")

public class PreviousDayDetailsServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
	PrintWriter out = response.getWriter();
	String ticker = request.getParameter("ticker");
	CompanyName cn = new CompanyName();
	String companyName = cn.getCompanyName(ticker);
	PreviousDayDetails cmv = new PreviousDayDetails();
	double pclose=0,pmax=0,pmin=0,pvolume=0,popen=0;
	try{
	pclose = cmv.getPreDayClose(ticker);
	popen = cmv.getPreDayOpen(ticker);
	pmax = cmv.getPreMaxValue(ticker);
	pmin = cmv.getPreMinValue(ticker);
	pvolume = cmv.getVolume(ticker);
	}catch(SQLException se){
		se.printStackTrace();
	}
	HttpSession session = request.getSession();
	//session.setAttribute("ticker", ticker);
	
	request.setAttribute("pclose", pclose);
	request.setAttribute("popen", popen);
	request.setAttribute("pmax", pmax);
	request.setAttribute("pmin", pmin); 
	request.setAttribute("pvolume", pvolume);
	request.setAttribute("cname", companyName);
	request.setAttribute("prevdate", cmv.date);
	//out.println(pclose);
	//out.println(cmv.date); 
	RequestDispatcher rd = request.getRequestDispatcher("search.jsp");
	rd.forward(request, response); 
	//response.sendRedirect("search.jsp");
	} 
	}
