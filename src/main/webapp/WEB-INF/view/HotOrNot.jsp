<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap.min.css") %>'>
<link rel='stylesheet' href='<%= org.webjars.AssetLocator.getWebJarPath("css/bootstrap-theme.min.css") %>'>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("jquery.min.js") %>'></script>
<script type='text/javascript' src='<%= org.webjars.AssetLocator.getWebJarPath("js/bootstrap.min.js") %>'></script>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <title>SDL</title>
<style type="text/css">
    div {
    display: table; /* shrink wrap the contents */
    margin: 0 auto; /* center via left/right margins */
    width: 800px;
    }

    form {
    display: inline;
    }
</style>
</head>
<body><h1>Wilkommen zu Steine der Leidenschaft Dummy</h1>



    <div>
        <img border="10px" src="${img}" style="height: 300px; width: 450px; "></tr>
        <iframe
                width="250"
                height="300"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAr19r1kEhzdA4Rms8lF4l3NO7nAgXFCnk
            &q=${coords.xCoordinate},${coords.yCoordinate}" allowfullscreen
                align="right">
        </iframe>
    </div>

<div>
    <br>
    <form  action="HotOrNot" method="POST">
        <button class="btn btn-lg btn-primary btn-block" value="hot" name="x" type="submit">
            HOT</button>
	</form >
<br>
    <form action="HotOrNot" method="POST">
        <button class="btn btn-lg btn-primary btn-block" value="not" name="x" type="submit">
            NOT</button>
	</form>
</div>
    <br><br><br><br>
	<form name="infos">Informationen:<br>
	${info}	
	</form>
	
</body>
</html>