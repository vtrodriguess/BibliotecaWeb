<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alugar</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="imagens/liv.png">
</head>
<body>
	<h1>Alugar</h1>
	<form name="formulario" action="alugar_form">

		<p>
			<label for="iID">Id: </label><input type="text" name="id"
				readonly value="<%out.print(request.getAttribute("id"));%>">

		</p>

		<p><label for="iCodigo">Código-Alugar: </label><input type="text" name="alugar" placeholder="0 para alugar"
			value="<%out.print(request.getAttribute("alugar"));%>"></p>
		<input type="button" value="Alugar" class="botao" onclick="validar()">
	</form>

	<script>
		function validar() {
			let alugar = formulario.alugar.value;
			if (alugar !== "0") {
				alert("Digite 0 para alugar")
			} else {
				document.forms["formulario"].submit()
			}
		}
	</script>
</html>