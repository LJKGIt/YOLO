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
 * Servlet implementation class SearchServlet2
 */
@WebServlet("/search2")
public class SearchServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		//제목검색
		int boardNo=0;
		String keyword=null;
		
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		
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
		
		keyword=request.getParameter("search");
		System.out.println("키워드 : "+keyword);
		//게시판 번호
				int pageList=10;
				//밑에 표시할 페이지 수 - 밑에서 보정
				int currentPage=1;
				//현재페이지
				if(request.getParameter("page")!=null){
					//넘겨받은 현재 페이지가 있는 경우
					currentPage=Integer.parseInt(request.getParameter("page"));
				}
				
				int countList=10;
				//한 페이지당 보여줄 게시글의 수 - 10개
				
				int totalRow=0;
				//글의 갯수.
				totalRow=new BoardService().countRowByTitle(boardNo, keyword);
				System.out.println("검색 갯수 : "+totalRow);
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
				
				int startNo=(currentPage-1)*countList+1;
				int endNo=startNo+countList-1;
		
		ArrayList<Board> list=new BoardService().searchByTitle(boardNo, keyword, startNo, endNo);
		
		if(list!=null){
			RequestDispatcher view=
							request.getRequestDispatcher("views/board/imageBoard/imageBoardList.jsp");
			request.setAttribute("list", list);
			request.setAttribute("currentPage", currentPage);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("boardNo", boardNo);
			request.setAttribute("boardName", boardName);
			request.setAttribute("countList", countList);
			request.setAttribute("maxPage", maxPage);
			view.forward(request, response);
		}else {
			System.out.println("제목검색실패");
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
