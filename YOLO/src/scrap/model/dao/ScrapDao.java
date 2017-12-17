package scrap.model.dao;

import static common.JDBCTemplate.*;
import java.sql.*;
import java.util.ArrayList;

import board.model.vo.Board;
import scrap.model.vo.Scrap;

public class ScrapDao {

	
	
	//스크랩 버튼 눌렀을때 DB에 저장하는 메서드
		public int clickScrap(Connection con, Scrap sc){
				int result=0;
				PreparedStatement pstmt=null;
				
				String query="insert into scrap values"
						+ "((select max(sc_number) from scrap)+1,"
						+ "?, ?, ?, default)";
				
				try {
					pstmt=con.prepareStatement(query);
					pstmt.setInt(1, sc.getBoardNo());
					pstmt.setInt(2, sc.getTextNo());
					pstmt.setString(3, sc.getName());
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
		//각 게시판 별로 스크랩한 글의 숫자를 리턴하는 메서드
		public int countRow(Connection con, int boardNo, String name){
			int totalRow=0;
			PreparedStatement pstmt=null;
			ResultSet rset=null;
			
			String query=null;
			
			switch(boardNo){
				case 1:
					query="select count(*) from scrap "
							+ "where board_number=1 "
							+ "and sc_name=?";
					break;
				case 2:
					query="select count(*) from scrap "
							+ "where board_number=2 "
							+ "and sc_name=?";
					break;
				case 3:
					query="select count(*) from scrap "
							+ "where board_number=3 "
							+ "and sc_name=?";
					break;
				case 4:
					query="select count(*) from scrap"
							+ " where board_number=4"
							+ "and sc_name=?";
					break;
				case 5:
					query="select count(*) from scrap "
							+ " where board_number=5 "
							+ "and sc_name=?";
					break;
				case 6:
					query="select count(*) from scrap"
							+ " where board_number=6"
							+ "and sc_name=?";
					break;
					
			}//switch 끝
			
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
		
			
			//스크랩한 글을 보여주는 메서드
			//게시판 이름/ 글 제목 / 스크랩 날짜
		public ArrayList<Scrap> viewScarp(Connection con, String name, int boardNo,
				int startNo, int endNo){
				ArrayList<Scrap> list=new ArrayList<Scrap>();
				ResultSet rset=null;
				PreparedStatement pstmt=null;
				
				String query=null;
				
				//게시판별로 조회하면서 스크랩정보 가져옴.
				try {
					
						switch(boardNo){
							case 1:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, "
										+ "sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join course_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							case 2:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join store_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							case 3:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join stay_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							case 4:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join free_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							case 5:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join accompany_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							case 6:
								query="select * from "
										+ "(select rownum rnum, "
										+ "wr_number, sc_number, wr_title, "
										+ "sc_date from (select wr_number, sc_number, "
										+ "wr_title, sc_date "
										+ "from scrap "
										+ "join board using (board_number) "
										+ "join tip_board using (wr_number) "
										+ "where sc_name=?"
										+ "order by sc_date desc) where rownum<=?) "
										+ "where rnum >=?";
								break;
							default :
						} //switch 끝
						
						pstmt=con.prepareStatement(query);
						pstmt.setString(1, name);
						pstmt.setInt(2, endNo);
						pstmt.setInt(3, startNo);
						rset=pstmt.executeQuery();
						
						while(rset.next()){
							Scrap sc=new Scrap();
							//sc.setBoardNo(rset.getInt("board_number"));
							sc.setTextNo(rset.getInt("wr_number"));
							sc.setTextTitle(rset.getString("wr_title"));
							sc.setDate(rset.getDate("sc_date"));
							sc.setScrapNo(rset.getInt("sc_number"));
							//스크랩 번호는 화면에서 보여주는 것은 아니지만, 삭제를 위해 필요.
							list.add(sc);
						}
						
					
					
					
				} catch (Exception e) {
					// TODO: handle exception
				}finally{
					close(rset);
					close(pstmt);
				}
				
				return list;
			}
			
			
			//스크랩 취소 - 
			public int cancelScrap(Connection con, int scrapNo){
				int result=0;
				PreparedStatement pstmt=null;
				
				String query="delete from scrap where sc_number=?";
				
				try {
					//체크한 수만큼 스크랩글 삭제.
					
						pstmt=con.prepareStatement(query);
						pstmt.setInt(1, scrapNo);
						result=pstmt.executeUpdate();
					
					
					//성공값과 체크한 스크랩글 수의 값이 일치하여야 성공.
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
}
