<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
	<link rel="stylesheet" href="./assets/home/css/style.css">
	<link rel="stylesheet" href="./assets/node_modules/bootstrap/dist/css/bootstrap.css" />
	<script src="./assets/node_modules/bootstrap/dist/js/bootstrap.js" ></script>
</head>
<body>
	<div class = "nav">
		<div class="nav_btn">
           <input type="button" class="btn btn-primary" style="width: 100%" value="Home" onclick="location.href='<%=request.getContextPath() %>/';">		
		</div>			
		<div class="nav_btn">
           <input type="button" class="btn btn-primary" style="width: 100%" value="예측 서비스" onclick="location.href='<%=request.getContextPath() %>/predict';">		
		</div>
		<div class="nav_btn">
           <input type="button" class="btn btn-primary" style="width: 100%" value="분석 서비스" onclick="location.href='<%=request.getContextPath() %>/factor';">		
		</div>	
	</div><!-- nav -->
</body>
</html>