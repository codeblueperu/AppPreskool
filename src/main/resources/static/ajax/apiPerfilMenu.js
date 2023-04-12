var __table__menu__perfil__ = "";
function init(){
	__table__menu__perfil__ = $("#__table__menu__perfil__").DataTable({
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

async function onBuscarSubMenu(){	
	await $.ajax({
	url: '/webservice/v1/buscarSubMenu',
	type: 'GET',
	dataType: 'json',
	data: {idperfil: $("#idperfil").val(), idmenu: $("#idmenu").val()},
	})
	.done(function({data,asigned}) {
		console.log(asigned)
		
		__table__menu__perfil__.clear().draw();
		
		for (let i = 0; i < data.length; i++) {         
         let checked = ''
          if(asigned.length > 0){
			  for (let x = 0; x < asigned.length; x++){
				  if(asigned[x].idMenu === data[i].idMenu && asigned[x].estado == "1"){
					  checked = 'checked'
				  }			  
		  	 }
		  }
		   let checkbox = ` <div class="form-check form-switch">
                        <input class="form-check-input" type="checkbox" id="chkstatus"
                            value="1" ${(checked)}>
                    </div>`
          
           __table__menu__perfil__.row
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

$("#__table__menu__perfil__ tbody").on("click", "#chkstatus", async function () {
	let status = $(this).parents("tr").find("td").eq(2).find("input:checkbox:checked").val()
    let idmenu = $(this).parents("tr").find("td").eq(0).html()        
    let idperfil = $("#idperfil").val()
    
    await $.ajax({
    url: '/webservice/v1/saveperfilmenu',
    type: 'GET',
    dataType: 'json',
    data: {"idperfil": idperfil, "idmenu": idmenu,"estado": (status == '1' ? '1' : '0') },
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

init()