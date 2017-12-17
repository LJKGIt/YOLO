package board.model.dao;

import static common.JDBCTemplate.*;

import java.sql.*;
import java.util.*;
import board.model.vo.Board;

public class BoardDao {

	//게시글의 수 리턴. - 페이징에 사용.
	public int countRow(Connection con, int boardNo){
		int totalRow=0;
		Statement stmt=null;
		ResultSet rset=null;
		String query=null;
		
		switch(boardNo){
			case 1:
				query="select count(*) from course_board";
				break;
			case 2:
				query="select count(*) from store_board";
				break;
			case 3:
				query="select count(*) from stay_board";
				break;
			case 4:
				query="select count(*) from free_board";
				break;
			case 5:
				query="select count(*) from accompany_board";
				break;
			case 6:
				query="select count(*) from tip_board";
				break;
			default :
		
		}
		
		try {
			stmt=con.createStatement();
			rset=stmt.executeQuery(query);
			
			if(rset.next())
				totalRow=rset.getInt(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(rset);
			close(stmt);
		}
		
		return totalRow;
		
	}
	
	//지역 검색에 따른 row 수 리턴
	public int countRowByCountry(Connection con, int boardNo, String country){
		int totalRow=0;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="select count(*) "
						+ "from course_board "
						+ "where country_code=?";
				break;
			case 2:
				query="select count(*) "
						+ "from store_board "
						+ "where country_code=?";
				break;
			case 3:
				query="select count(*) "
						+ "from stay_board "
						+ "where country_code=?";
				break;
			case 4:
				query="select count(*) "
						+ "from free_board "
						+ "where country_code=?";
				break;
			case 5:
				query="select count(*) "
						+ "from accompany_board "
						+ "where country_code=?";
				break;
			case 6:
				query="select count(*) "
						+ "from tip_board "
						+ "where country_code=?";
				break;
				
		}
		
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, country);
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
	
