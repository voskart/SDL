<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SDL</title>
</head>
<body><h1>Wilkommen zu Steine der Leidenschaft Dummy</h1>

		<img border="10px" src="https://upload.wikimedia.org/wikipedia/commons/8/8b/Chalcopyrite-Magnetite-cktsr-10c.jpg" style="height: 267px; width: 386px; ">
	<form  action="HotOrNot" method="POST" style="width: 338px; ">
		<input type="submit" title="hot" name="x" value="hot" src="hot.jpg" style="cursor: hand"">
	</form ><form action="HotOrNot" method="POST" style="width: 335px; ">
		<input type="submit" title="not" value="not" name="x" src="path3149.png" style="cursor: hand">
	</form>
	
	<form name="infos">Informationen<br>
	${info}	
	</form>
	
</body>
</html>