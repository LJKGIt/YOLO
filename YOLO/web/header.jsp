<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="member.model.vo.Member"%>
<%
	Member member = (Member) session.getAttribute("member");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/new.css">
<script type="text/javascript" src="js/jquery-3.2.1.min.js"></script>
<title>header</title>
<script type="text/javascript">
//비밀번호 찾기 - 아이디 입력후 힌트제공
$(function(){
	$('#showHint').click(function(){
		
		$.ajax({
			url : "showHint",
			type :'get',
			data : {id : $('#findId').val()},
			dataType : "text",
			success : function(value){
				alert(value);
			}
		});
		
	});
});

$(function(){
	$('#showPwd').click(function(){
		
		$.ajax({
			url : "showPwd",
			type :'get',
			data : {id : $('#findId').val(), answer : $('#answer').val()},
			dataType : "text",
			success : function(value){
				alert(value);
			}
		});
		
	});
});


//전체메뉴 보이기 / 숨기기
$(function(){
	 var con = document.getElementById("totalmenu");
	 con.style.display = 'none';
});
$(function(){
	 var st = document.getElementById("joinpage");
	 st.style.display = 'none';
});
/* end */
function doDisplay(){
    var con = document.getElementById("totalmenu");
    if(con.style.display=='none'){
        con.style.display = 'block';
    }else{
        con.style.display = 'none';
    }
}//end

//회원가입 보이기//숨기기
function joinpg(){
    var st = document.getElementById("joinpage");
    if(st.style.display=='none'){
        st.style.display = 'block';
    }else{
        st.style.display = 'none';
    }
}
/*end*/
 
 
 /*
/* 로그인 페이지 보이기 */
$(function(){
	 var io = document.getElementById("id01");
	 io.style.display = 'none';
});

function loginDisplay(){
    var io = document.getElementById("id01");
    if(io.style.display=='none'){
        io.style.display = 'block';
    }else{
        io.style.display = 'none';
    }
}//end
 //회원가입 보이기
$(function(){
	 var jo = document.getElementById("id02");
	 jo.style.display = 'none';
});
//비밀번호 찾기
$(function(){
	 var fp = document.getElementById("id03");
	 fp.style.display = 'none';
});
//비밀번호 찾기
function findPwd(){
	   var jo = document.getElementById("id01");
	   jo.style.display='none';
	   var fp = document.getElementById("id03");
	   if(fp.style.display=='none'){
	       fp.style.display = 'block';
	   }else{
	       fp.style.display = 'none';
	   }
}

function joinDisplay(){
   var jo = document.getElementById("id02");
   if(jo.style.display=='none'){
       jo.style.display = 'block';
   }else{
       jo.style.display = 'none';
   }
}//end
 
