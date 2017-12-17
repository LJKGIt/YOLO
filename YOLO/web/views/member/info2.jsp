<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member" %>   
 
<%
	Member m = (Member)session.getAttribute("member");

%>    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Insert title here</title>
<!-- 중복검사 -->
<script type="text/javascript" src="/YOLO/views/js/jquery-3.2.1.min.js">
</script>
<script type="text/javascript">
$(function(){
	$('#checkname').click(function(){
			
		$.ajax({
			url : "/YOLO/checkName",
			data : {name : $('#name').val()},
			type : "get",
			dataType : "text",
			success : function(data){
				if(data=="yes"){
					alert('사용 가능한 닉네임 입니다.');
				}else {
					alert('이미 등록된 닉네임 입니다.');
				}
				
			}
		});
	});
});
</script>
<style>
body { 
	margin-top: 180px;
   
    margin-right: 150px;
    margin-left: 80px;
    }
#myMenu a {
    position: absolute;
    left: -80px;
    transition: 0.3s;
    padding: 10px;
    width: 90px;
    text-decoration: none;
    font-size: 20px;
    color: white;
    border-radius: 0 20px 20px 0;
}

#myMenu a:hover {
    left: 0;
}

#about {
    top: 20px;
    background-color: #4CAF50;
}

#blog {
    top: 80px;
    background-color: #2196F3;
}

#projects {
    top: 140px;
    background-color: #f44336;
}

#contact {
    top: 200px;
    background-color: #555
}




</style>
</head>

<body>



  <div id="myMenu" class="menu">
  <a href="/YOLO/myinfo?id=<%=m.getId() %>"  id="about">내정보</a>
  <a href="/YOLO/mytext?&boardNo=1&name=<%=m.getName() %>" id="blog">내 작성글</a>
  <a href="/YOLO/viewscrap?&boardNo=1&name=<%=m.getName() %>" id="projects">내 스크랩</a>

</div>

<!-- <div style="margin-left:80px;">

</div> -->
<h1 align="center"><%= m.getName() %>님 정보 보기</h1>
<br>

<div class="content">
<div id="myinfo" class="tabcontent">
<form action="/YOLO/update" method="post">
<table width="600" cellspacing="5" align="center"> <!-- bgcolor="#99ccff" --> <!-- Result Size: 664 x 489 -->
<tr><td width="150">ID</td>
<td width="450"><input type="text" name="id" value="<%= m.getId() %>"  readonly >
</td></tr>
<tr><td>Password</td><td>
<input type="password" name="pwd" value="<%= m.getPwd() %>"></td></tr>
<tr><td>Name</td><td>
<input type="text" name="name" id="name"  value="<%= m.getName() %>">
<button type="button" id="checkname">중복검사</button></td></tr>

 <tr><td colspan="2" align="center">
 <hr>
<input type="submit" value="수정하기" >&nbsp;&nbsp;&nbsp;
<a href="/YOLO/mdelete?id=<%=m.getId()%>">
<input type="button" value="회원탈퇴"></a>
 </td></tr>
 
 
</table>
</form>
 </div>
</div>

</body>
</html>