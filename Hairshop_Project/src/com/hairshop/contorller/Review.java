package com.hairshop.contorller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hairshop.dao.HairDAO;
import com.hairshop.vo.HairVO;

/**
 * Servlet implementation class Review
 */
@WebServlet("*.review")
public class Review extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String uri;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Review() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String c = request.getRequestURI().substring(request.getContextPath().length());
		
		
		switch(c) {
		/*case "/output.review":
			
			ArrayList<HairVO> ar1 = null;
			try {
				ar1 = new HairDAO().ReviewOutput();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				request.setAttribute("error", e.getMessage());
				uri ="Error.jsp";
				RequestDispatcher rd = request.getRequestDispatcher(uri);
				rd.forward(request, response);
			}
			
			uri ="Review.jsp";
			request.setAttribute("reviewoutput", ar1);
			break;*/
		
			
		case "/check.review":
			
			HairDAO hdao2 = new HairDAO();
			HairVO hvo = null;
			String text1 = request.getParameter("r_text");
			System.out.println(text1);
			if(text1.equals("")) {
				request.setAttribute("errorcheck", "review04");
				uri ="Errorcheck.jsp";
			}else {
				HttpSession ses = request.getSession(false);
			
			
				if(ses == null || ses.getAttribute("session1") == null) {
					request.setAttribute("errorcheck", "login01");
					uri = "Errorcheck.jsp";
				}else {
					HairVO sesid = (HairVO) ses.getAttribute("session1");
					String id = sesid.getId();
					try {
						hvo = hdao2.Reviewcheck(id);
					} catch (ClassNotFoundException | SQLException e) {
						// TODO Auto-generated catch block
						request.setAttribute("error", e.getMessage());
						uri ="Error.jsp";
						RequestDispatcher rd = request.getRequestDispatcher(uri);
						rd.forward(request, response);
					}
					if(hvo!=null) {
						long sysdate = new Date().getTime();
						long rdate = hvo.getReservation_date().getTime()+604800000;
					
						if(sysdate>rdate || sysdate<hvo.getReservation_date().getTime()) {
						
						request.setAttribute("errorcheck", "review01");
						uri ="Errorcheck.jsp";
					}else {
						request.setAttribute("text", text1);
						request.setAttribute("hvo", hvo);
						uri ="reviewinput.do";
					}
				}else {
					request.setAttribute("errorcheck", "review02");
					uri ="Errorcheck.jsp";
				}
			}			
			break;
		
		}
	}
		RequestDispatcher rd = request.getRequestDispatcher(uri);
		rd.forward(request, response);
}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
