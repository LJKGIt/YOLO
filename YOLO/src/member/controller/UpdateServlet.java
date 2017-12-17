package member.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;
import java.io.*;
/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
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
		id=request.getParameter("id");
		
		
		
		
		pwd=request.getParameter("pwd");
		
		name=request.getParameter("name");
		
		
		
		System.out.println("수정확인"+id+pwd+name);
		
		Member member=new Member();
		member.setId(id);
		member.setName(name);
		member.setPwd(pwd);
		
		int result=new MemberService().updateMember(member);

		if(result>0){
			//성공시
		PrintWriter writer=response.getWriter();
			writer.println("<script type='text/javascript'>");
		     writer.println("alert('수정이 완료되었습니다.');");
		     writer.println("</script>");
		 		HttpSession session=request.getSession();
		 		session.setAttribute("member", member);
		     response.addHeader("refresh", "1;url=index.jsp");
		     
		} else {
			//실패시
			System.out.println("회원정보 수정 실패");
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
