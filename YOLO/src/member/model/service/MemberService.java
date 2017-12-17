package member.model.service;

import member.model.dao.MemberDao;
import member.model.vo.Member;
import java.sql.Connection;
import static common.JDBCTemplate.*;

public class MemberService {

	//로그인
	public Member memberLogin(String id, String pwd) {
		
		Connection con=getConnection();
		Member member=new MemberDao().memberLogin(con, id, pwd);
		
		close(con);
		return member;

	}
	
	//정보 수정.
	public int updateMember(Member m) {
		
		Connection con=getConnection();
		
		int result=new MemberDao().updateMember(con, m);
		close(con);
		return result;
	}
	
	//회원가입
	public int joinMember(String id, String pwd, String name) {
		int result=0;
		Connection con=getConnection();
		
		result=new MemberDao().joinMember(con, id, pwd, name);
		
		close(con);
		
		return result;
	}

	//id 중복검사
	public Member checkId(String id) {
		Connection con=getConnection();
		
		Member m=new MemberDao().checkId(con, id);
		close(con);
		return m;
		
	}

	//닉네임 중복검사
	public Member checkName(String name) {
		Connection con=getConnection();
		System.out.println("서비스 도착");
		Member m=new MemberDao().checkName(con, name);
		close(con);
		return m;
	}

	//회원탈퇴
	public int deleteMember(String id) {
		int result=0;
		Connection con=getConnection();
		
		result=new MemberDao().deleteMember(con, id);
		
		close(con);
		return result;
	}
	
	//해당 멤버 정보 조회 - 수정시 필요
	public Member selectOne(String id){
		Connection con=getConnection();
		
		Member m=new MemberDao().selectOne(con, id);
		
		close(con);
		return m;
	}
	
}