//email 중복 확인
$(function(){
	$('#checkId').click(function(){
		$.ajax({
			url : "checkId",
			type :'get',
			data : {id : $('#userid').val()},
			dataType : "text",
			success : function(value){
				//alert("서블릿이 보낸 값 : " + data);
				if(value == 'yes'){
					//console.log(value);
					alert("사용 가능한 아이디입니다.");
					$('#userpwd').focus();
				}else{
					//console.log(value);
					alert("이미 존재하는 아이디입니다. 다른 아이디로 정하십시오.");
					$('#userId').select();
				} 
			}, 
			error : function(value){
				alert("에러 : " + value);
			}
		});
		return false;
	});


//nikname 중복 확인

	$('#nickcheck').click(function(){
		$.ajax({
			url : "checkName",
			data : {name : $('#nickname').val()},
			type :'get',
			dataType : "text",
			success : function(value){
				//alert("서블릿이 보낸 값 : " + data);
				if(value == 'yes'){
					alert("사용 가능한 닉네임입니다.");
					
				}
				if(value == 'no'){
					alert("이미 존재하는 닉네임입니다. 다른 닉네임으로 정하십시오.");
					$('#username').select();
				} 
			}, 
			error : function(value){
				alert("에러 : " + value);
			}
		});
		return false;
	});
}); 
	

    function fun3(){
		 var obj=document.jf;
		 if(obj.userid.value.length > 10 || obj.userid.value.length == 0){
			document.getElementById("alert_text").innerHTML=	('<span style = "color : #777"><font color="red">아이디는 10자 이내로 입력해 주세요</font></span>'); 
			return false;
		 }else{
			 document.getElementById("alert_text").innerHTML=('<span style = "color : #777"><font color="#66ff00">허용 범위입니다.</font></span>'); 

		 }
	 }
	 function fun4(){
		 var obj=document.jf;
		 if(obj.nickname.value.length > 7 || obj.nickname.value.length == 0){
				document.getElementById("alert_name").innerHTML=	('<span style = "color : red"><font color="red">7자 이내로 입력해 주세요.</font></span>'); 
				return false;
			 }else{
			 document.getElementById("alert_name").innerHTML=('<span style = "color : #777"><font color="#66ff00">허용 범위입니다.</font></span>');
	 }
	 }
	 function fun5(){
		 var obj=document.jf;
		 if(obj.userpwd1.value=='' || obj.userpwd1.value != obj.userpwd.value){
				document.getElementById("alert_pwd").innerHTML=	('<span style = "color : #777"><font color="red">패스워드가 일치하지 않습니다.</font></span>'); 
				return false;
			 }else{
				 document.getElementById("alert_pwd").innerHTML=('<span style = "color : #777"><font color="#66ff00">입력한 패스워드가 일치합니다.</font></span>');
	 }
	 }
	 
	 $(document).ready(function(){
		  var modalLayer = $("#modalLayer");
		  var modalLink = $("#signin");
		  var modalCont = $(".modalContent");
		  var marginLeft = modalCont.outerWidth()/2;
		  var marginTop = modalCont.outerHeight()/2; 

		  modalLink.click(function(){
		    modalLayer.fadeIn("slow");
		    modalCont.css({"margin-top" : -marginTop, "margin-left" : -marginLeft});
		    $(this).blur();
		    $(".modalContent > a").focus(); 
		    return false;
		  });

		  $(".modalContent > button").click(function(){
		    modalLayer.fadeOut("slow");
		    modalLink.focus();
		  });		
		});
	 
  </script>
<style type="text/css">
body {
	margin: 0;
}

#topbar { 
	position: fixed;
	width: 100%;
	margin: 0;
	background: linear-gradient(to bottom, black, #4d4d4d);
	height: 50px;
	z-index: 10;
	clear: none;
}

div#member {
	width: 343;
	height: 28;
	padding: 0;
	margin-top: 11px;
	margin-right: 20px;
	float: right;
}

div#loginbar {
	width: 343;
	height: 28;
	padding: 0;
	color: white;
}

#topbar form {
	font-family: monospace;
}

#topbar form input {
	background-color: white;
	border: none;
	color: black;
	text-align: left;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	padding: 5px 5px;
	border-radius: 5px;
}

#topbar form #signin {
	background: linear-gradient(to bottom, #4CAF50, #007927);
	border: none;
	color: white;
	text-align: center;
	padding: 5px 5px;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
}

#topbar form #join {
	background: linear-gradient(to bottom, #8888ff, #0000e3);
	border: none;
	color: white;
	text-align: center;
	padding: 5px 5px;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
}

#loginbar #logout {
	background: linear-gradient(to bottom, #4CAF50, #007927);
	border: none;
	color: white;
	text-align: center;
	padding: 5px 5px;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
	border-radius: 5px;
}

#loginbar #myinfo {
	background: linear-gradient(to bottom, #8888ff, #0000e3);
	border: none;
	color: white; 
	text-align: center;
	padding: 5px 5px;
	text-decoration: none;
	display: inline-block;
	font-size: 15px;
	cursor: pointer;
	border-radius: 5px;
}

#menu {
	display: inline-block;
	cursor: pointer;
	margin-top: 6px;
	margin-left: 20px;
}

#menu #bar1, #bar2, #bar3 {
	width: 20px;
	height: 4px;
	background-color: white;
	margin: 6px 0;
	transition: 0.4s;
}

#topbar img {
	margin-top: 6px;
	margin-left: 20px;
}

#toplogo {
	text-align: center;
	padding-top: 50px;
	height: 150px;
	width: 100%;
}

#toplogo img {
	margin-top: 40px;
}


#totalmenu {

	width: 180px;
	 z-index: 1;
	padding-top: 50px;
	display: none;
	/* float: left; */
	/*position: absolute;*/ /*위치 조정하기위한 css */
	background: white;
}
#totalmenu a {
 display: block;
	
}
#totalmenu {
	position:fixed;
	list-style: none;
	padding-left: 0px;
	margin-top: 0px;
	margin-bottom: 0px;
	/* display: none; */
}
#totalmenu ul {
    list-style-type: none;
    margin: 0;
    padding: 0;
    width: 200px;
    background-color: #f1f1f1;
    text-align:center;
}

 #li a {
    display: block;
    color: #000;
    padding: 8px 16px;
    text-decoration: none;
}

