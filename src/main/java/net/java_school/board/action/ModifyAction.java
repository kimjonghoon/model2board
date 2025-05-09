package net.java_school.board.action;

import java.io.IOException;
import java.net.URLEncoder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import net.java_school.action.Action;
import net.java_school.action.ActionForward;
import net.java_school.board.Article;
import net.java_school.board.BoardService;
import net.java_school.db.dbpool.OracleConnectionManager;

public class ModifyAction implements Action {

	private OracleConnectionManager dbmgr;
	
	public ModifyAction(OracleConnectionManager dbmgr) {
		this.dbmgr = dbmgr;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {

		ActionForward forward = new ActionForward();

		BoardService service = new BoardService(dbmgr);
		int no = Integer.parseInt(req.getParameter("no"));
		
		int curPage = Integer.parseInt(req.getParameter("curPage"));
		String keyword = req.getParameter("keyword");
		if (keyword == null) keyword = "";
		keyword = URLEncoder.encode(keyword, "UTF-8");
		
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		
		Article article = new Article();
		article.setArticleNo(no);
		article.setTitle(title);
		article.setContent(content);
		
		service.modifyArticle(article);
		
		forward.setView("/board/view.do?no=" + no + "&curPage=" + curPage + "&keyword=" + keyword);
		forward.setRedirect(true);
		
		return forward;
	}

}
