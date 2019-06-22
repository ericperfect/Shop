<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>会员登录</title>
<link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
<script src="js/jquery-1.11.3.min.js" type="text/javascript"></script>
<script src="js/jquery.validate.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<!-- 引入自定义css文件 style.css -->
<link rel="stylesheet" href="css/style.css" type="text/css" />

<style>
body {
	margin-top: 20px;
	margin: 0 auto;
}

.carousel-inner .item img {
	width: 100%;
	height: 300px;
}

.container .row div {
	/* position:relative;
				 float:left; */
	
}

font {
	color: #666;
	font-size: 22px;
	font-weight: normal;
	padding-right: 17px;
}
</style>

<script type="text/javascript">
$(function(){
	$("#myform").validate({
		rules:{
			"username":{
				"required":true,
				"checkUsername":true
			},
	
			"passowrd":{
				"required":true,
				"rangelength":[6,12]
			}
		},
	
		messages:{
			"username":{
				"required":"用户不能为空",
			},
			"passowrd":{
				"required":"密码不能为空",
			}
		}
	});
});

$(function(){
	$("#myform").validate({
		rules:{
			"username":{
				"required":true,
			},
			"password":{
				"required":true
			}
		}
	});
});
</script>
</head>
<body>

	<!-- 引入header.jsp -->
	<jsp:include page="/header.jsp"></jsp:include>


	<div class="container"
		style="width: 100%; height: 540px; background: #FF2C4C url('images/loginbg.jpg') no-repeat;">
		<div class="row">
			<div class="col-md-7">
				<!--<img src="./image/login.jpg" width="500" height="330" alt="会员登录" title="会员登录">-->
			</div>

			<div class="col-md-5">
				<div
					style="width: 440px; border: 1px solid #E7E7E7; padding: 20px 0 20px 30px; border-radius: 5px; margin-top: 60px; background: #fff;">
					<font>会员登录</font>USER LOGIN
					<div>&nbsp;</div>
					<form id="myform" class="form-horizontal" action="${pageContext.request.contextPath }/login" method="post">
						<div class="form-group">
							<label for="username" class="col-sm-2 control-label">用户名</label>
							<div class="col-sm-6">
								<input type="text" class="form-control" id="username" name="username"
									placeholder="请输入用户名">
							</div>
						</div>
						<div class="form-group">
							<label for="inputPassword3" class="col-sm-2 control-label">密码</label>
							<div class="col-sm-6">
								<input id="password" type="password" class="form-control" id="inputPassword3" name="password"
									placeholder="请输入密码">
							</div>
						</div>
						<div class="form-group">
							<label for="" class="col-sm-2 control-label">验证码</label>
							<!-- 写验证码 -->
							<div class="col-sm-4">
								<input type="text" class="form-control" id="code_input" value="" placeholder="请输入验证码">
							</div>
							<div  class="col-sm-4">
								<div id="v_container" style="width: 190px;height: 39px;"></div>

	<script src="js/gVerify.js"></script>
	
							</div>
						</div>
						<div class="form-group">
							<div class="col-sm-offset-2 col-sm-10">
								<input type="submit" width="100" id="mysubmit" value="登录" name="submit"
									style="background: url('./images/login.gif') no-repeat scroll 0 0 rgba(0, 0, 0, 0); height: 35px; width: 100px; color: white;">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>

	

</body>
<script>
		var verifyCode = new GVerify("v_container");

		document.getElementById("mysubmit").onclick = function(){
			var value = document.getElementById("code_input").value;
			var res = verifyCode.validate(value);
			if(res){
				
			}else{
				alert("验证码错误");
				location.reload();
				return false;
			}
		}
	</script>
</html>