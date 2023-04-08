//================================ RUTA TOKEN ====================================//
const urlParams = new URLSearchParams(window.location.search);
var studen_edit = urlParams.get("student");
var periodo_escolar = urlParams.get("periodo");
//====================================================================//

var __table_estudiante__ = "";

function init(){
	__table_estudiante__ = $("#__table_estudiante__").DataTable({
    paging: true,
    lengthChange: true,
    searching: true,
    ordering: true,
    info: true,
    retrieve: true,
    processing: true,
    autoWidth: true,
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

	if(studen_edit != null && periodo_escolar != null){
		onBuscarDataEstudianteID(studen_edit, periodo_escolar);
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
		let option = `<option value="">---: SELECCIONE :---</option>`
		for (var i = 0; i < data.length; i++) {
			option += `<option value="${data[i].idGrado}">${data[i].gradoDescripcion} ${data[i].tipoGrado}</option>`
		}
		$("#idGrado").html(option);
		$("#cboidGrado").html(option)
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

async function onProcesarEstudiante(){
	if($("#nombreEstudiante").val() == "" || $("#apPaternoEstudiante").val() == "" || $("#apMaternoEStudiante").val() == ""
	|| $("#numDocumentoEstudiante").val() == "" || $("#fnacimiento").val() == "" || $("#ncelularEstudiante").val() == ""
	|| $("#nivelEscolar").val() == "" || $("#turno").val() == "" || $("#sexoEstudiante").val() == ""
	|| $("#direccionEstudiante").val() == "" || $("#emailEstudiante").val() == "" || $("#idGrado").val() == ""
	|| $("#idSeccion").val() == "" || $("#idPeriodoEscolar").val() == "" || $("#nombreapoderado").val() == ""
	|| $("#apmaternoapoderado").val() == "" || $("#appaternoapoderado").val() == "" || $("#numDocumentoapoderado").val() == ""
	|| $("#celularapoderado").val() == "" || $("#emailapoderado").val() == "" || $("#direccionapoderado").val() == ""){
		getMessageALert('warning','Lo sentimos!', "Todo los campos son requerido :(")
		return false;
	}
	$("#btnsave").attr("disabled", true);
	$("#btncancel").css("display", "none");
    $("#btnsave").html(
      `<i class="fa fa-spinner fa-spin fa-fw"></i> Procesando....`
    );
	let jsonData = {
		"idEstudiante": $("#idEstudiante").val(),
		"colegioProcencia": $("#colegioProcencia").val(),
		"observacion": $("#observacion").val(),
		"nombreEstudiante": $("#nombreEstudiante").val(),
		"apPaterno": $("#apPaternoEstudiante").val(),
		"apMaterno": $("#apMaternoEStudiante").val(),
		"numDocumento": $("#numDocumentoEstudiante").val(),
		"fnacimiento": $("#fnacimiento").val(),
		"ncelular": $("#ncelularEstudiante").val(),
		"nivelEscolar": $("#nivelEscolar").val(),
		"turno": $("#turno").val(),
		"sexoEstudiante": $("#sexoEstudiante").val(),
		"direccionEstudiante": $("#direccionEstudiante").val(),
		"emailEstudiante":$("#emailEstudiante").val(),
		"gradoAlumno": {
			"idGrado": $("#idGrado").val()
		},
		"seccionAlumno": {
			"idSeccion": $("#idSeccion").val()
		},
		"periodoEscolar": {
			"idPeriodoEscolar": $("#idPeriodoEscolar").val()
		},
		"apoderadoEstudiante": {
			"idApoderado": $("#idApoderado").val(),
			"nombre": $("#nombreapoderado").val(),
			"apmaterno": $("#apmaternoapoderado").val(),
			"appaterno": $("#appaternoapoderado").val(),
			"numDocumento": $("#numDocumentoapoderado").val(),
			"celular": $("#celularapoderado").val(),
			"email": $("#emailapoderado").val(),
			"direccion": $("#direccionapoderado").val(),
		}

	}
	await $.ajax({
		url: '/api/v1/mantenimiento/guardarstudentdata',
		type: 'POST',
		dataType: 'JSON',
		data: JSON.stringify(jsonData),
		contentType: "application/json"
	})
	.done(function({data,message}) {
		$("#btnsave").removeAttr("disabled");
		$("#btncancel").css("display", "block");
		$("#btnsave").html('Procesar Registro');
		getMessageALert('success','Muy Bien!', message)
		setTimeout(() => {
		 window.location.href = "estudiantes"
		}, 3000);

	})
	.fail(function(err) {
		$("#btnsave").removeAttr("disabled");
		$("#btncancel").css("display", "block");
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

async function onBuscarEstudianteNivelGradoSeccionPeriodo(){
	if($("#cboperiodo").val() == "" ||$("#nivelEscolar").val() == "" || $("#cboidGrado").val() == ""  || $("#cboidSeccion").val() == ""){
		return false;
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarEstudiantePeriodoNivelGradoSeccion',
		type: 'GET',
		dataType: 'JSON',
		data: {"periodo": $("#cboperiodo").val(),
		 "nivel": $("#nivelEscolar").val(),
		 "grado": $("#cboidGrado").val(),
		 "seccion": $("#cboidSeccion").val()
		},
	})
	.done(function({data}) {
		console.log(data);
		__table_estudiante__.clear().draw();
		for (let i = 0; i < data.length; i++) {      

		let deletebtn  = `<button onclick="onEliminarEstudiante(${data[i].idEstudiante})" class="btn btn-sm bg-success-light me-2" >
												<i class="feather-trash-2"></i></button>`   
		let editbtn = `<a href="/vieweditstudent?student=${data[i].idEstudiante}&periodo=${data[i].periodoEscolar.idPeriodoEscolar}" class="btn btn-sm bg-danger-light" >
												<i class="feather-edit"></i></a>`
         	           
          __table_estudiante__.row
            .add([
              data[i].periodoEscolar.anioEscolar,
              data[i].numDocumento,
              data[i].nombreEstudiante + ' ' + data[i].apPaterno + ' ' + data[i].apMaterno,
              data[i].nivelEscolar,
              data[i].turno,
              data[i].gradoAlumno.gradoDescripcion + ' ' + data[i].seccionAlumno.descripcionSeccion,              
              data[i].apoderadoEstudiante.nombre + ' ' + data[i].apoderadoEstudiante.appaterno + ' ' + data[i].apoderadoEstudiante.apmaterno,
              `${editbtn} ${deletebtn}`
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

async function onBuscarDataEstudianteID(idEstudiante, idperiodoEscolar){
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarEstudinatePeriodoEscolar',
		type: 'GET',
		dataType: 'JSON',
		data: {"periodo": idperiodoEscolar, "idestudiante": idEstudiante},
	})
	.done(function({data}) {
		console.log(data);
		$("#nivelEscolar").val(data.nivelEscolar)		
		$("#idEstudiante").val(data.idEstudiante)
		$("#colegioProcencia").val(data.colegioProcencia)
		$("#observacion").val(data.observacion)
		onBusarGradoNivel();
		$("#nombreEstudiante").val(data.nombreEstudiante)
		$("#apPaternoEstudiante").val(data.apPaterno)
		$("#apMaternoEStudiante").val(data.apMaterno)
		$("#numDocumentoEstudiante").val(data.numDocumento)
		$("#fnacimiento").val(data.fnacimiento)
		$("#emailEstudiante").val(data.emailEstudiante)
		$("#ncelularEstudiante").val(data.ncelular)
		$("#nivelEscolar").val(data.nivelEscolar)
		$("#direccionEstudiante").val(data.direccionEstudiante)
		$("#turno").val(data.turno)
		$("#sexoEstudiante").val(data.sexoEstudiante)		
		$("#idSeccion").val(data.seccionAlumno.idSeccion)
		$("#idPeriodoEscolar").val(data.periodoEscolar.idPeriodoEscolar)
		$("#idApoderado").val(data.apoderadoEstudiante.idApoderado)
		$("#nombreapoderado").val(data.apoderadoEstudiante.nombre)
		$("#apmaternoapoderado").val(data.apoderadoEstudiante.apmaterno)
		$("#appaternoapoderado").val(data.apoderadoEstudiante.appaterno)
		$("#numDocumentoapoderado").val(data.apoderadoEstudiante.numDocumento)
		$("#celularapoderado").val(data.apoderadoEstudiante.celular)
		$("#emailapoderado").val(data.apoderadoEstudiante.email)
		$("#direccionapoderado").val(data.apoderadoEstudiante.direccion)	
		setTimeout(() => {
		 $("#idGrado").val(data.gradoAlumno.idGrado)	
		}, 400);
		

	
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

function onEliminarEstudiante(id){
	getMessageALert('warning','Lo sentimos!', "este accion se encuentra en mantenimiento")
}

function getMessageALert(_icon, _title, _message){
	Swal.fire({
	  icon: _icon,
	  title: _title,
	  html: _message
	})
}

init();