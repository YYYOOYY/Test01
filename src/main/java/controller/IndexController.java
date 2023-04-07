package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import data.Board;

@WebServlet("/index")
public class IndexController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		SqlSessionFactory factory= 
				(SqlSessionFactory)req.getServletContext().getAttribute("sqlSessionFactory");
		SqlSession sqlSession = factory.openSession(true);
		
		int p;
		if(req.getParameter("page") == null) {
			p = 1;
		}else {
			p = Integer.parseInt(req.getParameter("page"));
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("a", p * 8 - 7);
		map.put("b", 8 * p);
		
		int total = sqlSession.selectOne("boards.countBoards");
		int lastPage = total / 8 + (total % 8 > 0 ? 1 : 0);
		
		int start = p % 5 == 0 ? p - 4 : p - (p % 5) + 1;
		int last = p % 5 == 0 ? p : p - (p % 5) + 5;
		
		last = last > lastPage ? lastPage : last;
		
		List<Board> boards = sqlSession.selectList("boards.findSomeByAtoB", map);
		
		sqlSession.close();
		req.setAttribute("start", start);
		req.setAttribute("last", last);
		req.setAttribute("boards", boards);
		
		boolean existPrev = start == 1 ? false : true;
		boolean existNext = last < lastPage-1 ? true : false;	
		
		req.setAttribute("existPrev", existPrev);
		req.setAttribute("existNext", existNext);
		
		req.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(req, resp);
		
	}
}
