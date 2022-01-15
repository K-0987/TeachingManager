<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.StudentDao"%>
<%@ page import="bean.LogBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>最新成绩</title>
</head>
<body>
	<div class="con_table">
		<%
		request.setCharacterEncoding("UTF-8");
		String s_id = (String)session.getAttribute("s_id");
		StudentDao sD = new StudentDao();
		List<LogBean> lList = sD.readLog(s_id);
		%>
		<h2>
			共有<%=lList.size()%>条记录
		</h2>
			<table id="users">
				<tr>
                    <th>科目</th>
                    <th>成绩</th>
                    <th>时间</th>
				</tr>
				<%
				if (lList.isEmpty()) {
				%>
				<tr>
					<td colspan="3">无记录</td>
				</tr>
				<%
				} else
				for (LogBean l : lList) {
				%>
				<tr>
                			<td><%=l.getTitle()%></td>
                			<td>已通过</td>
                			<td><%=l.getTime()%></td>
				</tr>
				<%
				}
				%>
			</table>
	</div>
</body>
</html>