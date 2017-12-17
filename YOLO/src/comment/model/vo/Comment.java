package comment.model.vo;

import java.io.Serializable;
import java.sql.Date;

public class Comment implements Serializable {

	
	
	private int textNo;
	//글 번호
	private int commentNo;
	//댓글 번호
	private String commentWriter;
	//작성자
	private String commentContent;
	//작성내용
	private Date date;
	//작성날짜
	private int preCmtNo;
	//선댓글 번호
	private int level;
	//댓글 깊이 - 대댓글
	
	public Comment(){}


	
	public int getTextNo() {
		return textNo;
	}


	public void setTextNo(int textNo) {
		this.textNo = textNo;
	}

	public int getCommentNo() {
		return commentNo;
	}


	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getPreCmtNo() {
		return preCmtNo;
	}

	public void setPreCmtNo(int preCmtNo) {
		this.preCmtNo = preCmtNo;
	}


	public int getLevel() {
		return level;
	}


	public void setLevel(int level) {
		this.level = level;
	}


	public String getCommentWriter() {
		return commentWriter;
	}

	public void setCommentWriter(String commentWriter) {
		this.commentWriter = commentWriter;
	}

	public String getCommentContent() {
		return commentContent;
	}

	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
	
	
	
	
}
