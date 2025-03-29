<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeans"%>
<%@ page import="java.util.ArrayList"%>
<%
ArrayList<JavaBeans> lista = (ArrayList<JavaBeans>) request.getAttribute("livros");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros</title>
<link rel="stylesheet" href="style.css">
<link rel="icon" href="imagens/liv.png">
</head>
<body>
	<h1>LIVROS</h1>
	<hr>
	<div class="cont">
		<a href="novoLivro.html" class="botao">Cadastrar Livro</a>
	</div>
	<br>
	<table class="tabela">
		<thead>
			<tr>
				<th>Id</th>
				<th>Titulo</th>
				<th>Autor</th>
				<th>Ano de Pub.</th>
				<th>Disponivel</th>
				<th>Remover</th>
				<th>Alugar / Devolver</th>

			</tr>
		</thead>
		<tbody>
			<%
			for (int i = 0; i < lista.size(); i++) {
			%>
			<tr>
				<td><%=lista.get(i).getId()%></td>
				<td><%=lista.get(i).getTitulo()%></td>
				<td><%=lista.get(i).getAutor()%></td>
				<td><%=lista.get(i).getAno()%></td>
				<td><%=lista.get(i).getDisponivel() == 1 ? "Sim" : "Nao"%></td>
				<td><a href="#" onclick="confirmar(<%=lista.get(i).getId()%>)"
					class="remover">Remover</a></td>
				<%
				if (lista.get(i).getDisponivel() == 1) {
				%>
				<td><a href="alugar?id=<%=lista.get(i).getId()%>"
					class="alugar">Alugar</a></td>
				<%
				} else {
				%>
				<td><a href="devolver?id_input=<%=lista.get(i).getId()%>"
					class="alugar">Devolver</a></td>
				<%
				}
				%>
			</tr>
			<%
			}
			%>
		</tbody>
	</table>
	<script>
	function confirmar(id){
		let resp = confirm("Confirma?")
		if(resp === true){
			window.location.href = "remover?id=" + id
		}
	}
	</script>
</body>
</html>