var grados = [];
var __table_students__ = "";


function init(){
	__table_students__ = $("#__table_students__").DataTable({
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

async function onBuscarTareaDocente(){
	if($("#cboidGrado").val() == "" || $("#cboSeccion").val() == "" || $("#cbocurso").val() == "" || $("#cbodocente").val() == ""){
		return false
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarTareaDocente',
		type: 'GET',
		dataType: 'json',
		data: {"idgrado": $("#cboidGrado").val(), "idsecion": $("#cboSeccion").val(),
		"idcurso": $("#cbocurso").val(), "iddocente": $("#cbodocente").val()},
	})
	.done(function({data}) {
		//console.log(grados)
		let option = `<option value="">---: SELECCIONE :---</option>`
		for (var i = 0; i < data.length; i++) {			
			option += `<option value="${data[i].idTarea}">${data[i].tema} </option>`	
		}
		$("#cbotareas").html(option);
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

async function onBusarEstudiantes(){
	if ($("#nivelEscolar").val() == "" || $("#cbotareas").val() == ""  || $("#cboidGrado").val() == "" || $("#cboSeccion").val() == "" || $("#cbocurso").val() == "") {
		getMessageALert('warning','Lo sentimos', 'Seleccione las opciones segun su busqueda');
		return false
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarAlumnosGradoNivelSeccionTarea',
		type: 'GET',
		dataType: 'json',
		data: {"nivel": $("#nivelEscolar").val(),
		"idgrado": $("#cboidGrado").val(),"idsecion": $("#cboSeccion").val(),
		"idcurso": $("#cbocurso").val(), "idtarea": $("#cbotareas").val()},
	})
	.done(function({data,presentaron}) {
		console.log(presentaron)
		__table_students__.clear().draw();
		if(data.length == 0){
			getMessageALert('warning','Lo sentimos!','No se encontraron estudiantes inscritos para los parametros de busqueda.')
			return
		}
		
		for (let i = 0; i < data.length; i++) {         
         	let presento = '',nopresento = '';

         	let nota_alumno = ""
          	if(presentaron.length > 0){
			  	for (let x = 0; x < presentaron.length; x++){
				  	//PRESENTE
					if(presentaron[x].alumno.idEstudiante === data[i].idEstudiante && presentaron[x].estadoPresenta == "SI"){
						presento = 'checked'
						nota_alumno = presentaron[x].nota
					}	
					  
					//TARDE
					if(presentaron[x].alumno.idEstudiante === data[i].idEstudiante && presentaron[x].estadoPresenta == "NO"){
						nopresento = 'checked'
						nota_alumno = presentaron[x].nota
					}	

								  		  
		  	 	}
		 	 }

		   let situacion = `<div class="col-lg-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input rbnstatus" type="radio" name="status${data[i].idEstudiante}" value="SI" id="p${data[i].idEstudiante}" ${presento}>
						<label class="form-check-label" for="p${data[i].idEstudiante}">
						SI
						</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input rbnstatus" type="radio" name="status${data[i].idEstudiante}" value="NO" id="t${data[i].idEstudiante}" ${nopresento}>
						<label class="form-check-label" for="t${data[i].idEstudiante}">
						NO
						</label>
					</div>
				</div>`

			let input = `<input type="number" min="0" style="width:90px" id="txtnota" value="${nota_alumno}">`
          
          __table_students__.row
            .add([
              data[i].idEstudiante,
              data[i].nombreEstudiante +  ' ' +  data[i].apPaterno +  ' ' +  data[i].apMaterno,
              data[i].turno,
              data[i].nivelEscolar,
              data[i].gradoAlumno.gradoDescripcion + ' ' + data[i].seccionAlumno.descripcionSeccion,
              situacion,
              input
            ])
            .draw(false);
        }
		
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

$("#__table_students__ tbody").on("click", ".rbnstatus", function () {
	
	if($("#token_cr").val() == "0"){
		getMessageALert("warning","Lo sentimos","Estimado usuario, usted no cuenta con permisos para continuar con esta operación.")
		return false
	}
  	let fecha = moment(new Date()).format('yyyy-MM-DD')
  	let hora = moment(new Date()).format('H:mm:ss')
  	let estado_presenta =  $(this).parents("tr").find("td").eq(5).find("input:radio:checked").val()
	let jsonData = {
		"idTareaAlumno": null,
		"estadoPresenta": estado_presenta,
		"nota": $(this).parents("tr").find("td").eq(6).find("input").val(),
		"alumno": {
			"idEstudiante": $(this).parents("tr").find("td").eq(0).html(),
		},
		"tarea":{
			"idTarea": $("#cbotareas").val()
		}
	}
	onGuardarTareaAlumno(jsonData,estado_presenta);	
});

$("#__table_students__ tbody").on("keypress", "#txtnota", function (e) {
	if($("#token_cr").val() == "0"){
		getMessageALert("warning","Lo sentimos","Estimado usuario, usted no cuenta con permisos para continuar con esta operación.")
		return false
	}
	
	if (e.keyCode === 13 && !e.shiftKey) {
	  	let fecha = moment(new Date()).format('yyyy-MM-DD')
	  	let hora = moment(new Date()).format('H:mm:ss')
	  	let estado_presenta =  $(this).parents("tr").find("td").eq(5).find("input:radio:checked").val()
	  	if(estado_presenta == "" || estado_presenta == null){
	  		setToast('bottom-end','error','Seleccione SI o NO')
	  		return false
	  	}
		let jsonData = {
			"idTareaAlumno": null,
			"estadoPresenta": estado_presenta,
			"nota": $(this).parents("tr").find("td").eq(6).find("input").val(),
			"alumno": {
				"idEstudiante": $(this).parents("tr").find("td").eq(0).html(),
			},
			"tarea":{
				"idTarea": $("#cbotareas").val()
			}
		}
		onGuardarTareaAlumno(jsonData,estado_presenta);	
	}
});

async function onGuardarTareaAlumno(jsonData,estado_presenta){
	await $.ajax({
		url: '/api/v1/mantenimiento/guardarTareaAlumno',
		type: 'POST',
		dataType: 'JSON',
		data: JSON.stringify(jsonData),
		contentType: "application/json"
	})
	.done(function({data}) {
		if(estado_presenta == 'SI'){
			setToast('top-end','success','SI Presento')
		}else{
			setToast('top-end','warning','No Presento')
		}
		
	})
	.fail(function(err) {
		console.log(err);
		if(err.status === 409){
			getMessageALert('warning','Upps!', err.responseJSON.message)
		}else if(err.status === 404){
			getMessageALert('warning','No Hay!', err.responseJSON.message)
		}else{
			getMessageALert('error','Error!', err.responseJSON.mensaje)
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