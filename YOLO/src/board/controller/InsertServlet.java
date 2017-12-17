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
import board.model.vo.Board;

/**
 * Servlet implementation class InsertServlet
 */
@WebServlet("/binsert")
public class InsertServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//게시글 작성.
		//차후 이미지 처리 추가
		//에디터를 통해 건너온 정보를 토대로
		//이미지 추출 작업 실행 > 이미지 따로 저장.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		String fileName=null;
		
		if(request.getParameter("files")!=null)
			fileName = request.getParameter("files").substring(request.getParameter("files").lastIndexOf("/")+1);
		String filePath = "/YOLO/images/fuploadImage"/*+"/"+fileName*/;
		int boardNo=0;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		Board b=new Board();
		b.setWriter(request.getParameter("name"));
		b.setTextTitle(request.getParameter("title"));
		b.setTextContent(request.getParameter("content"));
		
		System.out.println("게시판 번호 "+boardNo);
		System.out.println("제목 : "+b.getTextTitle());
		System.out.println("작성자 : "+b.getWriter());
		System.out.println("내용 : "+b.getTextContent());
		
		
		String country="전국";
		//지역에 대한 처리
		if(request.getParameter("area")!=null){
		country=request.getParameter("area");
		}else {
			System.out.println("지역 받아오기 실패");
		}
		
		if(country.equals("경기")){
			b.setCountryCode(031);
		}else if(country.equals("인천")){
			b.setCountryCode(032);
		}else if(country.equals("강원")){
			b.setCountryCode(033);
		}else if(country.equals("충남")){
			b.setCountryCode(041);
		}else if(country.equals("대전")){
			b.setCountryCode(042);
		}else if(country.equals("충북")){
			b.setCountryCode(043);
		}else if(country.equals("부산")){
			b.setCountryCode(051);
		}else if(country.equals("울산")){
			b.setCountryCode(052);
		}else if(country.equals("대구")){
			b.setCountryCode(053);
		}else if(country.equals("경북")){
			b.setCountryCode(054);
		}else if(country.equals("경남")){
			b.setCountryCode(055);
		}else if(country.equals("전남")){
			b.setCountryCode(061);
		}else if(country.equals("광주")){
			b.setCountryCode(062);
		}else if(country.equals("서울")){
			b.setCountryCode(02);
		}else if(country.equals("전북")){
			b.setCountryCode(063);
		}else if(country.equals("제주")){
			b.setCountryCode(064);
		}else{
			//default값. 0 전국.
			b.setCountryCode(0);
		}
		System.out.println("지역번호 " +b.getCountryCode());
		//실험
		
		
		
		int result=new BoardService().writeBoard(boardNo, b);
		
		if(result>0){
			//글쓰기 성공.
			PrintWriter writer = response.getWriter();
		     writer.println("<script type='text/javascript'>");
		     writer.println("alert('작성이 완료되었습니다.');");
		     writer.println("</script>");
		     
		     RequestDispatcher view=
		    		 request.getRequestDispatcher("blist");
		     request.setAttribute("boardNo", boardNo);
		     view.forward(request, response);
		     
		}else {
			System.out.println("글쓰기 실패");
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
