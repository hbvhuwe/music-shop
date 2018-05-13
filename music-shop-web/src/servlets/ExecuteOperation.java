package servlets;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.table.TableModel;

import controller.IModel;
import controller.JpaController;
import model.Composition;
import model.Disk;
import model.Group;

@WebServlet("/ExecuteOperation")
public class ExecuteOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String tableName = (String) session.getAttribute("tableName");
		JpaController controller = (JpaController) session.getAttribute("controller");
		String className = "model." + tableName;
		String operation = (String) session.getAttribute("operation");
		if (operation.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			controller.delete(id, tableName);
		} else {
			IModel obj = null;
			try {
				obj = (IModel) Class.forName(className).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (obj instanceof Group) {
				String name = request.getParameter("Name");
				((Group) obj).setName(name);
				String musician = request.getParameter("Musician");
				((Group) obj).setMusician(musician);
				String style = request.getParameter("Style");
				((Group) obj).setStyle(style);
			} else if (obj instanceof Disk) {
				String name = request.getParameter("Name");
				((Disk) obj).setName(name);
				int amount = Integer.parseInt(request.getParameter("Amount"));
				((Disk) obj).setAmount(amount);
				double price = Double.parseDouble(request.getParameter("Price"));
				((Disk) obj).setPrice(price);
				Date presentDate = Date.valueOf(request.getParameter("PresentDate"));
				((Disk) obj).setPresentDate(presentDate);
				int groupId = Integer.parseInt(request.getParameter("GroupID"));
				Group group = (Group) findById(groupId, controller, "Group");
				((Disk) obj).setGroup(group);
			} else if (obj instanceof Composition) {
				String name = request.getParameter("Name");
				((Composition) obj).setName(name);
				Time duration = Time.valueOf(request.getParameter("Duration"));
				((Composition) obj).setDuration(duration);
				String presentDateString = request.getParameter("PresentDate");
				if (presentDateString != null) {
					Date presentDate = Date.valueOf(presentDateString);
					((Composition) obj).setPresentDate(presentDate);
				}
				String diskIdString = request.getParameter("DiskID");
				if (diskIdString != null) {
					int diskId = Integer.parseInt(diskIdString);
					Disk disk = (Disk) findById(diskId, controller, "Disk");
					((Composition) obj).setDisk(disk);
				}
			}
			switch (operation) {
			case "edit":
				int id = Integer.parseInt(request.getParameter("id"));
				controller.edit(id, obj);
				break;
			case "add":
				controller.add(obj);
			}
		}
		TableModel tableModel = controller.getModel(tableName);
		session.setAttribute("tableModel", tableModel);
		request.getRequestDispatcher("showTable.jsp").forward(request, response);
	}

	private IModel findById(int id, JpaController controller, String className) {
		try {
			IModel obj = null;
			Class<?> clazz = Class.forName("model." + className);
			for (Object x : controller.getObjectList(clazz)) {
				obj = (IModel) x;
				if (obj.getId() == id)
					return obj;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
