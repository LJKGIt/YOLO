package board.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class SelectOneText
 */
@WebServlet("/bdetail")
public class SelectOneText extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOneText() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 상세보기 메뉴.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		int boardNo=0;
		int textNo=0;
		int currentPage=1;
		int commentPage = 1;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		textNo=Integer.parseInt(request.getParameter("textNo"));
		if(request.getParameter("page")!=null)
			currentPage=Integer.parseInt(request.getParameter("page"));
		if(request.getParameter("commentPage") != null){
			commentPage = Integer.parseInt(request.getParameter("commentPage"));
		}
		Board b=new BoardService().selectOne(boardNo, textNo);
		
		RequestDispatcher view = null;
		if(b!=null){
			//성공시
			//+추가적으로 게시글별 댓글 보여주는 서블릿을 호출해야 한다.
			
			view=request.getRequestDispatcher("cselect");
			request.setAttribute("board", b);
			request.setAttribute("page", currentPage);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("commentPage", commentPage);
			view.forward(request, response);
		
		} else {
			view = request.getRequestDispatcher("views/board/imageBoard/boardError.jsp");
			request.setAttribute("message", "글 보기 실패");
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
