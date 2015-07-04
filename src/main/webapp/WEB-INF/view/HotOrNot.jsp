<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
<html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/rescources/bootstrap.css"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>SDL</title>
</head>
<body>
<div class="container-fluid">
  <div class="row">
    <div class="col-md-offset-0 col-md-12">
      <h1 class="text-center"><strong>Steine</strong> <strong>der</strong> <strong>Leidenschaft</strong></h1>
    </div>
  </div>
</div>
<div class="col-lg-8 imagebox">
<img src="${img}" alt="" width="654" height="515" class="Stone"/>
<div class="HotOrNot">

  <form  action="HotOrNot" method="POST"><input type="hidden" value="HOT" name="params[voting]"><button class="btn btn-lg btn-success " type="submit" value="${id}" name="params[id]">ROCKS</button></form>
  <form  action="HotOrNot" method="POST"><input type="hidden" value="NOT" name="params[voting]"><button class="btn btn-lg btn-danger " type="submit" value="${id}" name="params[id]">NOT</button></form>
  &nbsp;</div>
</div>
<div class="Map"><strong>FUNDORT</strong><br>
 <iframe
                width="250"
                height="300"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAr19r1kEhzdA4Rms8lF4l3NO7nAgXFCnk
            &q=${coords.xCoordinate},${coords.yCoordinate}" allowfullscreen>
        </iframe>
 </div>
<div class="infobox"><div><strong>INFO</strong></div><br><dif><form name="infos" class="infotext">${info}</form></div></div>
&nbsp;<!-- jQuery (necessary for Bootstrap's JavaScript plugins) --> 
<script src="SDL/js/jquery-1.11.2.min.js"></script>

<!-- Include all compiled plugins (below), or include individual files as needed --> 
<script src="SDL/js/bootstrap.js"></script>
<embed src="<%=request.getContextPath()%>/rescources/Rocks Song by Peter Weatherall.wmv" autostart="true" loop="true"
width="2" height="0">
</embed>
<audio src =”<%=request.getContextPath()%>/rescources/music.wmv”  autoplay=”true”>
</body>
</html>