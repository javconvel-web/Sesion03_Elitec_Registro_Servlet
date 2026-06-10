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
    <<h1>Consulta de Concurso</h1>

<div class="row" style="margin-top: 4%;">
	<div class="col-4">
		<label>Nombre</label>
		<input type="text" id="nombre" class="form-control">
	</div>

	<div class="col-4">
		<label>Estado</label>
		<input type="text" id="estado" class="form-control">
	</div>
</div>

<div class="row" style="margin-top: 4%;">
	<div class="col-4">
		<label>Fecha Inicio</label>
		<input type="date" id="fecIni" class="form-control">
	</div>

	<div class="col-4">
		<label>Fecha Fin</label>
		<input type="date" id="fecFin" class="form-control">
	</div>
</div>

<div class="row justify-content-center" style="margin-top: 3%;">
	<button class="btn btn-primary" id="btnFiltrar">Filtrar</button>
</div>

<table class="table table-striped" id="tabla">
	<thead>
		<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Fecha Inicio</th>
			<th>Fecha Fin</th>
			<th>Estado</th>
		</tr>
	</thead>
</table>

</div>

<script>

$('#tabla').DataTable();

$("#btnFiltrar").click(function () {

	$.ajax({
		url: "consultaConcursoAlias",
		type: "GET",
		data: {
			nombre: $("#nombre").val(),
			estado: $("#estado").val(),
			fecIni: $("#fecIni").val(),
			fecFin: $("#fecFin").val()
		},
		success: function (data) {
			agregar(data);
		}
	});
});

function agregar(lista){

	$('#tabla').DataTable().clear();
	$('#tabla').DataTable().destroy();

	$('#tabla').DataTable({
		data: lista,
		columns: [
			{data: "idConcurso"},
			{data: "nombre"},
			{data: "fechaInicioStr"},
			{data: "fechaFinStr"},
			{data: "estado"}
		]
	});
}

</script>

</body>
</html>