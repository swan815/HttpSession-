package com.lsy.javaweb;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProcessStep2Servlet
 */
@WebServlet("/processStep2")
public class ProcessStep2Servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.获取请求参数：name,address,cardtype,card
		String name=request.getParameter("name"); 
		String address=request.getParameter("address");
		String cardType=request.getParameter("cardType");
		String card=request.getParameter("card");
		//2.把请求信息存入到httpSession中
		HttpSession session = request.getSession();
		Customer customer=new Customer(name,address,card,cardType);
		session.setAttribute("customer", customer);
		//3.重定向到conform.jsp
		response.sendRedirect(request.getContextPath()+"/shoppingcart/confirm.jsp");
	}

}
