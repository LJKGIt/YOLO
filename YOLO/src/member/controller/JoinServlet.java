package member.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import member.model.service.MemberService;
import java.io.*;
/**
 * Servlet implementation class JoinServlet
 */
@WebServlet("/join")
public class JoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JoinServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String id=null;
		String pwd=null;
		String name=null;
		
		id=request.getParameter("userid");
		pwd=request.getParameter("userpwd");
		name=request.getParameter("nickname");
		
		System.out.println("값 확인 : "+id+pwd+name);
		
		int result=new MemberService().joinMember(id, pwd, name);
		PrintWriter writer =null;
		if(result>0){
			//성공시
			writer	= response.getWriter();
		     writer.println("<script type='text/javascript'>");
		     writer.println("alert('회원가입이 완료되었습니다. 로그인 해주세요.');");
		     writer.println("</script>");
		     //회원가입시 메인화면
		     

		     response.addHeader("refresh", "1;url=index.jsp");
			   
		} else {
			//실패시
			writer	= response.getWriter();
		     writer.println("<script type='text/javascript'>");
		     writer.println("alert('회원가입에 실패하였습니다. 주어진 양식을 다 작성해주세요.');");
		     writer.println("</script>");
		     response.addHeader("refresh", "1;url=index.jsp");
			   
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
