package comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;
import comment.model.service.CommentService;
import comment.model.vo.Comment;
/**
 * Servlet implementation class InsertCmtServlet
 */
@WebServlet("/cinsert")
public class InsertCmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertCmtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글 작성시.
		int boardNo=0;
		
		int hit=1;
		if(request.getParameter("hit")!=null)
			hit=Integer.parseInt(request.getParameter("hit"));
		
		Comment c= new Comment();
		//글 번호를 가져오는 방법 생각해봐야 함.
		int boardPage = Integer.parseInt(request.getParameter("page"));
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		c.setTextNo(Integer.parseInt(request.getParameter("textNo")));
		c.setCommentWriter(request.getParameter("writer"));
		c.setCommentContent(request.getParameter("content"));
		if(request.getParameter("preCmtNo") != null && request.getParameter("cmtLevel") != null){
			c.setPreCmtNo(Integer.parseInt(request.getParameter("preCmtNo")));
			c.setLevel(Integer.parseInt(request.getParameter("cmtLevel")));
		}else{
			c.setPreCmtNo(0);
			c.setLevel(0);
		}
		System.out.println(c);
		int result=new CommentService().writeComment(boardNo, c);
		Board b=new BoardService().selectOne(boardNo, c.getTextNo(), hit);

		RequestDispatcher view = null;
		if(result>0){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(Integer.toString(result));
			
			System.out.println("@");
			
		} else {
			view = request.getRequestDispatcher("views/board/imageBoard/boardError.jsp");
			request.setAttribute("message", "스트랩 실패");
			view.forward(request, response);
		}
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
