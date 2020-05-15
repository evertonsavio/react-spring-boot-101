package evertonsavio.dev.kanbanfullstack.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
public class Project {

    public Project() {
    //Hibernate requer construtor vazio
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome e necessario!")
    private String projectName;
    @NotBlank(message = "Node do Identificador Necessario!")
    @Size(min=4, max=5, message = "Usar de 4 a 5 caracteres")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;
    @NotBlank(message = "Nao pode ser deixado em branco")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;
    @JsonFormat(pattern = "yyyy-mm-dd")
    @Column(updatable = false)
    private Date created_At;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_At;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectIdentifier() {
        return projectIdentifier;
    }

    public void setProjectIdentifier(String projectIdentifier) {
        this.projectIdentifier = projectIdentifier;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }

    public Date getCreated_At() {
        return created_At;
    }

    public void setCreated_At(Date created_At) {
        this.created_At = created_At;
    }

    public Date getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(Date updated_At) {
        this.updated_At = updated_At;
    }

    @PrePersist
    protected void onCreate(){
        this.created_At = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updated_At = new Date();
    }
}

//A anotação @Entity é utilizada para informar que uma classe também é uma entidade.
// A partir disso, a JPA estabelecerá a ligação entre a entidade e uma tabela de mesmo nome no banco de dados,
// onde os dados de objetos desse tipo poderão ser persistidos. A anotação @Entity ainda pode receber um parâmetro: name.
// Assim, ao invés de utilizar o nome da classe para referenciar a entidade no momento de criar uma consulta,
// por exemplo, você deverá referenciar o nome especificado nesse parâmetro.

//@Entity
//public class Pessoa {
//    @Id
//    private Long id;
//    private String nome;
    //getters e setters omitidos...
//}

//@PrePersist
//The @PrePersist annotation is used to specify a callback method that fires before an entity is persisted.
//        See the JPA callbacks section for more info.
//@PreRemove
//The @PreRemove annotation is used to specify a callback method that fires before an entity is removed.
//        See the JPA callbacks section for more info.
//@PreUpdate
//The @PreUpdate annotation is used to specify a callback method that fires before an entity is updated.
//        See the JPA callbacks section for more info.