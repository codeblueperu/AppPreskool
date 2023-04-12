var rolUser = "";

function init() {
  if (!window.sessionStorage.getItem("session")) {
    onListarMenuLogin();
  }
  onCargarDatiosLogin();
}
function onListarMenuLogin() {
  $.ajax({
    url: "/api/v1/mantenimiento/datosLogin",
    type: "GET",
    dataType: "json",
    data: { nivel: "" },
  })
    .done(function ({ rol, username }) {
      window.sessionStorage.setItem(
        "session",
        JSON.stringify({ rolUser: rol, username: username })
      );
      onCargarDatiosLogin();
    })
    .fail(function (err) {
      console.log(err);
    });
}

function onCerrarSeccion() {
  window.sessionStorage.clear();
  window.location.href = "/logout";
}

function onCargarDatiosLogin() {
  let datos = JSON.parse(window.sessionStorage.getItem("session"));
  $("#usernameLogin").html(datos.username);
  $("#cargoLogin").html(datos.rolUser);
  $("#topusernameLogin").html(datos.username);
  $("#topcargoLogin").html(datos.rolUser);
  rolUser = datos.rolUser;
}

function openModal() {
  $("#mdlClavesadue").modal("show");
}

async function onUpdateClave() {
  if ($("#txtsadunewclave").val() == "") {
    $("#divnewClavesadu").html("este campo es requerido");
    return false;
  }

  if ($("#txtsadurepitnewclave").val() == "" ) {
    $("#divrepidnewClavesadu").html("este campo es requerido");
    return false;
  }

  if($("#txtsadunewclave").val() != $("#txtsadurepitnewclave").val()){
    $("#divnewClavesadu").html("Los campos no coinciden");
    $("#divrepidnewClavesadu").html("Los campos no coinciden");
    return false;
  }

  await $.ajax({
    url: "/webservice/v1/updatepassword",
    type: "POST",
    dataType: "json",
    data: { password: $("#txtsadunewclave").val() },
  })
    .done(function ({ data }) {
        $("#mdlClavesadue").modal("hide");
      Swal.fire({"icon":"success","title":"Muy Bien!", "text": data})
    })
    .fail(function (error) {
      console.log(error);
    });
}
init();
