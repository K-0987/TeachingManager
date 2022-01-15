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
	<%
	request.setCharacterEncoding("UTF-8");
	String i_id = (String) session.getAttribute("i_id");
	InstructorDao iD = new InstructorDao();
	String dept_name = iD.selectAInstructor(i_id).getDept_name();
	%>
	<br/><br/><br/><br/><br/>
	<div class="con_edit">
		<form action="ICServlet" method="post" target="Mframe">
			<fieldset>
				<legend> 开设课程 </legend>
				<input type="hidden" name="e_cinsid" value="<%=i_id%>">
				<div class="con_input">
					<input type="hidden" name="e_cid" id="ecid" value=""> <span>课程名：</span>
					<input type="text" placeholder="课程名" name="e_ctitle">
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：</span> <input type="text"
						placeholder="学院" name="e_cdept" value=<%=dept_name%>>
				</div>
				<div class="con_input">
					<span>课&nbsp;&nbsp;&nbsp;&nbsp;时：</span> <input type="text"
						placeholder="课时" name="e_chour">
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;分：</span> <input type="text"
						placeholder="学分" name="e_ccred">
				</div>
				<button name="btn" class="submit-btn"
					onclick="document.form.submit()" value="edit">开&nbsp;&nbsp;&nbsp;&nbsp;设</button>
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
	</script>
</html>