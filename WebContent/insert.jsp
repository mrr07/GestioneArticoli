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
            
            if (document.form.codice.value == null || document.form.codice.value == "" || 
				document.form.descrizione.value == null || document.form.descrizione.value == "" ||
				document.form.prezzo.value == null || document.form.prezzo.value == "" || isNaN(document.form.prezzo.value) ||
				document.form.categoria.value == null || document.form.categoria.value == "") {
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
		        <h5>Inserisci nuovo elemento</h5> 
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
					
					
					
					<form name='form' method="post" action="ExecuteInsertArticoloServlet?ruoloUtente=<c:out value = "${ruolo}"/>" onsubmit="return validate();">
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Codice <span class="text-danger">*</span></label>
								<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger">*</span></label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" required>
							</div>
							
							<div class="form-group col-md-6">
								<label>Categoria <span class="text-danger">*</span></label>
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
								<label>Prezzo <span class="text-danger">*</span></label>
								<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" required>
							</div>
							
						
							
						</div>
							
						<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
					

					</form>
					
					<script>
						// Example starter JavaScript for disabling form submissions if there are invalid fields
						(function() {
  							'use strict';
  							window.addEventListener('load', function() {
    							// Fetch all the forms we want to apply custom Bootstrap validation styles to
    							var forms = document.getElementsByClassName('needs-validation');
    							// Loop over them and prevent submission
    							var validation = Array.prototype.filter.call(forms, function(form) {
      								form.addEventListener('submit', function(event) {
       									if (form.checkValidity() === false) {
          									event.preventDefault();
          									event.stopPropagation();
        								}
        								form.classList.add('was-validated');
      								}, false);
    							});
  							}, false);
						})();
					</script>

		    
		    
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>