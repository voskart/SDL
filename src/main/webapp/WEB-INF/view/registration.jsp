<%--
  Created by IntelliJ IDEA.
  User: voskart
  Date: 11.06.15
  Time: 23:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'>
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-theme.min.css") %>'>
<link href="/SDL/rescources/css/bootstrap.css" rel="stylesheet" type="text/css">
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
<html>
<head>
<title>SDL Registration-Page</title>
</head>
<body>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#inverseNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
      <a class="navbar-brand" href="#">SDL</a></div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="inverseNavbar1">
      <ul class="nav navbar-nav">
        <li ><a href="/sdl/login">Login</a></li>
        <li class="active"><a href="#">Registration<span class="sr-only">(current)</span></a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<center>
<div  class="container">
	<div class="row">
    	<div class="col-sm-6 col-md-4 col-md-offset-4" class="grey">
            <h1 class="text-centere">Register for SDL</h1>
            <div class="account-wall">
                <form action="registration" method="POST">
                    <input type="text" name="username" class="form-control" placeholder="username" required autofocus>
                    <input type="password" name="password" class="form-control" placeholder="password" required>
                    <br>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Create an account</button>
                </form>
            </div>
         </div>
     </div>
</div>
</center>
</body>
</html>
