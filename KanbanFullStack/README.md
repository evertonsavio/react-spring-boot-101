## Spring Security ROADMAP - API to register and secury users  
  
* Criação as classes "UserController" e a entidade User.  
```
User é reservado em Postgres, se voce tentar criar uma tabela com esse nome pelo Hibernate, voce nao vai conseguir.   
fullname deve ser em case sensitive ou var ter erro -> fullName.  
```
  
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
  
### Generate Json Web Token JWT  
  
1. Preencher entidade User com os valores de DB de usuario. Criar o repositorio UserRepository e UserService.  
  
2. No User Service add:  
```
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
```
  
e na classe principal add:  
```
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
``` 
  
3. Criar as rotas no controller "UserController" e add a rota de registro ao security.  

```
.antMatchers("api/users/**").permitAll()
```  
  
* Criacao de Error Handles para lidar com erros de usuarios duplicados. @Unique  
  
* Criacao do package validator para validar o password.  
  
  
  



  
 
  
  
  




