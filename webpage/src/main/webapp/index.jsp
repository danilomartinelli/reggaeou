<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Reaggeou!</title>

<meta name="description"
	content="O Reaggeou � um servi�o de newslatter no qual o usu�rio recebe via 
    email todos os eventos que ocorrer�o em Salvador de acordo com as categorias da sua prefer�ncia.">
<meta name="keywords" content="Eventos, Shows, Festa">
<meta name="robots" content="index, follow">
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
<link rel="shortcut icon" href="img/fav_icon.png" type="image/x-icon">
<script src="main.js"></script>
</head>

<body>
	<div class="container">
		<div class="container_header">
			<div class="container_logo">
				<a href="./index.jsp">
					<h1 class="logo">Reaggeou</h1>
				</a>
			</div>
		</div>
		<div class="container_description">
			<div class="description">
				<!-- Descri��o do servi�o da aplica��o -->
				<h2>Lorem Ipsum</h2>
				<p>O Reggaeou! � um servi�o de newsletter no qual o usu�rio
					recebe via email ou whatsapp todos os eventos que ocorrer�o em
					Salvador de acordo com as categorias da sua prefer�ncia.</p>
			</div>
			<!-- <img src="./public/img/banner_01.png" alt=""> -->
		</div>
		<div class="container_registerBox">
			<div class="container_register">
				<form action="RegisterController" method="POST">
					<div class="container_category">
						<div style="font-size: 1.5vmax">
							<c:out value="${SuccessfullyRemoved}" />
						</div>
						<div class="description_category">
							<h2>Categorias</h2>
							<p>Escolha as categorias dos eventos que deseja receber</p>
						</div>
						<div class="categories">
							<div class="categoryBox">
								<div class="category">
									<input type="checkbox" name="category" id="ctg1" value="3">
									<label for="ctg1"><i class="fas fa-graduation-cap"></i></label>
								</div>
								<p>Cursos e Workshop</p>
							</div>
							<div class="categoryBox">
								<div class="category">
									<input type="checkbox" name="category" id="ctg2" value="4">
									<label for="ctg2"><i class="fas fa-palette"></i></label>
								</div>
								<p>Arte e Cultura</p>
							</div>
							<div class="categoryBox">
								<div class="category">
									<input type="checkbox" name="category" id="ctg3" value="1">
									<label for="ctg3"><i class="fas fa-guitar"></i></label>
								</div>
								<p>Festas e Shows</p>
							</div>
							<div class="categoryBox">
								<div class="category">
									<input type="checkbox" name="category" id="ctg4" value="2">
									<label for="ctg4"><i class="fas fa-biohazard"></i></label>
								</div>
								<p>Ci�ncia e Tecnologia</p>
							</div>
							<div class="categoryBox">
								<div class="category">
									<input type="checkbox" name="category" id="ctg5" value="5">
									<label for="ctg5"><i class="fas fa-running"></i></label>
								</div>
								<p>Esportes</p>
							</div>
						</div>
						<div style="color: #151D40">
							<p class="message_error">
								<c:out value="${error}" />
							</p>
						</div>
					</div>
					<div class="register">
						<div class="box">
							<div class="box2">
								<div class="register_input">
									<label> Email:<br> <input type="email"
										name="email" id="email" placeholder="example@example.com">
									</label>
								</div>
								<div class="register_input">
									<label> Telefone:<br> <input type="text"
										name="tel" id="tel" placeholder="(71) 91111-1111">
									</label>
								</div>
							</div>
							<div class="message">
								<a href="./cancellation.jsp">Deseja cancelar o servi�o ?</a>
								<p class="message_error">
									<c:out value="${error}" />
								</p>
							</div>
							<button type="submit" name="register">Cadastrar</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>