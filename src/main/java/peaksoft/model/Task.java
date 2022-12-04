package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.CascadeType.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task-seq")
    @SequenceGenerator(name = "task-seq", sequenceName = "task_seq", allocationSize = 1)
private Long id;

    @Column
    private String name;

    @Column
    private String text;

    @Column
    private String deadline;

    @ManyToOne(cascade = {DETACH, REFRESH, MERGE,PERSIST})
    private Lesson lesson;
}
