<%@page import="it.gestionearticoli.model.Articolo"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Visualizza elemento</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
		
		<div class='card'>
		    <div class='card-header'>
		        Visualizza dettaglio
		    </div>
		    
		    <c:set var = "articolo" scope = "request" value = "${requestScope.articoloDaVisualizzare}"/>
		    <c:set var = "ruolo" scope = "request" value = "${requestScope.ruoloUtente}"/>
		    <c:if test="${ ruolo == null }">
				<c:redirect url="/index.jsp"/>
			</c:if>
		
		    <div class='card-body'>
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">ID:</dt>
				  <dd class="col-sm-9"><c:out value = "${articolo.id}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">codice:</dt>
				  <dd class="col-sm-9"><c:out value = "${articolo.codice}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">descrizione:</dt>
				  <dd class="col-sm-9"><c:out value = "${articolo.descrizione}"/></dd>
		    	</dl>
		    	
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">prezzo:</dt>
				  <dd class="col-sm-9"><c:out value = "${articolo.prezzo}"/></dd>
		    	</dl>
		    	
		    	 
		    	<dl class="row">
				  <dt class="col-sm-3 text-right">Categoria:</dt>
				  <dd class="col-sm-9"><c:out value = "${articolo.categoria}"/></dd>
		    	</dl>
		    	
		    	
		    </div>
		    
		    <div class='card-footer'>
		        <a href="ListArticoliServlet?ruoloUtente=<c:out value = "${ ruolo }"/>" class='btn btn-outline-secondary' style='width:80px'>
		            <i class='fa fa-chevron-left'></i> Back
		        </a>
		    </div>
		</div>	
	
	
	
	<!-- end main container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>