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
			throw new Error('Erro na requisição');
		}
		return response.json()
	}).then(data => {
		console.log(data)
		window.location.href = 'http://localhost:8080/Biblioteca/main';
	}).catch(error => {
		console.log(error)
	})
}