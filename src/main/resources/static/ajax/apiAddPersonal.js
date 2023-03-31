

function init(){
	onListarCursos();
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
	.done(function({data}) {
		getMessageALert('success','Muy Bien!', `El docente ${data.nombre} fue registrado con éxito.`)
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


function getMessageALert(_icon, _title, _message){
	Swal.fire({
	  icon: _icon,
	  title: _title,
	  html: _message
	})
}


init();