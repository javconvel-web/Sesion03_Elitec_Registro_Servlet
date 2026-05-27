<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>


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
		<h1>Registro de Concurso</h1>
		<form id="formConcurso" method="post" novalidate >
			<div class="row" style="margin-top: 2%;">
				<div class="col-3">
					<label for="nombre">Nombre</label> 
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el nombre (4 a 30 caracteres)</div>
				</div>
				<div class="col-3">
					<label for="fecNac">Fecha de Inicio</label> 
					<input type="date" class="form-control" id="fecIni" name="fecIni" required>
					<div class="invalid-feedback">Ingrese la Fecha de Inicio</div>
				</div>
				<div class="col-3">
					<label for="fecNac">Fecha de Fin</label> 
					<input type="date" class="form-control" id="fecFin" name="fecFin" required>
					<div class="invalid-feedback">Ingrese la Fecha de Fin</div>
				</div>
				<div class="col-3">
					<label for="dni">Estado</label> 
					<select class="form-control" id="estado" name="estado" required>
                        <option value="">Seleccione el estado</option>
                        <option value="1">Activo</option>
                        <option value="0">Inactivo</option>
                     </select>
					<div class="invalid-feedback">Ingrese el estado</div>
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

		
		let form = $('#formConcurso')[0];
        if (form.checkValidity() === false) {
            $(form).addClass('was-validated');
            return;
        }

     
        $.ajax({
			url: 'registraConcursoAlias',
			type: 'POST',
			data: $(form).serialize(),
			success: function (response) {
				
				console.log('response >>> '+ response);
				//limpiar el formulario
				$('#formConcurso')[0].reset();
				
				//limpiar las validaciones
				$('#formConcurso').removeClass('was-validated');
				
				//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
				$('#formConcurso').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
				setTimeout(function () {
					$('.alert').remove();
				}, 3000);
			},
			error: function (xhr, status, error) {
				// Manejar errores aquí
				console.error('Error al registrar :', error);
			}
		});
	});
</script>

</body>
</html>ml>