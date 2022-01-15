<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="dao.GeneralDao"%>
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
	<script>
		var edit_c_id;
     	function sub_confirm(){
     		if(!confirm("你确定要删除该条记录吗？"))
     			return false;
     	}
     	function sel_confirm(){
     		if(!confirm("你确定要选课吗？"))
     			return false;
     	}
		function c_edit(ebtn){
			edit_c_id = ebtn.value;
			window.location.hash = "#e1";
			document.getElementById("e1").style.display = "block";
			document.getElementById("ecid").value = edit_c_id;
			document.getElementById("cid").innerHTML = edit_c_id;
		}
		function c_edit_cancel(){
			document.getElementById("e1").style.display = "none";
		}
	</script>
	<div class="con_search">
	<form action="MCServlet" method="post" target="Mframe">
		<input type="text" name="keys" placeholder="请输入关键词搜索课程，支持课程名和教师名搜索，关键词以空格隔开..">
		<button name="btn" value="search" onclick="document.form.submit()">提&nbsp;&nbsp;&nbsp;&nbsp;交</button>
	</form>
	</div>

	<hr />

	<div class="con_table">
		<%request.setCharacterEncoding("UTF-8");
		String s_id = (String)session.getAttribute("s_id");
		GeneralDao gD = new GeneralDao();
		String[] keys = (String[])request.getAttribute("keys");
		List<CourseBean> cList = gD.selectAllCourse(keys, s_id);
		%>
		<h2>
			共搜索到<%=cList.size()%>条记录
		</h2>
		<form action="MCServlet" method="post" target="Mframe">
			<table id="users">
				<tr>
					<th>编号</th>
					<th>课程名</th>
					<th>导师</th>
					<th>所属学院</th>
					<th>课时</th>
					<th>学分</th>
					<th>操作</th>
				</tr>
				<%
					if (cList.isEmpty()) {
				%>
				<tr>
					<td colspan="7">未找到记录</td>
				</tr>
				<%
					} else
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
						<%if (s_id == null) {%>
					    <button title="编辑" name="btn" class="btn_img" onclick="c_edit(this);return false;" value="<%=c.getId()%>">
                			<i class="fas fa-edit fa-lg"></i></button>
						<button title="删除" name="btn" class="btn_img" onclick="return sub_confirm()" value="D<%=c.getId()%>">
							<i class="fas fa-trash-alt fa-lg"></i>
						</button>
						<%} else {%>
						<button title="选课" name="btn" class="btn_img" onclick="return sel_confirm()" value="S<%=c.getId()%>">
                			<i class="fas fa-check fa-lg"></i></button>
                		<input type="hidden" name="s_id" value=<%=s_id%>>
						<%} %>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
		<br/><br/>
		<div class="con_edit" id="e1" style="display: none;">
			<form action="MCServlet" method="post" target="Mframe">
				<fieldset>
					<legend>
						编辑课程<span id="cid"></span>信息
					</legend>
					<div class="con_input">
						<input type="hidden" name="e_cid" id="ecid" value="">
						<span>课程名：</span>
						<input type="text" placeholder="课程名" name="e_ctitle">
					</div>
					<div class="con_input">
						<span>教&nbsp;&nbsp;&nbsp;&nbsp;师：</span>
						<input type="text"placeholder="若要更换教师请输入教师编号.." name="e_cinsid">
					</div>
					<div class="con_input">
						<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：</span>
						<input type="text"placeholder="学院" name="e_cdept">
					</div>
					<div class="con_input">
						<span>课&nbsp;&nbsp;&nbsp;&nbsp;时：</span>
						<input type="text" placeholder="课时" name="e_chour">
					</div>
					<div class="con_input">
						<span>学&nbsp;&nbsp;&nbsp;&nbsp;分：</span>
						<input type="text" placeholder="学分" name="e_ccred">
					</div>
					<button name="btn" class="submit-btn"
						onclick="document.form.submit()" value="edit">更&nbsp;&nbsp;&nbsp;&nbsp;改</button>
					<button class="submit-can" onclick="c_edit_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
				</fieldset>
			</form>
		</div>

	</div>
</body>
    <script type="text/javascript">
		var error='<%=request.getAttribute("msg")%>';
		if(error==0)
			alert("执行成功！");
		else if(error==-1)
			alert("输入数据有误！");
		else if(error==-2)
			alert("删除失败！");
		else if(error==-3)
			alert("选课失败！")
	</script>
</html>