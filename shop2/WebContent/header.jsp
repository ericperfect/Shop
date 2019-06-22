<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<script type="text/javascript">
					
					$(function(){
						var content = "";
						$.post(
							"${pageContext.request.contextPath}/categoryList",
							function(data){
								for(var i=0;i<data.length;i++){
									content+="<li><a href='${pageContext.request.contextPath}/productListByCid?cid="+data[i].cid+"'>"+data[i].cname+"</a></li>";
								}
								$("#categoryUL").html(content);
							},
							"json"
						);
					});
					
					
	
</script>
<!DOCTYPE html>

<!-- 登录 注册 购物车... -->
<div class="container-fluid">
	<div class="col-md-4">
		<img src="img/123.png" width="300" height="80"/>
	</div>
	<div class="col-md-5">
		<img src="img/header.png" />
	</div>
	<div class="col-md-3" style="padding-top:20px">
		<ol class="list-inline">
			<c:if test="${empty user }">
				<li><a href="login.jsp">登录</a></li>
				<li><a href="register.jsp">注册</a></li>
			</c:if>
			
			<c:if test="${!empty user }">
				<li><a href="javascript:;"></a>${user.username },您好</li>
				<li><a href="${pageContext.request.contextPath }/quit">退出</a></li>
			</c:if>
			<li><a href="cart.jsp">购物车</a></li>
			<li><a href="${pageContext.request.contextPath}/myOrders">我的订单</a></li>
			<li><a href="${pageContext.request.contextPath}/admin/index.jsp">商城后台</a></li>
		</ol>
	</div>
</div>

<!-- 导航条 -->
<div class="container-fluid">
	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<!-- Brand and toggle get grouped for better mobile display -->
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="${pageContext.request.contextPath}/index">首页</a>
			</div>

			<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
				<ul class="nav navbar-nav" id="categoryUL">
					<%-- 
						<c:forEach items="${categoryList }" var="category">
							<li><a href="#">${category.cname }</a></li>
						</c:forEach> 
					--%>
				</ul>
				
				<form class="navbar-form navbar-right" action="${pageContext.request.contextPath}/ProductByPname" role="search">
					<div class="form-group" style="position:relative">
						<input id="hiddenPname" type="hidden" name="pname">
						<input id="search" type="text" class="form-control" placeholder="Search" onkeyup="searchWord(this)">
						<div id="showDiv" style="display:none; position:absolute;z-index:1000;background:#fff; width:179px;border:1px solid #ccc;">
							
						</div>
					</div>
					<button type="submit" class="btn btn-default">搜索</button>
				</form>
				<script type="text/javascript">
				
					function overFn(obj){
						$(obj).css("background","#DBEAF9");
					}
					function outFn(obj){
						$(obj).css("background","#fff");
					}
					
					function clickFn(obj){
						$("#hiddenPname").val($(obj).html());
						$("#search").val($(obj).html());
						$("#showDiv").css("display","none");
					}
					
					
					
				
					function searchWord(obj){
						//1、获得输入框的输入的内容
						var word = $(obj).val();
						//2、根据输入框的内容去数据库中模糊查询---List<Product>
						var content = "";
						$.post(
							"${pageContext.request.contextPath}/ProductFindByWord",
							{"word":word},
							function(data){
								//3、将返回的商品的名称 现在showDiv中
								//[{"pid":"1","pname":"小米 4c 官方版","market_price":8999.0,"shop_price":8999.0,"pimage":"products/1/c_0033.jpg","pdate":"2016-08-14","is_hot":1,"pdesc":"小米 4c 标准版 全网通 白色 移动联通电信4G手机 双卡双待 官方好好","pflag":0,"cid":"1"}]
								
								if(data.length>0){
									for(var i=0;i<data.length;i++){
										var pid = data[i].pid;
										content+="<div id='mydiv' pid="+pid+" style='padding:5px;cursor:pointer' onclick='clickFn(this)' onmouseover='overFn(this)' onmouseout='outFn(this)'>"+data[i].pname+"</div>";
									}
									$("#showDiv").html(content);
									$("#showDiv").css("display","block");
								}
							},
							"json"
						);
						
					}
				</script>
			</div>
		</div>
	</nav>
</div>