<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Devolver</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="imagens/liv.png">
</head>
<body>
	<h1>Devolver</h1>
	<form name="formulario" action="devolver_form">
		<p>
			<label for="iID">Id: </label><input type="text" name="id_input" readonly
				value="<%out.print(request.getAttribute("id"));%>">
		</p>
		
		<p><label for="iCodigo">Código-Devolver: </label><input type="text" name="devolver_input" placeholder="1 para devolver"
			value="<%out.print(request.getAttribute("devolver"));%>"></p> <input
			type="button" value="Devolver" class="botao" onclick="validar()">
	</form>

	<script>
		function validar() {
			let devolver = formulario.devolver_input.value;
			if (devolver !== "1") {
				alert("Digite 1 para devolver")
			} else {
				document.forms["formulario"].submit()
			}
		}
	</script>
</body>
</html>