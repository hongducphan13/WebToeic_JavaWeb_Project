package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import BEAN.grammarguideline;
import DAO.GrammarguidelinemanageDAO;
import DB.DBConnection;

/**
 * Servlet implementation class Insertgrammarguidelinename
 */
@WebServlet("/Insertgrammarguidelinename")
public class Insertgrammarguidelinename extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Insertgrammarguidelinename() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String grammarname = request.getParameter("grammarname");
		
		grammarguideline grammarguideline = new grammarguideline();
		grammarguideline.setGrammarguidelinename(grammarname);
		Connection conn = DBConnection.CreateConnection();
		try {
			
			
			boolean ktt= GrammarguidelinemanageDAO.Insertgrammarguidelinename(request, conn, grammarguideline);
			
			if(ktt)
			{
				RequestDispatcher rd = request.getRequestDispatcher("Insertgrammarguidelineforward");
				rd.forward(request, response);
				
			}
			else 
			{
				request.setAttribute("msglistgrammarguidelinemanage", " Add failed ");
				RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
				rd.forward(request, response);
			}
				
			
			conn.close();
		} catch (SQLException e) {
			request.setAttribute("msglistgrammarguidelinemanage", e.getMessage());
			RequestDispatcher rd = request.getRequestDispatcher("Listgrammarguidelinemanage");
			rd.forward(request, response);
		}
	}

}
