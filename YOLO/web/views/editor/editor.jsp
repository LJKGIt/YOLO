<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html; charset=UTF-8;");
    String boardName = request.getParameter("boardName");
    int boardNo = Integer.parseInt(request.getParameter("boardNo"));
    %>
<!DOCTYPE html>
<html lang="en">
<head>

  <meta charset="UTF-8">
  <title>Welcome Bw Editor</title>
</head>
<body>
	<%@ include file="../../header.jsp" %>
	<br>
	<br>
	<div>
	<header><h1>Bw Editor</header>
	<form action="/YOLO/binsert?boardNo=<%= boardNo %>" method="post">
	<input type="hidden" name="uploadfile" id="files">
	<input type="hidden" name="name" value="<%= member.getName() %>">
	 &nbsp지역 &nbsp&nbsp&nbsp<select name="area">
		<option value="전국" selected size="80">전국</option>
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
	<br><br>
	 &nbsp제목 &nbsp&nbsp <input type="text" name="title" size="150">
	</div>
	<br><br>
  <link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
  <script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
  <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.css" rel="stylesheet">
  <script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.6/summernote.js"></script>
  <textarea id="summernote" name="content"></textarea>
  <!-- summernote apply -->
	<script type="text/javascript">
  $(function() {
    $('#summernote').summernote({
      width: '100%',	    
      height: 300,          // 기본 높이값
      minHeight: null,      // 최소 높이값(null은 제한 없음)
      maxHeight: null,      // 최대 높이값(null은 제한 없음)
      focus: true,          // 페이지가 열릴때 포커스를 지정함
      lang: "ko-KR",         // 한국어 지정(기본값은 en-US)
      callbacks: {
      onImageUpload: function(files, editor, welEditorble) {
		data = new FormData();
		data.append("file",files[0]);
		var $note = $(this);
		$.ajax({
			data : data,
			type : "post",
			url : '/YOLO/fupload', // servlet url
			cache : false,
			contentType : false,
			processData : false,
			success : function(url){
				//alert(url);
				$("#files").attr('src',url);
				$("#files").val(url);
				$note.summernote('insertImage',url);
			}
		});
	}
      }
    });
  });
</script>
	<!-- summernote apply -->
  <input type="submit" value="작성">
  &nbsp;&nbsp;&nbsp;<input type="reset" value="취소" href="javascript:history.go(-1);"> 
  </form>
</body>
</html>