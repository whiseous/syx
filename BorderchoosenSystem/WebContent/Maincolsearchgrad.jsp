<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("k")%>';
	if(conti=='1')
	{
		alert("添加成功！。");
	}
	
	function confirm()
	{
		location.href="Coladmin.action?id=${cadmin.tid}&opttype=inserttutor"; 
	}
	</script>
	<meta charset="utf-8">
		<title>研究生导师自选系统（院级管理端）</title>
			<style type="text/css">
				.cls1 {background-color: #F0F0F0;}
				tr:hover {color: aqua;}
				#wrapper {width: 100%;margin: 0 auto;}
				#header {float:right;width: 100%;height: 40px;background-color:#0080FF;text-align:right;}
				#container {width: 100%;height:1000px;margin-top: 5px;}
				#left {float:left;width: 4%;height: 95%;background-color:#ECF5FF}
				#leftbar {float:left;width: 15%;height: 95%;background-color:#ECF5FF}
				#main {float:left;text-align:center;width: 80%;height: 95%;background-color:#FFFFFF}
				#mainboard {text-align:left;margin:0 auto;width: 80%;height: 80%;background-color:#F0F0F0}
				#board {text-align:left;margin:0 auto;width: 95%;height: 90%;background-color:#FFFFFF}
				
				a{
					text-decoration:none;
				}
				a:visited{
					color:#2828FF;
				}
				table,th,td{
					border:1px lightgray solid;
					border-collapse:collapse;
				}
				table{
					width:100%;
					margin:0px auto;
				}
				th,td{
					padding:10px;
				}
				th{
					background-color:#FCFCFC;
					color:black;
				}
				.info{
					background-color:#F0F0F0;
					color:black;
				}
				.input{
					margin:10px 50px 5px 40%;
					
				}
				
				.itext{
					height: 30px;
					width: 300px;
					
				}
				.ifieldtext{
					height: 100px;
					width: 880px;
					
				}
				
				
				
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！：${cadmin.tid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>●返回首页</a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●导师信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=tutorin'>1.导师信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=selectshowtutor'>2.导师信息维护</a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●研究生信息管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=graduin'>1.研究生信息录入</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=selectshowgrad'><b>2.研究生信息维护</b></a><br></br>
				<a href='Login.action?id=${cadmin.tid}&pwd=${cadmin.tpwd}&identity=coladmin'>●系统管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='Coladmin.action?id=${cadmin.tid}&opttype=changepwd'>1.更改密码</a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				
				<div id="main">
				<form action="Coladmin.action?id=${cadmin.tid}" method="post" name="cadupgrad" id="cadupgrad">
				<input type=hidden name="opttype" value="reupgrad">
					<br></br>
				<font size="4">欢迎您！管理员：${cadmin.tname}，请输入需查询的相关学生信息</font><br></br>
				<input type="text" class="itext" name="searchstutext" placeholder="请输入相关数据">
				<input type="submit" value="确定">
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th>学生编号</th>
										<th>学生姓名</th>
										<th>学生性别</th>
										<th>学生专业</th>
										<th>查看详细</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="s" items="${slist}" varStatus="status">
								       <tr bgcolor="${status.index % 2 == 1 ? 'FFFFFF' : 'F0F0F0'}">
								         <td>${s.sid}</td>
								         <td>${s.sname}</td>
								         <td>${s.sgender}</td>
								         <td>${s.spro}</td>
								         <td>
								         	<a href='Coladmin.action?id=${cadmin.tid}&sid=${s.sid}&opttype=delegrad'>删除</a>
								            <a href='Coladmin.action?id=${cadmin.tid}&sid=${s.sid}&opttype=viewgrad'>查看详细</a>
								         </td>
								       </tr>
								    </c:forEach>
								</tbody>
							</table>
						</div>	
					</div>		
					</form>		
				</div>
			</div>
		</div>
		
</body>
</html>