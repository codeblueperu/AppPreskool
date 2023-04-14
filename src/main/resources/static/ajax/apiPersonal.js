
var __table_docente__ = "";
function init(){
	__table_docente__ = $("#__table_docente__").DataTable({
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

  if($("#token_vw").val() == "1"){
    onListaPersonalAll();	
  }
 
}

async function onListaPersonalAll(){
	await $.ajax({
		url: '/api/v1/mantenimiento/listapersonalAll',
		type: 'GET',
		dataType: 'json',
		data: {nivel: ''},
	})
	.done(function({data}) {
    let update = $("#token_up").val();
    let eliminar = $("#token_dt").val();
		__table_docente__.clear().draw();
		for (let i = 0; i < data.length; i++) {  
    let deletebtn  = `<button onclick="onEliminarEstudiante(${data[i].idPersonal})" class="btn btn-sm bg-success-light me-2" >
                        <i class="feather-trash-2"></i></button>`   
    let editbtn = `<a href="/vieweditpersonal?person=${data[i].idPersonal}" class="btn btn-sm bg-danger-light" >
                        <i class="feather-edit"></i></a>`         	           
          __table_docente__.row
            .add([
              data[i].numDocumento,
              data[i].nombre + ' ' + data[i].apellidos,
              data[i].ncelular,
              data[i].fechaNacimiento,
              data[i].email,              
              data[i].direccion,
              `${(update == "1" ? editbtn : '')} ${(eliminar  == "1" ? deletebtn : '')}`
            ])
            .draw(false);
        }
	})
	.fail(function(err) {
		console.log(err);
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