<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.StudentDao"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>学生管理界面</title>
</head>
<body>
	<%StudentDao sD = new StudentDao();
	  String id = (String)request.getAttribute("id");
	  session.setAttribute("s_id", id);%>
	<div id="header">
		<span class="title_con">学工系统</span>
	</div>

	<div id="content">
		<div class="con_left">
			<div class="con_title">
				<span class="con_title_sp">欢迎你,<%=sD.selectNameById(id)%>同学</span>
			</div>
			<div>
				<div class="con_list">
					<i class="fas fa-hand-pointer"></i>
					<a target="Mframe" href="allcourse.jsp">网上选课</a>
				</div>
				<div class="con_list">
					<i class="fas fa-clipboard-list"></i>
					<a target="Mframe" href="student_course.jsp">我的课程</a>
				</div>
				<div class="con_list">
					<i class="fas fa-user"></i>
					<a target="Mframe" href="self.jsp">个人信息</a>
				</div>
				<div class="con_list">
					<i class="fas fa-file-alt"></i>
					<a target="Mframe" href="newscore.jsp">最新成绩</a>
				</div>
				<div class="con_list">
					<i class="fas fa-exchange-alt"></i>
					<a href="login.jsp">切换用户</a>
				</div>
			</div>
		</div>
		<div class="con_right">
			<iframe name="Mframe" src="newscore.jsp" height="600px" width="84.5%"
				style="border-style: none;"></iframe>
		</div>
	</div>

	<div id="footer">
		<p>@3159</p>
	</div>
</body>
</html>