import java.io.*;
import java.util.Date;

import javax.servlet.annotation.*;
import javax.servlet.http.*;
import javax.servlet.*;

import stockanalysis.*;

@WebServlet("/currentdaydetails")
                       
public class CurrentDayDetailsServlet extends HttpServlet {

	@Override
	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		CurrentDetails cd = new CurrentDetails();
		HttpSession session = request.getSession();
		//cd.getCurrentDetails(ticker);
		PrintWriter out = response.getWriter();
		//out.println(session.getAttribute("ticker"));
		String ticker = request.getParameter("ticker");
		cd.getCurrentDetails(ticker);
		session.setAttribute("ticker", ticker);
		Date cdate = cd.cdate;
		double cclose = cd.close;
		double chigh = cd.high;
		double clow = cd.low;
		double copen = cd.open;
		double highest = cd.highest;
		double lowest = cd.lowest;
		double todayVol = cd.totVol;
		double cvolume = cd.volume;		
		request.setAttribute("cdate",cdate);	
		request.setAttribute("cclose",cclose);
		request.setAttribute("chigh",chigh);
		request.setAttribute("clow",clow);
		request.setAttribute("copen",copen);
		request.setAttribute("cvolume",cvolume);
		request.setAttribute("highest",highest);
		request.setAttribute("lowest",lowest);
		request.setAttribute("todayVol",todayVol);
		RequestDispatcher rd = request.getRequestDispatcher("/previousdetails");
		rd.forward(request,response);
	}
}
