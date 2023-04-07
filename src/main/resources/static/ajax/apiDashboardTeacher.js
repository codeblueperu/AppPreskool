function init(){
    onListarDashboardCount();
}

async function onListarDashboardCount(){
	await $.ajax({
		url: '/api/v1/serices/dataDashboardTeacher',
		type: 'GET',
		dataType: 'JSON',
		data: {"code": 'Alls'},
	})
	.done(function({docente,tareas,alumnos}) {
		$("#ctclases").html(docente[0].lstSeccion.length)        
        $("#ctgrados").html(docente[0].lstGrado.length)
        $("#cttareas").html(tareas.length)
        $("#ctalumnos").html(alumnos)
		
        let tablegrado = ''
        for (let i = 0; i < docente[0].lstGrado.length; i++) {
            tablegrado += `<tr>
                        <td>${i+1}</td>
                        <td>${docente[0].lstGrado[i].tipoGrado}</td>
                        <td>${docente[0].lstGrado[i].gradoDescripcion}</td>
                    </tr>`       
        } 
        $("#__grados___").html(tablegrado)

        let tablecurso = ''
        for (let i = 0; i < docente[0].lstcursos.length; i++) {
            tablecurso += `<tr>
                        <td>${i+1}</td>
                        <td>${docente[0].lstcursos[i].nombreCurso}</td>
                        <td>${docente[0].lstcursos[i].estadoCurso}</td>
                    </tr>`       
        } 
        $("#__cursos___").html(tablecurso)
	})
	.fail(function(err) {
		console.log(err);
	});
	
}


init();