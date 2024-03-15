var formulario = document.getElementById("formulario");

function comprobarUsuario()
{
    var expresionRegular = /^[A-Za-z0-9Ññ]{3,}$/;
    var usuarioIntroducido = formulario.usuario.value;

    if (usuarioIntroducido.trim() != "" && expresionRegular.test(usuarioIntroducido))
    {
        formulario.usuario.style.borderColor = "#00e200";
        return true;
    }
    else
    {
        formulario.usuario.style.borderColor = "red";
        return false;
    }
}

function comprobarContraseña()
{
    var contraseñaIntroducida = formulario.contraseña.value;

    if (contraseñaIntroducida.trim() != "")
    {
        formulario.contraseña.style.borderColor = "#00e200";
        return true;
    }
    else
    {
        formulario.contraseña.style.borderColor = "red";
        return false;
    }
}

function validarFormulario()
{
    if (comprobarUsuario() && comprobarContraseña()) return true;
    else
    {
        alert("Los campos introducidos contienen errores");
        return false;
    }
}