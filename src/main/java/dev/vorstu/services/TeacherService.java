package dev.vorstu.services;

import dev.vorstu.dto.GroupNameDTO;
import dev.vorstu.dto.TeacherDTO;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.TeacherEntity;
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


    // TODO допроверить логику
    public void addTeacher(TeacherDTO teacherDTO){
        TeacherEntity teacherEntity = teacherMapper.toTeacherEntity(teacherDTO);
        if(teacherDTO.getGroupsOfStudents() != null){
            teacherEntity.setGroupsOfStudents(toListGroupEntities(teacherDTO.getGroupsOfStudents()));
        }
        teacherRepository.save(teacherEntity);
    }

    // TODO по-хорошему проверка на успешное удаление
    public Long deleteTeacher(Long id){
        teacherRepository.deleteById(id);
        return id;
    }

    // TODO через маппер должно работать
    public TeacherDTO updateTeacher(TeacherDTO teacherDTO){
        TeacherEntity teacherEntity = teacherRepository.findById(teacherDTO.getId()).orElseThrow();
        teacherEntity.setName(teacherDTO.getName());
        teacherEntity.setSurname(teacherDTO.getSurname());
        teacherEntity.setPatronymic(teacherDTO.getPatronymic());
        teacherEntity.setGroupsOfStudents(toListGroupEntities(teacherDTO.getGroupsOfStudents()));
        teacherRepository.save(teacherEntity);
        return teacherDTO;
    }


    // TODO по логике должен вроде быть в маппере, но нужен репозиторий, поэтому здесь
    public List<GroupEntity> toListGroupEntities(List<GroupNameDTO> groupNameDTOList) {

        List<Long> ids = new ArrayList<>();

        for(GroupNameDTO group : groupNameDTOList){
            ids.add(group.getId());
        }
        return groupRepository.getGroupsByIds(ids);
    }

}
