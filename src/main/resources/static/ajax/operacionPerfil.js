var __table_perfil_operation__ = ""

function init(){
	__table_perfil_operation__ = $("#__table_perfil_operation__").DataTable({
    paging: false,
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
}

async function onSearchOperaciones(){	
	
	if($("#idperfil").val() == "" || $("#idmenu").val() == ""){
		return false
	}
	await $.ajax({
	url: '/webservice/v1/searchOperaciones',
	type: 'GET',
	dataType: 'json',
	data: {idperfil: $("#idperfil").val(),idmenu: $("#idmenu").val()},
	})
	.done(function({data,asigned}) {
		//console.log(asigned)
		
		
		__table_perfil_operation__.clear().draw();
		
		for (let i = 0; i < data.length; i++) {         
         let onselect = '',oninsert = '',onupdate = '',ondelete = '';
          if(asigned.length > 0){
			  for (let x = 0; x < asigned.length; x++){
				  //SLEECT
				  if(asigned[x].fkMenu.idMenu === data[i].idMenu && asigned[x].leer == "1"){
					  onselect = 'checked'
				  }	
				  
				  //INSERT
				  if(asigned[x].fkMenu.idMenu === data[i].idMenu && asigned[x].crear == "1"){
					  oninsert = 'checked'
				  }	
				  
				  //UPDATE
				  if(asigned[x].fkMenu.idMenu === data[i].idMenu && asigned[x].actualizar == "1"){
					  onupdate = 'checked'
				  }	
				  
				  //DELETE
				  if(asigned[x].fkMenu.idMenu === data[i].idMenu && asigned[x].eliminar == "1"){
					  ondelete = 'checked'
				  }			  
		  	 }
		  }
		   let chkselect = `<input type="checkbox" id="chkstatus" ${onselect}>`
		   let chkinsert = `<input type="checkbox" id="chkstatus" ${oninsert}>`
		   let chkupdatet = `<input type="checkbox" id="chkstatus" ${onupdate}>`
		   let chkdelete = `<input type="checkbox" id="chkstatus" ${ondelete}>`
		       
          
          __table_perfil_operation__.row
            .add([
              data[i].idMenu,
              data[i].nombre,
              chkselect,
              chkinsert,
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


 $("#__table_perfil_operation__ tbody").on("click", "#chkstatus", async function () {
	
  	let idoperacion = $(this).parents("tr").find("td").eq(0).html()
  	let chkselect = $(this).parents("tr").find("td").eq(2).find("input:checkbox:checked").val()
  	let chkinsert = $(this).parents("tr").find("td").eq(3).find("input:checkbox:checked").val()
  	let chkupdate = $(this).parents("tr").find("td").eq(4).find("input:checkbox:checked").val()
  	let chkdelete = $(this).parents("tr").find("td").eq(5).find("input:checkbox:checked").val()
  	let idperfil = $("#idperfil").val()
  	//console.log(chkinsert,chkupdate,chkdelete,idoperacion)
  	
  await $.ajax({
	url: '/webservice/v1/savePerfilOperation',
	type: 'GET',
	dataType: 'json',
	data: {"idoperacion": idoperacion, "idperfil": idperfil,
			"onselect": (chkselect == 'on' ? '1' : '0'),
			"oninsert": (chkinsert == 'on' ? '1' : '0'),
			"onupdate": (chkupdate == 'on' ? '1' : '0'),
			"ondelete": (chkdelete == 'on' ? '1' : '0') 
		},
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