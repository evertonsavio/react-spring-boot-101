## Spring Security ROADMAP - API to register and secury users  
  
* Criação as classes "UserController" e a entidade User.  
* Criação do package "security"  
  
Dependencias:  
```
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-security</artifactId>
		</dependency>
		<dependency>
			<groupId>com.google.code.gson</groupId>
			<artifactId>gson</artifactId>
			<version>2.8.5</version>
		</dependency>
		<dependency>
			<groupId>io.jsonwebtoken</groupId>
			<artifactId>jjwt</artifactId>
			<version>0.9.1</version>
		</dependency>
```  
Spring Security is runninng now, todo request esta desautorizado no momento.   
    
Handling Authrization Errors, Java Classes no pacote security:  JwtAuthenticationEntryPoint, SecurityConfig. No package exceptions, criação da classe InvalidLoginResponse.