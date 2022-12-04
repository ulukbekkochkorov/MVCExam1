package peaksoft.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_seq")
    @SequenceGenerator(name = "company_seq", sequenceName = "company_seq",allocationSize = 1)
    private Long id;
    @Column
    private String companyName;
    @Column
    private String locatedCountry;
    private  int countStudent;
    public void addStudent(){
        countStudent++;
    }
    public void removeStudent(){
        countStudent--;
    }
    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "company")
    private List<Course> courses;

    public void addCourse(Course course){
        if (courses == null){
            courses=new ArrayList<>();
        }
        courses.add(course);
    }

}
