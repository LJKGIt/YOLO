<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
<style> 
    body{
        background-color: #e3e3e3;
        height: 100%;
    }
    #toplogo {
        padding-top: 100px;
        text-align: center;
        height: 100px;
        width: 100%;
    }
    #toplogo img {
    }
    section {
        
    }
    #midrank{
        font-family: sans-serif;
        height:280px;
        margin-top: 70px;
        width: 100%;
        color : white;
        background-color: black;
        opacity: 0.6;
        text-align: center;
    }
    #midrank a { text-decoration: none; color: white; }
    #midrank a:visited { text-decoration: none; }
    #midrank a:hover { text-decoration: none; }
    #midrank a:focus { text-decoration: none; }
    #midrank a:hover, a:active { text-decoration: none; }
    
    #midrank span {
	    font-size : 20px;
	    cursor: pointer;
    }
    
    #midrank #rank1, #rank2, #rank3{
    	padding-top: 9px;
        float: left;
        height: 100%;
        width: 23%;
        line-height: 1.8;
    }
    #rank1{
        margin-left: 16%;
    }
    #midrank ul{
		margin: 0;
        padding-left: 10%;
        padding-right: 10%;
        list-style: none;
        font-size : 17px;
    }
    #midrank div h2{
        margin-top: 0;
        margin-bottom: 0;  
    }
/*     #midrank div hr{
        width: 30px;
        height:3px; background-color:white; color:white; border:none;
    } */
    #midcs{
        margin-top: 5px;
        height: 150px;
        width: 80%;
        margin-left: 10%;
        margin-right: 10%;
        text-align: center;
        border: 1px solid;
    }
     #midcs div{
        width: 300px;
        height: 100%;
        display: inline-block;
    }
    #logopage{
        padding-top: 50px;
    	width: 100%;
        height: 100%;
    }
    .backgroundTransition{
/* 		width: 99%;
        height: 720px;
 	    margin-left: 0.5%;
        margin-right: 0.5%;
		background: url('images/main/bg4.png') no-repeat center center fixed;
      	background-size:100% 100%;
        text-align: center; */
    }
    #mainsearch{
    	width:100%;
    	height: 45px;
    	margin-top: 80px;
        text-align: center;
    }
    #mainsearch input[type="text"]{
        margin: 0;
        padding: 10px;
    	background-color: white;
		border: none;
		color: black;
		text-align: left;
		text-decoration: none;
		display: inline-block;
		font-size: 18px;
		padding: 12px;
		border-radius: 10px;
    }
    #msearch {
        padding: 15px;
        background-image: url(/YOLO/images/main/search.png);
        width: 44px;
        height: 44px;
        border: none;
        color: white;
        display: inline-block;
        cursor: pointer;
        background-size:100%;
        border-radius: 5px;
    }
    #rank1 span {
    	margin:0;
    	padding:0;
    }
</style>
<link rel="stylesheet" type="text/css" href="/YOLO/css/backgroundTransition.css" />
<script type="text/javascript" src="/YOLO/js/jquery-3.2.1.min.js"></script>
</head>
<body>
<div id="stage">
    <header>
        <%@ include file="/mainheader.jsp" %>
    </header>
    <script type="text/javascript">
    var i = 0;
    var i2 = 0;
    var i3 = 0;
    function up1(n){
    	var p = 0;
    	i--;
    	p = i;
		p = p - 1;
		if(p == 0){
			p = 4
			i = 5;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
	
				var values = "";
	
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+  
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '1' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank1 ul").html(values);
			}
		});
	};
	function up2(n){
    	var p = 0;
    	i2--;
    	p = i2;
		p = p - 1;
		if(p == 0){
			p = 4
			i2 = 5;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
	
				var values = "";
	
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+  
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '2' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank2 ul").html(values);
			}
		});
	};
	function up3(n){
    	var p = 0;
    	i3--;
    	p = i3;
		p = p - 1;
		if(p == 0){
			p = 4
			i3 = 5;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
	
				var values = "";
	
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+  
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '3' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank3 ul").html(values);
			}
		});
	};
	
	function down1(n){
		var p = 0;
	   	p = i - 1;
	   	p = p + 1;
	   	i++;
		if(p == 5){
			p = 1
			i = 2;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
		
				var values = "";
		
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+ 
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '1' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank1 ul").html(values);
			}
		});
	};
	function down2(n){
		var p = 0;
	   	p = i2 - 1;
	   	p = p + 1;
	   	i2++;
		if(p == 5){
			p = 1
			i2 = 2;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
		
				var values = "";
		
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+ 
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '2' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
				
				}
				$("#rank2 ul").html(values);
			}
		});
	};
	function down3(n){
		var p = 0;
	   	p = i3 - 1;
	   	p = p + 1;
	   	i3++;
		if(p == 5){
			p = 1
			i3 = 2;
		}
		$.ajax({
			url : "rownum",
			data : {boardNo : n, page : p},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
		
				var values = "";
		
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+   
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '3' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank3 ul").html(values);
			}
		});
	};
	
	function rank1(i){
		$.ajax({
			url : "rownum",
			data : {boardNo : "1", page : i},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈
	
				var values = "";
	
				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+   
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '1' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank1 ul").html(values);
			}
		});
	};
	function rank2(i2){
		$.ajax({
			url : "rownum",
			data : {boardNo : "2", page : i2},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈

				var values = "";

				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+   
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '2' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
				}
				$("#rank2 ul").html(values);
			}
		});
	};
	function rank3(i3){
		$.ajax({
			url : "rownum",
			data : {boardNo : "3", page : i3},
			type : "post",
			dataType : "json",
			success : function(data) {
				//console.log(data);
				var jsonStr = JSON.stringify(data); //객체를 문자열로 변환
				//console.log(jsonStr);
				var json = JSON.parse(jsonStr); //문자열을 배열 객체로 바꿈

				var values = "";

				for ( var i in json.list) {
					//한글 깨짐을 막기 위해 문자 인코딩 처리한 json 객체의 값은 decodeURIComponent() 로 디코딩 처리함
					values += "<li>" + "<div style='float:left'>" + json.list[i].rNum + "." + "</div>"
					+  
					'<a href="/YOLO/bdetail?textNo=' + json.list[i].bnum + '&boardNo=' + '3' +'">' + decodeURIComponent(json.list[i].title) + '</a>'
					
					
							
				}
				$("#rank3 ul").html(values);
			}
		});
	};
	
	$(function(){
		i = 1;
		i2 = 1;
		i3 = 1;
		rank1(i);
		i++;
		rank2(i2);
		i2++;
		rank3(i3);
		i3++;
		var interval = setInterval(function(){	
			if(i > 4){		//i가 3이상이되면 반복이 종료되므로 
				i = 1;		//다시 i를 1으로 초기화
			}
			rank1(i);
			i++;
			if(i2 > 4){		//i가 3이상이되면 반복이 종료되므로 
				i2 = 1;		//다시 i를 1으로 초기화
			}
			rank2(i2);
			i2++;
			if(i3 > 4){		//i가 3이상이되면 반복이 종료되므로 
				i3 = 1;		//다시 i를 1으로 초기화
			}
			rank3(i3);
			i3++;
		  }, 4000);	
	});
