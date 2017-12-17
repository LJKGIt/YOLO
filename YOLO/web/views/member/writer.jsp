<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="member.model.vo.Member,board.model.vo.Board, java.sql.*,java.util.*" %>   
 
<%
	Member m = (Member)session.getAttribute("member");
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	int currentPage=((Integer)request.getAttribute("currentPage")).intValue();
	int startPage=((Integer)request.getAttribute("startPage")).intValue();
	int endPage=((Integer)request.getAttribute("endPage")).intValue();
	int boardNo=((Integer)request.getAttribute("boardNo")).intValue();

%>   
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript">

		
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
</style>
</head>
<body>

<hr>
<div align="center" width="400" height="100" id="list-div">
  <div id="myMenu" class="menu">
  <a href="/YOLO/myinfo?id=<%=m.getId() %>"  id="about">내정보</a>
  <a href="/YOLO/mytext?name=<%=m.getName() %>&boardNo=1" id="blog">내 작성글</a>
  <a href="/YOLO/viewscrap?name=<%=m.getName()%>&boardNo=1" id="projects">내 스크랩</a>
</div>
</div>


<div id="mwriter" class="tabcontent">
<div id="myDIV" class="writerheader">
<div class="icon-bar"><!-- 	Result Size: 792 x 489 -->
 <a class="active" href="mytext?boardNo=1&name=<%=m.getName()%>">코스</a> 
<a href="mytext?boardNo=2&name=<%=m.getName()%>">맛집</a> 
  <a href="mytext?boardNo=3&name=<%=m.getName()%>">숙박</a>
  <a href="mytext?boardNo=4&name=<%=m.getName()%>">자유</a>
  <a href="mytext?boardNo=5&name=<%=m.getName()%>">함께해요</a>
  <a href="mytext?boardNo=6&name=<%=m.getName()%>">여행꿀팁</a> 
</div>

<table id="myTable">
  <tr class="header">
      <th>제목</th>
    <th>작성날짜</th>
    
 </tr>
 <%for(Board b:list){ %>
 <tr>
<td><a href="/YOLO/bdetail?boardNo=<%=boardNo%>&textNo=<%=b.getTextNo()%>&page=1"><%=b.getTextTitle()%></a></td>
 <td><%=b.getDate()%></td>
 </tr>
<% }%>

<tr>
<% for(int i=startPage; i<currentPage; i++){ %>

<a href="/YOLO/mytext?name=<%=m.getName()%>&boardNo=<%=boardNo%>&page=<%=i%>"><%=i %></a>

<% }%>
<%=currentPage %>
<% for(int i=currentPage+1; i<=endPage; i++){ %>

<a href="/YOLO/mytext?name=<%=m.getName()%>&boardNo=<%=boardNo%>&page=<%=i%>"><%=i %></a>

<% }%>
</tr>
<p align="center"><a href="/YOLO/index.jsp">시작페이지로 이동</a></p> 
</table>

</div>
</div>

</body>
</html> 
