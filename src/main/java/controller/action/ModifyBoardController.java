package controller.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Board;
import data.User;

@WebServlet("/action/modify")
public class ModifyBoardController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		
		String code = req.getParameter("code");
		
		req.setAttribute("code", code);
		
		HttpSession session = req.getSession();
		boolean logon = (boolean)session.getAttribute("logon");
		
		if(logon) {
			Board board = sqlSession.selectOne("boards.findByNonUserBoards", code);
			if(board != null) {
				req.getRequestDispatcher("/WEB-INF/views/action/passCheckModify.jsp").forward(req, resp);
			}else {
				req.getRequestDispatcher("/WEB-INF/views/action/modify.jsp").forward(req, resp);							
			}
		}else {
			req.getRequestDispatcher("/WEB-INF/views/action/passCheckModify.jsp").forward(req, resp);
		}
	}
}
