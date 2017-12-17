 package comment.model.dao;

import comment.model.vo.Comment;
import static common.JDBCTemplate.*;

import java.sql.*;
import java.util.ArrayList;

public class CommentDao {

	
	//특정 글에 대한 댓글의 갯수를 구함.
	public int CountRow(Connection con, int boardNo, int textNo){
		int totalRow=0;
		ResultSet rset=null;
		PreparedStatement pstmt=null;
		
		String query=null;
		switch(boardNo){
			case 1:
				query="select count(*) from course_comment"
						+ " where wr_number=?";
				break;
			case 2:
				query="select count(*) from store_comment"
						+ " where wr_number=?";
				break;
			case 3:
				query="select count(*) from stay_comment"
						+ " where wr_number=?";
				break;
			case 4:
				query="select count(*) from free_comment"
						+ " where wr_number=?";
				break;
			case 5:
				query="select count(*) from accompany_comment"
						+ " where wr_number=?";
				break;
			case 6:
				query="select count(*) from tip_comment"
						+ " where wr_number=?";
				break;
		}
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, textNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				totalRow=rset.getInt(1);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(rset);
			close(pstmt);
		}
		
		
		return totalRow;
		
		
	}
	
	//댓글 작성
	//댓글의 댓글인 경우 선댓글 번호와, 댓글 깊이를 알아내야 하는데
	//어떻게 할 것인지?
	// 게시판 번호도 보내야함..
	//어떻게 처리할 것인지에 대해 고민해봐야 할듯.
	public int writeComment(Connection con,int boardNo, Comment c){
		int result=0;
		
		PreparedStatement pstmt=null;
		
		String query=null;
		
		//댓글 코드에 따라 저장되는 테이블이 다름.
		//선댓글 유무에 따라 쿼리문이 다름. (댓글의 댓글)
		
		if(c.getPreCmtNo()==0){
			//댓글의 댓글이 아닌경우.
			switch(boardNo){
				case 1:
					query="insert into course_comment values("
							+ "?, (select max(cmt_number) from course_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
				case 2:
					query="insert into store_comment values("
							+ "?, (select max(cmt_number) from store_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
				case 3:
					query="insert into stay_comment values("
							+ "?, (select max(cmt_number) from stay_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
				case 4:
					query="insert into free_comment values("
							+ "?, (select max(cmt_number) from free_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
				case 5:
					query="insert into accompany_comment values("
							+ "?, (select max(cmt_number) from accompany_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
				case 6:
					query="insert into tip_comment values("
							+ "?, (select max(cmt_number) from tip_comment)+1,"
							+ "null, 0, ?, ?, default)";
					break;
			
			} //스위치 끝
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, c.getTextNo());
				pstmt.setString(2, c.getCommentWriter());
				pstmt.setString(3, c.getCommentContent());
				
				result=pstmt.executeUpdate();
				
				if(result>0)
					commit(con);
				else
					rollback(con);
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				close(pstmt);
			}
			
			
		}// if 끝 
		else {
			//댓글의 댓글인 경우.
			switch(boardNo){
			case 1:
				query="insert into course_comment values("
						+ "?, (select max(cmt_number) from course_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
			case 2:
				query="insert into store_comment values("
						+ "?, (select max(cmt_number) from store_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
			case 3:
				query="insert into stay_comment values("
						+ "?, (select max(cmt_number) from stay_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
			case 4:
				query="insert into free_comment values("
						+ "?, (select max(cmt_number) from free_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
			case 5:
				query="insert into accompany_comment values("
						+ "?, (select max(cmt_number) from accompany_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
			case 6:
				query="insert into tip_comment values("
						+ "?, (select max(cmt_number) from tip_comment)+1,"
						+ "?, ?, ?, ?, default)";
				break;
		
		}//switch 끝
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, c.getTextNo());
				pstmt.setInt(2, c.getPreCmtNo());
				pstmt.setInt(3, c.getLevel());
				pstmt.setString(4, c.getCommentWriter());
				pstmt.setString(5, c.getCommentContent());
				
				result=pstmt.executeUpdate();
				
				if(result>0)
					commit(con);
				else
					rollback(con);
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				close(pstmt);
			}
		}//else의 끝
		
		return result;
	}
	
	//댓글 수정.
	public int updateComment(Connection con, int boardNo, int commentNo,
			String commentContent){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		switch(boardNo){
			case 1:
				query="update course_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			case 2:
				query="update store_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			case 3:
				query="update stay_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			case 4:
				query="update free_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			case 5:
				query="update accompany_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			case 6:
				query="update tip_comment set cmt_content=?"
						+ "where cmt_number=?";
				break;
			default :
		}
		
		try {
			//구현
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, commentContent);
			pstmt.setInt(2, commentNo);
			result=pstmt.executeUpdate();
			if(result>0){
				commit(con);
			}else {
				rollback(con);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(pstmt);
		}
		
		return result;
	}
	
	//댓글 삭제
	public int deleteComment(Connection con, int boardNo, int commentNo){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="delete from course_comment where cmt_number=?";
				break;
			case 2:
				query="delete from store_comment where cmt_number=?";
				break;
			case 3:
				query="delete from stay_comment where cmt_number=?";
				break;
			case 4:
				query="delete from free_comment where cmt_number=?";
				break;
			case 5:
				query="delete from accompany_comment where cmt_number=?";
				break;
			case 6:
				query="delete from tip_comment where cmt_number=?";
				break;
			default :
				break;
		}
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, commentNo);
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
	
	
	//게시글별 댓글 보여주는 기능
	public ArrayList<Comment> selectComment(Connection con, int boardNo, int textNo,
			int startNo, int endNo){
		ArrayList<Comment> list=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String query=null;
		
		//게시판에 따라 쿼리문 상이.
		switch(boardNo){
			case 1:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from course_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
			case 2:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from store_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
			case 3:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from stay_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
			case 4:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from free_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
			case 5:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from accompany_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
			case 6:
				query="select * from "
						+ "(select rownum rnum, cmt_writer, "
						+ "cmt_level, cmt_number, cmt_date, "
						+ "pre_cmt_number, cmt_content from "
						+ "(select * from tip_comment "
						+ "where wr_number=? "
						+ "start with pre_cmt_number is null "
						+ "connect by prior "
						+ "cmt_number=pre_cmt_number) "
						+ "where rownum <=?) where rnum>=?";
				break;
				
		}
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1,	textNo);
			pstmt.setInt(2, endNo);
			pstmt.setInt(3, startNo);
			
			rset=pstmt.executeQuery();
			if(rset!=null){
				list=new ArrayList<Comment>();
			}while(rset.next()){
				Comment c=new Comment();
				c.setTextNo(textNo);
				c.setCommentNo(rset.getInt("cmt_number"));
				c.setCommentWriter(rset.getString("cmt_writer"));
				c.setCommentContent(rset.getString("cmt_content"));
				c.setDate(rset.getDate("cmt_date"));
				c.setLevel(rset.getInt("cmt_level"));
				c.setPreCmtNo(rset.getInt("pre_cmt_number"));
				list.add(c);
			}
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	//댓글 수정시 띄워줘야 할 댓글 select
	public Comment selectOne(Connection con, int boardNo, int cmtNo){
		Comment c=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query=null;
		
		//게시판 번호에 따라 쿼리문 상이
		switch(boardNo){
			case 1:
				query="select * from course_comment where cmt_number=?";
				break;
			case 2:
				query="select * from store_comment where cmt_number=?";
				break;
			case 3:
				query="select * from stay_comment where cmt_number=?";
				break;
			case 4:
				query="select * from free_comment where cmt_number=?";
				break;
			case 5:
				query="select * from accompany_comment where cmt_number=?";
				break;
			case 6:
				query="select * from tip_comment where cmt_number=?";
				break;
			default :
		}
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, cmtNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				c.setCommentNo(rset.getInt("cmt_number"));
				c.setCommentContent(rset.getString("cmt_content"));
				c.setCommentWriter(rset.getString("cmt_writer"));
				c.setDate(rset.getDate("cmt_date"));
				c.setTextNo(rset.getInt("wr_number"));
				c.setLevel(rset.getInt("cmt_level"));
				c.setPreCmtNo(rset.getInt("pre_cmt_number"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(rset);
			close(pstmt);
		}
		
		
		return c;
	}
	
}
