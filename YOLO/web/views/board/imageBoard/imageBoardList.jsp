<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
   <%@ page import="board.model.vo.Board, java.sql.*, java.util.*" %>
    <%
    	int boardNo = ((Integer)request.getAttribute("boardNo")).intValue();
    	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list"); 
    	int currentPage = ((Integer)request.getAttribute("currentPage")).intValue();
    	int countList = ((Integer)request.getAttribute("countList")).intValue();
    	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
    	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
    	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
    	String boardName=(String)request.getAttribute("boardName");
    	int row = 0;
    	String src="http://localhost:8282/YOLO/images/fuploadImage/20170804173316.jpg";
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" href="yolo.css" type="text/css"> -->
<title>yolo</title>
<script type="text/javascript" src="/YOLO/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
$(function(){
	var src='F:/2_ORACLE/server/YOLO/web/images/fuploadImage/20170803194128.jpg';

	$('.texttable').click(function(){
		var tn = $(this).attr('value');
		var bn = <%= boardNo %>;
		$.ajax({
			url : "/YOLO/hit",
			data : {textNo : tn, boardNo : bn },
			type : "post",
			dataType : "text",
			success : function(data){
				if(data=='1'){
					
				}
			}
		});
	});
});
</script>

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
<body style="margin: 0px;">
	 <%@ include file="../../../header.jsp" %>
			
				<!-- 검색 -->
				<table cellpadding="35" align="center">
					<form action="search2?boardNo=<%= boardNo %>" method="post">
					<p align="center">
					<input type="text" name="search" id="search">
					<button><img width="26px" height="26px" src="https://www.kaihou-shop.com/img/search.png"></button>
					</p></form>
					
				<!-- 게시판 목록 -->
				<%
					for(Board b : list){
						row += 1;
						if(row % 5 == 0 && row / 5 == 1){
				%>
					<tr>
				<%}%>	<!-- if문 close -->
					<td>
					
				 <a href="/YOLO/bdetail?boardNo=<%=b.getBoardNo()%>&textNo=<%=b.getTextNo()%>&page=<%=currentPage%>">
						<table style="margin:0" class="texttable" value="<%= b.getTextNo() %>">
						<tr>
						<td colspan="2">
							<p align="center">
							<input type="image" width="316" height="200" class="img" name="" src=<%=src %>>
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
			<td>
				<% if(currentPage <= 1){ %>
				[이전]
				<% }else{ %>
				<a href="/YOLO/blist?page=<%= currentPage - 1 %>&boardNo=<%= boardNo %>">[이전]</a>
				<% } %>
				
				<% 
					for(int p = startPage; p <= endPage; p++){ 
						if(p == currentPage){
				%>
					<font color="red" size="4"><b>[<%= p %>]</b></font>
				<% }else{ %>
					<a href="/YOLO/blist?page=<%= p %>&boardNo=<%= boardNo %>"><%= p %></a>
				<% }} //for and if close%>
				
				<% if(currentPage >= maxPage){ %>
					[다음]
				<% }else{ %>
					<a href="/YOLO/blist?page=<%= currentPage + 1 %>&boardNo=<%= boardNo %>">[다음]</a>
					<a href="/YOLO/blist?page=<%= startPage + 10 %>&boardNo=<%= boardNo %>">[다음페이지]</a>
				<% } %>
			</td>
			<td>
				<% if(member != null){ %>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<button><a href="views/editor/editor.jsp?boardNo=<%= boardNo %>&boardName=<%= boardName %>">글쓰기</a></button>
				<% } %>
			</td>
			</tr>
		</table>

		 	<%-- <%@ include file="../../../footer.html" %> --%>

</body>
</html>