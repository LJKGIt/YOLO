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
 * Servlet implementation class UpdateTextServlet
 */
@WebServlet("/tupdate")
public class UpdateTextServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTextServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 수정을 위한 미리보기 서블릿
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");

		int boardNo=0;
		int textNo=0;
		//boardNo=Integer.parseInt(request.getParameter());
		//textNo=Integer.parseInt(request.getParameter());
		
		Board b=new BoardService().selectOne(boardNo, textNo);
		
		if(b!=null){
			RequestDispatcher view=
					request.getRequestDispatcher("");
			request.setAttribute("text", b);
			view.forward(request, response);
		}else {
			//실패시 처리
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
