<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">

	function confirm()
	{
		location.href="bteller.action?id=${bteller.eid}&opttype=inserttutor"; //暂无
	}
	</script>
	<meta charset="utf-8">
		<title>银行储蓄系统（业务员端）</title>
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
				<font size="3">欢迎您！：${bteller.eid}</font>
			</div>
			<div id="container">
				<div id="left"></div>
				<div id="leftbar">
				<br></br>
				<a href='LoginFrm.html'>●返回首页</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●存款业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_DEPOSIT_LIVE'>1.活期存款</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_DEPOSIT_SOLID'>2.定期存款</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●取款业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_WITHDRAW'>1.取款</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●查询打印业务</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_PRINT_WITHDRAW'><b>1.用户取款记录</b></a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=SERVICE_PRINT_HISTORY'>2.用户历史记录</a><br></br>
				<a href='Loginbstore.action?account=${bteller.account}&pwd=${bteller.password}&identity=bankteller'>●系统管理</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=changepwd'>1.更改密码</a><br></br>
				&nbsp;&nbsp;&nbsp;&nbsp;<a href='bteller.action?id=${bteller.eid}&opttype=viewlogs'>2.查看日志</a><br></br>
				<a href='LoginFrm.html'>●安全退出</a>
				</div>
				
				<div id="main">
				<form action="bteller.action?id=${bteller.eid}" method="post" name="selive" id="selive">
				<input type=hidden name="opttype" value="reupprintwithdraw">
					<br></br>
				<font size="4">欢迎您！业务员：${bteller.eid}，请输入需查询的相关取款信息</font><br></br>
				<input type="text" class="itext" name="searchtext" placeholder="请输入相关数据">
				<input type="submit" value="确定">
				<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th>储户ID</th>
										<th>储户姓名</th>
										<th>取款金额</th>
										<th>取款日期</th>
										<th>查看详细</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="s" items="${slist}" varStatus="status">
								       <tr bgcolor="${status.index % 2 == 1 ? 'FFFFFF' : 'F0F0F0'}">
								         <td>${s.user.uid}</td>
								         <td>${s.user.username}</td>
								         <td>${s.brecord.withdraw}</td>
								         <td>${s.brecord.withdrawdate}</td>
								         <td>
								            <a href='bteller.action?id=${bteller.eid}&uid=${s.user.uid}&utime=${s.brecord.withdrawdate}&opttype=viewbenefit'>查看详细</a>
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