<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Aggiorna Dati</title>
	
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
	<c:set var = "ruolo" scope = "session" value = "${ requestScope.ruoloUtente}"/>
	<c:if test="${ utente.ruolo == null }">
			<c:redirect url="index.jsp"/>
	</c:if>
	
	<c:if test="${ utente.ruolo == 'Guest' }">
			<c:redirect url="LogoutServlet"/>
	</c:if>
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
		        <h5>Modifica elemento</h5> 
		    </div>
		    <div class='card-body'>

					<h6 class="card-title">I campi con <span class="text-danger">*</span> sono obbligatori</h6>

					<form name="form" method="post" action="UpdateArticoloServlet" onsubmit="return validate();">
					
					<c:if test = "${articolo.prezzo == 0}">  
						<c:set var = "articolo_prezzo"  value = ""/>
					</c:if>
					<c:set var = "articolo" scope = "request" value = "${ requestScope.articoloDaAggiornare}"/>
				
					
						<div class="form-row">
							<div class="form-group col-md-6">
								<label>Codice <span class="text-danger">*</span></label>
								<input type="text" name="codice" id="codice" class="form-control" placeholder="Inserire il codice" required value="<c:out value = "${articolo.codice}"/>">
							</div>
							
							<div class="form-group col-md-6">
								<label>Descrizione <span class="text-danger">*</span></label>
								<input type="text" name="descrizione" id="descrizione" class="form-control" placeholder="Inserire la descrizione" required value="<c:out value = "${articolo.descrizione}"/>">
							</div>
							
							<div class="form-group col-md-6">
								<label>Categoria <span class="text-danger">*</span></label>
								<select name="categoria" id="categoria" class="form-control">
										<c:forEach items="${ listaCategorie}" var="categoria">
        									<option value="${categoria.nome}"><c:out value="${categoria.nome}"></c:out></option>
    									</c:forEach>
  									</select>
							</div>
						</div>
						
						<div class="form-row">	
							<div class="form-group col-md-3">
								<label>Prezzo <span class="text-danger">*</span></label>
								<c:if test = "${articolo.prezzo == 0}">
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" required value="<c:out value = "${articolo_prezzo}"/>">
								</c:if>
								<c:if test = "${articolo.prezzo != 0}">
									<input type="number" class="form-control" name="prezzo" id="prezzo" placeholder="Inserire prezzo" required value="<c:out value = "${articolo.prezzo}"/>">
								</c:if>
							</div>
							
						<div>	
							<input type="hidden" name="id" id="id" placeholder="Inserire id" value="<c:out value = "${articolo.id}"/>">
						</div>
						<div>	
							<input type="hidden" name="ruolo" id="ruolo" placeholder="Inserire ruolo" value="<c:out value = "${ruolo}"/>">
						</div>
							
						</div>
							
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