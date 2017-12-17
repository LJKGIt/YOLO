package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class LikesCountServlet
 */
@WebServlet("/likes")
public class LikesCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LikesCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//추천수 증가
		//좋아요 버튼을 눌렀을때.
		
		int boardNo=0;
		int textNo=0;
		
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		textNo=Integer.parseInt(request.getParameter("textNo"));
		
		int result=new BoardService().likesCount(boardNo, textNo);
		
		if(result>0){
			//성공시.
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(Integer.toString(result));
			
		}else {
			//실패시. > 중복 이라거나
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
