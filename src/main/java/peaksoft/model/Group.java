package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "group_seq")
    @SequenceGenerator(name = "group_seq", sequenceName = "group_seq", allocationSize = 1)
    private Long id;

    @Column
    private String groupName;

    @Column
    private String dateOfStart;

    @Column
    private String image;

    @ManyToMany(cascade = {DETACH, MERGE, REFRESH}, fetch = FetchType.LAZY)
    @JoinTable(name = "group_course",
     joinColumns = @JoinColumn(name = "course_id"),
     inverseJoinColumns = @JoinColumn(name = "group_id"))
    private List<Course> courses;
    public void addCourse(Course course){
        if (courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
    }

    @OneToMany(cascade = {DETACH, MERGE, REFRESH, PERSIST},fetch = FetchType.LAZY, mappedBy = "group")
    private List<Student>students;

    public void addStudent(Student student){
        if (students == null){
            students = new ArrayList<>();
        }
        students.add(student);
        for (Course c: courses) {
            c.getCompany().addStudent();
        }
    }


}
