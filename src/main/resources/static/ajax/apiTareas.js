//================================ RUTA TOKEN ====================================//
const urlParams = new URLSearchParams(window.location.search);
var id_tarea = urlParams.get("id");
//====================================================================//

var grados = [];

function init(){
	if(rolUser === "ESTUDIANTE"){
		$(".perstudent").css("display","none")
	}
	//$("#dtpfecha").val(moment(new Date()).format('yyyy-MM-DD'));
	$("#contfile").css("display","none")
	if(id_tarea != null){
		onCargarDatosEditTarea(id_tarea)		
	}
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
			if(grados.length > 0){
				for (var x = 0; x < grados.length; x++) {
					if(grados[x].idGrado === data[i].idGrado){
						option += `<option value="${data[i].idGrado}">${data[i].gradoDescripcion} </option>`
					}				
				}	
			}
					
		}
		$("#cboidGrado").html(option);
	})
	.fail(function(err) {
		console.log(err);
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.detail)
		}
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
		if(lstGrado.length > 0){
			grados = lstGrado;
			onBusarGradoNivel();
		}else{
			grados = []
			$("#cboidGrado").html(`<option value="">---: SELECCIONE :---</option>`);
		}
		

	})
	.fail(function(error) {
		console.log(err);
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.detail)
		}
	});	
}

async function onCargarDatosEditTarea(id){
	
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarDatosTareaId',
		type: 'GET',
		dataType: 'JSON',
		data: {"idtarea": id},
	})
	.done(function({data}) {
		console.log(data);
		$("#idTarea").val(data.idTarea);
		$("#cbodocente").val(data.personal.idPersonal);

		buscarDatosDocente();
		$("#nivelEscolar").val(data.nivelEscolar);
		$("#tema").val(data.tema);		
		$("#dtpfecha").val(data.fechaPresentacion);
		$("#txtcriterios").val(data.observacion)
		if(data.nameDocumento != null || data.nameDocumento != ""){
			$("#contfile").css("display","block")
		}
		setTimeout(() => {
			$("#cboidGrado").val(data.grado.idGrado);
			$("#cboSeccion").val(data.seccion.idSeccion);
			$("#cbocurso").val(data.curso.idCurso);
		}, 400);
		

	})
	.fail(function(error) {
		console.log(err);
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.detail)
		}
	});	
}




function getMessageALert(_icon, _title, _message){
	Swal.fire({
	  icon: _icon,
	  title: _title,
	  html: _message
	})
}



init();