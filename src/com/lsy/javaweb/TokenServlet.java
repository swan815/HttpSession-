package com.lsy.javaweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TokenServlet
 */
@WebServlet("/tokenServlet")
public class TokenServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		System.out.println("name:"+name);
		
		boolean valid=TokenProcessor.getInstance().isTokenValid(request);
		if(valid){
			TokenProcessor.getInstance().resetToken(request);
		}else{
			response.sendRedirect(request.getContextPath()+"/token/token.jsp");
			return;
		}
		
		//request.getRequestDispatcher("/token/success.jsp").forward(request, response);
		
	}

}
