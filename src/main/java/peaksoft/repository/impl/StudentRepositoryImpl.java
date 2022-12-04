package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.model.Instructor;
import peaksoft.model.Student;
import peaksoft.repository.GroupRepository;
import peaksoft.repository.StudentRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class StudentRepositoryImpl implements StudentRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Student> getAllStudents(Long groupId) {
        return entityManager.createQuery("select s from Student s where s.group.id= :id", Student.class)
                .setParameter("id", groupId).getResultList();
    }

    @Override
    public List<Student> getStudentList() {
        return entityManager.createQuery("from Student", Student.class).getResultList();
    }

    @Override
    public void addStudent(Long id, Student student) {
        Group group = entityManager.find(Group.class,id);
        group.addStudent(student);
        student.setGroup(group);
        entityManager.merge(student);

    }

    @Override
    public Student getStudentById(Long id) {
        return entityManager.find(Student.class,id);
    }

    @Override
    public void updateStudent(Student student, Long id) {
        Student student1 = entityManager.find(Student.class,id);
        student1.setFirstName(student.getFirstName());
        student1.setLastName(student.getLastName());
        student1.setPhoneNumber(student.getPhoneNumber());
        student1.setEmail(student.getEmail());
        student1.setStudyFormat(student.getStudyFormat());
        entityManager.merge(student1);

    }

    @Override
    public void deleteStudent(Long id) {

        Student student = entityManager.find(Student.class, id);
        for (Course c: student.getGroup().getCourses())
             { c.getCompany().removeStudent();
        }
        entityManager.remove(student);
    }

    @Override
    public void assignStudent(Long groupId, Long studentId) throws IOException {
        Group group = entityManager.find(Group.class,groupId);
        Student student= entityManager.find(Student.class,studentId);
        if (group.getStudents()!= null){
            for (Student s: group.getStudents()) {
                if (s.getId()==studentId){
                    throw new IOException("This student already exist");
                }
            }
        }
        student.getGroup().getStudents().remove(student);
        student.setGroup(group);
        group.addStudent(student);
        entityManager.merge(student);

    }
}