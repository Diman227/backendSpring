package dev.vorstu.services;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.TeacherEntity;
import dev.vorstu.mappers.GroupMapper;
import dev.vorstu.mappers.TeacherMapper;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.repositories.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupMapper groupMapper;
    private final TeacherMapper teacherMapper;

    // todo может для таких случаев вообще другие dto нужны?
    public GroupDTO addGroup(GroupDTO groupDTO){

        GroupEntity group = groupMapper.toGroupEntity(groupDTO);
        return groupMapper.toGroupDTO(groupRepository.save(group));
    }

    public GroupDTO getGroupById(Long id){

        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Группы с id - " + id + " не существует"));
        return groupMapper.toGroupDTO(group);
    }

    public Long deleteGroup(Long id){

        groupRepository.deleteById(id);
        return id;
    }

    public GroupDTO updateGroup(GroupDTO groupDTO){

        GroupEntity group = groupMapper.toGroupEntity(groupDTO);
        return groupMapper.toGroupDTO(groupRepository.save(group));
    }

    public GroupDTO addTeacherToGroup(Long groupId, TeacherDTO teacherDTO){

        GroupEntity group = groupRepository.getGroupWithTeacher(groupId).orElseThrow(() -> new RuntimeException("Группы с id - " + groupId + " не существует"));

        if(group.getGroupTeacher() != null) {
            throw new RuntimeException("Учитель у группы с id - " + groupId + " уже существует");
        }

        TeacherEntity teacher = teacherRepository.findById(teacherDTO.getId()).orElseThrow(() -> new RuntimeException("Учителя с id - " + teacherDTO.getId() + " не существует"));
        //todo setGroupTeacherId ,  teacherRepository.findById remove
        group.setGroupTeacher(teacher);
        groupRepository.save(group);
        return groupMapper.toGroupDTO(group);
    }

    public List<GroupNameDTO> getAllGroupNames(){
        return groupMapper.toListGroupNameDTOs(groupRepository.getAllGroupNames());
    }

    public List<GroupDTO> getAllGroups() {
        List<GroupEntity> groupEntities = new ArrayList<>();
        this.groupRepository.findAll().forEach(groupEntities::add);
        return this.groupMapper.toListGroupDTOs(groupEntities);
    }
}
