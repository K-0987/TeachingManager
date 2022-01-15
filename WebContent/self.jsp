<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.StudentBean"%>
<%@ page import="bean.InstructorBean"%>
<%@ page import="dao.StudentDao"%>
<%@ page import="dao.InstructorDao"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>个人信息</title>
</head>
<body>
	<%
	String u_id = (String) session.getAttribute("s_id");
	if (u_id != null) {
		StudentDao sD = new StudentDao();
		StudentBean s = new StudentBean();
		s = sD.selectAStudent(u_id);
	%>
	<br/><br/>
	<div class="con_edit">
		<form action="MSServlet" method="post" target="Mframe">
			<fieldset>
				<legend> 我的信息 </legend>
				<input type="hidden" name="e_sid" value=<%=u_id%>>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;号：<%=u_id%></span>
				</div>
				<div class="con_input">
					<span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<%=s.getName()%></span>
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：<%=s.getDept_name()%></span>
				</div>
				<div class="con_input">
					<span>总学分：<%=s.getTol_cred()%></span>
				</div>
				<%String sex = s.getSex();%>
				<div class="con_input">
					<span>性&nbsp;&nbsp;&nbsp;&nbsp;别：<%if(sex.equals("M"))%>男<%else %>女</span>
				</div>
				<hr/>
				<div class="con_input">
					<span>原密码：</span> <input type="password"
						placeholder="原密码" name="e_sspword">
				</div>
				<div class="con_input">
					<span>新密码：</span> <input type="password"
						placeholder="密码" name="e_spword">
				</div>
				<button name="btn" class="submit-btn"
					onclick="document.form.submit()" value="edit">更改密码</button>
			</fieldset>
		</form>
	</div>
	<%
	} else {
	u_id = (String) session.getAttribute("i_id");	
	InstructorDao iD = new InstructorDao();
	InstructorBean i = new InstructorBean();
	i = iD.selectAInstructor(u_id);
	%>
	<br/><br/>
	<div class="con_edit">
		<form action="MIServlet" method="post" target="Mframe">
			<fieldset>
				<legend> 我的信息 </legend>
				<input type="hidden" name="e_iid" value=<%=u_id%>>
				<div class="con_input">
					<span>工&nbsp;&nbsp;&nbsp;&nbsp;号：<%=u_id%></span>
				</div>
				<div class="con_input">
					<span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：<%=i.getName()%></span>
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：<%=i.getDept_name()%></span>
				</div>
				<%String position = i.getPosition();%>
				<div class="con_input">
					<span>职&nbsp;&nbsp;&nbsp;&nbsp;位：<%if(position.equals("A")){%>教授<%}else if(position.equals("B")){%>副教授<%}else %>讲师</span>
				</div>
				<%String sex = i.getSex();%>
				<div class="con_input">
					<span>性&nbsp;&nbsp;&nbsp;&nbsp;别：<%if(sex.equals("M"))%>男<%else %>女</span>
				</div>
				<hr/>
				<div class="con_input">
					<span>原密码：</span> <input type="password"
						placeholder="原密码" name="e_iipword">
				</div>
				<div class="con_input">
					<span>新密码：</span> <input type="password"
						placeholder="密码" name="e_ipword">
				</div>
				<button name="btn" class="submit-btn"
					onclick="document.form.submit()" value="edit">更改密码</button>
			</fieldset>
		</form>
	</div>
	<%
	}
	%>
</body>
    <script type="text/javascript">
		var error='<%=request.getAttribute("msg")%>';
		if(error==0)
			alert("执行成功！");
		else if(error==-1)
			alert("原密码错误，修改密码失败！");
		else if(error==-2)
			alert("删除失败！");
	</script>
</html>