package board.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	private String boardName;
	//게시판 이름 - 내글보기/스크랩 메뉴에서 사용.
	private int boardNo;
	//게시판 번호
	private int textNo;
	//글번호
	private String textTitle;
	//글제목
	private String textContent;
	//글내용
	private int rownum;
	//행번호
	private int countryCode;
	//지역코드
	/*02, '서울특별시'
	031, '경기도'
	032, '인천광역시'
	033, '강원도'
	041, '충청남도'
	042, '대전광역시'
	043, '충청북도'
	051, '부산광역시'
	052, '울산광역시'
	053, '대구광역시'
	054, '경상북도'
	055, '경상남도'
	061, '전라남도'
	062, '광주광역시'
	063, '전라북도'
	064, '제주도'*/
	private int hits;
	//조회수
	private int likes;
	//추천수
	private String writer;
	//작성자
	private Date date;
	//작성일
	private String country;
	
	public Board(){}
	
	

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

	public String getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle = textTitle;
	}

	public String getTextContent() {
		return textContent;
	}

	public void setTextContent(String textContent) {
		this.textContent = textContent;
	}

	public int getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}

	public int getHits() {
		return hits;
	}

	public void setHits(int hits) {
		this.hits = hits;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}



	public String getWriter() {
		return writer;
	}



	public void setWriter(String writer) {
		this.writer = writer;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getBoardName() {
		return boardName;
	}



	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}



	public String getCountry() {
		return country;
	}



	public void setCountry(String country) {
		this.country = country;
	}


	

	public int getRownum() {
		return rownum;
	}



	public void setRownum(int rownum) {
		this.rownum = rownum;
	}



	@Override
	public String toString() {
		return "Board [boardName=" + boardName + ", boardNo=" + boardNo + ", textNo=" + textNo + ", textTitle="
				+ textTitle + ", textContent=" + textContent + ", countryCode=" + countryCode + ", hits=" + hits
				+ ", likes=" + likes + ", writer=" + writer + ", date=" + date + ", country=" + country + "]";
	}
	
	
	
	
}
