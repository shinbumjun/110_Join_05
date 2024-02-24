<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" >
<head>

  	<script type="text/javascript">
		//컨트롤러에서 보낸 메세지가 있을 경우
		window.onload = function(e){ 
			
			var resultMsg = '${resultMsg}';
			var resultCode = '${resultCode}';
			if(resultMsg.length > 0){
				alert(resultMsg);
				// 메시지를 한 번 보여준 후 resultMsg 값을 비웁니다.
		        resultMsg = '';
			}
		}
	</script>

  <meta charset="UTF-8">
  <title>CodePen - Flat Login Form</title>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900'>
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Montserrat:400,700'>
<link rel='stylesheet' href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<!-- ===== CSS 주의할점 : /resources/css/style.css resource안에 css파일 안에 style.css를 넣는다.  ===== -->
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
<!-- partial:index.partial.html -->
<div class="container">
  <div class="info">
    <h1>로그인 폼</h1><span>신범준 <i class="fa fa-heart"></i> by <a href="http://andytran.me">달리기는 게임이다</a></span>
  </div>
</div>
<div class="form">
  <div class="thumbnail"><img src="https://s3-us-west-2.amazonaws.com/s.cdpn.io/169963/hat.svg"/></div>
  <form action="/spring/sign-up.do" method="post" class="register-form">
    <input name="memberId" type="text" placeholder="영문이름"/>
    <input name="passwd" type="password" placeholder="비밀번호"/>
    <input name="email" type="text" placeholder="이메일"/>
    <button>생성</button>
    <p class="message">이미 등록되었습니까? <a href="#">로그인</a></p>
  </form>
  <form action="/spring/login.do" method="post" class="login-form">
    <input name="memberId" type="text" placeholder="영문이름를 입력해주세요"/>
    <input name="passwd" type="password" placeholder="비밀번호를 입력해주세요"/>
    <button>로그인</button>
    <p class="message">미등록? <a href="#">계정생성</a></p>
  </form>
</div>
<video id="video" autoplay="autoplay" loop="loop" poster="polina.jpg">
  <source src="http://andytran.me/A%20peaceful%20nature%20timelapse%20video.mp4" type="video/mp4"/>
</video>
<!-- partial -->
  <script src='//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
  
  <!-- 주의할점 : /resources/js/script.js설정시 resources안에 js파일안에 script.js 넣을 것 -->
  <script src="${pageContext.request.contextPath}/resources/js/script.js"></script>

</body>
</html>