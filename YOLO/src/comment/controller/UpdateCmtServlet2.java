package comment.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import comment.model.service.CommentService;

import java.io.*;
/**
 * Servlet implementation class UpdateCmtServlet2
 */
@WebServlet("/update2")
public class UpdateCmtServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCmtServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//댓글 수정하기
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		int boardNo=0;
		int cmtNo=0;
		String content=null;
		boardNo=Integer.parseInt(request.getParameter("boardNo"));
		cmtNo=Integer.parseInt(request.getParameter("cmtNo"));
		content=request.getParameter("content");
		
		int result=new CommentService().updateComment(boardNo, cmtNo, content);
		
		if(result>0){
			//성공시
			response.setContentType("text/html; charset=utf-8");
			response.getWriter().append(Integer.toString(result));
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