#li a.active {
    background-color: #3e3e28;
    color: white;
}

#li a:hover:not(.active) {
    background-color: #3e3e3e;
    color: white;
}


/* #joinpage {
	position: absolute;
	top: 2px;
	left: 70%;
	/* float : right; */
	padding-top: 50px;
	display: none;
	background: white;
} */



/* 로그인 div */
#log{
 width : 600px;
 height: 100px;
 padding-top: 50px;
 padding-bottom: 50px;
 width: 495px;
 height: 300px;
 padding-left: 30px;
 padding-right: 30px;
 
}
/* 로그인 팝업창  */
#content{
    height: 210px;
    width: 600px;

}
/* 비밀번호 찾기 창 */
#content3{
    height: 200px;
    width: 600px;

}

/* #jf{

background : white;
} */

#gaip{
 position : absolute;
 left :35%;
 bottom: 15%;
}
#signin1{
position : absolute;
left : 450px;
top : 70px;
margin-left: 20px;
}
#container{
	
    height: 530px;
    padding-top: 12px;
    width: 530px;
    padding-left: 119px;
}

#container1{
    padding-top: 50px;
    padding-left: 35px;
}
#content2{
width: 590px;
height: 800px;
-ms-overflow-style:none;
}
#h3{
     width: 400px;
    margin-top: 18px;
    margin-right: 0px;
}
::-webkit-scrollbar{
display : none;
}


</style>
</head>
<body>
	<div id="topbar">
		<div id="menu" onclick="doDisplay();">
			<div id="bar1"></div>
			<div id="bar2"></div>
			<div id="bar3"></div>
		</div>
		<div id="member">
			<%
				if (member != null) {
			%>
			<div id="loginbar" style="vertical-align: middle">
				<%=member.getName()%>님 <a href="/YOLO/logout"><input id="logout"
					type="button" value="로그아웃"></a> <a href="/YOLO/myinfo?id="<%=member.getId() %>"><input
					id="myinfo" type="button" value="내정보"></a>
			</div>
			<%
				} else {
			%>
				<form action="login" >	
				 <input id="signin" type="button"
					value="로그인"  onclick="loginDisplay();"> 
					<input id="join" type="button"
					value="회원가입" onclick="joinDisplay()">
				
				</form>
			<%
				}
			%>
		</div>
	</div>
	<!-- 로그인 창 -->
	
	<div id="id01" class="w3-modal">
    <div id ="content" class="w3-modal-content">
      <div id="container1" class="w3-container">
        <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-display-topright">&times;</span>
        <div id = "log">
        <form id = "loginbutton" action="login" method="post" ><br>
        <label id = "id"><b>아 이 디 : </b></label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" name="userid" size="30" placeholder="아이디를 입력해주세요" required><br>
		<br>		
		<label id = "id"><b>비밀번호 : </b></label>		
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="password" name="userpwd" size="30"  placeholder="********" required>	
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;<button id="signin1" class = "w3-button w3-black" type="submit"
					value="로그인"  style="width:60pt;height:60pt;">로그인</button>
		<br><a onclick="findPwd()">[비밀번호 찾기]</a>
		</form>
      
      </div>
    </div>
  </div>
  
  <!--  비밀번호 찾기 -->
  <div id="id03" class="w3-modal">
    <div id ="content3" class="w3-modal-content">
      <div id="container1" class="w3-container">
        <span onclick="document.getElementById('id03').style.display='none'" class="w3-button w3-display-topright">&times;</span>
        <div id = "log">
        <form><br>
        <label id = "id"><b>아 이 디 입력  : </b></label>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="findId" name="id" size="30" placeholder="아이디를 입력해주세요">&nbsp;<button type="button" id="showHint">비밀번호 힌트 보기</button></button><br>
				
		<br><label id="id"><b>정    답 : </b></label>
		&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" id="answer" name="answer" size="40" placeholder="비밀번호 힌트의 정답을 입력하세요.">
		&nbsp;<button type="button" id="showPwd">비밀번호 보기</button>
		<br><br>
		</div>
		&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
      
      </div>
    </div>
  </div>


	<ul id="totalmenu">
  <li id="li"><a class="active" href="#home">PHOTO 게시판</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=1">추천여행코스</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=2">추천 맛집</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=3">추천 숙박</a></li>
  <li id="li"><a class="active" href="#home">일반 게시판</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=4">자유게시판</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=5"">함께해요</a></li>
  <li id="li"><a href="/YOLO/blist?boardNo=6">여행꿀팁</a></li>
  <li id="li"><a class="active" href="#home">교통&행사정보</a></li>
  <li id="li"><a href="https://www.kobus.co.kr/mrs/rotinf.do">버스</a></li>
  <li id="li"><a href="http://www.letskorail.com/">기차</a></li>
  <li id="li"><a href="http://korean.visitkorea.or.kr/kor/bz15/addOn/main/publish/index.jsp">지역행사정보</a></li>

