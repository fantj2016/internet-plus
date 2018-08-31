<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>晋软杯</title>
</head>
<body class="login-bg">

        <form method="post" class="layui-form" action="/changePasswd" >
            <input type="hidden" name="valid" value="${valid}"/>
            <input name="passwd" placeholder="密码"  type="password" lay-verify="required" class="layui-input" >
            <hr class="hr15">
            <input name="rePasswd" lay-verify="required" placeholder="确认密码"  type="password" class="layui-input">
            <hr class="hr15">
            <input type="submit"  value="提交">
        </form>

</body>
</html>


