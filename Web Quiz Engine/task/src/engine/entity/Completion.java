package engine.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
//@Table(name="completions")
public class Completion {

    @Id
    @GeneratedValue
    @JsonIgnore
   //  transient
   private  int idCompletion;

    @ManyToOne
    @JoinColumn(name="id_quiz")
    //@Column(name="id_quiz")
    Quiz kwis;

    @Column
    LocalDateTime completedAt;

    @Column
    @JsonIgnore
    private String completedBy;

    public Completion() {
    }

    public Completion(Quiz kwis, LocalDateTime completedAt, String completedBy) {
        this.kwis = kwis;
        this.completedAt = completedAt;
        this.completedBy = completedBy;
    }

    public Completion(Quiz kwis, LocalDateTime completedAt) {
        this.kwis = kwis;
        this.completedAt = completedAt;
    }

    public int getIdCompletion() {
        return idCompletion;
    }

    public void setIdCompletion(int idCompletion) {
        this.idCompletion = idCompletion;
    }

    public Quiz getKwis() {
        return kwis;
    }

    public void setKwis(Quiz kwis) {
        this.kwis = kwis;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public String getCompletedBy() {
        return completedBy;
    }

    public void setCompletedBy(String completedBy) {
        this.completedBy = completedBy;
    }


    @Override
    public String toString() {
        return "Completion{" +
                "idCompletion=" + idCompletion +
             //   ", kwis=" + kwis +
                ", completedAt=" + completedAt +
                ", completedBy='" + completedBy + '\'' +
                '}';
    }
}
