<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
    <title>로그인</title>
    <style>
        .social {
            display: flex;
            flex-direction: column;
        }
        a {
            width: 200px;
            height: 50px;
        }
        img {
            width: 100%;
            height: 100%;
        }
    </style>
</head>
<body>
    <h1>로그인</h1>
    <div>
        <form action="/login" method="post">
            <input type="text" name="email" placeholder="Email"/>
            <input type="password" name="password" placeholder="Password"/>
        </form>
    </div>
    <h3>소셜로그인</h3>
    <div class="social">
        <%-- /oauth2/authorization 부분은 Spring Security에서 기본제공하는 url이다. --%>
        <a href="/oauth2/authorization/kakao"><img src="../../img/login/kakao_login.png"/></a>
        <a href="/oauth2/authorization/naver"><img src="../../img/login/naver_login.png"/></a>
        <a href="/oauth2/authorization/google"><img src="../../img/login/google_login.png"/></a>
    </div>
</body>
</html>
