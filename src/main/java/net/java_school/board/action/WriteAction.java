package net.java_school.board.action;

import java.io.IOException;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.db.dbpool.OracleConnectionManager;

public class WriteAction implements Action {

	private OracleConnectionManager dbmgr;
	
	public WriteAction(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Article article = new Article();
		article.setTitle(title);
		article.setContent(content);
		
		BoardService service = new BoardService(dbmgr);
		service.addArticle(article);
		
		forward.setView("/board/list.do?curPage=1");
		
		forward.setRedirect(true);
		
		return forward;
	}

}
