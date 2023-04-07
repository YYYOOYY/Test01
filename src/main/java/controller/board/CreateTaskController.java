package controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mindrot.jbcrypt.BCrypt;

import data.User;

@WebServlet("/board/create-task")
public class CreateTaskController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		boolean logon = (boolean)req.getSession().getAttribute("logon");
		if(logon == true) {
			User user = (User)req.getSession().getAttribute("logonUser");
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			
			Map<String, Object> map = new HashMap<>();
			map.put("writer", user.getNick());
			map.put("title", title);
			map.put("body", body);
			
			int r = sqlSession.insert("boards.createUser", map);
			
			if(r == 1) {
				resp.sendRedirect("/");
			}else {
				req.getRequestDispatcher("/WEB-INF/views/board/create.jsp?error=r").forward(req, resp);
			}
		}else {
			String writer = req.getParameter("writer");
			String boardPass = req.getParameter("boardPass");
			String title = req.getParameter("title");
			String body = req.getParameter("body");
			
			String hashedPw = BCrypt.hashpw(boardPass, BCrypt.gensalt());
			
			Map<String, Object> params = new HashMap<>();
			params.put("writer", writer);
			params.put("boardPass", hashedPw);
			params.put("title", title);
			params.put("body", body);
			
			int r = sqlSession.insert("boards.createNonUser", params);
			
			sqlSession.close();
			if(r == 1) {
				resp.sendRedirect("/");
			}else {
				req.getRequestDispatcher("/WEB-INF/views/board/create.jsp?error=r").forward(req, resp);
			}
		}
		
	}
}
