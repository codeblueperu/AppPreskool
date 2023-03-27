var __table_perfil_menu__ = "";
function init(){
	__table_perfil_menu__ = $("#__table_perfil_menu__").DataTable({
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

async function onSendSubMenu(){	
	await $.ajax({
	url: '/webservice/v1/redirectSubMenu',
	type: 'GET',
	dataType: 'json',
	data: {idperfil: $("#idperfil").val(), idmenu: $("#idmenu").val()},
	})
	.done(function({data,asigned}) {
		console.log(asigned)
		
		__table_perfil_menu__.clear().draw();
		
		for (let i = 0; i < data.length; i++) {         
         let checked = ''
          if(asigned.length > 0){
			  for (let x = 0; x < asigned.length; x++){
				  if(asigned[x].fkMenu.idMenu === data[i].idMenu && asigned[x].estado == "1"){
					  checked = 'checked'
				  }			  
		  	 }
		  }
		   let checkbox = `<input type="checkbox" id="chkstatus" ${checked}>`
          
          __table_perfil_menu__.row
            .add([
              data[i].idMenu,
              data[i].nombre,
              checkbox
            ])
            .draw(false);
        }
	})
	.fail(function(error) {
		console.log(error);
	});

}

 $("#__table_perfil_menu__ tbody").on("click", "#chkstatus", async function () {
	
  	let idmenu = $(this).parents("tr").find("td").eq(0).html()
  	let status = $(this).parents("tr").find("td").eq(2).find("input:checkbox:checked").val()
  	let idperfil = $("#idperfil").val()
  	
  await $.ajax({
	url: '/webservice/v1/saveperfilmenu',
	type: 'GET',
	dataType: 'json',
	data: {"idperfil": idperfil, "idmenu": idmenu,"estado": (status == 'on' ? '1' : '0') },
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
		  html: `<h5 style="fot-size:14px;">${data}</h5>`
		})
	})
	.fail(function(error) {
		console.log(error);
	});
  	
  	
});

async function onSavePerfilMenu(){	
//	await $.ajax({
//	url: '/webservice/v1/redirectSubMenu',
//	type: 'GET',
//	dataType: 'json',
//	data: {idperfil: $("#idperfil").val(), idmenu: $("#idmenu").val()},
//	})
//	.done(function({data}) {
//		__table_perfil_menu__.clear().draw();
//		
//		for (let i = 0; i < data.length; i++) {         
//          let checkbox = `<input type="checkbox">`
//          __table_perfil_menu__.row
//            .add([
//              data[i].idMenu,
//              data[i].nombre,
//              checkbox
//            ])
//            .draw(false);
//        }
//	})
//	.fail(function(error) {
//		console.log(error);
//	});

}

init();