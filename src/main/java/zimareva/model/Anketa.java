package zimareva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "anketa")
public class Anketa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @NotBlank
    @NotEmpty(message = "Title should not be empty")
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;


//    /*@OneToMany (
//            mappedBy = "anketa",
//            cascade = CascadeType.ALL,
//            orphanRemoval = true
//    )
//    @JsonIgnoreProperties("anketa")
//    private List<CompletedAnketa> completedAnketas = new ArrayList<>();*/

//    @NotBlank
    @OneToMany (
            mappedBy = "anketa",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("anketa")
    private List<Question> questions = new ArrayList<>();

    public Anketa(@NotEmpty(message = "Title should not be empty") String title) {
        this.title = title;
        this.createdAt = new Date();
    }

    public Anketa() {
    }

    public Anketa(@NotEmpty(message = "Title should not be empty") String title, Date createdAt, List<CompletedAnketa> completedAnketas, List<Question> questions) {
        this.title = title;
        this.createdAt = createdAt;
//        this.completedAnketas = completedAnketas;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

//    public List<CompletedAnketa> getCompletedAnketas() {
//        return completedAnketas;
//    }
//
//    public void setCompletedAnketas(List<CompletedAnketa> completedAnketas) {
//        this.completedAnketas = completedAnketas;
//    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public void addQuestion (Question question){
        questions.add(question);
    }

    public void removeQuestion (Question question){
        questions.remove(question);
    }

    @Override
    public String toString() {
        return "Anketa{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", createdAt=" + createdAt +
                ", questions=" + questions.toString() +
                '}';
    }
}
