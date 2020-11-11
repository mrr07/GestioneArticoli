<%@page import="it.gestionearticoli.model.Articolo"%>
<%@page import="java.util.List"%>
<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<head>
	<jsp:include page="./header.jsp" />
	<title>Pagina dei risultati</title>
	
	<!-- style per le pagine diverse dalla index -->
    <link href="./assets/css/global.css" rel="stylesheet">
    
</head>
<body>
	<jsp:include page="./navbar.jsp" />
	
	<main role="main" class="container">
	
		<div class="alert alert-success alert-dismissible fade show ${successMessage==null?'d-none': ''}" role="alert">
		  ${successMessage}
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
		<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
		  Esempio di operazione fallita!
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
		  Aggiungere d-none nelle class per non far apparire
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
		    <span aria-hidden="true">&times;</span>
		  </button>
		</div>
		
		<c:set var = "ruolo" scope = "session" value = "${ requestScope.ruoloUtente}"/>
		<c:if test="${ ruolo == null }">
			<c:redirect url="/index.jsp"/>
		</c:if>
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<c:if test="${ruolo eq 'Operatore'}">
		    		<a class="btn btn-primary " href="PrepareInsertCategoriaServlet?ruoloUtente=<c:out value = "${ruolo}"/>">Add New</a>
		    	</c:if>
		    	<c:if test="${ruolo eq 'Amministratore'}">
		    		<a class="btn btn-primary " href="PrepareInsertCategoriaServlet?ruoloUtente=<c:out value = "${ruolo}"/>">Add New</a>
		    	</c:if>
		    	
		    
		        <div class='table-responsive'>
		            <table class='table'>
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Nome</th>
		                        <c:if test="${ requestScope.ricerca != 'ricercato' }">
		                        	<th>Azioni</th>
		                        </c:if>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaCategorieAttribute}" var="item" >
         						
         					<tr>
         						<c:if test = "${ item.nome != 'Non Categorizzato' }" >
		                        	<td>${ item.id }</td>
		                        	<td>${ item.nome }</td>
		                        </c:if>
		                        
		                        <c:if test = "${ requestScope.ricerca != 'ricercato' }" >
		                        <c:if test = "${ item.nome != 'Non Categorizzato' }" >
		                        <td>
		                        	<c:set var = "nomeRicerca" scope = "session" value = "${ nomeRicerca }"/>
		                        	<c:if test = "${ruolo eq 'Operatore'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowPerCategoriaServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateCategoriaServlet?IdDaAggiornare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Edit</a>
									</c:if>
									<c:if test = "${ruolo eq 'Amministratore'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowPerCategoriaServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateCategoriaServlet?IdDaAggiornare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Edit</a>
										<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteCategoriaServlet?IdDaEliminare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>&nomeRicerca=<c:out value = "${nomeRicerca}"/>">Delete</a>
									</c:if>
									<c:if test = "${ruolo eq 'Guest'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowPerCategoriaServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
									</c:if>
								</td>
								</c:if>
								</c:if>
		                    </tr>
         						
     						 </c:forEach>
		              
		                </tbody>
		            </table>
		        </div>
		   
			<!-- end card-body -->			   
		    </div>
		</div>	
	
	
	
	
	
	
	<!-- end container -->	
	</main>
	<jsp:include page="./footer.jsp" />
	
</body>
</html>