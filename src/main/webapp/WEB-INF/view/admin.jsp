<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4" crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.3/jquery.min.js"></script>
  <title>Admin</title>
</head>
<body>
<h1>Admin</h1>
<div>
  <button type="button" onclick="location.href='login'" class="btn btn-primary">로그인</button>
  <button type="button" onclick="location.href='logout'" class="btn btn-primary">로그아웃</button>
</div>
<div>
  <div>페이지 이동</div>
  <button type="button" onclick="location.href='home'" class="btn btn-primary">Home 페이지(permitAll)</button>
  <button type="button" onclick="location.href='auth'" class="btn btn-primary">인증 유저(authenticated)</button>
  <button type="button" onclick="location.href='anonymous'" class="btn btn-primary">권한(anonymous 전용)</button>
  <button type="button" onclick="location.href='user'" class="btn btn-primary">권한(user 전용)</button>
  <button type="button" onclick="location.href='admin'" class="btn btn-primary">권한(admin 전용)</button>
  <button type="button" onclick="location.href='userAdmin'" class="btn btn-primary">권한(user, admin 둘다)</button>
</div>
</body>
</html>
