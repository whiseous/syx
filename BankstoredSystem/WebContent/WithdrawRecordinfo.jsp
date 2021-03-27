<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
	<script type="text/javascript">
	var conti='<%=request.getParameter("k")%>';
	if(conti=='1')
	{
		alert("取款成功！。");
	}
	else if(conti=='-2')
	{
		alert("可能的错误！");
	}
	
	function confirm()
	{
		var a=alert("已打印！");
		location.href="bteller.action?id=${bteller.eid}&uid=${u.uid}&opttype=withdraw"; 
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
				<form action="bteller.action?id=${bteller.eid}" method="post" name="upsewithdraw" id="upsewithdraw">
				<input type=hidden name="opttype" value="viewbenefit">
					<br></br>
					<font size="4">您好！此为储户</font>
					<font size="4" color=#01814A >${u.username}</font>
					<font size="4">的${d.depositype}取款记录</font>
					<br></br>
					<div id="mainboard">
					<br></br>
						<div id="board">
							<table >
								<thead>
									<tr>
										<th colspan="6">取款记录信息</th>
									</tr>
								</thead>
								<tbody>
										
										<tr class="info">
								         <td><b>姓名</b></td>
								         <td><input class="itext" type="text" maxlength="10"  value="${u.username}" name="name" readonly="1"></td>
								         <td><b>编号</b></td>
								         <td><input class="itext" type="text" maxlength="10"  value="${u.uid}" name="uid" readonly="1"></td>
								         
								      	</tr>
								       	
								       	<tr>
								       	 <td><b>取款金额</b></td>
								         <td><input class="itext" type="text" maxlength="10"  value="${b.withdraw}" name="amount" readonly></td>
								         <td><b>取款利息</b></td>
								         <td><input class="itext" type="text" maxlength="10"  value="${b.wdbenefit}" name="benefit" readonly></td>
								       	</tr>
								       
								      	<tr class="info">
								         <td><b>取款日期</b></td>
								         <td><input class="itext" type="text" maxlength="10"  value="${b.withdrawdate}" name="utime" readonly></td>
								      	</tr>
								      	
								      	
								</tbody>
							</table>
							<button class="input" onclick="confirm();">打印</button>
							<button type="button" onclick="javascript:window.location.href='bteller.action?id=${bteller.eid}&opttype=SERVICE_PRINT_WITHDRAW' " onsubmit='return false';>返回</button>
							<font style="color:grey"></font>
						</div>	
					</div>	
					</form>		
					
				</div>
			</div>
		</div>
		
</body>
</html>