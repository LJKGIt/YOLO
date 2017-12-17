package scrap.model.service;
import static common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.*;

import scrap.model.dao.ScrapDao;
import scrap.model.vo.Scrap;

public class ScrapService {

	
	
	
	//스크랩 버튼 눌렀을때.
	public int clickScrap(Scrap sc){
		Connection con=getConnection();
		
		int result=new ScrapDao().clickScrap(con, sc);
		close(con);
		return result;
	}
	
	//스크랩 보여주기.
	public ArrayList<Scrap> viewScarp(String name, int boardNo, int startNo, int endNo){
		Connection con=getConnection();
		
		ArrayList<Scrap> list=new ScrapDao().viewScarp(con, name, boardNo, startNo, endNo);
		close(con);
		return list;
		
	}
	
	//갯수 리턴
	public int countScrap(int boardNo, String name){
		int totalRow=0;
		Connection con=getConnection();
		
		totalRow=new ScrapDao().countRow(con, boardNo, name);
		
		return totalRow;
	}
	
	//스크랩 취소
	public int cancelScrap(int scrapNo){
		int result=0;
		Connection con=getConnection();
		result=new ScrapDao().cancelScrap(con, scrapNo);
		close(con);
		return result;
	}
	
	
}
