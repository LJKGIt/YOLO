<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="board.model.vo.Board, comment.model.vo.Comment, java.util.*"%>
    <%
    	Board b = (Board)request.getAttribute("board");
    	int boardPage = ((Integer)request.getAttribute("page")).intValue();
    	int currentPage = ((Integer)request.getAttribute("commentPage")).intValue();
    	int boardNo = ((Integer)request.getAttribute("boardNo")).intValue();
    	ArrayList<Comment> list = (ArrayList<Comment>)request.getAttribute("list");
    	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
    	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
    	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
    %>
   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <link rel="stylesheet" type="text/css" href="views/board/css/board.css"> -->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<title>boardDetailView</title>
<style type="text/css">
	.comment-list{	/* 댓글 전체 폼 */
		display: block;
		background-color: #f9f9f9;
		margin: auto;
		width: 50%;
	}
	
	.comment-list-ul{	/* 댓글 전체 폼안의 ul */
		list-style: none;
		padding-left: 0px;
		width: 690;
	}
	.addcomment-button{		/* 댓글의 댓글의 버튼 */
	
    padding-left: 10px;
    background: url(http://cafeimgs.naver.net/cafe4/bu_arr.png) no-repeat 0 0;
    _background: none;
    _filter: progid:DXImageTransform.Microsoft.AlphaImageLoader(src='http://cafeimgs.naver.net/cafe4/bu_arr.png', sizingMethod='crop');
    font-size: 12px;
    font-family: '돋움',Dotum;
    letter-spacing: -1px;
	}
	.com-content{		/* 댓글 작성 칸 */
	padding: 0 0 3px 28px;
    margin: 0;
    line-height: 15px;
    text-align: left;
    word-break: break-all;
    word-wrap: break-word;
	}
	.paging{
		margin: auto;
		width: 13%;
	}
	
</style>
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		var preCmtNo = null;
		 var cmtLevel = null;
	
		 // 좋아요 클릭
		$('#like').click(function(){
			var textNo = <%= b.getTextNo() %>;
			var boardNo = <%= boardNo %>;

			$.ajax({
				url: "/YOLO/likes",
				type: "post",
				data: {"textNo" : textNo, "boardNo" : boardNo},
				dataType : "json",
				success : function(data){
					 alert('게시글을 추천하였습니다.'); 
					 $('#likeCount').text(data);
					 $('#like').css("background-color", "#afe6f5");
					 $('#like').off('click');
				}
			});	//ajax close
		}); //$('#like').click(function(){ close
		
		// 내 관심글에 추가
		$('#addscrap').click(function(){
			var textNo = <%= b.getTextNo() %>;
			var boardNo = <%= boardNo %>;
			var user = $('#username').val();
			if(user == null){
				alert("로그인해 주세요.");
			}else{
			$.ajax({
				url : "/YOLO/scrap",
				type : "post",
				data : {"textNo" : textNo, "boardNo" : boardNo, "name" : user},
				success: function(data){
					if(data > 0){
						alert("내 관심글에 추가하였습니다.");
					}
				}
			}); //ajax close
			
			}
				
		}); //$('#addscrap').click(function(){ close
		
		//댓글의 댓글 추가
		 $('.comment').click(function(){
			
			 if($(this).text() == '답글'){
				 var result = $(this).attr('value');
				 
				 var strarr = result.split(',');
				 preCmtNo = strarr[0];
				 cmtLevel = strarr[1];
				
				 $(this).text("답글취소");
				 $(this).parent().parent().parent().append('<div id="add-com-text"><input type="text" id="content" size="50" style="height: 50px;"><input type="button" id="comment-insert" value="등록" style="height: 50px;"><hr></div>');
				
				 $('#comment-insert').click(function(){
						 
						 var textNo = <%= b.getTextNo() %>;
						 var boardNo = <%= boardNo %>;
						 var user = $('#username').val();
						 var content = '└' + $('#content').val();
						 var boardNo = <%= boardNo %>;
						 var boardPage = <%= boardPage %>;
						 
						 $.ajax({
							url : '/YOLO/cinsert',
							type : 'post',
							data : {"textNo" : textNo, "boardNo" : boardNo, "writer" : user, 
								"content" : content, "preCmtNo" : preCmtNo, "cmtLevel" : cmtLevel,
								 "page" : boardPage},
							dataType : 'json',
							success : function(data){
								if(data > 0){
									alert("댓글을 추가하였습니다.");
									$('.comment').text("답글");
									$('#add-com-text').remove();	//댓글 달면 댓글폼 사라지게
									location.reload();
								} //if close
							} //success close
						 }); //ajax close
					}); //$('#comment-insert').click(function(){ close

			 }else{
				 $(this).text("답글");
				 $('#add-com-text').remove();	//답글 취소 누르면 댓글쓰는 폼 없애줌
			 }
				
		}); //$('.comment').click(function(){ close 
			
		// 글의 댓글 추가
		$('#comment-insert2').click(function(){
			 var textNo = <%= b.getTextNo() %>;
			 var boardNo = <%= boardNo %>;
			 var user = $('#username').val();
			 var content = $('#content2').val();
			 var boardPage = <%= boardPage %>;
			 
			 $.ajax({
					url : '/YOLO/cinsert',
					type : 'post',
					data : {"textNo" : textNo, "boardNo" : boardNo, "writer" : user, 
						"content" : content, "page" : boardPage},
					dataType : 'json',
					success : function(data){
						if(data > 0){
							alert("댓글을 추가하였습니다.");
							location.reload();
						}
					}
				 }); //ajax close
		}); //$('#comment-insert-2').click(function(){
		
		// 댓글 삭제
		$('.cmt-delete').click(function(){
			var boardNo = <%= b.getBoardNo() %>;
			var cmtNo = $(this).attr('value');
			
			$.ajax({
				url : '/YOLO/cdelete',
				type : 'post',
				data : {"boardNo" : boardNo, "cmtNo" : cmtNo},
				dataType : 'json',
				success : function(data){
					if(data > 0){
						alert('삭제되었습니다.');	
						location.reload();
					}
				}	//function(data) close
				
			});	//ajax close
			
		});//$('#cmt-delete').click(function(){ close
		
		 $('.cmt-update').click(function(){
				 var result = $(this).attr('value');
				 var strarr = result.split(',');
				 var cmtContent = strarr[0];
				 var cmtNo = strarr[1];
				
				 $(this).parent().parent().parent().append('<div id="add-com-text"><input type="text" id="content" value="'+ cmtContent +'" size="50" style="height: 50px;"><input type="button" id="comment-insert" value="등록" style="height: 50px;"><hr></div>');
				
				 $('#comment-insert').click(function(){
						 var boardNo = <%= boardNo %>;
						 var content = $('#content').val();
						 
						 $.ajax({
							url : '/YOLO/update2',
							type : 'post',
							data : {"cmtNo" : cmtNo, "boardNo" : boardNo,
								"content" : content},
							dataType : 'json',
							success : function(data){
								if(data > 0){
									alert("댓글을 수정하였습니다.");
									$('#add-com-text').remove();	//댓글 달면 댓글폼 사라지게
									location.reload();
								} //if close
							} //success close
						 }); //ajax close
					}); //$('#comment-insert').click(function(){ close
		}); //$('.cmt-update').click(function(){ close 
		
	}); //$(function(){ close
	
