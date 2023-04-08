var rolUser = ''

function init(){
   if(!window.sessionStorage.getItem('session')){
        onListarMenuLogin();
    } 
    onCargarDatiosLogin();
}
function onListarMenuLogin(){
    $.ajax({
          url: '/api/v1/mantenimiento/datosLogin',
          type: 'GET',
          dataType: 'json',
          data: {nivel: ''},
      })
      .done(function({rol,username}) {
          window.sessionStorage.setItem('session',JSON.stringify({"rolUser": rol, "username": username})) 
          onCargarDatiosLogin();        
      })
      .fail(function(err) {
          console.log(err);
      });
  }

  function onCerrarSeccion(){
    window.sessionStorage.clear()
    window.location.href = "/logout"
  }

  function onCargarDatiosLogin(){
    let datos = JSON.parse(window.sessionStorage.getItem('session'))
    $("#usernameLogin").html(datos.username)
    $("#cargoLogin").html(datos.rolUser)
    $("#topusernameLogin").html(datos.username)
    $("#topcargoLogin").html(datos.rolUser)
    rolUser = datos.rolUser;
  }

  init();