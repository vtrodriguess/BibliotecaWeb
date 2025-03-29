/**
 * Validação de formulario
 */

function validar(){
	let titulo = formulario.titulo.value
	let autor = formulario.autor.value
	let disponivel = parseInt(formulario.disponivel.value);
	
	if(titulo === ""){
		alert("Preencha o campo nome")
		formulario.titulo.focus()
		return false
	}else if(autor === ""){
		alert("Preencha o campo nome")
		formulario.autor.focus()
		return false
	}else if (disponivel !== 1){
		alert("Disponivel precisa ser igual a 1")
		formulario.disponivel.focus()
		return false
	}else{
		document.forms["formulario"].submit()
	}

}