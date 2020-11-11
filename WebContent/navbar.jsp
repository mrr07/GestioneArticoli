<!-- navbar -->

<%@page import="it.gestionearticoli.model.Utente"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!doctype html>
<html lang="it">
<c:set var = "utente" scope = "session" value = "${ utente }"/>  <!--  -->
<c:if test="${ utente.ruolo == null }">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
    </ul>
    
    <a class="btn btn-success" href="login.jsp">Login</a>
  </div>
</nav>
</c:if>

<c:if test="${ utente.ruolo == 'Amministratore' }">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="index.jsp">Home</a>
          <a class="dropdown-item" href="ListArticoliServlet?ruoloUtente=${ utente.ruolo }">Risultati</a>
          <a class="dropdown-item" href="PrepareInsertArticoloServlet?ruoloUtente=<c:out value = "${ruolo}"/>">Inserisci nuovo elemento</a>
          
         
        </div>
      </li>
    </ul>
    
    <c:set var = "logout" scope = "session" value = "${ utente }"/> 
   	
   	<a>Bentornato/a<c:out value = "${ utente.nome }"/></a>
    <a class="btn btn-success" href="LogoutServlet">Logout</a>
   
    <!--
    <a class="btn btn-success" href="login.jsp">Login</a>
      <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>-->
  </div>
</nav>
</c:if>

<c:if test="${ utente.ruolo == 'Guest' }">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="index.jsp">Home</a>
          <a class="dropdown-item" href="ListArticoliServlet?ruoloUtente=${ utente.ruolo }">Risultati</a>

          
         
        </div>
      </li>
    </ul>
    
    <c:set var = "logout" scope = "session" value = "${ utente }"/> 
   	
   	<a>Bentornato/a<c:out value = "${ utente.nome }"/></a>
    <a class="btn btn-success" href="LogoutServlet">Logout</a>
   
    <!--
    <a class="btn btn-success" href="login.jsp">Login</a>
      <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>-->
  </div>
</nav>
</c:if>

<c:if test="${ utente.ruolo == 'Operatore' }">
<nav class="navbar navbar-expand-md navbar-dark fixed-top bg-primary">

	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
       <span class="navbar-toggler-icon"></span>
     </button>

  <div class="collapse navbar-collapse" id="navbarsExampleDefault">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Link</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
      </li>
      <li class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Dropdown</a>
        <div class="dropdown-menu" aria-labelledby="dropdown01">
          <a class="dropdown-item" href="index.jsp">Home</a>
          <a class="dropdown-item" href="ListArticoliServlet?ruoloUtente=${ utente.ruolo }">Risultati</a>
          <a class="dropdown-item" href="insert.jsp?ruoloUtente=${ utente.ruolo }">Inserisci nuovo elemento</a>
          
         
        </div>
      </li>
    </ul>
    
    <c:set var = "logout" scope = "session" value = "${ utente }"/> 
   	
   	<a>Bentornato/a<c:out value = "${ utente.nome }"/></a>
    <a class="btn btn-success" href="LogoutServlet">Logout</a>
   
    <!--
    <a class="btn btn-success" href="login.jsp">Login</a>
      <form class="form-inline my-2 my-lg-0">
      <input class="form-control mr-sm-2" type="text" placeholder="Search" aria-label="Search">
      <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
    </form>-->
  </div>
</nav>
</c:if>
