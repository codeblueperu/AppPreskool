var grados = [];

function init(){

}

async function onBusarGradoNivel(){
	await $.ajax({
		url: '/api/v1/serices/listagraodonivel',
		type: 'GET',
		dataType: 'json',
		data: {nivel: $("#nivelEscolar").val()},
	})
	.done(function({data}) {
		//console.log(grados)
		let option = `<option value="">---: SELECCIONE :---</option>`
		for (var i = 0; i < data.length; i++) {
			for (var x = 0; x < grados.length; x++) {
				if(grados[x].idGrado === data[i].idGrado){
					option += `<option value="${data[i].idGrado}">${data[i].gradoDescripcion} </option>`
				}				
			}			
		}
		$("#cboidGrado").html(option);
	})
	.fail(function(err) {
		console.log(err);
	});
}

async function buscarDatosDocente(){
	if($("#cbodocente").val() == ""){
		return false;
	}

	await $.ajax({
		url: '/api/v1/mantenimiento/buscardatosdocente',
		type: 'GET',
		dataType: 'JSON',
		data: {iddocente: $("#cbodocente").val()},
	})
	.done(function({data}) {
		let {lstcursos} = data;
		let {lstSeccion} = data;
		let {lstGrado} = data;

		//CURSOS
		let optioncursos =  `<option value="">---: SELECCIONE :---</option>`
		for (var i = 0; i < lstcursos.length; i++) {
			optioncursos +=`<option value="${lstcursos[i].idCurso}">${lstcursos[i].nombreCurso}</option>` 
		}

		$("#cbocurso").html(optioncursos);

		//SECCIONES
		let optionsecciones =  `<option value="">---: SELECCIONE :---</option>`
		for (var i = 0; i < lstSeccion.length; i++) {
			optionsecciones +=`<option value="${lstSeccion[i].idSeccion}">${lstSeccion[i].descripcionSeccion}</option>` 
		}

		$("#cboSeccion").html(optionsecciones);

		//GRADOS
		grados = lstGrado;

	})
	.fail(function(error) {
		console.log(error);
	});
	
}

init();