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

async function onBusarEstudiantes(){
	
	await $.ajax({
		url: '/api/v1/mantenimiento/buscarAlumnosGradoNivelSeccionPeriodo',
		type: 'GET',
		dataType: 'json',
		data: {"idperiodo": 1,"nivel": $("#nivelEscolar").val(),
		"idgrado": $("#cboidGrado").val(),"idsecion": $("#cboSeccion").val(),
		"idcurso": $("#cbocurso").val(), "fecha": $("#dtpfecha").val()},
	})
	.done(function({asistencia, data}) {
		__table_students__.clear().draw();
		if(data.length == 0){
			getMessageALert('warning','Lo sentimos!','No se encontraron estudiantes inscritos para los parametros de busqueda.')
			return
		}
		
		for (let i = 0; i < data.length; i++) {         
         let present = '',tarde = '',ausente = '';
          if(asistencia.length > 0){
			  for (let x = 0; x < asistencia.length; x++){
				  //PRESENTE
				  if(asistencia[x].estudiante.idEstudiante === data[i].idEstudiante && asistencia[x].descripcion_asistencia == "1"){
					  present = 'checked'
				  }	
				  
				  //TARDE
				 if(asistencia[x].estudiante.idEstudiante === data[i].idEstudiante && asistencia[x].descripcion_asistencia == "2"){
					  tarde = 'checked'
				 }
				  				  
				  //FALTO
				  if(asistencia[x].estudiante.idEstudiante === data[i].idEstudiante && asistencia[x].descripcion_asistencia == "0"){
					  ausente = 'checked'
				  }
				  		  
		  	 }
		  }

		   let situacion = `<div class="col-lg-9">
					<div class="form-check form-check-inline">
						<input class="form-check-input rbnstatus" type="radio" name="status${data[i].idEstudiante}" value="1" id="p${data[i].idEstudiante}" ${present}>
						<label class="form-check-label" for="p${data[i].idEstudiante}">
						Presente
						</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input rbnstatus" type="radio" name="status${data[i].idEstudiante}" value="2" id="t${data[i].idEstudiante}"  ${tarde}>
						<label class="form-check-label" for="t${data[i].idEstudiante}">
						Tarde
						</label>
					</div>
					<div class="form-check form-check-inline">
						<input class="form-check-input rbnstatus" type="radio" name="status${data[i].idEstudiante}" value="0" id="a${data[i].idEstudiante}" ${ausente}>
						<label class="form-check-label" for="a${data[i].idEstudiante}">
						Ausente
						</label>
					</div>
				</div>`
          
          __table_students__.row
            .add([
              data[i].idEstudiante,
              data[i].nombreEstudiante +  ' ' +  data[i].apPaterno +  ' ' +  data[i].apMaterno,
              data[i].turno,
              data[i].nivelEscolar,
              data[i].gradoAlumno.gradoDescripcion + ' ' + data[i].seccionAlumno.descripcionSeccion,
              situacion
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

$("#__table_students__ tbody").on("click", ".rbnstatus", async function () {
	
  	let fecha = moment($("#dtpfecha").val()).format('yyyy-MM-DD')
  	let hora = moment(new Date()).format('H:mm:ss')
  	let estado_asistencia =  $(this).parents("tr").find("td").eq(5).find("input:radio:checked").val()

	let jsonData = {
		"idAsistencia": null,
		"descripcion_asistencia": estado_asistencia,
		"observacion": "-",
		"fecha": $("#dtpfecha").val(),
		"hora": hora,
		"estudiante": {
			"idEstudiante": $(this).parents("tr").find("td").eq(0).html(),
		},
		"curso":{
			"idCurso": $("#cbocurso").val()
		}
	}
	await $.ajax({
		url: '/api/v1/mantenimiento/guardarasistencia',
		type: 'POST',
		dataType: 'JSON',
		data: JSON.stringify(jsonData),
		contentType: "application/json"
	})
	.done(function({data}) {
		if(estado_asistencia == '1'){
			setToast('top-end','success','Alumno Presente')
		}else if(estado_asistencia == '2'){
			setToast('top-end','warning','Alumno Tarde')
		}else{
			setToast('top-end','error','Alumno Ausente')
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
    	
  	
});


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