	//제목 검색에 따른 row 수 리턴
	
	
	//게시글 보기 - 최신순(기본값)
	public ArrayList<Board> selectBoard(Connection con, int boardNo, int startNo, int endNo){
		ArrayList<Board> list=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		
		String query=null;
		//게시판에 따라 쿼리문이 달라짐.
		switch(boardNo){
			case 1:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from course_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
			case 2:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from store_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
			case 3:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from stay_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
			case 4:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from free_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
			case 5:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from accompany_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
			case 6:
				query="select * from(select rownum rnum, "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name,"
						+ " wr_hits, wr_likes from(select "
						+ "board_number, board_name, "
						+ "wr_number, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from tip_board "
						+ "join country using (country_code) "
						+ "join board using (board_number) "
						+ "order by wr_number desc) "
						+ "where rownum<=?) where rnum>=?";
					break;
				default: 
					
		}//switch
		
		try {
			
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, endNo);
			pstmt.setInt(2, startNo);
			rset=pstmt.executeQuery();
			
			if(rset!=null){
				list=new ArrayList<Board>();
				while(rset.next()){
					Board b=new Board();
					b.setBoardName(rset.getString("board_name"));
					b.setBoardNo(rset.getInt("board_number"));
					b.setWriter(rset.getString("wr_writer"));
					b.setCountry(rset.getString("country_name"));
					b.setTextNo(rset.getInt("wr_number"));
					b.setDate(rset.getDate("wr_date"));
					b.setHits(rset.getInt("wr_hits"));
					b.setLikes(rset.getInt("wr_likes"));
					b.setTextTitle(rset.getString("wr_title"));
					b.setTextContent(rset.getString("wr_content"));
					list.add(b);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			close(rset);
			close(pstmt);
		}
		
		return list;
		
	}
	//게시글 보기 - 인기순
		public ArrayList<Board> selectBoardHit(Connection con, int boardNo, int startNo, int endNo){
			ArrayList<Board> list=null;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			//게시판에 따라 쿼리문이 달라짐.
			switch(boardNo){
				case 1:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from course_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
				
				case 2:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from store_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
					
				case 3:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from stay_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
					
				case 4:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from free_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
				case 5:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from accompany_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
				case 6:
					query="select * from"
							+ "(select rownum rnum, board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from(select board_number,"
							+ " wr_number, wr_writer, wr_title,"
							+ " wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from tip_board "
							+ "join country using (country_code) order "
							+ "by wr_likes desc) where rownum<=?) where "
							+ "rnum>=?";
					break;
				default :
			}
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setInt(1, endNo);
				pstmt.setInt(2, startNo);
				rset=pstmt.executeQuery();
				
				if(rset!=null){
					list=new ArrayList<Board>();
					while(rset.next()){
						Board b=new Board();
						b.setRownum(rset.getInt(1));
						b.setBoardNo(rset.getInt("board_number"));
						b.setWriter(rset.getString("wr_writer"));
						b.setCountry(rset.getString("country_name"));
						b.setTextNo(rset.getInt("wr_number"));
						b.setDate(rset.getDate("wr_date"));
						b.setHits(rset.getInt("wr_hits"));
						b.setLikes(rset.getInt("wr_likes"));
						b.setTextTitle(rset.getString("wr_title"));
						b.setTextContent(rset.getString("wr_content"));
						list.add(b);
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				close(rset);
				close(pstmt);
			}
			
			return list;
			
		}
	
	
	//게시글 작성
	public int writeBoard(Connection con, int boardNo, Board b){
		int result=0;
		PreparedStatement pstmt=null;
		String query=null;
		
		System.out.println("게시글 작성  dao");
		switch(boardNo){
			case 1:
				query="insert into course_board values "
						+ "(1, "
						+ "(select max(wr_number) from course_board)+1,"
						+ " ?, ?, ?, default, ?, 0, 0)";
				break;
			case 2:
				query="insert into store_board values "
						+ "(2"
						+ "(select max(wr_number) from store_board)+1,"
						+ " ?, ?, ?, ?, default, ?, 0, 0)";
				break;
			case 3:
				query="insert into stay_board values "
						+ "(3, "
						+ "(select max(wr_number) from stay_board)+1,"
						+ " ?, ?, ?, default, ?, 0, 0)";
				break;
			case 4:
				query="insert into free_board values "
						+ "(4,"
						+ " (select max(wr_number) from free_board)+1"
						+ ", ?, ?, ?, default, ?, 0, 0)";
				break;
			case 5:
				query="insert into accompany_board values "
						+ "(5,"
						+ " (select max(wr_number) from accompany_board)+1,"
						+ " ?, ?, ?, default, ?, 0, 0)";
				break;
			case 6:
				query="insert into tip_board values "
						+ "(6,"
						+ "(select max(wr_number) from tip_board)+1 ,"
						+ " ?, ?, ?, default, ?, 0, 0)";
				break;
				
		}
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, b.getWriter());
			pstmt.setString(2, b.getTextTitle());
			pstmt.setString(3, b.getTextContent());
			pstmt.setInt(4, b.getCountryCode());
			result=pstmt.executeUpdate();
			
			System.out.println("dao result"+result);
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
	
	//게시글 상세보기 - 댓글이랑 연결해야함.(selectOne)
	//게시글수정시에도 이 메서드를 사용해서 정보 가져감.
	public Board selectOne(Connection con, int boardNo, int textNo){
		Board b=null;
		PreparedStatement pstmt=null;
		ResultSet rset=null;
		String query=null;
		//System.out.println("로딩0");
		switch(boardNo){
			case 1:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from course_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
			case 2:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from store_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
			case 3:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from stay_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
			case 4:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from free_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
			case 5:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from accompany_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
			case 6:
				query="select board_number, "
						+ "wr_number, board_name, wr_writer, wr_title, "
						+ "wr_content, wr_date, country_name, "
						+ "wr_hits, wr_likes from tip_board "
						+ "join country using (country_code) "
						+ "join board using (board_number)"
						+ "where wr_number=?";
						break;
				
			default :
		}
		
		//System.out.println("로딩0-1");
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, textNo);
			rset=pstmt.executeQuery();
			
			if(rset.next()){
				System.out.println("로딩0-2");
				b=new Board();
				b.setBoardNo(rset.getInt("board_number"));
				b.setTextNo(rset.getInt("wr_number"));
				b.setWriter(rset.getString("wr_writer"));
				b.setCountry(rset.getString("country_name"));
				b.setDate(rset.getDate("wr_date"));
				b.setHits(rset.getInt("wr_hits"));
				b.setLikes(rset.getInt("wr_likes"));
				b.setTextTitle(rset.getString("wr_title"));
				b.setTextContent(rset.getString("wr_content"));
				b.setBoardName(rset.getString("board_name"));
				//System.out.println(b.toString());
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			close(rset);
			close(pstmt);
		}
		//System.out.println("로딩0-3");
		return b;
	}
	
	//게시글 수정
	//제목, 내용, 지역 수정.
	public int updateText(Connection con, int boardNo, Board b){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="update course_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			case 2:
				query="update store_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			case 3:
				query="update stay_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			case 4:
				query="update free_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			case 5:
				query="update accompany_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			case 6:
				query="update tip_board set"
						+ "wr_title=?,"
						+ "wr_content=?,"
						+ "country_code=?"
						+ "where wr_number=?";
				break;
			default :
				
		}
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setString(1, b.getTextTitle());
			pstmt.setString(2, b.getTextContent());
			pstmt.setInt(3, b.getCountryCode());
			pstmt.setInt(4, b.getTextNo());
			
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
	
	//게시글 삭제
	public int deleteText(Connection con, int boardNo, int textNo){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="delete from course_board where wr_number=?";
				break;
			case 2:
				query="delete from store_board where wr_number=?";
				break;
			case 3:
				query="delete from stay_board where wr_number=?";
				break;
			case 4:
				query="delete from free_board where wr_number=?";
				break;
			case 5:
				query="delete from accompany_board where wr_number=?";
				break;
			case 6:
				query="delete from tip_board where wr_number=?";
				break;
			default :
				
		}
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, textNo);
			
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
	
