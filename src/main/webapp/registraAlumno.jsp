<html>
<head>
<meta charset="UTF-8">
<title>Registrar Alumno</title>


<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/bootstrap.esm.js" type="text/javascript"></script>
<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-grid.css" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/bootstrap-utilities.css" rel="stylesheet">


</head>
<body>
	<div class="container">
		<h1>Registro de Alumno</h1>
		<form id="formAlumno" action="registraAlumnoAlias" method="post" novalidate >
			<div class="row" style="margin-top: 2%;">
				<div class="col-3">
					<label for="nombre">Nombre</label> 
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el nombre (4 a 30 caracteres)</div>
				</div>
				<div class="col-3">
					<label for="dni">DNI</label> 
					<input type="text"	class="form-control" id="dni" name="dni" placeholder="Ingrese el DNI" maxlength="8" required>
					<div class="invalid-feedback">Ingrese el DNI (8 dígitos)</div>
				</div>
				<div class="col-3">
					<label for="correo">Correo</label> 
					<input type="email" class="form-control" id="correo" name="correo"	placeholder="Ingrese el correo" required>
					<div class="invalid-feedback">Ingrese el correo</div>
				</div>
				<div class="col-3">
					<label for="fecNac">Fecha de Nacimiento</label> 
					<input type="date" class="form-control" id="fecNac" name="fecNac" required>
					<div class="invalid-feedback">Ingrese la Fecha de Nacimiento (Mayor de 18 años)</div>
				</div>
			</div>
			<div class="row justify-content-center" style="margin-top: 2%">
				<button class="btn btn-primary" id="btnRegistrar"style="width: 200px">Registrar</button>
			</div>
		</form>
	</div>

<script type="text/javascript">
	$("#btnRegistrar").click(function(e) {
		console.log("click en registrar");		
		e.preventDefault(); //Evita que el formulario se envíe automáticamente

		
		let form = $('#formAlumno')[0];
        if (form.checkValidity() === false) {
            $(form).addClass('was-validated');
            return;
        }

     
        $.ajax({
			url: 'registraAlumnoAlias',
			type: 'POST',
			data: $(form).serialize(),
			success: function (response) {
				
				console.log('Alumno registrado exitosamente');
				//limpiar el formulario
				$('#formAlumno')[0].reset();
				
				//limpiar las validaciones
				$('#formAlumno').removeClass('was-validated');
				
				//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
				$('#formAlumno').prepend('<div class="alert alert-success" role="alert">Alumno registrado exitosamente</div>');
				setTimeout(function () {
					$('.alert').remove();
				}, 3000);
			},
			error: function (xhr, status, error) {
				// Manejar errores aquí
				console.error('Error al registrar el alumno:', error);
				// Puedes mostrar un mensaje de error al usuario
			}
		});
	});
</script>

</body>
</html>