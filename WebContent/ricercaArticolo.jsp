<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Ricerca Articolo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
			 	Operazione fallita!
			  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
			    <span aria-hidden="true">&times;</span>
			  </button>
		</div>
		
		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none': ''}" role="alert">
		  ${errorMessage}
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<div class='card'>
		    <div class='card-header'>
		        <h5>Inserisci valori</h5> 
		    </div>
		    <div class='card-body'>


					<c:set var = "ruolo" scope = "session" value = "${ requestScope.ruoloUtente}"/>
					<c:if test="${ utente.ruolo == null }">
						<c:redirect url="index.jsp"/>
					</c:if>
					
					
					
					<form method="post" action="ExecuteSearchArticoloServlet?ruoloUtente=<c:out value = "${ruolo}"/>" novalidate="novalidate">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Codice <span class="text-danger"></span></label>
								<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice">
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger"></span></label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione">
							</div>
							
							<div class="form-group col-md-6">
								<label>Categoria <span class="text-danger"></span></label>
									<select name="categoria" id="categoria" class="form-control">
										<c:forEach items="${ listaCategorie}" var="categoria">
											<option value="" selected disabled hidden>Seleziona Categoria</option>
        									<option value="${categoria.nome}"><c:out value="${categoria.nome}"></c:out></option>
    									</c:forEach>
  									</select>
								
							</div>
							
							
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Prezzo <span class="text-danger"></span></label>
								<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo">
							</div>
							
						
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Cerca</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>