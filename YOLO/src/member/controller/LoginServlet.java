package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		System.out.println("로그인서블릿");
		
		//아이디 비밀번호 받기.
		String id=null;
		String pwd=null;
		id=request.getParameter("userid");
		pwd=request.getParameter("userpwd");
		
		
		
		
		Member member=new MemberService().memberLogin(id, pwd);
		
		//로그인 성공
		if(member!=null){
			
			HttpSession session=request.getSession();
			session.setAttribute("member", member);
			//화면 이동
			response.sendRedirect("index.jsp");
			
			//실패
		} else {
			PrintWriter writer = response.getWriter();
		     writer.println("<script type='text/javascript'>");
		     writer.println("alert('로그인에 실패하였습니다. ID와 비밀번호를 확인해주세요.');");
		     writer.println("</script>");
		     //로그인 실패시 이동화면
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
