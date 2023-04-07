package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/action/modify")
public class ModifyBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String code = req.getParameter("code");
		
		req.setAttribute("code", code);
		
		HttpSession session = req.getSession();
		boolean logon = (boolean)session.getAttribute("logon");
		
		if(logon) {
			req.getRequestDispatcher("/WEB-INF/views/action/modify.jsp").forward(req, resp);			
		}else {
			req.getRequestDispatcher("/WEB-INF/views/action/passCheckModify.jsp").forward(req, resp);
		}
	}
}
