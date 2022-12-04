package peaksoft.repository.impl;

import org.springframework.stereotype.Repository;
import peaksoft.model.Course;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.Callable;

@Repository
@Transactional
public class GroupRepositoryImpl implements GroupRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Group> getAllListGroups() {
        return entityManager.createQuery("select g from Group g").getResultList();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return entityManager.find(Course.class, courseId).getGroups();
    }

    @Override
    public void addGroup(Group group, Long courseId) {
        Course course = entityManager.find(Course.class, courseId);
        course.addGroup(group);
        group.addCourse(course);
        entityManager.merge(course);
    }

    @Override
    public Group getGroupById(Long id) {
        return entityManager.find(Group.class, id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        Group group1 = entityManager.find(Group.class, id);
        group1.setGroupName(group.getGroupName());
        group1.setDateOfStart(group.getDateOfStart());
        group1.setImage(group.getImage());
        entityManager.merge(group1);
    }

    @Override
    public void deleteByIdGroup(Long id) {
        Group group = entityManager.find(Group.class, id);
        group.setCourses(null);
        entityManager.remove(group);
    }

    @Override
    public void assigningGroup(Long courseId, Long groupId) throws IOException {
        Group group = entityManager.find(Group.class, groupId);
        Course course = entityManager.find(Course.class, courseId);
        if (course.getGroups() != null) {
            for (Group g : course.getGroups()) {
                if (g.getId() == groupId) {
                    throw new IOException("This group exists!");
                }
            }
        }
        group.addCourse(course);
        course.addGroup(group);
        entityManager.merge(group);
        entityManager.merge(course);

    }
}

