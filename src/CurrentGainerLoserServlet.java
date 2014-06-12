

import java.io.*;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import stockanalysis.*;

//@WebServlet("/gainerloser")

public class CurrentGainerLoserServlet extends HttpServlet {
	UpdateGainersLosers ugl = null;
	@Override
	public void init(){
		System.out.println("+++++++++++++++++++++");
		ugl =  new UpdateGainersLosers();
		
		
	}
	@Override
	public void service(HttpServletRequest request, HttpServletResponse responce) throws IOException, ServletException{
		
		 
	}
	@Override

	public void destroy() {
		System.out.println("destroying ugl obj");
		ugl = null;
	}

}
