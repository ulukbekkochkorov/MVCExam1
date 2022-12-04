package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Instructor;
import peaksoft.repository.InstructorRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Repository
@Transactional
public class InstructorRepositoryImpl implements InstructorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Instructor> getAllInstructors(Long courseId) {
        return entityManager.createQuery("select i from Instructor i where i.course.id= :id", Instructor.class)
                .setParameter("id", courseId).getResultList();
    }

    @Override
    public List<Instructor> getInstructorList() {
        return entityManager.createQuery("from Instructor", Instructor.class).getResultList();
    }

    @Override
    public void addInstructor(Long id, Instructor instructor) {
        Course course = entityManager.find(Course.class,id);
        course.addInstructor(instructor);
        instructor.setCourse(course);
        entityManager.merge(course);

    }

    @Override
    public Instructor getInstructorById(Long id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    public void updateInstructor(Instructor instructor, Long id) {
        Instructor instructor1 = entityManager.find(Instructor.class,id);
        instructor1.setFirstName(instructor.getFirstName());
        instructor1.setLastName(instructor.getLastName());
        instructor1.setEmail(instructor.getEmail());
        instructor1.setSpecialization(instructor.getSpecialization());
        instructor1.setPhoneNumber(instructor.getPhoneNumber());
        entityManager.merge(instructor1);

    }

    @Override
    public void deleteInstructor(Long id) {
        entityManager.remove(entityManager.find(Instructor.class,id));

    }

    @Override
    public void assignInstructor(Long courseId, Long instructorId) throws IOException {
        Instructor instructor = entityManager.find(Instructor.class,instructorId);
        Course course = entityManager.find(Course.class,courseId);

        if (course.getInstructorsList()!= null){
            for (Instructor i: course.getInstructorsList()) {
                if (i.getId()==instructorId){
                    throw new IOException("This instructor already exist");
                }
            }
        }
        instructor.getCourse().getInstructorsList().remove(instructor);
        instructor.setCourse(course);
        course.addInstructor(instructor);
        entityManager.merge(instructor);
        entityManager.merge(course);


    }
}