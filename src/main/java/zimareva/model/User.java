package zimareva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "First name should not be empty")
    @Size(min = 2, max = 30, message = "First name should be between 2 and 30 characters")
    private String firstName;

    @NotEmpty(message = "Last name should not be empty")
    @Size(min = 2, max = 30, message = "Last name should be between 2 and 30 characters")
    private String lastName;

    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("user")
    private List<Answer> answers = new ArrayList<>();

    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("user")
    private List<CompletedAnketa> completedAnketas = new ArrayList<>();

    public User() {
    }

    public User(String firstName, String secondName) {
        this.firstName = firstName;
        this.lastName = secondName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

//    public void setAnswers(List<Answer> answers) {
//        this.answers = answers;
//    }

    public List<CompletedAnketa> getCompletedAnketas() {
        return completedAnketas;
    }

//    public void setCompletedAnketas(List<CompletedAnketa> completedAnketas) {
//        this.completedAnketas = completedAnketas;
//    }

    public void addAnswer (Answer answer){
        answers.add(answer);
    }

    public void removeAnswer (Answer answer){
        answers.remove(answer);
    }
}
