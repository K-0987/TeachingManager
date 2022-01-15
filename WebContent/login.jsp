<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./xgxt_login.css">
<title>用户登录-学工系统</title>
</head>
<body>
<%session.invalidate();%>
<div id="header">
	<div class="header_title">
		<span class="title_con">学工系统</span>
	</div>
</div>

<div id="content">
		<div class="con">
		<div class="con_title">
			<span class="con_title_sp">欢迎登录学工系统</span>
		</div>
		<div class="con_panel">
			<form action="/TeachingManager/LoginServlet" method="post">
			<div class="con_input">
				<span>用户名：</span><input type="text" placeholder="学号/工号" name="id">
			</div>
			<div class="con_input">
				<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span><input type="password" placeholder="密码" name="pWord">
			</div>
			<div class="con_select">
				<input type="radio" name="t1" value="S">学生
				<input type="radio" name="t1" value="I">教师
				<input type="radio" name="t1" value="M">管理员
			</div>
			<input type="submit" value="登    录" class="submit-btn">
			<p style="text-align:center;color:red;margin-top:0">${msg}</p>
			</form>
		</div>
	</div>
</div>

<div id="footer" style="text-align:center">
	<p>@3159</p>
</div>



</body></html>