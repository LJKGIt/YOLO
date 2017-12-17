package scrap.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrap.model.service.ScrapService;
import scrap.model.vo.Scrap;

/**
 * Servlet implementation class ClickScrap
 */
@WebServlet("/scrap")
public class ClickScrap extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClickScrap() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//스크랩 버튼 눌렀을 때.
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String name=null;
		int  boardNo=0;
		int textNo=0;
		name=request.getParameter("name");
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		textNo=Integer.parseInt(request.getParameter("textNo"));		
		Scrap sc=new Scrap();
		sc.setBoardNo(boardNo);
		sc.setTextNo(textNo);
		sc.setName(name);
		
		int result=new ScrapService().clickScrap(sc);
		
		if(result>0){
			//성공시
		response.setContentType("text/html; charset=utf-8");
		response.getWriter().append(Integer.toString(result));
		     //스크랩 후 페이지 이동.
			   //  response.addHeader("refresh", "1;url=");
		}else {
			//실패시
			
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
