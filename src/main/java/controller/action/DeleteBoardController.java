package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

@WebServlet("/action/delete")
public class DeleteBoardController extends HttpServlet {
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
			int r = sqlSession.delete("boards.deleteByBoard", code);
			
			if(r == 1) {
				resp.sendRedirect("/");
			}else {
				resp.sendRedirect("/board/detail?code="+code);
			}
		}else {
			req.getRequestDispatcher("/WEB-INF/views/action/passCheckDelete.jsp").forward(req, resp);
		}
	}
}
