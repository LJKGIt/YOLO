package comment.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.vo.Board;
import comment.model.service.CommentService;
import comment.model.vo.Comment;

/**
 * Servlet implementation class SelectServlet
 */
@WebServlet("/cselect")
public class SelectCmtServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCmtServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글을 보여주는 서블릿.
		
		response.setContentType("text/html; charset=utf-8");
		
		int textNo=0;
		//글의 번호
		Board b = (Board)request.getAttribute("board");
		
		textNo = b.getTextNo();
		int boardNo=((Integer)request.getAttribute("boardNo")).intValue();
		//게시판의 번호
		int boardPage = 1;
		if(request.getParameter("page") != null){
			boardPage = ((Integer)request.getAttribute("page")).intValue();
		}
		
		int pageList=10;
		//밑에 표시할 페이지 수 - 밑에서 보정
		int currentPage=1;
		//현재페이지
		if(request.getParameter("commentPage")!=null){
			//넘겨받은 현재 페이지가 있는 경우
			currentPage=Integer.parseInt(request.getParameter("commentPage"));
		}
		
		int countList=10;
		//한 페이지당 보여줄 댓글의 수 - 20개
		
		int totalRow=0;
		//글의 갯수.
		totalRow=new CommentService().countRow(boardNo, textNo);
		
		int maxPage=totalRow/countList;
		//총 페이지 게시글의 수 / 보여줄 글 수
		if(totalRow%countList>0){
			//만약 위에 계산식에 나머지가 있는 경우 - 페이지 +1
			maxPage++;
		}
		
		if(currentPage>maxPage){
			//만약 현재 페이지가 총 페이지 보다 크다면 보정
			currentPage=maxPage;
		}
		
		
		int startPage=1;
		//시작페이지
		if(currentPage>10){
			//만약 현재 페이지가 11이상인 경우.
			startPage=((currentPage-1)/countList)*countList+1;
		}
		
		int endPage=startPage+10;
		//화면에 보여줄 마지막 페이지
		if(endPage>maxPage){
			endPage=maxPage;
		}
		
		int startNo=(currentPage-1)*countList+1;
		int endNo=startNo+countList-1;
		
		
		ArrayList<Comment> list=new CommentService().selectComment(boardNo, textNo, startNo, endNo);
		
		RequestDispatcher view = null;
		if(list!=null){
			//성공시
			view=request.getRequestDispatcher("views/board/imageBoard/boardDetailView.jsp");
			request.setAttribute("list", list);
			request.setAttribute("commentPage", currentPage);
			request.setAttribute("page", boardPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("board", b);
			view.forward(request, response);
		} else {
			view = request.getRequestDispatcher("views/board/imageBoard/boardError.jsp");
			request.setAttribute("message", "댓글 불러오기 실패");
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
