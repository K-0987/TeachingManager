<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="dao.ManagerDao"%>
<%@ page import="bean.*" %>
<%@ page import="java.util.List" %>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <link rel="stylesheet" href="./xgxt_login.css">
        <script src="https://kit.fontawesome.com/1b3f57b6d8.js"></script>
        <title>所有用户</title>
    </head>

    <body>
    <%request.setCharacterEncoding("UTF-8");
      ManagerDao mD = new ManagerDao();
      List<StudentBean> sList = mD.selectAllStudent();
      List<InstructorBean> iList = mD.selectAllInstructor();
      List<AccountBean> aList = mD.selectAllManager();
     %>
     <script type="text/javascript">
	 	var edit_s_id;
		var edit_i_id;
		var edit_m_id;
     	function sub_confirm(){
     		if(!confirm("你确定要删除该条记录吗？"))
     			return false;
     	}
		function s_edit(ebtn){
			edit_s_id = ebtn.value;
			window.location.hash = "#e1";
			document.getElementById("e1").style.display = "block";
			document.getElementById("esid").value = edit_s_id;
			document.getElementById("sid").innerHTML = edit_s_id;
		}
		function s_edit_cancel(){
			document.getElementById("e1").style.display = "none";
		}
		function i_edit(ebtn){
			edit_i_id = ebtn.value;
			window.location.hash = "#e2";
			document.getElementById("e2").style.display = "block";
			document.getElementById("eiid").value = edit_i_id;
			document.getElementById("iid").innerHTML = edit_i_id;
		}
		function i_edit_cancel(){
			document.getElementById("e2").style.display = "none";
		}
		function m_edit(ebtn){
			edit_m_id = ebtn.value;
			window.location.hash = "#e3";
			document.getElementById("e3").style.display = "block";
			document.getElementById("emid").value = edit_m_id;
			document.getElementById("mid").innerHTML = edit_m_id;
		}
		function m_edit_cancel(){
			document.getElementById("e3").style.display = "none";
		}      	
     </script>
        <div class="con_table">
        	<form action="MSServlet" method="post" target="Mframe">
        	<fieldset><legend>学生</legend>
            <table id="users">
                <tr>
                    <th>学号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>所属学院</th>
                    <th>总学分</th>
                    <th>操作</th>
                </tr>
                <%if(sList.isEmpty()){%>
                	<tr>
                    	<td colspan="6">未找到记录</td>
                	</tr>
                <%}else{
                	for(StudentBean s:sList){%>
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
                				<button title="编辑" name="btn" class="btn_img" onclick="s_edit(this);return false;" value="<%=s.getId()%>">
                					<i class="fas fa-edit fa-lg"></i></button>
                				<button title="删除" name="btn" class="btn_img" onclick="return sub_confirm()" value="D<%=s.getId()%>">
                					<i class="fas fa-trash-alt fa-lg"></i></button>
                				<button title="课程" name="btn" class="btn_img" onclick="document.form.submit()" value="C<%=s.getId()%>">
                					<i class="fas fa-clipboard-list fa-lg"></i></button>
                			</td>               			
                		</tr>
                <%}} %>
                <tr>
                	<td>-</td>
                	<td><input type="text" name="s_name"></td>
                	<td><select name="s_sex">
                		<option value="M">男</option>
                		<option value="F">女</option>
                		</select></td>
                	<td><input type="text" name="s_dept_name"></td>
                	<td>-</td>
                	<td>
                		<button title="添加" name="btn" class="btn_img" onclick="document.form.submit()" value="add">
                			<i class="fas fa-plus-square fa-lg"></i></button>
                	</td>
                </tr>
            </table>
            </fieldset></form>
        </div>

		<div class="con_edit" id="e1" style="display: none;">
		<form action="MSServlet" method="post" target="Mframe">
			<fieldset>
				<legend>编辑学生<span id="sid"></span>信息</legend>
				<div class="con_input">
					<input type="hidden" name="e_sid" id="esid" value="">
					<span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
					<input type="text" placeholder="姓名" name="e_sname">
				</div>
				<div class="con_input">
					<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<input type="password" placeholder="密码" name="e_spword">
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：</span>
					<input type="text" placeholder="学院" name="e_sdept">
				</div>
				<div class="con_input">
					<span>总学分：</span>
					<input type="text" placeholder="总学分" name="e_scred">
				</div>
				<div class="con_input">
					<span>性&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
					<select name="e_ssex">
						<option value="" selected>不修改</option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>
				</div>
				<button name="btn" class="submit-btn" onclick="document.form.submit()" value="edit">更&nbsp;&nbsp;&nbsp;&nbsp;改</button>
				<button class="submit-can" onclick="s_edit_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
			</fieldset>
		</form>
		</div>

        <div class="con_table">
        <form action="MIServlet" method="post" target="Mframe">
		<fieldset><legend>教师</legend>
            <table id="users">
                <tr>
                    <th>工号</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>所属学院</th>
                    <th>职位</th>
                    <th>操作</th>
                </tr>
                <%if(iList.isEmpty()){%>
                	<tr>
                    	<td colspan="6">未找到记录</td>
                	</tr>
                <%}else{
                	for(InstructorBean i:iList){%>
                		<tr>
                			<td><%=i.getId()%></td>
                			<td><%=i.getName()%></td>
                			<td><%if(i.getSex().equals("F")){%>
                				女
                				<%}else{%>
                				男<%}%></td>
                			<td><%=i.getDept_name() %></td>
                			<td><%String p = i.getPosition();
                				if(p.equals("A")){%>教授
                				<%}else if(p.equals("B")){%>副教授
                				<%}else{%>讲师
                				<%}%></td>
                			<td>
                				<button title="编辑" name="btn" class="btn_img" onclick="i_edit(this);return false;" value="<%=i.getId()%>">
                					<i class="fas fa-edit fa-lg"></i></button>
                				<button title="删除" name="btn" class="btn_img" onclick="return sub_confirm()" value="D<%=i.getId()%>">
                					<i class="fas fa-trash-alt fa-lg"></i></button>
                				<button title="课程" name="btn" class="btn_img" onclick="document.form.submit()" value="C<%=i.getId()%>">
                					<i class="fas fa-clipboard-list fa-lg"></i></button>
                			</td>
                		</tr>
                <%}} %>
                <tr>
                	<td>-</td>
                	<td><input type="text" name="i_name"></td>
                	<td><select name="i_sex">
                		<option value="M">男</option>
                		<option value="F">女</option>
                		</select></td>
                	<td><input type="text" name="i_dept_name"></td>
                	<td><select name="i_position">
                		<option value="C">讲师</option>
                		<option value="B">副教授</option>
                		<option value="A">教授</option>
                		</select></td>
                	<td>
                		<button title="添加" name="btn" class="btn_img" onclick="document.form.submit()" value="add">
                			<i class="fas fa-plus-square fa-lg"></i></button>
                	</td>
                </tr>
            </table>
            </fieldset></form>
        </div>
        
        <div class="con_edit" id="e2" style="display: none;">
		<form action="MIServlet" method="post" target="Mframe">
			<fieldset>
				<legend>编辑教师<span id="iid"></span>信息</legend>
				<div class="con_input">
					<input type="hidden" name="e_iid" id="eiid" value="">
					<span>姓&nbsp;&nbsp;&nbsp;&nbsp;名：</span>
					<input type="text" placeholder="姓名" name="e_iname">
				</div>
				<div class="con_input">
					<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<input type="password" placeholder="密码" name="e_ipword">
				</div>
				<div class="con_input">
					<span>学&nbsp;&nbsp;&nbsp;&nbsp;院：</span>
					<input type="text" placeholder="学院" name="e_idept">
				</div>
				<div class="con_input">
					<span>职&nbsp;&nbsp;&nbsp;&nbsp;位：</span>
					<select name="e_iposition">
						<option value="" selected>不修改</option>
						<option value="C">讲师</option>
						<option value="B">副教授</option>
						<option value="A">教授</option>
					</select>
				</div>
				<div class="con_input">
					<span>性&nbsp;&nbsp;&nbsp;&nbsp;别：</span>
					<select name="e_isex">
						<option value="" selected>不修改</option>
						<option value="M">男</option>
						<option value="F">女</option>
					</select>
				</div>
				<button name="btn" class="submit-btn" onclick="document.form.submit()" value="edit">更&nbsp;&nbsp;&nbsp;&nbsp;改</button>
				<button class="submit-can" onclick="i_edit_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
			</fieldset>
		</form>
		</div>
        
        <div class="con_table">
        <form action="MMServlet" method="post" target="Mframe">
		<fieldset><legend>管理员</legend>
            <table id="users">
                <tr>
                    <th>账户名</th>
                    <th>密码</th>
                    <th>操作</th>
                </tr>
                <%if(aList.isEmpty()){%>
                	<tr>
                    	<td colspan="6">未找到记录</td>
                	</tr>
                <%}else{
                	for(AccountBean a:aList){%>
                		<tr>
                			<td><%=a.getUser_id()%></td>
                			<td>******</td>
                			<td>
                			    <button title="编辑" name="btn" class="btn_img" onclick="m_edit(this);return false;" value="<%=a.getUser_id()%>">
                					<i class="fas fa-edit fa-lg"></i></button>
                				<button title="删除" name="btn" class="btn_img" onclick="return sub_confirm()" value="D<%=a.getUser_id()%>">
                					<i class="fas fa-trash-alt fa-lg"></i></button>	
                			</td>
                		</tr>
                <%}} %>
                <tr>
                	<td><input type="text" name="m_id"></td>
                	<td><input type="password" name="m_password"></td>
                	<td><button title="添加" name="btn" class="btn_img" onclick="document.form.submit()" value="add">
                		<i class="fas fa-plus-square fa-lg"></i></button></td>
                </tr>
            </table>
            </fieldset></form>
        </div>
     
        <div class="con_edit" id="e3" style="display: none;">
		<form action="MMServlet" method="post" target="Mframe">
			<fieldset>
				<legend>编辑管理员<span id="mid"></span>信息</legend>
				<div class="con_input">
					<input type="hidden" name="e_mid" id="emid" value="">
					<span>密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
					<input type="password" placeholder="密码" name="e_mpword">
				</div>
				<button name="btn" class="submit-btn" onclick="document.form.submit()" value="edit">更&nbsp;&nbsp;&nbsp;&nbsp;改</button>
				<button class="submit-can" onclick="m_edit_cancel();return false;">取&nbsp;&nbsp;&nbsp;&nbsp;消</button>
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
			alert("删除失败！");
	</script>
	</html>