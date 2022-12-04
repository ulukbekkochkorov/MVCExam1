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
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student")
    @SequenceGenerator(name = "student", sequenceName = "student_seq", allocationSize = 1)
private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phoneNumber;

    @Column
    private String email;

    @Column
    private StudyFormat studyFormat;

    @ManyToOne (cascade = {DETACH, MERGE, REFRESH,PERSIST}, fetch = FetchType.EAGER)
    private Group group;


}
