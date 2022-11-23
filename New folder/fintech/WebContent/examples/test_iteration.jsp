<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Exemplo JSP</title>
</head>
<body>
	
	
	<%-- import --%>
	<%@ page import="java.util.*" language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
	
	<h5> Declarando variaveis </h5>
	<%! String nome = "JSP"; %>
	<%! int nr = 0; %>
	<%! Object obj = new Object(); %>

	
	<h5>Utilizando expressions!</h5>
	<p>A data de hoje é : <%=(new java.util.Date()).toLocaleString()%></p>
	
	<ul>	
		<%for(int x = 0; x < 10 ; x++){%>					
				<li><%=x%></li>				
		<%}%>
	</ul>

	<% out.append("Seu ip é: " + request.getRemoteAddr()); %>
	
	<%
    Integer hitsCount = (Integer)application.getAttribute("hitCounter");
    if( hitsCount ==null || hitsCount == 0 ){
       /* First visit */
       out.println("Welcome to my website!");
       hitsCount = 1;
    }else{
       /* return visit */
       out.println("Welcome to my website!");
       hitsCount += 1;
    }
    application.setAttribute("hitCounter", hitsCount);
        
%>
<p>Total number of visits: <%= hitsCount%></p>
	
</body>
</html>