</ul>

		
	<!-- 회원가입 -->
	<div id="id02" class="w3-modal">
    <div id = "content2" class="w3-modal-content">
      <div id = "container"class="w3-container">
        <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-display-topright">&times;</span>
		<form id = "jf" name="jf" method="post" action="/YOLO/join">
			<!-- action="/YOLO/join" -->
			<h3 id = "h3" align ="center" style="margin-bottom: 10px;">회 원 가 입</h3>
			<table cellspacing="0"  width="380" height="380" style = "border-collapse : collapse; font-size : 9pt;">
				<tr height = 40>
					
					<td width = "15%" height="80px" align="center">아이디</td>
					<td><input id="userid" name="userid"  size = 30  type="text" onkeyup = "fun3()" placeholder= "아이디를 입력해주세요."/>
						<a id="checkId" style = "cursor:pointer">[중복 확인]</a><br>
						<span id = "alert_text"><span style = "color : #777"></span></span>
						<br><span style = "color : #777">입력 한 후 중복확인을 해주세요.</span></td>
				</tr>
				<tr height = "7">
				<td colspan = "3"><hr></td>
				</tr>
				<tr height = "30">
					<td width ="15%" height= "80px" align="center" >비밀번호</td>
					<td><input id="userpwd" name="userpwd" size = 30  type="password"  onkeyup = "fun5();" placeholder= "비밀번호를 입력해주세요."/>
					</td>
				</tr>
				<tr height = "7">
				<td colspan = "3"><hr></td>
				</tr>
				<tr>
					<td width = "15%"  height= "80px" align="center" >비밀번호 확인</td>
					<td><input id="userpwd1" name="userpwd1" size = 30 type="password" onkeyup="fun5();" placeholder= "비밀번호를 한번 더 입력해주세요." /><br>
					<span id = "alert_pwd"><span style = "color : #777"></span></span></td>		
					
				</tr>
				<tr height = "7">
				<td colspan = "3"><hr></td>
				</tr>
				<tr>
					<td width = "15%"  height= "80px" align="center" >비밀번호 힌트</td>
					<td><input id="hint" name="hint" size = 30 type="text" placeholder= "비밀번호 힌트를 입력해주세요." /><br><br></td>
				</tr>
				<tr height = "7">
				<td colspan = "3"><hr></td>
				</tr>
				<tr>
				<td width = "15%"  height= "80px" align="center" >정 답</td>
				<td><input id="answer" name="answer" size = 30 type="text" placeholder= "정답을 입력해주세요." /><br></td>
				</tr>
				<tr height = "7">
				<td colspan = "3"><hr></td>
				</tr>
				<tr style="height: 65px;">
					<td width = "15%" height="80px" align="center" >닉네임</td>
					<td><input id="nickname" type="text" size = 30 name="nickname" onkeyup = "fun4()" placeholder= "사용하실 닉네임을 입력해주세요."/>
						<a id="nickcheck" style = "cursor:pointer">[중복 확인]</a><br>
						<span id = "alert_name"><span style = "color : #777">한글 최대7자, 영문 최대 21자</span></span>
						<br><span style = "color : #777">입력 한 후 중복확인을 해주세요.</span></td>
				</tr>
				
			</table>
			<div id = "gaip">
			<br /> <button class="w3-button w3-black w3-round-xlarge" type="submit" value="가입하기" onclick = "" >가입하기</button> 
					 &nbsp;&nbsp;<button class="w3-button w3-black w3-round-xlarge" type="reset" value="다시쓰기" >다시쓰기</button>
		</div>
		</form>
	</div>
      </div>
    </div>

	<div id="toplogo">
		<a href="/YOLO/index.jsp"><img src="/YOLO/images/header/logo.png" width="200"
			height="100"></a>
	</div>
</body>
</html>