</script>
    <section>
	<div id="logopage">
		<div class="backgroundTransition">
            <script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
            <script type="text/javascript" src="/YOLO/js/backgroundTransition.js"></script>
            <script type="text/javascript">
              $(document).ready(function(){
                  $('.backgroundTransition').backgroundTransition({
                      backgrounds:[
                          { src: '/YOLO/images/main/bg.jpg' },
                          { src: '/YOLO/images/main/bg1.jpg' },
                          { src: '/YOLO/images/main/bg2.jpg' },
                          { src: '/YOLO/images/main/bg3.jpg' },
                          { src: '/YOLO/images/main/bg4.png' },
                          { src: '/YOLO/images/main/bg5.jpg' },
                          { src: '/YOLO/images/main/bg6.jpg' } 
                      ],
                      transitionDelay: 1,
                      animationSpeed: 800
                  });
              });
            </script>
            <div id="toplogo">
		      <a href="index.jsp"><img src="/YOLO/images/main/logo.png" width="230"
			     height="100"></a>
            </div>
            <div id="mainsearch">
            	<form action="search99" method="post">
				<input type="text" name="keyword" size="30" placeholder="검색할 단어를 입력하세요.">
                    <button id="msearch"></button>
				</form>
            </div>
            <div id=midrank>
                <div id=rank1>
                    <h2><a href="/YOLO/blist?boardNo=1">코스</a></h2>
                    <span><b onclick="up1(1);">∧</b></span>
                    <div>
                    <ul>
                    </ul>
                	</div>
                	<span><b onclick="down1(1);">∨</b></span>
                </div>
                <div id=rank2>
                    <h2><a href="/YOLO/blist?boardNo=2">맛집</a></h2>
                	<span><b onclick="up2(2)">∧</b></span>
                    <div>
                    <ul>
                    </ul>
                    </div>
                    <span><b onclick="down2(2)">∨</b></span>
                </div>
                <div id=rank3>
                    <h2><a href="/YOLO/blist?boardNo=3">숙박</a></h2>
                    <span><b onclick="up3(3)">∧</b></span>
                    <div>
                    <ul>
                    </ul>
                    </div>
                    <span><b onclick="down3(3)">∨</b></span>
                </div>
            </div>
        </div>
	</div>
	<div style="text-align: center; color: black; background-color: white; width: 100%; height: 50px;">
	
	</div>
	<!--<div id=midcs>
        <div style="background-image: url(images/main/bg.jpg); background-size:100%;"></div>
        <div style="background-image: url(images/main/bg2.jpg); background-size:100%;"></div>
        <div style="background-image: url(images/main/bg3.jpg); background-size:100%;"></div>
        <div style="background-image: url(images/main/bg5.jpg); background-size:100%;"></div>
    </div>-->
    </section>
    <footer>
    <%@ include file="mainfooter.jsp" %>
    </footer>
</div>
</body>
</html>