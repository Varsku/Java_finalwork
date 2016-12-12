<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
<div id="lisaa">
<form action="UusiVuokrausServlet" method ="get" >
<div>
<h2>Lisää uusi vuokraus</h2>
Aloituspäivä:&nbsp;&nbsp; <input type="text" name="date" value="${date}" class="date"	placeholder="Valitse päivä" spellcheck="false"/> <br>
Lopetuspäivä: <input type="text" name="date2" value="${date2}" class="date" placeholder="Valitse päivä" spellcheck="false"/>
<c:if test="${pvmVirhe==true}" >
<br><c:out value="Anna päivämäärät oikeassa järjestyksessä" /><br>
</c:if>
<c:if test="${pvmPuuttuu==true}" >

<br><c:out value="Anna kumpikin päivämäärä "/><br>
</c:if>

<button type="submit" name="action" value="Hae asiakkaat ja autot">Hae asiakkaat ja autot</button>
</div>	  
<div>
<c:if test="${not empty autothaettu}" >
<p>
Valitse vuokraaja: <select name="vuokraaja">
	  <c:forEach items="${asiakkaat}" var= "asiakas">
		<option value="${asiakas.id}" 
			<c:if test="${vuokraaja==asiakas.id}"> SELECTED </c:if> > 
		<c:out value ="${asiakas.id}" />&nbsp;
		<c:out value ="${asiakas.etunimi}" />&nbsp;
		<c:out value ="${asiakas.sukunimi}" /> 
		</option>
	  </c:forEach>
	  </select>
	  <br />
	  <br />
	  </p>
 <table>
 <tr> <td>
Valitse vuokrattava auto: </td> <td> </td><td> </td><td> </td><td> </td>
</tr>

	<c:forEach items="${autot}" var="auto">
	<tr>
	<td><input type="radio" class="radionappi" name="valittuauto" value="${auto.rekno}">
	<c:out value="${auto.rekno}" /> </td>
	<td><c:out value="${auto.merkki}" /> &nbsp;</td>
	<td><c:out value="${auto.malli}" /> &nbsp;</td>
	<td><c:out value="${auto.vrkhinta}" /> &nbsp;</td>
	<td><c:out value="${auto.huoltopvm}" /> &nbsp;</td>
	<br />
	</input>
	</tr>
	</c:forEach>
 </table>
 <c:if test="${autoPuuttuu==true}" >
 <c:out value="Valitse myös auto!" />
 </c:if>
 <br />
 <button type="submit" name = "action" value="Jatka">Jatka</button>
 </c:if>
 
</div>

</form>
</div>
</body>
</html>