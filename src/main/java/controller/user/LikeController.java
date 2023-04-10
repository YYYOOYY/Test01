package controller.user;

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
import data.Like;
import data.User;

@WebServlet("/action/like")
public class LikeController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		String code = req.getParameter("code");
		
		boolean logon = (boolean)req.getSession().getAttribute("logon");
		
		if(logon == true) {
			User user = (User)req.getSession().getAttribute("logonUser");
			Map<String, Object> params = new HashMap<>();
			params.put("code", code);
			params.put("userId", user.getId());
			
			Board board = sqlSession.selectOne("boards.findByBoard", code);
						
			if(user.getNick().equals(board.getWriter())){
				resp.sendRedirect("/board/detail?code="+code+"&error=q");
			}else {				
				Like like = sqlSession.selectOne("likes.findByLikes", params);
				if(like == null) {
					int r = sqlSession.insert("likes.boardLike", params);
					
					if(r == 1) {
						sqlSession.update("boards.updateByLikes", code);
						
						sqlSession.close();
						resp.sendRedirect("/board/detail?code="+code);
					}else {
						resp.sendRedirect("/board/detail?code="+code+"&error=r");
					}				
				}else {
					resp.sendRedirect("/board/detail?code="+code+"&error=e");
				}
			}
		}
	}
}
