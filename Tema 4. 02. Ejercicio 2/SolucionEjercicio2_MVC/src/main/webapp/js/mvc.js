

function onClickCheckboxValidaciones(){
  let ch = document.querySelector("#checkboxDeshabilitarValidacionesCliente");
  if (ch.checked){
    document.getElementById("formulario").setAttribute("novalidate","novalidate");
  } else {
    document.getElementById("formulario").removeAttribute("novalidate");
  }
}

