<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Inserisci nuovo</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
    <script type="text/javascript" language="javascript">
        function validate() {
            
            if (document.form.nome.value == null || document.form.nome.value == "") {
                alert("inserimento non valido");
                return false;
            }
	
			return true;
            
        } 
	</script>
    
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
		        <h5>Inserisci nuova Categoria</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<c:set var = "ruolo" scope = "session" value = "${ requestScope.ruoloUtente}"/>
					<c:if test="${ utente.ruolo == null }">
						<c:redirect url="index.jsp"/>
					</c:if>
					<c:if test="${ utente.ruolo == 'Guest' }">
						<c:redirect url="LogoutServlet"/>
					</c:if>
					<form name="form" method="post" action="ExecuteInsertCategoriaServlet?ruoloUtente=<c:out value = "${ruolo}"/>" onsubmit="return validate();">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Nome <span class="text-danger">*</span></label>
								<input type="text" name="nome" id="nome" class="form-control" placeholder="Inserire il nome" required>
							</div>
							
							<!--
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger">*</span></label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" required>
							</div>
							
							  
							<div class="form-group col-md-6">
								<label>Categoria <span class="text-danger">*</span></label>
								<input type="text" class="form-control" name="categoria" id="categoria" placeholder="Inserire categoria" required>
							</div>
							-->
							
						</div>
						
						<!--  
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Prezzo <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" required>
							</div>
							
						</div>
						-->
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>