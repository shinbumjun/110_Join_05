<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
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


<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<a href="/spring/join-page.do" style="text-decoration: none;">회원가입 폼으로</a>
로그인이 완료
</body>
</html>