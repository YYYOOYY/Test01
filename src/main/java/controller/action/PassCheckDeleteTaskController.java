package controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

@WebServlet("/action/passCheckDelete-task")
public class PassCheckDeleteTaskController extends HttpServlet{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		req.setAttribute("code", code);
		
		String pass = req.getParameter("pass");
		
		String boardPass = sqlSession.selectOne("boards.findByBoardPass", code);
		
		if(BCrypt.checkpw(pass, boardPass)) {
			int r = sqlSession.delete("boards.deleteByBoard", code);
			
			if(r == 1) {
				resp.sendRedirect("/");
			}else {
				resp.sendRedirect("/board/detail?code="+code);
			}
		}else {
			req.getRequestDispatcher("/WEB-INF/views/action/passCheckDelete.jsp?error=r").forward(req, resp);
		}
	}
}
