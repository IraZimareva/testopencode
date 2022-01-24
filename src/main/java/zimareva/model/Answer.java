package zimareva.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "answer")
public class Answer {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String textOfAnswer;

    //@ManyToOne
    //@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties("answers")
    private User user;

    public Answer() {
    }

    public Answer(String textOfAnswer, User user) {
        this.textOfAnswer = textOfAnswer;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTextOfAnswer() {
        return textOfAnswer;
    }

    public void setTextOfAnswer(String textOfAnswer) {
        this.textOfAnswer = textOfAnswer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
