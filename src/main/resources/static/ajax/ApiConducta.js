var grados = [];
var __table_conducta = "";


function init(){
	__table_conducta = $("#__table_conducta").DataTable({
	    paging: true,
	    lengthChange: true,
	    searching: false,
	    ordering: false,
	    info: true,
	    retrieve: true,
	    processing: true,
	    autoWidth: false,
	    language: {
	      sProcessing: "Procesando...",
	      sLengthMenu: "Mostrar _MENU_ registros",
	      sZeroRecords: "No se encontraron resultados",
	      sEmptyTable: "Ningún dato disponible en esta tabla",
	      sInfo: "Mostrando registros del _START_ al _END_ de un total de _TOTAL_",
	      sInfoEmpty: "Mostrando registros del 0 al 0 de un total de 0",
	      sInfoFiltered: "(filtrado de un total de _MAX_ registros)",
	      sInfoPostFix: "",
	      sSearch: "Buscar:",
	      sUrl: "",
	      sInfoThousands: ",",
	      sLoadingRecords: "Cargando...",
	    },
	});

	$("#dtpfecha").val(moment(new Date()).format('yyyy-MM-DD'));
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

async function onBusarEstudiantes(){
	if($("#cboidGrado").val()  == "" || $("#cboSeccion").val() == "" || $("#cbocurso").val() == ""  ||  $("#cbotareas").val() == ""){
		return false;
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarAlumnosGradoNivelSeccionTarea',
		type: 'GET',
		dataType: 'json',
		data: {"nivel": $("#nivelEscolar").val(),
		"idgrado": $("#cboidGrado").val(),"idsecion": $("#cboSeccion").val(),
		"idcurso": $("#cbocurso").val(), "idtarea": $("#cbotareas").val()},
	})
	.done(function({data}) {
		console.log(presentaron)
		
	
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

function getMessageALert(_icon, _title, _message){
	Swal.fire({
	  icon: _icon,
	  title: _title,
	  html: _message
	})
}

function setToast(_position,_icon,_message){
	const Toast = Swal.mixin({
	  toast: true,
	  position: _position,
	  showConfirmButton: false,
	  timer: 3000,
	  timerProgressBar: true
	})

	Toast.fire({
	  icon: _icon,
	  title: _message
	})
}



init();