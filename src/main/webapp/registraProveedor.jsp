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
		<h1>Registro de Proveedor</h1>
		<form id="formProveedor" method="post" novalidate >
			<div class="row" style="margin-top: 2%;">
				<div class="col-8">
					<label for="registro">Nombre</label> 
					<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" required>
					<div class="invalid-feedback">Ingrese el Nombre</div>
				</div>
				<div class="col-4">
					<label for="titulo">DNI</label> 
					<input type="text" class="form-control" id="dni" name="dni" placeholder="Ingrese el DNI" maxlength="8" required>
					<div class="invalid-feedback">Ingrese el DNI</div>
				</div>
			</div>
			<div class="row" style="margin-top: 2%;">	
				<div class="col-4">
					<label for="tipo">Tipo</label> 
					<select class="form-control" id="tipo" name="tipo" required>
                        <option value="">Seleccione un tipo</option>
                    </select>    
					<div class="invalid-feedback">Ingrese el tipo</div>
				</div>
				<div class="col-4">
					<label for="pais">País</label> 
					<select class="form-control" id="pais" name="pais" required>
                        <option value="">Seleccione una categoría</option>
                    </select>    
					<div class="invalid-feedback">Ingrese el país</div>
				</div>
			</div>
			<div class="row justify-content-center" style="margin-top: 2%">
				<button class="btn btn-primary" id="btnRegistrar"style="width: 200px">Registrar</button>
			</div>
		</form>
	</div>

<script type="text/javascript">
  $(document).ready(function () {
	  $.ajax({
			url: 'cargaTipoAlias',
			type: 'GET',
			success: function (data) {
				console.log('Tipos cargados:', data);
				var comboBox = $('#tipo');
				data.forEach(function (obj) {
					comboBox.append('<option value="' + obj.idTipo + '">' + obj.descripcion + '</option>');
				});
			},
			error: function (xhr, status, error) {
				console.error('Error al cargar tipos:', error);
			}
		});

	  $.ajax({
		  url: 'cargaPaisAlias',
		  type: 'GET',
		  success: function (data) {
			  console.log('Paises cargados:', data);
			  var comboBox = $('#pais');
			  data.forEach(function (obj) {
				  comboBox.append('<option value="' + obj.idPais + '">' + obj.nombre + '</option>');
			  });
		  },
		  error: function (xhr, status, error) {
			  console.error('Error al cargar paises:', error);
		  }
	  });
  });

	$("#btnRegistrar").click(function(e) {
		console.log("click en registrar");		
		e.preventDefault(); //Evita que el formulario se envíe automáticamente

		
		let form = $('#formProveedor')[0];
        if (form.checkValidity() === false) {
            $(form).addClass('was-validated');
            return;
        }

     
        $.ajax({
			url: 'registraProveedorAlias',
			type: 'POST',
			data: $(form).serialize(),
			success: function (response) {
				
				console.log('response >>> '+ response);
				//limpiar el formulario
				$('#formProveedor')[0].reset();
				
				//limpiar las validaciones
				$('#formProveedor').removeClass('was-validated');
				
				//enviar un mensaje de éxito al usuario en forma de div que dure 3 segundos
				$('#formProveedor').prepend('<div class="alert alert-success" role="alert">'+ response.mensajeSalida +'</div>');
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
</html>