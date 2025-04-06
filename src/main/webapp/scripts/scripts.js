function adicionar_fetch() {
	const infos = {
		titulo: document.getElementById("ititulo").value,
		autor: document.getElementById("iautor").value,
		ano: document.getElementById("iano").value,
		disponivel: 1
	}


	fetch('http://localhost:8080/Biblioteca/inserir', {
		method: 'POST',
		headers: {
			"Content-Type": "application/json"
		},
		body: JSON.stringify(infos)
	}).then(response => {
		if (!response.ok) {
			throw new Error("Erro na requisição");
		}
		return response.json()
	}).then(data => {
		console.log(data)
		window.location.href = 'http://localhost:8080/Biblioteca/main';
	}).catch(erro => {
		console.log(erro)
	})
}

function deletar_fetch(id) {

	fetch(`http://localhost:8080/Biblioteca/remover?id=${id}`, {
		method: 'DELETE'
	})
		.then(response => {
			if (response.status === 204) {
				console.log("Solicitação bem sucedida")
				window.location.href = 'http://localhost:8080/Biblioteca/main'
			}
		})
		.catch(erro => {
			console.log(erro)
		})
}

function confirmar(id) {
	let resp = confirm("Confirma?")
	if (resp === true) {
		deletar_fetch(id)
	}
}

function alugar_fetch(id) {

	fetch(`http://localhost:8080/Biblioteca/alugar?id=${id}`, {
		method: 'PUT'
	}).then(response => {
		if (response.status === 204) {
			console.log("Solicitação bem sucedida")
			window.location.href = 'http://localhost:8080/Biblioteca/main'
		}
	}).catch(erro => {
		console.log(erro)
	})
}

function confirmarAlugar(id) {
	let resp = confirm("Confirma?")
	if (resp === true) {
		alugar_fetch(id)
	}
}

function devolver_fetch(id) {

	fetch(`http://localhost:8080/Biblioteca/devolver?id=${id}`, {
		method: 'PUT'
	}).then(response => {
		if (response.status === 204) {
			console.log("Solicitação bem sucedida")
			window.location.href = 'http://localhost:8080/Biblioteca/main'
		}
	}).catch(erro => {
		console.log(erro)
	})
}

function confirmarDevolver(id) {
	let resp = confirm("Confirma?")
	if (resp === true) {
		devolver_fetch(id)
	}
}

function listar_fetch() {
	fetch('http://localhost:8080/Biblioteca/livros', {
		method: 'GET',
	}).then(response => {
		if (!response.ok) {
			throw new Error("Erro na requisição")
		}
		return response.json()
	}).then(data => {
		let html = document.getElementById("tabela")
		for (let i = 0; i < data.length; i++) {
			let disp = data[i].disponivel === 1 ? "Sim" : "Nao"

			let botoes = `<td><a href="#" onclick="confirmar(${data[i].id})" class="remover">Remover</a></td>`

			if (data[i].disponivel === 1) {
				botoes += `<td><a href="#" onclick="confirmarAlugar(${data[i].id})" class="alugar">Alugar</a></td>`
			} else {
				botoes += `<td><a href="#" onclick="confirmarDevolver(${data[i].id})" class="alugar">Devolver</a></td>`
			}

			html.innerHTML += `
			           <tr>
			               <td>${data[i].id}</td>
			               <td>${data[i].titulo}</td>
			               <td>${data[i].autor}</td>
			               <td>${data[i].ano}</td>
			               <td>${disp}</td>
			               ${botoes}
			           </tr>
			       `
		}
	}).catch(erro => {
		console.log(erro)
	})
}

