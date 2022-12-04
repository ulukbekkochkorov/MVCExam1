package peaksoft.service.impl;

import org.springframework.stereotype.Service;
import peaksoft.service.GroupService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import peaksoft.model.Group;
import peaksoft.repository.GroupRepository;

import java.io.IOException;
import java.util.List;

@Service
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceImpl(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    @Override
    public List<Group> getAllListGroups() {
        return groupRepository.getAllListGroups();
    }

    @Override
    public List<Group> getAllGroups(Long courseId) {
        return groupRepository.getAllGroups(courseId);
    }

    @Override
    public void addGroup(Group group, Long id) {
        groupRepository.addGroup(group, id);
    }

    @Override
    public Group getGroupById(Long id) {
        return groupRepository.getGroupById(id);
    }

    @Override
    public void updateGroup(Group group, Long id) {
        groupRepository.updateGroup(group, id);

    }

    @Override
    public void deleteByIdGroup(Long id) {
        groupRepository.deleteByIdGroup(id);

    }

    @Override
    public void assigningGroup(Long courseId, Long groupId) throws IOException {
        groupRepository.assigningGroup(courseId,groupId);

    }
}