</script>

</head>
<body>
	<%@ include file="../../../header.jsp" %>
	
	<br><br><br><br><br><br>
	
	<table align="center">
		<tr><td>게시판</td><td><%= b.getBoardName() %></td></tr>
		<tr><td>제목</td><td><%= b.getTextTitle() %></td></tr>
		<tr><td>작성자</td><td><%= b.getWriter() %></td></tr>
		<tr><td>작성일</td><td><%= b.getDate() %></td></tr>
		<tr><td>지역</td><td><%= b.getCountry() %></td></tr>
		<tr><td>내용</td><td><%= b.getTextContent() %></td></tr>
		<tr><td>조회수 : </td><td><%= b.getHits() %></td></tr>
		<tr><td>좋아요수 : </td><td id="likeCount"><%= b.getLikes() %></td></tr>
		<tr><td colspan="2"></td></tr>
		<tr><td colspan="2">
				<button type="button" class="fa fa-thumbs-up" id="like"> Like</button>&nbsp;&nbsp;&nbsp;
				<button type="button" id="addscrap">내 관심글+</button>&nbsp;&nbsp;&nbsp;
				<button><a href="/YOLO/blist?page=<%=boardPage %>&boardNo=<%=boardNo%>">뒤로가기</a></button>&nbsp;&nbsp;&nbsp;
				<% if(member != null){ %>
				<% if(b.getWriter().equals(member.getName())){%>
				<button><a href="/YOLO/tupdate?num=<%= b.getTextNo() %>&page=<%= boardPage %>&boardNo=<%= boardNo %>">수정하기</a></button>&nbsp;&nbsp;&nbsp;
				<button><a href="/YOLO/tdelete?boardNo=<%= boardNo %>&textNo=<%= b.getTextNo() %>">삭제하기</a></button>
				<% }} %>	<!-- 로그인하고, 글을 쓴 유저와 로그인한 유저가 같은때만 수정할 수 있게 -->
			</td></tr>
	</table>
	
	<%if(member != null){%>
	<input type="hidden" id="username" value="<%= member.getName() %>">
	<% } %>
	
	<div class="comment-list">
		<ul class="comment-list-ul">
			<%
				for(Comment c : list){
			%>
			<li class="comment-list-li">
				<p><span style="font-weight: bolder;"><%= c.getCommentWriter() %></span>
				 <span style="opacity: 0.5; font-size: 13px;"><%= c.getDate() %></span> 
				 <% if(member != null){ %>
				 <span class="addcomment-button"><a class="comment" href="#addcomment-button" value="<%= c.getCommentNo() %>,<%= c.getLevel() + 1 %>">답글</a></span> 
				 <% if(member.getName().equals(c.getCommentWriter())){ %>
				 <div style="float: right;">
				 <span style="opacity: 0.8; font-size: 13px;"><a class="cmt-update" value="<%= c.getCommentContent() %>,<%= c.getCommentNo() %>">수정</a></span> <span style="opacity: 0.8; font-size: 13px;">/</span>
				 <span style="opacity: 0.8; font-size: 13px;"><a class="cmt-delete" value="<%= c.getCommentNo() %>">삭제</a></span>
				 </div >
				 <% } %>
				 </p>
				 <% } %>
				<b class="com-content"><%= c.getCommentContent() %></b>
				<hr>
			</li>
			<% } %>
			
			<li>
				<input type="text" id="content2" size="50" style="height: 50px;">
				<input type="button" id="comment-insert2" value="등록" style="height: 50px;"><hr>
			</li>
		</ul>
	</div>
		<div class="paging">
			<% if(currentPage <= 1){ %>
			<b class="w3-bar-item w3-button w3-hover-black">[이전]</b>
			<% }else{ %>
			<a href="/YOLO/bdetail?textNo=<%= b.getTextNo() %> %>&commentPage=<%= currentPage - 1 %>&boardNo=<%= boardNo %>" class="w3-bar-item w3-button w3-hover-black">[이전]</a>
			<% } %>
			<% 
				for(int p = startPage; p <= endPage; p++){ 
					if(p == currentPage){
			%>
				<b class="w3-bar-item w3-black w3-button">[<%= p %>]</b>
			<% }else{ %>
				<a href="/YOLO/bdetail?textNo=<%= b.getTextNo() %>&commentPage=<%= p %>&boardNo=<%= boardNo %>" class="w3-bar-item w3-button w3-hover-black"><%= p %></a>
			<% }} //for and if close%>
				
			<% if(currentPage >= maxPage){ %>
				<b class="w3-bar-item w3-button w3-hover-black">[다음]</b>
			<% }else{ %>
				<a href="/YOLO/bdetail?textNo=<%= b.getTextNo() %>&commentPage=<%= currentPage + 1 %>&boardNo=<%= boardNo %>" class="w3-bar-item w3-button w3-hover-black">[다음]</a>
			<% } %>
			</div>
</body>
</html>