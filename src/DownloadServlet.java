
import java.io.*;

import javax.servlet.*;
//import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import stockanalysis.*;

/**
 * Servlet implementation class DownloadServlet
 */
//@WebServlet(description = "download the stock data from yahoo by load on start up", urlPatterns = { "/DownloadServlet" },loadOnStartup=1)
public class DownloadServlet extends HttpServlet {
	private PeriodicExecute pe = null;
	public void init(ServletConfig config){
		pe = new PeriodicExecute();
		
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce) throws IOException, ServletException{
		PrintWriter out = responce.getWriter();
		DownloadTicker dt = new DownloadTicker();
		String range="d";
		System.out.println("calling the download data method");
		try {
			dt.getNseTicker(range);
		} catch (Exception e) {
			//responce.sendError(404);
			e.printStackTrace();
		}
		out.println("new");
		//esponce.sendRedirect("shome.jsp");
		//RequestDispatcher rd = request.getRequestDispatcher("shome.jsp");
		//rd.forward(request, responce);
	}
	@Override
	public void destroy(){
		pe=null;
		
	}
	

}
