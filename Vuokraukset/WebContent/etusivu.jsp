<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://code.jquery.com/jquery-1.12.4.min.js"></script>
<script src="https://code.jquery.com/ui/1.10.4/jquery-ui.min.js"></script>
<script src="_script.js"></script>
<link rel="stylesheet" type="text/css" href="style.css">
<title>Insert title here</title>
</head>
<body>
<div id="etusivu">
<a href="kirjaudu_ulos">Kirjaudu ulos</a>
<br>
<br>
<br>
<br>
	<div id="haku">
	<form action="site" method="get" >
	<h2>Hae vuokrauksia</h2>
<button type="submit" name="action" value="hae kaikki vuokraukset" > Hae kaikki vuokraukset</button>
<button type="submit" name="action" value="hae tietyn pvm:n vuokraukset"> Hae tietty vuokraus</button>
<input type="text" name="date" class="date"	placeholder="Valitse päivä" spellcheck="false"/>
<c:if test="${tyhja==true}" >
<br />
<c:out value="${date}" /> <c:out value=" ei löytyny yhtään vuokrausta" />
</c:if>
<br />
<br />
<c:if test="${TK_VIRHE==true}" >
<c:out value="Tietokantaan ei nyt saada yhteyttä. Korjaamme vian tuotapikaa" />
</c:if>
</form>
</div>

<div id="uusi">
<h2>Lisää uusi vuokraus</h2>
<form action="UusiVuokrausServlet" method="get" >
<button type="submit" name="action" value="tee uusi vuokraus">Lisää vuokraus</button> &nbsp; &nbsp;
<br />
<c:if test="${LISAYSONNISTUI== true}" >
 <c:out value="Vuokrauksen lisäys onnistui" />
</c:if>
<c:if test="${LISAYSEPAONNISTUI== true}" >
 <c:out value="Vuokrauksen lisäys EPÄONNISTUI" />
</c:if>
</form>
</div>
</div>
</body>
</html>