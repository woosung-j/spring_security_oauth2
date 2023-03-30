소셜 로그인 Sample 
=
> * Spring Boot 3.0.5
> * Spring Security 6.0
> * oauth2-client
> * JAVA 17
> * MariaDB + MyBatis
> * JSP(Bootstrap, JQuery, jstl)

## 로그인 목록
> * Kakao
> * Naver
> * Google

## 데이터베이스 테이블
### users
```SQL
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email varchar(50) NOT NULL UNIQUE,
    password varchar(100),
    name varchar(50) NOT NULL,
    nickname varchar(50),
    role varchar(50)
);
```