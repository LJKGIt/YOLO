package comment.model.service;

import java.sql.*;
import java.util.ArrayList;

import static common.JDBCTemplate.*;

import comment.model.dao.CommentDao;
import comment.model.vo.Comment;

public class CommentService {

	
	//게시글별 댓글 개수 리턴.
	public int countRow(int boardNo, int textNo){
		int totalRow=0;
		Connection con=getConnection();
		
		totalRow=new CommentDao().CountRow(con, boardNo, textNo);
		close(con);
		
		return totalRow;
	}
	
	
	//댓글 작성
	public int writeComment(int boardNo, Comment c){
		int result=0;
		Connection con=getConnection();
		result=new CommentDao().writeComment(con, boardNo, c);
		close(con);
		return result;
	}
	
	//댓글 수정
	public int updateComment(int boardNo, int commentNo,
			String commentContent){
		int result=0;
		Connection con=getConnection();
		result=new CommentDao().updateComment(con, boardNo, commentNo, commentContent);
		close(con);
		
		return result;
	}
	//댓글 삭제
	public int deleteComment(int boardNo, int commentNo){
		int result=0;
		Connection con=getConnection();
		result=new CommentDao().deleteComment(con, boardNo, commentNo);
		close(con);
		return result;
	}
	
	//댓글 보여주기 - 게시글별
	public ArrayList<Comment> selectComment(int boardNo, int textNo, int startNo, int endNo){
		ArrayList<Comment> list=null;
		Connection con=getConnection();
		list=new CommentDao().selectComment(con, boardNo, textNo, startNo, endNo);
		close(con);
		return list;
	}
	
	//댓글하나 보여주기 - 수정시
	public Comment selectOne(int boardNo, int cmtNo){
		Comment c=null;
		Connection con=getConnection();
		c=new CommentDao().selectOne(con, boardNo, cmtNo);
		close(con);
		return c;
	}
}
