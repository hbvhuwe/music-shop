package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.JpaController;

/**
 * Servlet implementation class SelectQuery
 */
@WebServlet("/SelectQuery")
public class SelectQuery extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectQuery() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		JpaController controller = (JpaController) session.getAttribute("controller");
		String query = request.getParameter("query");
		if (query.equals("name")) {
			session.setAttribute("tableModel", controller.doQuery1());
			session.setAttribute("tableName", "Всі альбоми групи Imagine Dragons");
		} else if(query.equals("songs")) {
			session.setAttribute("tableModel", controller.doQuery2());
			session.setAttribute("tableName", "Всі пісні з альбому AM");
		}
		request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
