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
		
		<c:set var = "ruolo" scope = "session" value = "${ ruoloUtente}"/>
		<c:if test="${ ruolo == null }">
			<c:redirect url="/index.jsp"/>
		</c:if>
		<div class='card'>
		    <div class='card-header'>
		        <h5>Lista dei risultati</h5> 
		    </div>
		    <div class='card-body'>
		    	<c:if test="${ruolo eq 'Operatore'}">
		    		<a class="btn btn-primary " href="PrepareInsertArticoloServlet?ruoloUtente=<c:out value = "${ruolo}"/>">Add New</a>
		    	</c:if>
		    	<c:if test="${ruolo eq 'Amministratore'}">
		    		<a class="btn btn-primary " href="PrepareInsertArticoloServlet?ruoloUtente=<c:out value = "${ruolo}"/>">Add New</a>
		    	</c:if>
		    	
		    
		        <div class='table-responsive'>
		            <table class='table table-striped ' >
		                <thead>
		                    <tr>
		                        <th>Id</th>
		                        <th>Codice</th>
		                        <th>Descrizione</th>
		                        <th>Prezzo</th>
		                        <th>Categoria</th>
		                        <c:if test="${ requestScope.ricercato != 'ricercato' }">
		                        	<th>Azioni</th>
		                        </c:if>
		                    </tr>
		                </thead>
		                <tbody>
		                	
		                	<c:forEach items="${requestScope.listaArticoliAttribute}" var="item" >
         						
         					<tr>
		                        <td>${ item.id }</td>
		                        <td>${ item.codice }</td>
		                        <td>${ item.descrizione }</td>
		                        <td>${ item.prezzo }</td>
		                        <td>${ item.categoria }</td>
		                        <c:if test="${ requestScope.ricercato != 'ricercato' }">
		                        <td>
		                        	 
		                        	<c:if test = "${ruolo eq 'Operatore'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowArticoloServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateArticoloServlet?IdDaAggiornare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Edit</a>
									</c:if>
									<c:if test = "${ruolo eq 'Amministratore'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowArticoloServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
										<a class="btn  btn-sm btn-outline-primary ml-2 mr-2" href="PrepareUpdateArticoloServlet?IdDaAggiornare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Edit</a>
										<a class="btn btn-outline-danger btn-sm" href="PrepareDeleteArticoloServlet?IdDaEliminare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Delete</a>
									</c:if>
									<c:if test = "${ruolo eq 'Guest'}">
										<a class="btn  btn-sm btn-outline-secondary" href="ShowArticoloServlet?IdDaVisualizzare=<c:out value = "${item.id}"/>&ruoloUtente=<c:out value = "${ruolo}"/>">Visualizza</a>
									</c:if>
									
								</td>
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