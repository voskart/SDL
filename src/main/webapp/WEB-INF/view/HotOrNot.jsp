<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link href="/sdl/rescources/js/jquery-2.1.1.js" rel="stylesheet" type="text/css">
<link href="/sdl/rescources/js/bootstrap.js" rel="stylesheet" type="text/css">
<link href="/sdl/rescources/css/sdlstyle.css" rel="stylesheet" type="text/css">
<html>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
<script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SDL</title>
</head>
<body>
<div class=container style='width: 100%'>
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#inverseNavbar1"><span class="sr-only">Toggle navigation</span><span class="icon-bar"></span><span class="icon-bar"></span><span class="icon-bar"></span></button>
      <a class="navbar-brand" href="#">SDL</a></div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse" id="inverseNavbar1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="#">"Rocks or Not"<span class="sr-only">(current)</span></a></li>
        <li ><a href="/sdl/Table">Table</a></li>
      </ul>
    </div>
    <!-- /.navbar-collapse -->
  </div>
  <!-- /.container-fluid -->
</nav>
<div class="imageMap">
<div class="imagebox">
		<img src="${img}" class="steinbild" >
    <div class="HotOrNot">

 				 <form class="Rocks" action="HotOrNot" method="POST"><input type="hidden" value="HOT" name="params[voting]"><button class=" btn-success " type="submit" value="${id}" name="params[id]">ROCKS</button></form>
  				<form class="Not"  action="HotOrNot" method="POST"><input type="hidden" value="NOT" name="params[voting]"><button class=" btn-danger " type="submit" value="${id}" name="params[id]">NOT</button></form>
  &nbsp;
  	</div>
</div>
<div class="Map"><strong>FUNDORT</strong><br>
 <iframe
                class="googlemap"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAr19r1kEhzdA4Rms8lF4l3NO7nAgXFCnk
            &q=${coords.xCoordinate},${coords.yCoordinate}" allowfullscreen>
        </iframe>
 </div>
 </div>
<div class="infobox"><div><strong><center>INFO</center></strong></div><br><dif><form name="infos" class="infotext">${info}</form></div></div>
</dir>
</body>
</html>