	//게시글 조회시 조회수 +1
	//게시글 상세보기와 연결.
	public int hitCount(Connection con, int boardNo, int textNo){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="update course_board set wr_hits="
						+ "(select wr_hits from course_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 2:
				query="update store_board set wr_hits="
						+ "(select wr_hits from store_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 3:
				query="update stay_board set wr_hits="
						+ "(select wr_hits from stay_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 4:
				query="update free_board set wr_hits="
						+ "(select wr_hits from free_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 5:
				query="update accompany_board set wr_hits="
						+ "(select wr_hits from accompany_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 6:
				query="update tip_board set wr_hits="
						+ "(select wr_hits from tip_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			default :
				
		}//switch 끝
		try {
			//System.out.println("조회수 dao");
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, textNo);
			pstmt.setInt(2, textNo);
			result=pstmt.executeUpdate();
			//System.out.println("조회수 dao2");
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
	
	//게시글 추천시 추천수 +1
	public int likesCount(Connection con, int boardNo, int textNo){
		int result=0;
		PreparedStatement pstmt=null;
		
		String query=null;
		
		switch(boardNo){
			case 1:
				query="update course_board set wr_likes="
						+ "(select wr_likes from course_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 2:
				query="update store_board set wr_likes="
						+ "(select wr_likes from store_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 3:
				query="update stay_board set wr_likes="
						+ "(select wr_likes from stay_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 4:
				query="update free_board set wr_likes="
						+ "(select wr_likes from free_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 5:
				query="update accompany_board set wr_likes="
						+ "(select wr_likes from accompany_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			case 6:
				query="update tip_board set wr_likes="
						+ "(select wr_likes from tip_board "
						+ "where wr_number=?)+1 "
						+ "where wr_number=?";
				break;
			default :
				
		}
		try {
			pstmt=con.prepareStatement(query);
			pstmt.setInt(1, textNo);
			pstmt.setInt(2, textNo);
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
	
		//게시글 검색 - 지역순으로
		public ArrayList<Board> searchByCountry(Connection con, int boardNo, String country,
				int startNo, int endNo){
			ArrayList<Board> list=null;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			//게시판에 따라 쿼리문이 달라짐.
			switch(boardNo){
				case 1:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from course_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 2:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from store_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 3:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from stay_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 4:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from free_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 5:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from accompany_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 6:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes  from tip_board "
							+ "join country using (country_code) "
							+ "where country_name=? order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
					default :
			}
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, country);
				pstmt.setInt(2, endNo);
				pstmt.setInt(3, startNo);
				rset=pstmt.executeQuery();
				
				if(rset!=null){
					list=new ArrayList<Board>();
					while(rset.next()){
						Board b=new Board();
						b.setBoardNo(rset.getInt("board_number"));
						b.setWriter(rset.getString("wr_writer"));
						b.setCountry(rset.getString("country_name"));
						b.setTextNo(rset.getInt("wr_number"));
						b.setDate(rset.getDate("wr_date"));
						b.setHits(rset.getInt("wr_hits"));
						b.setLikes(rset.getInt("wr_likes"));
						b.setTextTitle(rset.getString("wr_title"));
						b.setTextContent(rset.getString("wr_content"));
						list.add(b);
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
	
		
		//제목  검색에 따른 row 수 리턴
		public int countRowByTitle(Connection con, int boardNo ,String keyword){
			int totalRow=0;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			
			switch(boardNo){
				case 1:
					query="select count(*) "
							+ "from course_board "
							+ "where wr_title like ?";
					break;
				case 2:
					query="select count(*) "
							+ "from store_board "
							+ "where wr_title like ?";
					break;
				case 3:
					query="select count(*) "
							+ "from stay_board "
							+ "where wr_title like ?";
					break;
				case 4:
					query="select count(*) "
							+ "from free_board "
							+ "where wr_title like ?";
					break;
				case 5:
					query="select count(*) "
							+ "from accompany_board "
							+ "where wr_title like ?";
					break;
				case 6:
					query="select count(*) "
							+ "from tip_board "
							+ "where wr_title like ?";
					break;
					
			}
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
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
	
		//게시글 검색 - 제목별 검색
		public ArrayList<Board> searchByTitle(Connection con, int boardNo, String keyword,
				 int startNo, int endNo){
			ArrayList<Board> list=null;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			//게시판에 따라 쿼리문이 달라짐.
			switch(boardNo){
				case 1:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from course_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 2:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from store_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 3:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from stay_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 4:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from free_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 5:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from accompany_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				case 6:
					query="select * from(select rownum rnum, "
							+ "board_number, wr_number, wr_writer, "
							+ "wr_title, wr_content, wr_date, "
							+ "country_name, wr_hits, wr_likes "
							+ "from(select board_number, "
							+ "wr_number, wr_writer, wr_title, "
							+ "wr_content, wr_date, country_name, "
							+ "wr_hits, wr_likes from tip_board "
							+ "join country using (country_code) "
							+ "where wr_title like ? "
							+ "order by wr_number desc) "
							+ "where rownum<=?) where rnum>=?";
							break;
				
				default :
			}
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, "%"+keyword+"%");
				pstmt.setInt(2, endNo);
				pstmt.setInt(3, startNo);
				rset=pstmt.executeQuery();
				
				if(rset!=null){
					list=new ArrayList<Board>();
					while(rset.next()){
						Board b=new Board();
						b.setBoardNo(rset.getInt("board_number"));
						b.setWriter(rset.getString("wr_writer"));
						b.setCountry("country_name");
						b.setTextNo(rset.getInt("wr_number"));
						b.setDate(rset.getDate("wr_date"));
						b.setHits(rset.getInt("wr_hits"));
						b.setLikes(rset.getInt("wr_likes"));
						b.setTextTitle(rset.getString("wr_title"));
						b.setTextContent(rset.getString("wr_content"));
						list.add(b);
						System.out.println(b.toString());
					}
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		
		
		//내 글 보기 
		//ui> 게시판이름 / 글 번호 / 글 제목 / 작성일 순으로.
		public ArrayList<Board> viewMyText(Connection con, String name,
				int boardNo, int startNo, int endNo){
			ArrayList<Board> list=new ArrayList<Board>();
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			String query=null;
			
			switch(boardNo){
				case 1:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from course_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
				case 2:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from store_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
				case 3:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from stay_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
				case 4:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from free_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
				case 5:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from accompany_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
				case 6:
					query="select * from "
							+ "(select rownum rnum, "
							+ "wr_number, wr_title, "
							+ "wr_writer, wr_date from "
							+ "(select * from tip_board "
							+ "where wr_writer=?) "
							+ "where rownum <=?) "
							+ "where rnum >=?";
					break;
			}
			
			
			
			
			try {
			
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
				pstmt.setInt(2, endNo);
				pstmt.setInt(3, startNo);
				rset=pstmt.executeQuery();
				
					while(rset.next()){
						Board b=new Board();
						b.setWriter(rset.getString("wr_writer"));
						b.setTextTitle(rset.getString("wr_title"));
						
						b.setTextNo(rset.getInt("wr_number"));
						b.setDate(rset.getDate("wr_date"));
						
						list.add(b);
						}
					
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				close(rset);
				close(pstmt);
			}
			
			return list;
		}
		
		
		//내 작성글 수 리턴
		public int countRowByWriter(Connection con, int boardNo, String name){
			int totalRow=0;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			
			switch(boardNo){
				case 1:
					query="select count(*) "
							+ "from course_board "
							+ "where wr_writer=?";
					break;
				case 2:
					query="select count(*) "
							+ "from store_board "
							+ "where wr_writer=?";
					break;
				case 3:
					query="select count(*) "
							+ "from stay_board "
							+ "where wr_writer=?";
					break;
				case 4:
					query="select count(*) "
							+ "from free_board "
							+ "where wr_writer=?";
					break;
				case 5:
					query="select count(*) "
							+ "from accompany_board "
							+ "where wr_writer=?";
					break;
				case 6:
					query="select count(*) "
							+ "from tip_board "
							+ "where wr_writer=?";
					break;
					
			}
			
			try {
				pstmt=con.prepareStatement(query);
				pstmt.setString(1, name);
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
		
		
		
		//이미지 저장경로 메서드... 일단 보류
	
	
}
	
	
	

