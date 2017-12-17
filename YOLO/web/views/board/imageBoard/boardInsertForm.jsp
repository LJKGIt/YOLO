<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="UTF-8">
<title>boardInsertForm</title>
<style type="text/css">
	a {
	text-decoration: none;
	color: black;
	}
</style>
</head>
<body>
	<%@ include file="../../../header.jsp" %>
	<br><br><br><br><br><br>
	<h1 align="center">추천코스 글쓰기</h1>
	<form action="/yolo/binsert" method="post">
		<input type="hidden" name="id" value="<%= member.getId() %>">
		<table cellspacing="5" align="center">
			<tr><td bgcolor="#ffcc00">게시판</td>
				<td>
					<select name="board">
					<option value="추천코스" selected>추천코스</option>
					<option value="추천맛집">추천맛집</option>
					<option value="추천숙박">추천숙박</option>
					<option value="자유">자유</option>
					<option value="함께해요">함께해여</option>
					<option value="여행꿀팁">여행꿀팁</option>
					</select>
				</td>
			</tr>
			<tr><td bgcolor="#ffcc00">제목</td><td><input type="text" name="title"></td></tr>
			<tr><td bgcolor="#ffcc00">지역</td>
				<td>
					<select name="area">
					<option value="전국" selected>전국</option>
					<option value="서울">서울</option>
					<option value="경기">경기</option>
					<option value="인천">인천</option>
					<option value="강원">강원</option>
					<option value="충남">충남</option>
					<option value="대전">대전</option>
					<option value="충북">충북</option>
					<option value="부산">부산</option>
					<option value="울산">울산</option>
					<option value="대구">대구</option>
					<option value="경북">경북</option>
					<option value="경남">경남</option>
					<option value="전남">전남</option>
					<option value="광주">광주</option>
					<option value="전북">전북</option>
					<option value="제주">제주</option>
					</select>	
				</td>
			</tr>
			<tr><td bgcolor="#ffcc00">내용</td><td><textarea rows="10" cols="100" name="content"></textarea></td></tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="올리기">
					<button><a href="javascript:history.go(-1);">취소하기</a></button>
				</td>
			</tr>
		</table>		
		
	</form>
</body>
</html>