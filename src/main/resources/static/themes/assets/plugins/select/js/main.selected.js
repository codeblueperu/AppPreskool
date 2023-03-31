$(function() {

	$('select[multiple].active.3col').multiselect({
	  columns: 2,
	  placeholder: 'Mis Cursos',
	  search: true,
	  searchOptions: {
	      'default': 'Buscar Curso'
	  },
	  selectAll: true
	});

});