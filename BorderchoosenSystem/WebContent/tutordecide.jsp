<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("k")%>';
	if(conti=='0')
	{
		alert("选择学生失败，请确认学生是否已经选满！。");
	}
	else if(conti=='-2')
	{
		alert("已经选过该学生了！");
	}else if(conti=='5')
	{
		alert("未知连接错误！");
	}
	
	
	function confirm()
	{
		var a=alert("已选择学生，可在 查看学生 界面查看。");
		location.href="Teacher.action?id=${tea.tid}&sid=${s.sid}&opttype=updateteachoice"; 
	}
	</script>
	<meta charset="utf-8">
		<title>研究生导师自选系统（导师端）</title>
			<style type="text/css">
				.cls1 {background-color: #F0F0F0;}
				tr:hover {color: aqua;}
				#wrapper {width: 100%;margin: 0 auto;}
				#header {float:right;width: 100%;height: 40px;background-color:#0080FF;text-align:right;}
				#container {width: 100%;height:1000px;margin-top: 5px;}
				#left {float:left;width: 4%;height: 95%;background-color:#ECF5FF}
				#leftbar {float:left;width: 15%;height: 95%;background-color:#ECF5FF}
				#main {float:left;text-align:center;width: 80%;height: 95%;background-color:#FFFFFF}
				#mainboard {text-align:left;margin:0 auto;width: 95%;height: 80%;background-color:#F0F0F0}
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
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！： ${tea.tid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>返回首页</a><br></br>
				<a href='Teacher.action?id=${tea.tid}&opttype=selectshowgrad'>选择学生</a><br></br>
				<a href='Teacher.action?id=${tea.tid}&opttype=listgrad'><b>查看学生</b></a><br></br>
				<a href='Teacher.action?id=${tea.tid}&opttype=confirmresult'>确定结果</a><br></br>
				<a href='Teacher.action?id=${tea.tid}&opttype=changepwd'>修改密码</a><br></br>
				<a href='LoginFrm.html'>安全退出</a>
				</div>
				<div id="main">
					<br></br>
					<font size="4">您好！</font>
					<font size="4" color=#01814A >${cname}学院</font>
					<font size="4">：${tea.tname}导师，以下是学生信息</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th colspan="6">学生基本信息</th>
									</tr>
								</thead>
								<tbody>
										
										<tr class="info">
								         <td><b>姓名</b></td>
								         <td>${s.sname}</td>
								         <td><b>性别</b></td>
								         <td>${s.sgender}</td>
								      	</tr>
								       
								      	<tr>
								         <td><b>专业</b></td>
								         <td>${s.spro}</td>
								         <td><b>学生编号</b></td>
								         <td>${s.sid}</td>
								      	</tr>
								      	
								      	<tr class="info">
								         <td><b>成绩</b></td>
								         <td>${s.sscore}</td>
								       	</tr>

								</tbody>
							</table>
							<button class="input" onclick="confirm();">选择学生</button>
							<button onclick="javascript:window.location.href='Mainteasee.jsp'">返回</button>
							
						</div>	
					</div>			
				</div>
			</div>
		</div>
		
</body>
</html>