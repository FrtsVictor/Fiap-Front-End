<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="UTF-8">
   <title>Insert title here</title>
   <link href="./resources/css/main.css" rel="stylesheet" />
   <link href="./resources/css/pages/login.css" rel="stylesheet" />    
   <meta http-equiv="X-UA-Compatible" content="IE=edge">
   <meta name="viewport" content="width=device-width, initial-scale=1.0">
   <title>Fintech</title>        
   <script src="https://kit.fontawesome.com/ee3ce56fb4.js"></script>
</head>
  <body>
    <div class="container">
      <form method="post" action="login">
        <div class="form-row">
          <label> 
          	Email
          	<input name="email" type="text" placeholder="example@gmail.com"> 
          </label>
        </div>
        <div class="form-row">
          <label>
            Senha
            <input name="password" type="password">
          </label>
        </div>

        <button><i class="fa-solid fa-right-to-bracket"></i> Entrar</button>

        <hr>
        <a class="button-facebook" href="#" title="Entrar com Facebook">
        	<i class="fa-brands fa-facebook"></i> Entrar com Facebook
        </a>
        <a class="button-google" href="#" title="Entrar com Google">
        	<i class="fa-brands fa-google"></i> Entrar com Google
        </a>
        <br>
        <a class="link" href="#" title="Recuperar conta"> Esqueci minha senha</a>
      </form>
    </div>    
    
    <p> ${ session.getAttribute("user") } </p>
    
  </body>
</html>