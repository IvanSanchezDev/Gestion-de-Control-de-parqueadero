/**SELECCIONAR RADIO AUTOMATICAMENTE */

const input = document.getElementById("placa")
input.addEventListener("change", select)



function select() {
	
	const checkMoto = document.getElementById("moto")
const checkCarro = document.getElementById("carro")
	var element = document.getElementById("placa").value;
	const ultimoCaracter = element.slice(-1);
	if (isNaN(ultimoCaracter)) {
		checkMoto.checked = true
	} else {

		checkCarro.checked = true
	}
}


function mostrarToast() {
	const toast = new bootstrap.Toast(document.querySelector('.toast'));

	toast.show();
}
