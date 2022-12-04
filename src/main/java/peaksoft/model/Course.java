package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;


@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "course_seq")
    @SequenceGenerator(name = "course_seq", sequenceName = "course_seq", allocationSize = 1)
    private Long id;
    @Column
    private String name;
    @Column
    private String duration;
    @Column
    private String description;

    @ManyToOne(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.LAZY)
    private Company company;

    @ManyToMany(cascade = {MERGE, DETACH, PERSIST,REMOVE, REFRESH}, fetch = FetchType.EAGER,mappedBy = "courses")
    private List<Group> groups;

    public void addGroup(Group group) {
        if (groups == null) {
            groups = new ArrayList<>();
        }
        groups.add(group);

    }

    //
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Lesson> lessons;

    @OneToMany(cascade = ALL, fetch = FetchType.LAZY, mappedBy = "course")
    private List<Instructor> instructorsList;

    public void addInstructor(Instructor instructor) {
        if (instructorsList == null) {
            instructorsList = new ArrayList<>();
        }
        instructorsList.add(instructor);

    }

    public void addLesson(Lesson lesson) {
        if (lessons == null) {
            lessons = new ArrayList<>();
        }
        lessons.add(lesson);
    }


}
