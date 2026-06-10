<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script src="js/bootstrap.js" type="text/javascript"></script>
<script src="js/bootstrap.bundle.js" type="text/javascript"></script>
<script src="js/bootstrap.esm.js" type="text/javascript"></script>
<script src="js/jquery-4.0.0.min.js" type="text/javascript"></script>
<script src="js/datatables.js" type="text/javascript"></script>

<link href="css/bootstrap.css" rel="stylesheet">
<link href="css/bootstrap-grid.css" rel="stylesheet">
<link href="css/bootstrap-reboot.css" rel="stylesheet">
<link href="css/bootstrap-utilities.css" rel="stylesheet">
<link href="css/datatables.css" rel="stylesheet">

</head>
<body>

<div class="container">
    <h1>Consulta de Alumno</h1>
    <div class="row" style="margin-top: 4%;">
    	<div class="col-4">
    		<label for="nombre">Nombre</label> 
			<input type="text" class="form-control" id="nombre" name="nombre" placeholder="Ingrese el nombre" maxlength="30" >
    	</div>	
    	<div class="col-4">
    		<label for="dni">DNI</label> 
			<input type="text" class="form-control" id="dni" name="dni" placeholder="Ingrese el dni" maxlength="8" >
    	</div>
    	<div class="col-4">
    		<label for="email">Correo Electrónico</label> 
			<input type="email" class="form-control" id="correo" name="correo" placeholder="Ingrese el correo" >		
    	</div>	
    </div>
    <div class="row" style="margin-top: 4%;">
    	<div class="col-4">
    		<label for="fecNacInicio">Fecha de Nacimiento(Desde)</label> 
			<input type="date" class="form-control" id="fecNacInicio" name="fecNacInicio">
    	</div>
    	<div class="col-4">
    		<label for="fecNacFin">Fecha de Nacimiento(Hasta)</label> 
			<input type="date" class="form-control" id="fecNacFin" name="fecNacFin">
    	</div>
    </div>
    <div class="row justify-content-center" style="margin-top: 2%">
		<button class="btn btn-primary" id="btnFiltrar"style="width: 200px">Filtrar</button>
	</div>
	<div class="row" style="margin-top: 4%;">
		<div class="col-12">
			<table class="table table-striped" id="id_table">
				<thead>
					<tr>
						<th>Código</th>
						<th>Nombre</th>
						<th>DNI</th>
						<th>Correo</th>
						<th>Fecha de Nacimiento</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>    
</div>

<script type="text/javascript">
	$('#id_table').DataTable();

	$("#btnFiltrar").click(function (e) {
		console.log("click en filtrar");
		e.preventDefault(); //Evita que el formulario se envíe automáticamente
		
		let nombre = $('#nombre').val();
		let dni = $('#dni').val();
		let correo = $('#correo').val();
		let fecNacInicio = $('#fecNacInicio').val();
		let fecNacFin = $('#fecNacFin').val();
		
		console.log("nombre: " + nombre);
		console.log("dni: " + dni);
		console.log("correo: " + correo);
		console.log("fecNacInicio: " + fecNacInicio);
		console.log("fecNacFin: " + fecNacFin);
		
		//enviar los datos al controlador
		$.ajax({
			url: 'consultaAlumnoAlias',
			type: 'GET',
			data: {
				nombre: nombre,
				dni: dni,
				correo: correo,
				fecNacInicio: fecNacInicio,
				fecNacFin: fecNacFin
			},
			success: function (response) {
				console.log(response);
				agregarGrilla(response);
			}
		});
	});	
	
	function agregarGrilla(lista){
		 $('#id_table').DataTable().clear();
		 $('#id_table').DataTable().destroy();
		 $('#id_table').DataTable({
				data: lista,
				language: IDIOMA,
				searching: true,
				ordering: true,
				processing: true,
				pageLength: 10,
				lengthChange: false,
				info:true,
				scrollY: 305,
		        scroller: {
		            loadingIndicator: true
		        },
				columns:[
					{data: "idAlumno",className:'text-center'},
					{data: "nombre",className:'text-center'},
					{data: "dni",className:'text-center'},
					{data: "correo", className:'text-center'},
					{data: "fechaNacimientoStr", className:'text-center'},
				]                                     
		    });
	}
	
	var IDIOMA = {
			processing:"procesando...",
		    lengthMenu: "MENU Registros por p&aacute;gina",
		    zeroRecords: "No existen registros",
		    info: "P&aacute;gina PAGE de PAGES",
		    infoEmpty: "Sin registros",
		    infoFiltered: "(Filtro de MAX registros)",
		    search: "Buscar:",
		    paginate: {
		        "first":      "Primero",
		        "last":       "Last",
		        "next":       "Siguiente",
		        "previous":   "Anterior"
		    }
		};
</script>

</body>
</html>