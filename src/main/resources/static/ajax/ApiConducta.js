var grados = [];
var students = [];

var __table_conducta__ = "";

function init(){
	__table_conducta__ = $("#__table_conducta__").DataTable({
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
	if($("#nivelEscolar").val() == "" || $("#cboidGrado").val() == ""  || $("#cboSeccion").val() == ""){
		return false;
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarEstudiantePeriodoNivelGradoSeccion',
		type: 'GET',
		dataType: 'JSON',
		data: {"periodo": 2023,
		 "nivel": $("#nivelEscolar").val(),
		 "grado": $("#cboidGrado").val(),
		 "seccion": $("#cboSeccion").val()
		},
	})
	.done(function({data}) {
		students = data;
		let option = `<option value="">---: SELECCIONE :---</option>`
		for (let i = 0; i < data.length; i++) {
			option += `<option value="${data[i].idEstudiante}">${data[i].apPaterno} ${data[i].apMaterno} ${data[i].nombreEstudiante}</option>`			
		}
		$("#cboalumnos").html(option)
	})
	.fail(function(err) {
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.detail)
		}
	});
	
}

function onMostrardatosAdicionales(){
	let id = $("#cboalumnos").val()
	for (let x = 0; x < students.length; x++) {
		if(students[x].idEstudiante == id){
			$("#txtdnistudents").val(students[x].numDocumento)
			$("#txtapoderado").val(students[x].apoderadoEstudiante.nombre + ' ' + students[x].apoderadoEstudiante.appaterno + ' ' + students[x].apoderadoEstudiante.apmaterno)
			$("#txtceluapoderado").val(students[x].apoderadoEstudiante.celular)
			break;
		}		
	}
	onListarSancionesAlumno();
}

async function onProcesarConducta(){
	if($("#txtmotivo").val() == "" || $("#cboalumnos").val() == "" 
		|| $("#cbodocente").val()  == ""|| $("#cbocurso").val() == ""){
		getMessageALert('warning','Lo sentimos!', "Todo los campos son requerido :(")
		return false;
	}
	$("#btnsave").attr("disabled", true);
	$("#btncancel").css("visibility", "hidden");
    $("#btnsave").html(
      `<i class="fa fa-spinner fa-spin fa-fw"></i> Procesando....`
    );

	let fecha = moment(new Date()).format('yyyy-MM-DD')
	let jsonData = {
		"idConducta": $("#idConducta").val(),
		"descripcion": $("#txtmotivo").val(),
		"fechaRegistra": fecha,
		"notificarAPoderadoEmail": ($('#chknotificar').prop('checked') ? 'SI' : 'NO'),
		"estudiante":{
			"idEstudiante": $("#cboalumnos").val(),
		},
		"personal": {
			"idPersonal": $("#cbodocente").val(),
		},
		"curso":{
			"idCurso":$("#cbocurso").val()
		}
	}

	await $.ajax({
		url: '/api/v1/mantenimiento/guardarConductaAlumno',
		type: 'POST',
		dataType: 'JSON',
		data: JSON.stringify(jsonData),
		contentType: "application/json"
	})
	.done(function({data,message}) {
		$("#btnsave").removeAttr("disabled");
		$("#btncancel").css("visibility", "visible");
		$("#btnsave").html('Procesar Registro');
		getMessageALert('success','Bien echo!', message)
		onListarSancionesAlumno();
	})
	.fail(function(err) {
		$("#btnsave").removeAttr("disabled");
		$("#btncancel").css("visibility", "visible");
		$("#btnsave").html('Procesar Registro');
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

async function onListarSancionesAlumno(){
	if($("#cbocurso").val() == "" || $("#cboalumnos").val() == ""  || $("#cbodocente").val() == ""){
		return false;
	}

	await $.ajax({
		url: '/api/v1/mantenimiento/buscarConductaAlumnoGradoSeccionNivel',
		type: 'GET',
		dataType: 'JSON',
		data: {
		 "idcurso": $("#cbocurso").val(),
		 "idalumno": $("#cboalumnos").val(),
		 "iddocente": $("#cbodocente").val()
		},
	})
	.done(function({data}) {
		console.log(data);
		__table_conducta__.clear().draw();
		for (let i = 0; i < data.length; i++){
			let btndelete = `<button onclick="onEliminarConducta(${data[i].idConducta})" class="btn btn-sm bg-success-light me-2" >
			<i class="feather-trash-2"></i></button>`  
			__table_conducta__.row
            .add([
              data[i].personal.apellidos + ' ' + data[i].personal.nombre,
              data[i].curso.nombreCurso,
              data[i].fechaRegistra,
              data[i].descripcion,
			  btndelete
            ])
            .draw(false);
		}
	})
	.fail(function(err) {
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.detail)
		}
	});
	
}

async function onEliminarConducta(id){
	await Swal.fire({
		title: 'Estas seguro de Eliminar?',
		text: "una vez eliminado no se podra recuperar!",
		icon: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Si, eliminar'
	  }).then((result) => {
		if (result.isConfirmed) {
			$.ajax({
				url: '/api/v1/mantenimiento/eliminarConductaAlumnoGradoSeccionNivel',
				type: 'DELETE',
				dataType: 'JSON',
				data: {"idconducta": id},
			})
			.done(function({message}) {
				getMessageALert('success','Bien echo!', message)
				onListarSancionesAlumno();
			})
			.fail(function(err) {
				if(err.status === 409){
					getMessageALert('warning','Upps!', err.responseJSON.message)
				}else if(err.status === 404){
					getMessageALert('warning','No Hay!', err.responseJSON.message)
				}else{
					getMessageALert('error','Error!', err.responseJSON.detail)
				}
			});
		}
	  })
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