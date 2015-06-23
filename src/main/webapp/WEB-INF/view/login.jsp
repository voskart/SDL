<%--
  Created by IntelliJ IDEA.
  User: voskart
  Date: 11.06.15
  Time: 23:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'>
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-theme.min.css") %>'>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>SDL Login-Page</title>
</head>
<body>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in to continue to SDL</h1>
            <div class="account-wall">
                <form action="login" method="POST">
                    <input type="text" name="username" class="form-control" placeholder="username" required autofocus>
                    <input type="password" name="password" class="form-control" placeholder="password" required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign in</button>
                </form>
            </div>
            <a href="/registration" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>
</body>
</html>
