package scrap.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scrap.model.service.ScrapService;
import scrap.model.vo.Scrap;

/**
 * Servlet implementation class DeleteScrapServlet
 */
@WebServlet("/sdelete")
public class DeleteScrapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteScrapServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//스크랩 삭제.
		System.out.println("스크랩삭제 서블릿 도착");
		int scrapNo=0;
		//스크랩 번호
		int boardNo=1;
		//스크랩 삭제 후 되돌아가기 위함.
		scrapNo = Integer.parseInt(request.getParameter("scrapNo"));
		String name=request.getParameter("name");
		//위와 마찬가지.
		int page=1;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));

		System.out.println(scrapNo + ", " + name + ", " + boardNo);
		int result=new ScrapService().cancelScrap(scrapNo);
		
		if(result>0){
			//삭제 성공시
			/*PrintWriter writer=response.getWriter();
			writer.println("<script type='text/javascript'>");
		     writer.println("alert('스크랩이 취소되었습니다.');");
		     writer.println("</script>");*/
			RequestDispatcher view=
					request.getRequestDispatcher("viewscrap");
			request.setAttribute("name", name);
			request.setAttribute("boardNo", boardNo);
			view.forward(request, response);
		}else {
			System.out.println("스크랩 취소 실패");
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
