package board.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import board.model.service.BoardService;
import board.model.vo.Board;

/**
 * Servlet implementation class SelectBoardServlet2
 */
@WebServlet("/blist2")
public class SelectBoardServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectBoardServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//인기순으로 보기.
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		//전달할 JSON객체 생성
		JSONObject json = new JSONObject();
		JSONArray jarr = new JSONArray();
		
		int boardNo=0;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		
		//밑에 표시할 페이지 수 - 밑에서 보정
		int currentPage=1;
		//현재페이지
		if(request.getParameter("page")!=null){
			//넘겨받은 현재 페이지가 있는 경우
			currentPage=Integer.parseInt(request.getParameter("page"));
		}
		int countList=10;
		//한 페이지당 보여줄 게시글의 수 - 10개
		
		int startNo=(currentPage-1)*countList+1;
		int endNo=startNo+countList-1;
		
		
		ArrayList<Board> list=new BoardService().selectBoardHit(boardNo, startNo, endNo);
		
		for(Board b : list){
			//한 사람의 정보를 저장할 json 객체
			JSONObject job = new JSONObject();
			job.put("rNum", b.getRownum());
			job.put("bnum", b.getTextNo());
			job.put("title", URLEncoder.encode(b.getTextTitle() , "UTF-8").replace('+', ' '));
			jarr.add(job);
		}
		
		json.put("list", jarr);
		response.setContentType("application/json"); 
		PrintWriter out = response.getWriter();
		out.print(json.toJSONString());
		out.flush();
		out.close();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
