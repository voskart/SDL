<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 <link rel="stylesheet" href="${pageContext.request.contextPath}/css/stone.css" />
    <title>SDL</title>
</head>
<body><h1>Wilkommen zu Steine der Leidenschaft Dummy</h1>



    <div>
        <img border="10px" src="https://upload.wikimedia.org/wikipedia/commons/8/8b/Chalcopyrite-Magnetite-cktsr-10c.jpg" style="height: 300px; width: 450px; "></tr>
        <iframe
                width="300"
                height="400"
                frameborder="0" style="border:0"
                src="https://www.google.com/maps/embed/v1/place?key=AIzaSyAr19r1kEhzdA4Rms8lF4l3NO7nAgXFCnk
            &q=-37.866963,144.980615" allowfullscreen
                align="right">
        </iframe>
    </div>

    <form  action="HotOrNot" method="POST" style="width: 338px; ">
		<input type="submit" title="hot" name="x" value="hot" src="hot.jpg" style="cursor: hand">
	</form >

    <form action="HotOrNot" method="POST" style="width: 335px; ">
		<input type="submit" title="not" name="x" value="not"  src="path3149.png" style="cursor: hand">
	</form>

    <br><br><br><br>
	<form name="infos">Informationen<br>
	${info}	
	</form>
	
</body>
</html>