<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.StudentDao"%>
<%@ page import="bean.CourseBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>课程管理</title>
</head>
<body>
	<div class="con_table">
		<script>
			function sub_confirm() {
				if (!confirm("你确定要退课吗"))
					return false;
			}
		</script>
		<%
		request.setCharacterEncoding("UTF-8");
		StudentDao sD = new StudentDao();
		String s_id = (String) request.getAttribute("s_id");
		if(s_id == null)
			s_id = (String) session.getAttribute("s_id");
		List<CourseBean> cList = sD.selectCoursesByStudent(s_id);
		%>
		<form action="SCServlet" method="post">
		<input type="hidden" name="s_id" value="<%=s_id%>">
		<fieldset>
			<legend>
				学生<%=sD.selectNameById(s_id)%>的课程</legend>
			<table id="users">
				<tr>
					<th>编号</th>
					<th>课程名</th>
					<th>导师</th>
					<th>所属学院</th>
					<th>课时</th>
					<th>学分</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
				<%
				if (cList.isEmpty()) {
				%>
				<tr>
					<td colspan="8">未找到记录</td>
				</tr>
				<%
				} else {
				for (CourseBean c : cList) {
				%>
				<tr>
					<td><%=c.getId()%></td>
					<td><%=c.getTitle()%></td>
					<td><%=c.getIns_name()%></td>
					<td><%=c.getDept_name()%></td>
					<td><%=c.getHours()%></td>
					<td><%=c.getCredits()%></td>
					<td>
						<%
						boolean completed = false;
						if (c.getCompleted().equals("Y")) {
							completed = true;
						%> 已完成 <%
						} else {
						%> 未完成 <%
						}
						%>
					</td>
					<td>
						<%if (!completed){%>
						<button title="退课" name="btn" class="btn_img"
							onclick="return sub_confirm()" value="D<%=c.getId()%>">
							<i class="fas fa-running fa-lg"></i>
						</button> <%}else{%>
							无操作
						<%}}}%>
					</td>
			</table>
		</fieldset>
		</form>
	</div>
</body>
    <script type="text/javascript">
		var error='<%=request.getAttribute("msg")%>';
		if(error==0)
			alert("执行成功！");
		else if(error==-1)
			alert("输入数据有误！");
		else if(error==-2)
			alert("退课失败！");
	</script>
</html>