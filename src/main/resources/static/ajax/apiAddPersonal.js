//================================ RUTA TOKEN ====================================//
const urlParams = new URLSearchParams(window.location.search);
var id_docente = urlParams.get("person");
//====================================================================//

function init(){
	onListarCursos();
	if(id_docente != null){
	    onBuscarDatosPersonaDocente(id_docente)
	}
}

async function onListarCursos(){
	await $.ajax({
		url: '/api/v1/serices/listarcursosAll',
		type: 'GET',
		dataType: 'JSON',
		data: {status: 'All'},
	})
	.done(function({data}) {
		//console.log(data);
		let option = ``;
		for (var i = 0; i < data.length; i++) {
			option += `<li><label for="c${data[i].idCurso}"><input type="checkbox" id="c${data[i].idCurso}" value="${data[i].idCurso}"> ${data[i].nombreCurso}</label></li>`
		}
		$("#lstcursos").html(option);
	})
	.fail(function(err) {
		console.log(err);
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
		let option = ``;
		for (var i = 0; i < data.length; i++) {
			option += `<li><label for="g${data[i].idGrado}"><input type="checkbox" id="g${data[i].idGrado}" value="${data[i].idGrado}"> ${data[i].gradoDescripcion} ${data[i].tipoGrado}</label></li>`
		}
		$("#lstgrados").html(option);
	})
	.fail(function(err) {
		console.log(err);
	});
}

async function onProcesarPersona(){
	if($("#nombre").val() == "" || $("#apellidos").val() == "" || $("#numDocumento").val() == "" 
	|| $("#fechaNacimiento").val() == "" || $("#ncelular").val() == "" || $("#direccion").val() == ""
	|| $("#sexo").val() == "" || $("#email").val() == "" || $("#nivelEscolar").val() == "" ){
		getMessageALert('warning','Lo sentimos!', "Todos los datos personales del docente son requeridos :(")
		return false;
	}

	$("#btnsave").attr("disabled", true);
	$("#btncancel").css("display", "none");
    $("#btnsave").html(
      `<i class="fa fa-spinner fa-spin fa-fw"></i> Procesando....`
    );

	let lstcursos = []

	$("ul#lstcursos input[type=checkbox]:checked").each(function(){
		lstcursos.push({"idCurso": this.value})
	});

	let lstGrado = []
	$("ul#lstgrados input[type=checkbox]:checked").each(function(){
		lstGrado.push({"idGrado": this.value})
	});

	let lstSeccion = []
	$("ul#lstseccion input[type=checkbox]:checked").each(function(){
		lstSeccion.push({"idSeccion": this.value})
	});

	let jsonData = {
		"idPersonal":$("#idPersonal").val(),
		"nombre":$("#nombre").val(),
		"apellidos":$("#apellidos").val(),
		"numDocumento":$("#numDocumento").val(),
		"fechaNacimiento":$("#fechaNacimiento").val(),
		"ncelular":$("#ncelular").val(),
		"direccion":$("#direccion").val(),
		"sexo":$("#sexo").val(),
		"email":$("#email").val(),
		"nivelAcademico": $("#nivelEscolar").val(),
		"lstcursos":lstcursos,
		"lstGrado": lstGrado,
		"lstSeccion": lstSeccion
	}

	await $.ajax({
		url: '/api/v1/mantenimiento/guardarpersonal',
		type: 'POST',
		dataType: 'JSON',
		data: JSON.stringify(jsonData),
		contentType: "application/json"
	})
	.done(function({data, message}) {
		$("#btnsave").removeAttr("disabled");
		$("#btncancel").css("display", "block");
		$("#btnsave").html('Procesar Registro');
		getMessageALert('success','Muy Bien!', message)
		setTimeout(() => {
		 window.location.href = "personal"
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
			getMessageALert('error','Error!', err)
		}
	});
}

async function onBuscarDatosPersonaDocente(idpersona){
  await $.ajax({
    url: '/api/v1/mantenimiento/buscarPersonalDocenteID',
    type: 'GET',
    dataType: 'JSON',
    data: {"person": idpersona},
  })
  .done(function({data}) {
    console.log(data);
    $("#nivelEscolar").val(data.nivelAcademico)
    $("#idPersonal").val(data.idPersonal)
    onBusarGradoNivel();
	$("#nombre").val(data.nombre)
	$("#apellidos").val(data.apellidos)
	$("#numDocumento").val(data.numDocumento)
	$("#fechaNacimiento").val(data.fechaNacimiento)
	$("#ncelular").val(data.ncelular)
	$("#direccion").val(data.direccion)
	$("#sexo").val(data.sexo)
	$("#email").val(data.email)


	setTimeout(() => {
		$("ul#lstcursos input[type=checkbox]").each(function(){
			for (var i = 0; i < data.lstcursos.length; i++) {
				if(this.value == data.lstcursos[i].idCurso){
					this.setAttribute('checked',true)
				}			
			}
		});

		$("ul#lstseccion input[type=checkbox]").each(function(){
			for (var i = 0; i < data.lstSeccion.length; i++) {
				if(this.value == data.lstSeccion[i].idSeccion){
					this.setAttribute('checked',true)
				}			
			}
		});	
		$("ul#lstgrados input[type=checkbox]").each(function(){
			for (var i = 0; i < data.lstGrado.length; i++) {
				if(this.value == data.lstGrado[i].idGrado){
					this.setAttribute('checked',true)
				}			
			}
		});
	}, 500);
	
  })
  .fail(function(err) {
    console.log(err);
  });
  
}

function check(){

}

function getMessageALert(_icon, _title, _message){
	Swal.fire({
	  icon: _icon,
	  title: _title,
	  html: _message
	})
}


init();