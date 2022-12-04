package peaksoft.repository;

import peaksoft.model.Group;

import java.io.IOException;
import java.util.List;

public interface GroupRepository {

    List<Group> getAllListGroups();
    List<Group> getAllGroups(Long courseId);

    void addGroup(Group group, Long courseId);

    Group getGroupById(Long id);

    void updateGroup(Group group, Long id);

    void deleteByIdGroup(Long id);
    void assigningGroup(Long courseId, Long GroupId) throws IOException;
}
