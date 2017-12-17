<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="member.model.vo.Member" %>   
 
<%
	Member m = (Member)session.getAttribute("member");
%>    
     
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
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
* {box-sizing: border-box}
body {font-family: "Raleway", sans-serif;}

/* 회원정보 수정▼ */
div.tab {
    float: left;
    border: 1px solid #ccc;
    background-color: #f1f1f1;
    width: 30%;
    height: 300px;
}

/* Style the buttons inside the tab(탭 안에 버튼 ) */
div.tab button {
    display: block;
    background-color: inherit;
    color: black;
    padding: 22px 16px;
    width: 100%;
    border: none;
    outline: none;
    text-align: left;
    cursor: pointer;
    transition: 0.3s;
    font-size: 17px;
}

/* Change background color of buttons on hover */
div.tab button:hover {
    background-color: #ddd;
}

/* Create an active/current "tab button" class */
div.tab button.active {
    background-color: #ccc;
}

/* Style the tab content */
.tabcontent {
    float: left;
    padding: 0px 12px;
    border: 1px solid #ccc;
    width: 70%;
    border-left: none;
    height: 300px;
}

</style>
<title>내 정보</title>
</head>

<body>

<div class="tab">
  <button class="tablinks" onclick="openInfo(event, 'myinfo')" id="defaultOpen"><B>내정보보기</B></button>
 <button class="tablinks" onclick="openInfo(event, 'mwriter')"><B> 내 작성글</B></button>
  <button class="tablinks" onclick="openInfo(event, 'mscrap')"><B>스크랩</B></button>
</div>

<div id="myinfo" class="tabcontent">
<form action="/YOLO/update" method="post">
<table>
<tr><td>아이디</td>
<td><input type="text" name="id" value="<%= m.getId() %>" readonly></td></tr>
<tr><td>비밀번호</td><td>
<input type="password" name="pwd"  value="<%= m.getPwd() %>"></td></tr>
<tr><td>닉네임</td><td>
<input type="text" name="name" id="name" value="<%= m.getName() %>">
<button type="button" id="checkname">중복검사</button></td></tr>
<br><br>
 <tr><td>
<input type="submit" value="수정하기" >&nbsp;&nbsp;&nbsp;
<a href="/YOLO/mdelete?id=<%=m.getId()%>"><input type="button" value="회원탈퇴"></a>
 </td></tr>

</table>
</form>
</div>


</body>
</html>