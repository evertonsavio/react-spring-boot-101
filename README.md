### SpringBoot Roadmap  
  
* Criação do package "domain" e a classe "Project" com a Entity e anotações das tabelas e colunas.  
  
Obs: Erros podem acontecer no JPA do relacionamento se for definido "projectId", então se definiu como "projectIdentifier".  
  
1. Verificar as anotações "@PrePersist, @PreUpdate" para setar as datas quando criar um objetor.  
  
DevTools dependence permite acessa or H2 no navegador facilmente. Com isso a Entity esta completa.  
  
* Criação de um novo package "repositories" com a interface "ProjectRepository" que extende para o CrudRepository<Project, Long>  
  
* Criação dos package "services" com a classe ProjectService e o package "web" com a classe ProjectController.  
  
1. No ProjectService faz a Injeção do ProjectRepository com @Autowired para ter acesso aos CRUD.

2. cria metodo para salvar dados.  
  
3. No ProjectController injetar ProjectService, criar a rotas de GET/POST.  
  
Exemplo:
```
    @PostMapping("")
    public ResponseEntity<Project> createNewProject(@RequestBody Project project){
	Project project1 = projectService.saveOrUpdateProject(project)
        return new ResponseEntity<Project>(project, HttpStatus.CREATED);
```
#### Com isso o post já funciona :D  
  
4. Add "@Valid" annotation no controller e ",BindingResult result" que verifica se objeto passado vai ter errors, fazer handle de erros. 
  
Exemplo:
```
if(result.hasErrors()){
		return new ResponseEntity<String>("Invalido o Objeto enviado", HttpStatus.BAD_REQUEST);
	}  
```  
  
* Agora de Trick part, para cada tipo de erro, quero retornar somente o campo relativo ao erro que o usuario esta tendo de uma forma limpa, o que pode se utilizar e getFieldErros.    
  
exemplo:
```
if (result.hasErrors()){
            Map<String, String> errorMap = new HashMap<>();
            for(FieldError error: result.getFieldErrors()){
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
        }
```  
  
* Existe campos na DATABASE que são unicos, se o usuario tenta criar um novo usuario com um campo repetido, tera um erro na criacao no level da database 500, para lidar com isso, precisa-se lidar com ErrorHandles. Criacao de custom error handles.   
  

 1. criação de um package "exceptions", com as classes ProjectExceptionResponse e a exception ProjectIdException com a anotacao @ResponseStatus(HttpStatus). Finalmente a ultima classe da Spring Framework CustomResponseEntityExceptionHandler com as anotações @ControllerAdvice e RestController extends para ResponseEntityExceptionHandler.  
```
try{
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save(project);
        }catch (Exception e){
            throw new ProjectIdException("Project ID " + project.getProjectIdentifier().toUpperCase()+" Already existis");
        }
```  
  



  
  
  
  
  

  