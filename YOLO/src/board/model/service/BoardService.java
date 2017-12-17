package board.model.service;

import java.sql.Connection;
import java.util.ArrayList;
import static common.JDBCTemplate.*;

import board.model.dao.BoardDao;
import board.model.vo.Board;

public class BoardService {

	//게시판별 게시글의 갯수 파악
	public int countRow(int boardNo){
		int totalRow=0;
		Connection con=getConnection();
		totalRow=new BoardDao().countRow(con, boardNo);
		close(con);
		
		return totalRow;
	}
	
	//지역검색 row
	public int countRowByCountry(int boardNo, String country){
		Connection con=getConnection();
		int countRow=new BoardDao().countRowByCountry(con, boardNo, country);
		close(con);
		
		return countRow;
	}
	
	//제목검색 row
	public int countRowByTitle(int boardNo, String keyword){
		Connection con=getConnection();
		int countRow=new BoardDao().countRowByTitle(con, boardNo, keyword);
		close(con);
		
		return countRow;
	}
	
	//내글 보기 row
	public int countRowByWriter(int boardNo, String name){
		Connection con=getConnection();
		int countRow=new BoardDao().countRowByWriter(con, boardNo, name);
		close(con);
		
		return countRow;
	}
	
	
	//게시글 목록 보기 - 최신순
	public ArrayList<Board> selectBoard(int boardNo, int startNo,int endNo){
		Connection con=getConnection();
		ArrayList<Board> list=new BoardDao().selectBoard(con, boardNo, startNo, endNo);
		
		close(con);
		return list;
		
	}
	
	//게시글 보기 - 인기순
	public ArrayList<Board> selectBoardHit(int boardNo, int startNo,int endNo){
		Connection con=getConnection();
		ArrayList<Board> list=new BoardDao().selectBoardHit(con, boardNo, startNo, endNo);
		
		close(con);
		return list;
		
	}
	
	
	//게시글 작성
	public int writeBoard(int boardNo, Board b){
		
		System.out.println("게시글 작성  service");
		Connection con=getConnection();
		int result=new BoardDao().writeBoard(con, boardNo, b);
		close(con);
		return result;
	}
	
	//게시글 상세보기
		public Board selectOne(int boardNo, int textNo){
			Connection con=getConnection();
			
			
			//좋아요를 눌렀을 때 조회수가 올라가는 것 방지.
			
			Board b=new BoardDao().selectOne(con, boardNo, textNo);
			
			return b;
		}
	
	//게시글 수정. 수정을 위해서는 위에 상세보기로 일단 불러와야한다.
	public int updateText(int boardNo, Board b){
		Connection con=getConnection();
		
		int result=new BoardDao().updateText(con, boardNo, b);
		close(con);
		return result;
	}
	
	//게시글 삭제
	public int deleteText(int boardNo, int textNo){
		Connection con=getConnection();
		
		int result=new BoardDao().deleteText(con, boardNo, textNo);
		close(con);
		
		return result;
	}
	
	//게시글 추천시 추천수 올리기.
	public int likesCount(int boardNo, int textNo){
		Connection con=getConnection();
		
		int result=new BoardDao().likesCount(con, boardNo, textNo);
		close(con);
		
		return result;
	}
	
	//지역별 게시글 검색.
	public ArrayList<Board> searchByCountry(int boardNo, String country, int startNo, int endNo){
		Connection con=getConnection();
		ArrayList<Board> list=new BoardDao().searchByCountry(con, boardNo, country, startNo, endNo);
		close(con);
		
		return list;
	}
	//제목별 게시글 검색
	public ArrayList<Board> searchByTitle(int boardNo, String keyword, int startNo, int endNo){
		Connection con=getConnection();
		ArrayList<Board> list=new BoardDao().searchByTitle(con, boardNo, keyword, startNo, endNo);
		close(con);
		
		return list;
	}
	
	//내 글 보기 - jsp에서 사용자 닉네임/게시판 번호를 전달해야함.
	public ArrayList<Board> viewMyText(String name, int boardNo, int startNo, int endNo){
		Connection con=getConnection();
		ArrayList<Board> list=new BoardDao().viewMyText(con, name, boardNo, startNo, endNo);
		close(con);
		
		return list;
	}
	//조회수 1
	public int hitCount(int boardNo, int textNo){
		Connection con=getConnection();
		
		int result=new BoardDao().hitCount(con, boardNo, textNo);
		close(con);
		return result;
	}
	
}
