<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>SDL</title>

<!-- Bootstrap -->
<link rel="stylesheet" href="<%=request.getContextPath()%>/rescources/bootstrap.css"/>
<link href="jQueryAssets/jquery.ui.core.min.css" rel="stylesheet" type="text/css">
<link href="jQueryAssets/jquery.ui.theme.min.css" rel="stylesheet" type="text/css">
<link href="jQueryAssets/jquery.ui.button.min.css" rel="stylesheet" type="text/css">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
<script src="jQueryAssets/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="jQueryAssets/jquery.ui-1.10.4.button.min.js" type="text/javascript"></script>
</head>
<body class="bgAnimObj">
<div class="container-fluid">
  <div>
    <div class="col-lg-offset-0 col-md-offset-0 col-md-12">
      <h1 class="text-center"><strong>Steiner der Leidenschaft</strong></h1>
    </div>
  </div>
  <hr>
</div>
<div>&nbsp;</div>
<div><center>
  <p>Willkommen zu "Steine der Leidenschaft"</p>
  <p>Diese Seite ist im Rahmen eines Projektes f&uumlr den Kurs XML-Technologien entstanden.</p>
  </center>
</div>
<div>
    <a class="btn btn-default" href="/SDL/login" >Login</a>
    <a class="btn list-inline btn-default" href="/SDL/registration" >Registration</a>
  &nbsp;</div>
<div></div>
<div></div>
<audio src="<%=request.getContextPath()%>/rescources/music.wmv" autoplay>
  Your browser does not support the <code>audio</code> element.
</audio>
</body>
</html>