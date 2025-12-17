package dev.vorstu.services;

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
public class TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    public TeacherDTO addTeacher(TeacherDTO teacherDTO){

        if(teacherRepository.existsById(teacherDTO.getId())){
            throw new RuntimeException("Учителя с id - " + teacherDTO.getId() + " уже существует");
        }

        TeacherEntity teacherEntity = teacherMapper.toTeacherEntity(teacherDTO);
        if(teacherDTO.getGroupsOfStudents() != null){
            teacherEntity.setGroupsOfStudents(toListGroupEntities(teacherDTO.getGroupsOfStudents()));
        }
        return teacherMapper.toTeacherDTO(teacherRepository.save(teacherEntity));
    }

    public Long deleteTeacher(Long id){

        if(!teacherRepository.existsById(id)){
            throw new RuntimeException("Учителя с id - " + id + " не существует");
        }

        teacherRepository.deleteById(id);
        return id;
    }

    public TeacherDTO updateTeacher(TeacherDTO teacherDTO){

        if(!teacherRepository.existsById(teacherDTO.getId())){
            throw new RuntimeException("Учителя с id - " + teacherDTO.getId() + " не существует");
        }

        TeacherEntity teacherEntity = teacherMapper.toTeacherEntity(teacherDTO);
        teacherEntity.setGroupsOfStudents(toListGroupEntities(teacherDTO.getGroupsOfStudents()));
        return teacherMapper.toTeacherDTO(teacherRepository.save(teacherEntity));
    }

    public List<GroupEntity> toListGroupEntities(List<GroupNameDTO> groupNameDTOList) {

        List<Long> ids = new ArrayList<>();

        for(GroupNameDTO group : groupNameDTOList){
            ids.add(group.getId());
        }
        return groupRepository.getGroupsByIds(ids);
    }

    public TeacherDTO getTeacherById(Long id){

        if(id == null){
            throw new RuntimeException("Id не может быть равно нулю");
        }
        TeacherEntity teacherEntity = teacherRepository.findById(id).orElseThrow();
        return teacherMapper.toTeacherDTO(teacherEntity);
    }

    public List<GroupNameDTO> getTeacherGroups(Long teacherId){
        if (teacherId == null){
            throw new RuntimeException("Id не может быть равно нулю");
        }
        List<GroupEntity> groups = this.teacherRepository.getTeacherGroups(teacherId).orElseThrow();
        return this.groupMapper.toListGroupNameDTOs(groups);
    }

}
