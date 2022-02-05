package zimareva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import zimareva.model.enums.QuestionEnum;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //@NotBlank
    private String question;

    @Enumerated(EnumType.STRING)
    private QuestionEnum type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "anketa_id")
    @JsonIgnoreProperties("questions")
    private Anketa anketa;

    @OneToMany (
            mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnoreProperties("question")
    private List<Option> options = new ArrayList<>();

    public Question() {
    }

    public Question(String question, QuestionEnum type, Anketa anketa, List<Option> options) {
        this.question = question;
        this.type = type;
        this.anketa = anketa;
        this.options = options;
    }

    public Question(String question, QuestionEnum type) {
        this.question = question;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public QuestionEnum getType() {
        return type;
    }

    public void setType(QuestionEnum type) {
        this.type = type;
    }

    public Anketa getAnketa() {
        return anketa;
    }

    public void setAnketa(Anketa anketa) {
        this.anketa = anketa;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public void addOption (Option option){
        options.add(option);
    }

    public void removeOption (Option option){
        options.remove(option);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", type=" + type +
                ", anketa=" + anketa +
                ", options=" + options +
                '}';
    }
}