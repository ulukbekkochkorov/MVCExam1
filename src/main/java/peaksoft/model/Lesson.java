package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lesson_seq")
@SequenceGenerator(name = "lesson_seq", sequenceName = "lesson_seq", allocationSize = 1)
    private Long id;

    @Column
    private String lessonName;

    @ManyToOne(cascade = {MERGE,DETACH,PERSIST,REFRESH}, fetch = FetchType.EAGER)
    Course course;

    @OneToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST}, mappedBy = "lesson")
    private List<Task> tasks;
    public void addTask(Task task){
        if (tasks == null){
            tasks = new ArrayList<>();
        }
        tasks.add(task);
    }

}
