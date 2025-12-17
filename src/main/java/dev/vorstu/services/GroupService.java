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

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final TeacherRepository teacherRepository;
    private final GroupMapper groupMapper;
    private final TeacherMapper teacherMapper;

    public GroupDTO addGroup(GroupDTO groupDTO){
        //todo remove check
        if(groupRepository.existsById(groupDTO.getId())){
            throw new RuntimeException("Группа с id - " + groupDTO.getId() + " уже существует");
        }

        GroupEntity group = groupMapper.toGroupEntity(groupDTO);
        group.setId(null); // todo check
        return groupMapper.toGroupDTO(groupRepository.save(group));
    }

    public GroupDTO getGroupById(Long id){

        //todo use spring validation in controller (@Validated)
        if(id == null){
            throw new RuntimeException("Id не может быть равно нулю");
        }

        GroupEntity group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Группы с id - " + id + " не существует"));
        return groupMapper.toGroupDTO(group);
    }

    public Long deleteGroup(Long id){

        if(id == null){
            throw new RuntimeException("Группы с id - " + id + " не существует");
        }

        groupRepository.deleteById(id);
        return id;
    }

    public GroupDTO updateGroup(GroupDTO groupDTO){
        //todo remove check
        if(!groupRepository.existsById(groupDTO.getId())){
            throw new RuntimeException("Учителя с id - " + groupDTO.getId() + " не существует");
        }

        GroupEntity group = groupMapper.toGroupEntity(groupDTO);
        return groupMapper.toGroupDTO(groupRepository.save(group));
    }

    public GroupDTO addTeacherToGroup(Long groupId, TeacherDTO teacherDTO){

        if(groupId == null){
            throw new RuntimeException("ID не может равняться нулю");
        }

        //todo hibernate n+1, join fetch
        GroupEntity group = groupRepository.findById(groupId).orElseThrow(() -> new RuntimeException("Группы с id - " + groupId + " не существует"));

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

}
