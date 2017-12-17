<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.Board"%>
    <%
    	Board b = (Board)request.getAttribute("board");
    	int currentPage = ((Integer)request.getAttribute("page")).intValue();
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	a {
	text-decoration: none;
	color: black;
	}
</style>
</head>
<body>
	<%@ include file="../../../header.jsp" %>
	<br><br><br>
	<!-- 글수정 -->
	<form action="/yolo/bupdate" method="post">
	<h1 align="center"><%= b.getTextNo() %>번 추천코스 글</h1>
		<input type="hidden" name="num" value="<%= b.getTextNo() %>">
		<input type="hidden" name="page" value="<%= currentPage %>"> 
		<table cellspacing="5" align="center">
			<tr><td bgcolor="#ffcc00">게시판</td>
				<td>
					<select name="board">
					<% if(b.getBoardName().equals("추천코스")){ %>
					<option value="추천코스" selected>추천코스</option>
					<% }else{ %>
					<option value="추천코스">추천코스</option>
					<% } %>
					<% if(b.getBoardName().equals("추천숙박")){ %>
					<option value="추천숙박" selected>추천숙박</option>
					<% }else{ %>
					<option value="추천숙박">추천숙박</option>
					<% } %><% if(b.getBoardName().equals("추천맛집")){ %>
					<option value="추천맛집" selected>추천맛집</option>
					<% }else{ %>
					<option value="추천맛집">추천맛집</option>
					<% } %>
					<% if(b.getBoardName().equals("자유")){ %>
					<option value="자유" selected>자유</option>
					<% }else{ %>
					<option value="자유">자유</option>
					<% } %>
					<% if(b.getBoardName().equals("함께해요")){ %>
					<option value="함께해요" selected>함께해요</option>
					<% }else{ %>
					<option value="함께해요">함께해요</option>
					<% } %>
					<% if(b.getBoardName().equals("여행꿀팁")){ %>
					<option value="여행꿀팁" selected>여행꿀팁</option>
					<% }else{ %>
					<option value="여행꿀팁">여행꿀팁</option>
					<% } %>
					
					</select>
				</td>
			</tr>
			<tr><td bgcolor="#ffcc00">제목</td><td><input type="text" name="title" value="<%= b.getTextTitle() %>"></td></tr>
			<tr><td bgcolor="#ffcc00">지역</td>
				<td>
					<select name="area">
					<% if(b.getCountry().equals("전국")){ %>
					<option value="전국" selected>전국</option>
					<% }else{ %>
					<option value="전국">전국</option>
					<% } if(b.getCountry().equals("서울")){%>
					<option value="서울" selected>서울</option>
					<%}else{ %>
					<option value="서울">서울</option>
					<% } if(b.getCountry().equals("경기")){ %>
					<option value="경기" selected>경기</option>
					<%} else{ %>
					<option value="경기">경기</option>
					<% } if(b.getCountry().equals("인천")){ %>
					<option value="인천" selected>인천</option>
					<% }else{ %>
					<option value="인천">인천</option>
					<%} if(b.getCountry().equals("강원")){ %>
					<option value="강원" selected>강원</option>
					<% }else{ %>
					<option value="강원">강원</option>
					<%} if(b.getCountry().equals("충남")){ %>
					<option value="충남" selected>충남</option>
					<%}else{ %>
					<option value="충남">충남</option>
					<%} if(b.getCountry().equals("대전")){ %>
					<option value="대전" selected>대전</option>
					<%}else{ %>
					<option value="대전">대전</option>
					<%} if(b.getCountry().equals("충북")){ %>
					<option value="충북" selected>충북</option>
					<%}else{ %>
					<option value="충북">충북</option>
					<%} if(b.getCountry().equals("부산")){ %>
					<option value="부산" selected>부산</option>
					<%}else{ %>
					<option value="부산">부산</option>
					<%} if(b.getCountry().equals("울산")){ %>
					<option value="울산" selected>울산</option>
					<%}else{ %>
					<option value="울산">울산</option>
					<%} if(b.getCountry().equals("대구")){ %>
					<option value="대구" selected>대구</option>
					<%}else{ %>
					<option value="대구">대구</option>
					<%} if(b.getCountry().equals("경북")){ %>
					<option value="경북" selected>경북</option>
					<%}else{ %>
					<option value="경북">경북</option>
					<%} if(b.getCountry().equals("경남")){ %>
					<option value="경남" selected>경남</option>
					<%}else{ %>
					<option value="경남">경남</option>
					<%} if(b.getCountry().equals("전남")){ %>
					<option value="전남" selected>전남</option>
					<%}else{ %>
					<option value="전남">전남</option>
					<%} if(b.getCountry().equals("광주")){ %>
					<option value="광주" selected>광주</option>
					<%}else{ %>
					<option value="광주">광주</option>
					<%} if(b.getCountry().equals("전북")){ %>
					<option value="전북" selected>전북</option>
					<%}else{ %>
					<option value="전북">전북</option>
					<%} if(b.getCountry().equals("제주")){ %>
					<option value="제주" selected>제주</option>
					<%}else{ %>
					<option value="제주">제주</option>
					<%} %>
					</select>	
				</td>
			</tr>
			<tr><td bgcolor="#ffcc00">내용</td><td><textarea rows="10" cols="100" name="content"><%= b.getTextContent() %></textarea></td></tr>
			<tr>
				<td colspan="2" align="center">
					<input type="submit" value="수정하기">
					<button><a href="javascript:history.go(-1);">취소하기</a></button>
				</td>
			</tr>
		</table>		
		</form>
	
</body>
</html>