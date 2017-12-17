package board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class SelectBoardServlet
 */
@WebServlet("/blist")
public class SelectBoardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시판별 리스트 보기. 최신순.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		
		int boardNo=0;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		//게시판 번호
		String boardName=null;
		//게시판 번호에 따른 게시판이름.
		switch(boardNo){
			case 1:
				boardName="추천코스";
				break;
			case 2:
				boardName="추천맛집";
				break;
			case 3:
				boardName="추천숙박";
				break;
			case 4:
				boardName="자유";
				break;
			case 5:
				boardName="함께해요";
				break;
			case 6:
				boardName="여행꿀팁";
				break;
				
		}
		//System.out.println("로딩1");
		
		int pageList=10;
		//밑에 표시할 페이지 수 - 밑에서 보정
		int currentPage=1;
		//현재페이지
		if(request.getParameter("page")!=null){
			//넘겨받은 현재 페이지가 있는 경우
			currentPage=Integer.parseInt(request.getParameter("page"));
		}
		
		int countList=12;
		//한 페이지당 보여줄 게시글의 수 - 10개
		
		int totalRow=0;
		//글의 갯수.
		totalRow=new BoardService().countRow(boardNo);
		
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
		
		int endPage=startPage+10-1;
		//화면에 보여줄 마지막 페이지
		if(endPage>maxPage){
			endPage=maxPage;
		}
		
		
		
		int startNo=0;
		//화면에 표시할 시작 글의 index
		int endNo=0;
		//화면에 표시할 마지막 글의 index
		
		startNo=(currentPage-1)*countList+1;
		endNo=startNo+countList-1;
		//System.out.println("로딩2");
		ArrayList<Board> list=new BoardService().selectBoard(boardNo, startNo, endNo);
		
		if(list!=null){
			//성공시
		//	System.out.println("로딩4");
			RequestDispatcher view=
						request.getRequestDispatcher("views/board/imageBoard/imageBoardList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("boardName", boardName);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("maxPage", maxPage);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("countList", countList);
			view.forward(request, response);
		} else {
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
