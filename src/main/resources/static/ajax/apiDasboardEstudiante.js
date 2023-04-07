function init(){
	onListarDashboardCount();
}

async function onListarDashboardCount(){
	await $.ajax({
		url: '/api/v1/serices/dataDashboardStudent',
		type: 'GET',
		dataType: 'JSON',
		data: {"code": 'Alls'},
	})
	.done(function({estudiante,docentes,tarearealizada,inasistencias}) {
		$("#ctstudent").html(estudiante.length)
		$("#ctteacher").html(docentes.length)
		 $("#cttareasi").html(tarearealizada.length)
		$("#ctfalto").html(inasistencias.length) 
		console.log(estudiante,docentes,tarearealizada,inasistencias);
	})
	.fail(function(err) {
		console.log(err);
	});
	
}

init();