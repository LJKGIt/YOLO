package board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;

/**
 * Servlet implementation class HitCountServlet
 */
@WebServlet("/hit")
public class HitCountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HitCountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//조회수 올리는 서블릿
		
		int boardNo=Integer.parseInt(request.getParameter("boardNo"));
		int textNo=Integer.parseInt(request.getParameter("textNo"));
		
		
		int result=new BoardService().hitCount(boardNo, textNo);
		
		if(result>0){
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(Integer.toString(result));
			
		}else {
			System.out.println("조회수 증가 실패");
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
