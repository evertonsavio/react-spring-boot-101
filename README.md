## Personal Kaban Tool com JavaScript React.JS e Java SpringBoot  
  
### SpringBoot Roadmap Para Desenvolvimento da RESTful API 
  
* Criação do package "domain" e a classe "Project" com a Entity e anotações das tabelas e colunas.  
  
Obs: Erros podem acontecer no JPA do relacionamento se for definido "projectId", então se definiu como "projectIdentifier". 

```
@Entity
public class Project {
    
	public Project() {//Hibernate requer construtor vazio
	}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e necessario!")
    private String projectName;
  
...
``` 
  
1. Verificar as anotações "@PrePersist, @PreUpdate" para setar as datas quando criar um objetor.  
```
    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
```
___________________________________________________________________________________________________________________________
  
DevTools dependence permite acessa or H2 no navegador facilmente. Com isso a Entity esta completa.  
  
* Criação de um novo package "repositories" com a interface "ProjectRepository" que extende para o CrudRepository<Project, Long>  

```
@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {
}
```
  
* Criação dos package "services" com a classe ProjectService e o package "web" com a classe ProjectController.  
  
1. No ProjectService faz a Injeção do ProjectRepository com @Autowired para ter acesso aos CRUD.

2. cria metodo para salvar dados.  

```
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
...
```
  
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
  
----------------------------------------------------------------
### JPA -> No ProjectRepository Encontrar projeto pelo atributo:  
```
    Project findByProjectIdentifier(String projectId);

    @Override
    Iterable<Project> findAll();
```  
   
No Service Level:  
```
   public Project findProjectByIdentifier(String projectId){

        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());

        if (project == null){
            throw new ProjectIdException("Project "+ projectId +" does not existis");
        }

        return project;
    }
...
```

No Controller Layer:  
```
    @GetMapping("/{projectId}")
    public ResponseEntity<?> getProjectByID(@PathVariable String projectId){
        Project project = projectService.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }
...
```  
  
### * Para codar o findALL Projects  
  
```
    @Override
    Iterable<Project> findAll();
```
  
Na Service Layer:  
  
```
public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    } 
```  
  
Iterable<Project> retorna um JSON com todos os objetos.  
  
Na Controller Layer:  
```  
    @GetMapping("/all")
    public Iterable<Project> getAllProjects(){
        return projectService.findAllProjects();
    }
```  

### * Delete  

Service Layer  
```
    public void deleteProjectByIdentifier(String projectId){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if (project == null){
            throw new ProjectIdException("Can not delete "+ projectId +" because does not existis");
        }
        projectRepository.delete(project);
    }
```  
Controller Layer  
```
    @DeleteMapping("/{projectId}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectId){
        projectService.deleteProjectByIdentifier(projectId);
        return new ResponseEntity<String>("Projeto de ID " +projectId+ " deletado com sucesso", HttpStatus.OK);
    }
```
----------------------------------------------------  
JPA entende que é pra fazer update, só precisar passar o id na database pelo POST.  
