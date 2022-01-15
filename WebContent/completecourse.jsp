<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.InstructorDao"%>
<%@ page import="bean.StudentBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>结课</title>
</head>
<body>
	<div class="con_table">
		<%
		request.setCharacterEncoding("UTF-8");
		String i_id = (String)request.getAttribute("i_id");
		String c_id = (String)request.getAttribute("c_id");
		InstructorDao iD = new InstructorDao();
		List<StudentBean> sList = iD.selectAllStudentFromCourse(c_id);
		%>
		<h2>
			学生列表--共有<%=sList.size()%>名学生
		</h2>
		<form action="ICServlet" method="post" target="Mframe">
		<input type="hidden" name="c_id" value="<%=c_id%>">
			<table id="users">
				<tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>所属学院</th>
                    <th>总学分</th>
                    <th>操作</th>
				</tr>
				<%
				if (sList.isEmpty()) {
				%>
				<tr>
					<td colspan="6">没有学生</td>
				</tr>
				<%
				} else
				for (StudentBean s : sList) {
				%>
				<tr>
                			<td><%=s.getId()%></td>
                			<td><%=s.getName()%></td>
                			<td><%if(s.getSex().equals("F")){%>
                				女
                				<%}else{%>
                				男<%}%></td>
                			<td><%=s.getDept_name() %></td>
                			<td><%=s.getTol_cred()%></td>
					<td>
						<%String completed = s.getCompleted();
						if(completed.equals("N")){%>
						<button title="通过" name="btn" class="btn_img"
							onclick="document.form.submit()" value="G<%=s.getId()%>">
							<i class="fas fa-check fa-lg"></i>
						</button>
						<%}else{%>
						已通过
						<%} %>
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