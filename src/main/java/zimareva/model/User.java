package zimareva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    @OneToMany (
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("user")
    private List<Answer> answers = new ArrayList<>();

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

    public void addAnswer (Answer answer){
        answers.add(answer);
    }

    public void removeAnswer (Answer answer){
        answers.remove(answer);
    }
}
