package controller.board;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
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

@WebServlet("/board/detail")
public class DetailController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		String code = req.getParameter("code");
		
		HttpSession session = req.getSession();
		boolean logon = (boolean)session.getAttribute("logon");
		if(logon == true) {
			User user = (User)session.getAttribute("logonUser");
			Map<String, Object> params = new HashMap<>();
			params.put("code", code);
			params.put("id", user.getId());
			
			Board board = sqlSession.selectOne("boards.findByUserBoards", params);
			if(board != null) {
				req.setAttribute("userBoard", true);
			}else {
				req.setAttribute("userBoard", false);
			}
		}else {
			Board board = sqlSession.selectOne("boards.findByNonUserBoards", code);
			if(board != null) {
				req.setAttribute("nonUserBoard", true);
			}else {
				req.setAttribute("nonUserBoard", false);
			}
		}
		
		Board board = (Board)sqlSession.selectOne("boards.findByBoard", code);
		sqlSession.update("boards.updateByViews", code);
		
		req.setAttribute("board", board);
		
		req.getRequestDispatcher("/WEB-INF/views/board/detail.jsp").forward(req, resp);
		
		sqlSession.close();
	}
}
