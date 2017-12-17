package member.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import member.model.vo.Member;
import java.io.*;
/**
 * Servlet implementation class CheckIdservlet
 */
@WebServlet("/checkId")
public class CheckIdservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CheckIdservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		String id=null;
		id=request.getParameter("id");
		System.out.println("파라미터 값 : "+id);
		Member m=new MemberService().checkId(id);
		
		
		
		PrintWriter writer=null;
		if(m==null){
			//중복없음.
			writer=response.getWriter();
			writer.append("yes");
			writer.flush();
			writer.close();
		}else if(m!=null){
			//중복 있음.
				writer=response.getWriter();
				writer.append("no");
				writer.flush();
				writer.close();
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
