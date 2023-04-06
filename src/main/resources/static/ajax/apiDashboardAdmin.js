
function init(){
	onListarDashboardCount();
}

async function onListarDashboardCount(){
	await $.ajax({
		url: '/api/v1/serices/countStudentTeacherSeccionCurse',
		type: 'GET',
		dataType: 'JSON',
		data: {"code": 'Alls'},
	})
	.done(function({estudiante,cursos,aulas,docentes,grados,groupprimaria,groupsecundaria}) {
		$("#ctstudent").html(estudiante.length)
		$("#ctteacher").html(docentes.length)
		$("#ctaulas").html(aulas.length)
		$("#ctcursos").html(cursos.length)
		console.log(estudiante,cursos,aulas,docentes,grados,groupprimaria);

		let gprimary = []
		let gsecundaria = []

		for (var i = 0; i < groupprimaria.length; i++) {
			gprimary.push(groupprimaria[i].total)
		}
		for (var i = 0; i < groupsecundaria.length; i++) {
			gsecundaria.push(groupsecundaria[i].total)
		}
		chartPrimariaGrados(gprimary,gsecundaria)

	})
	.fail(function(err) {
		console.log(err);
	});
	
}

function chartPrimariaGrados(gprimary,gsecundaria){
	var options = {
          series: [
          {
            name: "PRIMARIA",
            data: gprimary
          },
          {
            name: "SECUNDARIA",
            data: gsecundaria
          }
        ],
          chart: {
          height: 350,
          type: 'line',
          dropShadow: {
            enabled: true,
            color: '#000',
            top: 18,
            left: 7,
            blur: 10,
            opacity: 0.2
          },
          toolbar: {
            show: false
          }
        },
        colors: ['#77B6EA', '#545454'],
        dataLabels: {
          enabled: true,
        },
        stroke: {
          curve: 'smooth'
        },
        title: {
          text: 'GRAFICO POR GRADOS DE ESTUDIANTES',
          align: 'left'
        },
        grid: {
          borderColor: '#e7e7e7',
          row: {
            colors: ['#f3f3f3', 'transparent'], // takes an array which will be repeated on columns
            opacity: 0.5
          },
        },
        markers: {
          size: 1
        },
        xaxis: {
          categories: ['1ERO', '2DO', '3ERO', '4TO', '5TO', '6TO'],
          title: {
            text: 'Grados'
          }
        },
        yaxis: {
          title: {
            text: ''
          },
          min: 3,
          max: 100
        },
        legend: {
          position: 'top',
          horizontalAlign: 'right',
          floating: true,
          offsetY: -25,
          offsetX: -5
        }
        };

        var chart = new ApexCharts(document.querySelector("#chartprimarygroup"), options);
        chart.render();
}

init();