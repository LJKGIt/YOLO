package comment.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.service.CommentService;


/**
 * Servlet implementation class DeleteCmtServlet
 */
@WebServlet("/cdelete")
public class DeleteCmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteCmtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글삭제
		int boardNo=0;
		//게시판 번호
		int cmtNo=0;
		//댓글번호
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		cmtNo=Integer.parseInt(request.getParameter("cmtNo"));
		
		int result=new CommentService().deleteComment(boardNo, cmtNo);
		
		if(result>0){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(Integer.toString(result));
		} else {
			RequestDispatcher view = request.getRequestDispatcher("views/board/imageBoard/boardError.jsp");
			request.setAttribute("message", "댓글 삭제 실패");
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
