package engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;


import javax.persistence.*;
import java.util.List;

@Entity
public class Quiz {

    @Id
    @GeneratedValue
    int id;

    @Column
    @Length(min=1)
    String title;

    @Column
     @Length(min=1)
    String text;

    @ElementCollection
    List<String> options;

   @JsonIgnore
   @ElementCollection(fetch = FetchType.EAGER)
    //private transient List<Integer> answer;
   private  List<Integer> answer;

    @JsonIgnore
    @Column
    private String wlasciciel;

    @JsonIgnore
   // @OneToMany(mappedBy = "kwis")
    private transient List<Completion> completions;


    public Quiz() {
    }



    public Quiz(String title, String text, List<String> options, List<Integer> answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }

    public Quiz(int id, String title, String text, List<String> options, List<Integer> answer, String wlasciciel, List<Completion> completions) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.wlasciciel = wlasciciel;
        this.completions = completions;
    }

    public Quiz(String title, String text, List<String> options, List<Integer> answer, String wlasciciel, List<Completion> completions) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
        this.wlasciciel = wlasciciel;
        this.completions = completions;
    }

    public Quiz(int id, String title, String text, List<String> options) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.options = options;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<Integer> getAnswer() {
        return answer;
    }

    public void setAnswer(List<Integer> answer) {
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWlasciciel() {
        return wlasciciel;
    }

    public void setWlasciciel(String wlasciciel) {
        this.wlasciciel = wlasciciel;
    }

    public List<Completion> getCompletions() {
        return completions;
    }

    public void setCompletions(List<Completion> completions) {
        this.completions = completions;
    }



    @Override
    public String toString() {
        return "Quiz{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", options=" + options +
                ", answer=" + answer +
                ", wlasciciel='" + wlasciciel + '\'' +
                ", completions=" + completions +
                '}';
    }
}
