<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="member.model.vo.Member,board.model.vo.Board, java.sql.*,java.util.*,scrap.model.vo.Scrap" %>   
 
<%
	Member m = (Member)session.getAttribute("member");
	ArrayList<Scrap> list = (ArrayList<Scrap>)request.getAttribute("list");
	int currentPage=((Integer)request.getAttribute("currentPage")).intValue();
	int startPage=((Integer)request.getAttribute("startPage")).intValue();
	int endPage=((Integer)request.getAttribute("endPage")).intValue();
	int boardNo=((Integer)request.getAttribute("boardNo")).intValue();

%>   


<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body { 
	margin-top: 180px;
   margin-right: 150px;
    margin-left: 80px;
    }

#myTable {
  border-collapse: collapse;
  width: 100%;
  border: 1px solid #ddd;
  font-size: 15px;
}

#myTable th, #myTable td {
  text-align: center;
  width: 10% 60% 30%;
  padding: 12px;
}

#myTable tr {
  border-bottom: 1px solid #ddd;
}

#myTable tr.header, #myTable tr:hover {
  background-color: #f1f1f1;
}

.icon-bar {
    width: 100%;
    background-color: #555;
    overflow: auto;
}

.icon-bar a {
    float: left;
    width: 15%;
    text-align: center;
    padding: 12px 0;
    transition: all 0.3s ease;
    color: white;
    font-size: 15px;
}

.icon-bar a:hover {
    background-color: #000;
}

/* .active {
    background-color: #4CAF50 !important;
} */
.writertable, tr{
	float:center;
	text-align: center;
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
a{
	text-decoration: none;
	color: black;
	cursor: pointer;
}
</style>

<body>

<hr>
<div align="center" width="400" height="100" id="list-div">
  <div id="myMenu" class="menu">
   <a href="/YOLO/myinfo?id=<%=m.getId() %>"  id="about">내정보</a>
  <a href="/YOLO/mytext?boardNo=1" id="blog">내 작성글</a>
  <a href="/YOLO/viewscrap?boardNo=1" id="projects">내 스크랩</a>
</div>

<div id="mscrap" class="tabcontent">
<div id="myDIV" class="writerheader">
<div class="icon-bar"><!-- 	Result Size: 792 x 489 -->
   <a class="active" href="viewscrap?boardNo=1&name=<%=m.getName()%>">코스</a> 
<a href="viewscrap?boardNo=2&name=<%=m.getName()%>">맛집</a> 
  <a href="viewscrap?boardNo=3&name=<%=m.getName()%>">숙박</a>
  <a href="viewscrap?boardNo=4&name=<%=m.getName()%>">자유</a>
  <a href="viewscrap?boardNo=5&name=<%=m.getName()%>">함께해요</a>
  <a href="viewscrap?boardNo=6&name=<%=m.getName()%>">여행꿀팁</a> 
</div>

<table id="myTable">
  <tr class="header">
     <th>제목</th>
  <th>스크랩 날짜</th>
    <th>선택</th>
 </tr>
 <%for(Scrap s:list){ %>
<tr>
<td><a href="/YOLO/bdetail?boardNo=<%=boardNo%>&textNo=<%=s.getTextNo()%>&page=1"><%=s.getTextTitle()%></a></td>
<td><%=s.getDate()%></td>
 <td><button><a href="/YOLO/sdelete?scrapNo=<%=s.getScrapNo()%>&boardNo=<%=boardNo %>&name=<%=m.getName()%>">스크랩취소</a></button></td>
</tr>
<% }%>
<tr>
<% for(int i=startPage; i<currentPage; i++){ %>

<a href="/YOLO/viewscrap?&boardNo=<%=boardNo%>&page=<%=i%>"><%=i %></a>

<% }%>
<%=currentPage %>
<% for(int i=currentPage+1; i<=endPage; i++){ %>

<a href="/YOLO/viewscrap?name=<%=m.getName()%>&boardNo=<%=boardNo%>&page=<%=i%>"><%=i %></a>
<% }%>
</tr>
<p align="center"><a href="/YOLO/index.jsp">시작페이지로 이동</a></p> 
<tr><td colspan="1">

</td></tr>
</table>

</div>
</div>
</div>
<script>
function openInfo(evt, infoName) {
    var i, tabcontent, tablinks;
    tabcontent = document.getElementsByClassName("tabcontent");
    for (i = 0; i < tabcontent.length; i++) {
        tabcontent[i].style.display = "none";
    }
    tablinks = document.getElementsByClassName("tablinks");
    for (i = 0; i < tablinks.length; i++) {
        tablinks[i].className = tablinks[i].className.replace(" active", "");
    }
    document.getElementById(infoName).style.display = "block";
    evt.currentTarget.className += " active";
}

</script>
</body>
</html> 
