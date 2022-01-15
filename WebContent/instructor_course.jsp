<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.InstructorDao"%>
<%@ page import="bean.CourseBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>开设课程</title>
</head>
<body>
	<div class="con_table">
		<%
		request.setCharacterEncoding("UTF-8");
		String i_id = (String) session.getAttribute("i_id");
		InstructorDao iD = new InstructorDao();
		List<CourseBean> cList = iD.selectCourseByInstructor(i_id);
		%>
		<h2>
			您共有<%=cList.size()%>门课程
		</h2>
		<form action="ICServlet" method="post" target="Mframe">
		<input type="hidden" name="i_id" value="<%=i_id%>">
			<table id="users">
				<tr>
					<th>编号</th>
					<th>课程名</th>
					<th>所属学院</th>
					<th>课时</th>
					<th>学分</th>
					<th>操作</th>
				</tr>
				<%
				if (cList.isEmpty()) {
				%>
				<tr>
					<td colspan="6">未找到记录</td>
				</tr>
				<%
				} else
				for (CourseBean c : cList) {
				%>
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getTitle()%></td>
					<td><%=c.getDept_name()%></td>
					<td><%=c.getHours()%></td>
					<td><%=c.getCredits()%></td>
					<td>
						<button title="详细" name="btn" class="btn_img"
							onclick="document.form.submit()" value="<%=c.getId()%>">
							<i class="fas fa-user-graduate fa-lg"></i>
						</button>
					</td>
				</tr>
				<%
				}
				%>
			</table>
		</form>
	</div>
</body>
</html>