package scrap.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Scrap implements Serializable{

	
	private int ScrapNo;
	//스크랩 번호 > 고유번호
	private int boardNo;
	//게시판 번호 - 어느 게시판인지
	private int textNo;
	//게시글 번호
	private String name;
	//스크랩한 사람.
	private String boardName;
	//게시판 이름 - 스크랩 보여주기에 사용
	private String textTitle;
	//글 제목 - 스크랩 보여주기에 사용.
	private Date date;
	//스크랩 날짜
	
	public Scrap(){}

	public int getScrapNo() {
		return ScrapNo;
	}

	public void setScrapNo(int scrapNo) {
		ScrapNo = scrapNo;
	}

	public int getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}

	public int getTextNo() {
		return textNo;
	}

	public void setTextNo(int textNo) {
		this.textNo = textNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBoardName() {
		return boardName;
	}

	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}

	public String getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	
	
}
