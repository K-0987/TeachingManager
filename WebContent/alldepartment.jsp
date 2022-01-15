<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="bean.DepartmentBean"%>
<%@ page import="dao.ManagerDao"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="./xgxt_login.css">
<script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
<title>个人信息</title>
</head>
<body>
	<script type="text/javascript">
		function d_edit(ebtn) {
			var name = " ";
			name = ebtn.value;
			window.location.hash = "#e1";
			document.getElementById("e1").style.display = "block";
			document.getElementById("e_name").value = name;
			document.getElementById("e_building").value = document.getElementById(name + "_building").innerHTML;
			document.getElementById("e_descripe").innerHTML = document.getElementById(name + "_descripe").innerHTML;
			document.getElementById("dname").innerHTML = name;
			return false;

		}
		function d_edit_cancel() {
			document.getElementById("e1").style.display = "none";
		}
		function d_new() {
			window.location.hash = "#e2";
			document.getElementById("e2").style.display = "block";

		}
		function d_new_cancel() {
			document.getElementById("e2").style.display = "none";
		}
	</script>
	<%
	ManagerDao mD = new ManagerDao();
	List<DepartmentBean> dList = mD.selectAllDepartment();
	for (DepartmentBean d : dList) {
	%>
	<br />
	<br />
	<div class="con_edit">
		<form action="MDServlet" method="post" target="Mframe">
			<fieldset>
				<%
				String name = d.getName();
				%>
				<legend><%=name%></legend>
				<input type="hidden" name="e_dept" value="<%=name%>">
				<div class="con_input" style="text-align:left;">
					<span id="<%=name%>_name">学院名：<%=d.getName()%></span>
				</div>
				<div class="con_input" style="text-align:left;">
					<span>楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</span><span
						id="<%=name%>_building"><%=d.getBuilding()%></span>
				</div>
				<div class="con_input" style="text-align:left;">
					<span>描&nbsp;&nbsp;&nbsp;&nbsp;述：</span><span
						id="<%=name%>_descripe"><%=d.getDescripe()%></span>
				</div>
                <button  name="btn" type="button" class="submit-btn" onclick="d_edit(this);return false;" value="<%=name%>" style="margin:0 auto;">
                		更改信息</button>
			</fieldset>
		</form>
	</div>
	<%
	}
	%>
	<br/><br/>
	<div class="con_edit" id="e1" style="display: none;">
		<form action="MDServlet" method="post" target="Mframe">
			<fieldset>
				<legend>
					编辑<span id="dname"></span>信息
				</legend>
				<input type="hidden" placeholder="学院名" name="e_dname" id="e_name">
				<div class="con_input">
					<span>楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</span> <input type="text"
						placeholder="楼宇" name="e_dbuilding" id="e_building">
				</div>
				<div class="con_input">
					<span>描&nbsp;&nbsp;&nbsp;&nbsp;述：</span>
					<textarea placeholder="描述" name="e_ddescripe" id="e_descripe" cols="30" rows="8"></textarea>
				</div>
				<button name="btn" class="submit-btn"
					onclick="document.form.submit()" value="edit">更&nbsp;&nbsp;&nbsp;&nbsp;改</button>
				<button class="submit-can" onclick="d_edit_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
			</fieldset>
		</form>
	</div>
	<button class="submit-btn" style="text-align: center;"
		onclick="d_new();return false;">开设学院</button>
	<div class="con_edit" id="e2" style="display: none;">
		<form action="MDServlet" method="post" target="Mframe">
			<fieldset>
				<legend>开设学院</legend>
				<div class="con_input">
					<span>学院名：</span> <input type="text" placeholder="学院名"
						name="e_dname">
				</div>
				<div class="con_input">
					<span>楼&nbsp;&nbsp;&nbsp;&nbsp;宇：</span> <input type="text"
						placeholder="楼宇" name="e_dbuilding">
				</div>
				<div class="con_input">
					<span>描&nbsp;&nbsp;&nbsp;&nbsp;述：</span>
					<textarea placeholder="描述" name="e_ddescripe" cols="30" rows="8"></textarea>
				</div>
				<button name="btn" class="submit-btn"
					onclick="document.form.submit()" value="new">确&nbsp;&nbsp;&nbsp;&nbsp;认</button>
				<button class="submit-can" onclick="d_new_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
			</fieldset>
		</form>
	</div>
</body>
<script type="text/javascript">
	var error =
<%=request.getAttribute("msg")%>
	if (error == 0)
		alert("执行成功！");
	else if (error == -1)
		alert("修改失败！");
	else if (error == -2)
		alert("添加失败！");
</script>
</html>