<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Fintech</title>    
    <script src="https://kit.fontawesome.com/ee3ce56fb4.js"></script>
	<link href="./resources/css/main.css" rel="stylesheet" />
</head>
<body>
 		<div class="content">
	        <form>
	          <div class="form-row">
	            <label>
	              Título
	              <input name="title" type="text" value="Despesa alimentar">
	            </label>
	            <label>
	              Valor
	              <input name="cost" type="number" value="100">
	            </label>
	          </div>
	          <div class="form-row">
	            <label>
	              Data
	              <input name="date" type="date">
	            </label>
	          </div>
	          <div class="form-row">
	            <label>
	              Detalhes
	              <textarea></textarea>
	            </label>
	          </div>
	          <button><i class="fa-solid fa-save"></i> Salvar</button>
	        </form>      
      		<%@ include file="footer.jsp" %>
      	</div>
</body>
</html>




