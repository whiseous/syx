<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	function confirm()
	{
		location.href="Student.action?id=${stu.sid}&opttype=reupchoice"; 
	}
	</script>
	<meta charset="utf-8">
		<title>研究生导师自选系统（学生端）</title>
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
					margin:10px 50px 5px 50%;
				}
			</style>
	</head>
<body>
		<div id="wrapper">
			<div id="header">
				<font size="3">欢迎您！： ${stu.sid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>返回首页</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showtutors'>导师信息</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=selecttutors'><b>选择导师</b></a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=showresult'>选择结果</a><br></br>
				<a href='Student.action?id=${stu.sid}&opttype=changepwd'>修改密码</a><br></br>
				<a href='LoginFrm.html'>安全退出</a>
				</div>
				<div id="main">
				<form action="Student.action?id=${stu.sid}" method="post" name="coninchoice" id="coninchoice">
				<input type=hidden name="opttype" value="reupchoice">
					<br></br>
				<font size="4"> ${stu.sname},您选择的导师如下（由上至下依次为第一志愿、第二志愿、第三志愿）</font>
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th>导师编号</th>
										<th>导师姓名</th>
										<th>第一志愿</th>
										<th>第二志愿</th>
										<th>第三志愿</th>
										<th>查看详细</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="t" items="${ctlist}" varStatus="status">
								       <tr bgcolor="${status.index % 2 == 1 ? 'F0F0F0' : 'FFFFFF'}">
								         <td>${t.tid}</td>
								         <td>${t.tname}</td>
								         <td><input name="tendest" value="${t.tid}" type="radio" ></td>
								         <td><input name="tender" value="${t.tid}" type="radio"></td>
								         <td><input name="tend" value="${t.tid}" type="radio"></td>
								         <td>
								            <a href='Student.action?id=${stu.sid}&tid=${t.tid}&opttype=viewtutor'>查看详细</a>
								            <a href='Student.action?id=${stu.sid}&tid=${t.tid}&opttype=deletetutor'>不选该导师</a>
								         </td>
								       </tr>
								    </c:forEach>
								</tbody>
							</table>
								<input class="input" type="submit" value="确定" onclick="" style="width:80px;height:30px" ></input>
						</div>	
					</div>		
					</form>	
				</div>
			</div>
		</div>
		
</body>
</html>