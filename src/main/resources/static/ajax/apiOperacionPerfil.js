var __table_operaciones__ = ""

function init(){
	__table_operaciones__ = $("#__table_operaciones__").DataTable({
    paging: true,
    lengthChange: true,
    searching: true,
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
}

async function onListaOperaciones(){	
	
	if($("#idperfil").val() == "" || $("#token_vw").val() == "0"){
		return false
	}
	await $.ajax({
	url: '/webservice/v1/listaOperaciones',
	type: 'GET',
	dataType: 'json',
	data: {idperfil: $("#idperfil").val()},
	})
	.done(function({data,asigned}) {		
		__table_operaciones__.clear().draw();
		
		for (let i = 0; i < data.length; i++) {         
         let onselect = '',oninsert = '',onupdate = '',ondelete = '';
          if(asigned.length > 0){
			  for (let x = 0; x < asigned.length; x++){
				  //SLEECT
				  if(asigned[x].fkOperaciones.idOperaciones === data[i].idOperaciones && asigned[x].leer == "1"){
					  onselect = 'checked'
				  }	
				  
				  //INSERT
				  if(asigned[x].fkOperaciones.idOperaciones === data[i].idOperaciones && asigned[x].crear == "1"){
					  oninsert = 'checked'
				  }	
				  
				  //UPDATE
				  if(asigned[x].fkOperaciones.idOperaciones === data[i].idOperaciones && asigned[x].actualizar == "1"){
					  onupdate = 'checked'
				  }	
				  
				  //DELETE
				  if(asigned[x].fkOperaciones.idOperaciones === data[i].idOperaciones && asigned[x].eliminar == "1"){
					  ondelete = 'checked'
				  }			  
		  	 }
		  }
			let chkselect = `<div class="form-check form-switch">
						<input th:if="checkbox != null " class="form-check-input" type="checkbox" id="chkstatus"
							value="1" ${onselect}>
						</div>`
		   let chkinsert = `<div class="form-check form-switch">
						<input th:if="checkbox != null " class="form-check-input" type="checkbox" id="chkstatus"
							value="1" ${oninsert}>
						</div>`
		   let chkupdatet = `<div class="form-check form-switch">
						<input th:if="checkbox != null " class="form-check-input" type="checkbox" id="chkstatus"
							value="1" ${onupdate}>
						</div>`
		   let chkdelete = `<div class="form-check form-switch">
						<input th:if="checkbox != null " class="form-check-input" type="checkbox" id="chkstatus"
							value="1" ${ondelete}>
						</div>`
		       
          
			__table_operaciones__.row
            .add([
              data[i].idOperaciones,
              data[i].nombre,
			  chkinsert,
              chkselect,             
              chkupdatet,
              chkdelete
            ])
            .draw(false);
        }
	})
	.fail(function(error) {
		console.log(error);
	});
}
 

 $("#__table_operaciones__ tbody").on("click", "#chkstatus", async function () {

	if($("#token_cr").val() == "0"){
        Swal.fire({"icon": "warning", "title": "warning", "text": "Lo sentimos no cuentas con permisos para otorgar estos accesos."})
        return false
    }

	let fecha = moment(new Date()).format('yyyy-MM-DD')
  	let idoperacion = $(this).parents("tr").find("td").eq(0).html()
  	let chkselect = $(this).parents("tr").find("td").eq(3).find("input:checkbox:checked").val()
  	let chkinsert = $(this).parents("tr").find("td").eq(2).find("input:checkbox:checked").val()
  	let chkupdate = $(this).parents("tr").find("td").eq(4).find("input:checkbox:checked").val()
  	let chkdelete = $(this).parents("tr").find("td").eq(5).find("input:checkbox:checked").val()
  	let idperfil = $("#idperfil").val()
  	//console.log(chkinsert,chkupdate,chkdelete,idoperacion)
  	let jsonData = {
		"crear": (chkinsert == '1' ? '1' : '0'),
		"leer": (chkselect == '1' ? '1' : '0'),
		"actualizar": (chkupdate == '1' ? '1' : '0'),
		"eliminar": (chkdelete == '1' ? '1' : '0'),
		"estado": "1",
		"fechaCreacionPerOpe": fecha,
		"fechaModificacionPerOpe": fecha,
		"fkPerfil": {
			"idPerfil": $("#idperfil").val()
		},
		"fkOperaciones":{
			"idOperaciones":idoperacion
		}
	}
	
  await $.ajax({
	url: '/webservice/v1/guardarPerfilOperaciones',
	type: 'POST',
	dataType: 'json',
	data: JSON.stringify(jsonData),
	contentType: "application/json"
	})
	.done(function({data}) {
		const Toast = Swal.mixin({
		  toast: true,
		  position: 'top-end',
		  showConfirmButton: false,
		  timer: 3000,
		  timerProgressBar: true
		})
		
		Toast.fire({
		  icon: 'success',
		  html: `<h5 style="fot-size:11px;">${data}</h5>`
		})
	})
	.fail(function(error) {
		console.log(error);
	});
  	
  	
});



init();