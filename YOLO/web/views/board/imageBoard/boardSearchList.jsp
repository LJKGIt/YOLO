<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
    <%@ page import="board.model.vo.Board, java.sql.*, java.util.*" %>
     <%
    	int boardNo = ((Integer)request.getAttribute("boardNo")).intValue();
    	String boardName = null;
    	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); 
    	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
    	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
    	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
    	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
    	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
    	int row = 0;
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
  #search{
    	 width: 405px;
		 height: 25px;
  		 background-color: transparent;
    	 color: #000;
    	 font-weight: 700;
   	 	 font-size: 18px;
    }

</style>
</head>
<body>
	<%@ include file="../../../header.jsp" %>
	
	<!-- 게시판 목록 -->
				<%
					for(Board b : list){
						boardName = b.getBoardName();
						row += 1;
						if(row % 5 == 0 && row / 5 == 1){
				%>
					<tr>
				<%}%>	<!-- if문 close -->
					<td>
					<a href="/yolo/bdetail?page=<%= currentPage %>&num=<%= b.getTextNo() %>&boardNo=<%= boardNo %>">
						<table style="margin: 0">
						<tr>
						<td colspan="2">
							<p align="center">
								<input type="image" class="img" name="" src="http://heraldk.com/wp-content/uploads/2015/05/20150601000729_0.jpg">
							</p>
						</td>
						</tr>
						<tr><td>제목</td><td name="btitle"><%= b.getTextTitle() %></td></tr>
						<tr><td>글쓴이</td><td name="bwriter"><%= b.getWriter() %></td></tr>
						<tr><td>조회수</td><td name="bhits"><%= b.getHits() %></td></tr>
						<tr><td>추천수</td><td name="blikes"><%= b.getLikes() %></td></tr>
						<tr><td>지역</td><td name="bcountry"><%= b.getCountry() %></td></tr>
						</table>
					</a>
					</td>
					<% if(row % 8 == 0){ %>
					</tr>
					<%}}%>	<!-- if and for문 close -->
				</table>
				
		<!-- 페이지 번호 처리 -->
		<table align="center">
			<tr>
			<td colspan="2">
				<% if(currentPage <= 1){ %>
				[이전]
				<% }else{ %>
				<a href="/yolo/bsearch?page=<%= currentPage - 1 %>&boardNo=<%= boardNo %>">[이전]</a>
				<% } %>
				
				<% 
					for(int p = startPage; p <= endPage; p++){ 
						if(p == currentPage){
				%>
					<font color="red" size="4"><b>[<%= p %>]</b></font>
				<% }else{ %>
					<a href="/yolo/bsearch?page=<%= p %>&boardNo=<%= boardNo %>"><%= p %></a>
				<% }} //for and if close%>
				
				<% if(currentPage >= maxPage){ %>
					[다음]
				<% }else{ %>
					<a href="/yolo/bsearch?page=<%= currentPage + 1 %>&boardNo=<%= boardNo %>">[다음]</a>
					<a href="/yolo/bsearch?page=<%= startPage + 10 %>&boardNo=<%= boardNo %>">[다음페이지]</a>
				<% } %>
			</td>
			
			</tr>
		</table>
	
</body>
</html>