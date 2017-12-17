package board.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class DeleteTextServlet
 */
@WebServlet("/tdelete")
public class DeleteTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteTextServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 삭제
		response.setContentType("text/html; charset=utf-8");
		
		int boardNo=0;
		int textNo=0;
	   boardNo=Integer.parseInt(request.getParameter("boardNo"));
	   textNo=Integer.parseInt(request.getParameter("textNo"));
		
		int result=new BoardService().deleteText(boardNo, textNo);
		
		if(result>0){
		     //삭제 후 페이지 이동.
		     RequestDispatcher view=
		    		 request.getRequestDispatcher("blist");
		     request.setAttribute("boardNo", boardNo);
		     
		     view.forward(request, response);
		}else {
			System.out.println("게시글 삭제 실패");
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
