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
	});
}

async function onProcesarEstudiante(){
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
	.done(function({data}) {
		getMessageALert('success','Muy Bien!', `El estudiante ${data.nombreEstudiante} fue registrado con éxito.`)
	})
	.fail(function(err) {
		console.log(err);
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err)
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
         	           
          __table_estudiante__.row
            .add([
              data[i].periodoEscolar.anioEscolar,
              data[i].numDocumento,
              data[i].nombreEstudiante + ' ' + data[i].apPaterno + ' ' + data[i].apMaterno,
              data[i].nivelEscolar,
              data[i].turno,
              data[i].gradoAlumno.gradoDescripcion + ' ' + data[i].seccionAlumno.descripcionSeccion,              
              data[i].apoderadoEstudiante.nombre + ' ' + data[i].apoderadoEstudiante.appaterno + ' ' + data[i].apoderadoEstudiante.apmaterno
            ])
            .draw(false);
        }
	})
	.fail(function() {
		console.log("error");
	})
	.always(function() {
		console.log("complete");
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