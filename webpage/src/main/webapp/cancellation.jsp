<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Cancelamento</title>

<meta name="author"
	content="Reaggeou! - Anderson Andrei, Gabriel Lemos, Danilo Martinelli">

<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.8.2/css/all.css"
	integrity="sha384-oS3vJWv+0UjzBfQzYUhtDYW+Pj2yciDJxpsK1OYPAYjqT085Qq/1cq5FLXAZQ7Ay"
	crossorigin="anonymous">
<link href="https://fonts.googleapis.com/css?family=Oswald"
	rel="stylesheet">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/main.css" />
<link rel="stylesheet" type="text/css" media="screen"
	href="css/cancellation.css" />
<link rel="shortcut icon" href="img/fav_icon.png"
	type="image/x-icon">
<script src="main.js"></script>
</head>

<body>
	<div class="container">
		<div class="container_header">
			<div class="container_logo">
				<a href="./index.html">
					<h1 class="logo">Reaggeou</h1>
				</a>
			</div>
		</div>
		<div class="container_description_cancellation">
			<div class="description_cancellation">
				<div class="container_title">
					<h2>Cancelamento do Reggaeou</h2>
					<p>Obrigado por contratar nossos servi�os, sentimos
					muito caso n�o tenha tido uma boa experi�ncia. Por favor
					deixe a raz�o do cancelamento.</p>
				</div>
			</div>
			<div class="cancellation">
				<form method="POST" action="CancellationController">
					<div class="cancellation_input">
						<label> Email:<br>
						<input type="email" name="email" id="email"
							placeholder="example@example.com">
						</label>
					</div>
					<div class="cancellation_input">
						<label> Qual o motivo do cancelamento ?<br>
						<input type="text" name="reason" id="reason"
							placeholder="Informe o motivo do cancelamento">
						</label>
					</div>
					<p class="message">
						<c:out value="${error}" />
					</p>
					<button type="submit" name="confirm_cancellation">Confirmar</button>
				</form>
			</div>
		</div>
	</div>
</body>

</html>