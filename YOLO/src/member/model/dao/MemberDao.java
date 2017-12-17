package member.model.dao;

import member.model.vo.Member;
import static common.JDBCTemplate.*;
import java.sql.*;

public class MemberDao {

	//로그인
	public Member memberLogin(Connection con, String id, String pwd) {
		Member member=null;
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String query="select * from member where id=? and pwd=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				member=new Member();
				member.setId(rset.getString("id"));
				member.setPwd(rset.getString("pwd"));
				member.setName(rset.getNString("name"));
			}
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally{
		   close(rset);
		   close(pstmt);
		}
		
		return member;
	}

	//회원정보 수정
	public int updateMember(Connection con, Member m) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String query="update member set pwd=?, name=? where id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, m.getPwd());
			pstmt.setString(2, m.getName());
			pstmt.setString(3, m.getId());
			
			result=pstmt.executeUpdate();
			
			if(result>0){
				commit(con);
			} else {
				rollback(con);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(pstmt);
		}
		
		return result;
	}

	//회원가입
	public int joinMember(Connection con, String id, String pwd, String name) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String query="insert into member values (?, ?, ?)";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, name);
			
			result=pstmt.executeUpdate();
			
			if(result>0){
				commit(con);
			}else {
				rollback(con);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(pstmt);
		}
		
	
		return result;	
	}

	//아이디 중복검사
	public Member checkId(Connection con, String id) {
		Member m=null;
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String query="select * from member where id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				m=new Member();
				m.setName(rset.getString("name"));
				m.setId(rset.getString("id"));
				m.setPwd(rset.getString("pwd"));
			}
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	//닉네임 중복검사
	public Member checkName(Connection con, String name) {
		Member m=null;
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String query="select * from member where name=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, name);
			rset=pstmt.executeQuery();
		
			if(rset.next()){
				m=new Member();
				m.setName(rset.getString("name"));
				m.setId(rset.getString("id"));
				m.setPwd(rset.getString("pwd"));
			
			}
		} catch (Exception e) {
			System.out.println("dao 에러");
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

	//회원 삭제
	public int deleteMember(Connection con, String id) {
		int result=0;
		PreparedStatement pstmt=null;
		
		String query="delete from member where id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			result=pstmt.executeUpdate();
			
			if(result>0){
				commit(con);
			} else {
				rollback(con);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(pstmt);
		}
		
		
		
		return result;
		
	}
	
	//회원 정보 보기 - 내 정보 수정시 나오는 메뉴
	
	public Member selectOne(Connection con,String id){
		Member m=new Member();
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String query="select * from member where id=?";
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, id);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				m.setId(id);
				m.setName(rset.getString("name"));
				m.setPwd(rset.getString("pwd"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return m;
	}

}
