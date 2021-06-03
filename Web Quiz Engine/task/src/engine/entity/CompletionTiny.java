package engine.entity;

import java.time.LocalDateTime;

public class CompletionTiny {

    int id;
    String completedAt;

    public CompletionTiny() {
    }

    public CompletionTiny(int id, LocalDateTime completedAt) {
        this.id = id;
        this.completedAt = completedAt.toString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt.toString();
    }

    @Override
    public String toString() {
        return "{" +
                "id:" + id +
                ", completedAt:" + completedAt +
                '}';
    }
}
