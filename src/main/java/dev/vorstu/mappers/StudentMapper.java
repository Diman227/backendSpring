package dev.vorstu.mappers;

import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.StudentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "groupId", source = "group.id")
    @Mapping(target = "credentialId", source = "linkedCredentials.id")
    StudentDTO toStudentDTO(StudentEntity studentEntity);

    // Группа присваивается в сервисе
//    @Mapping(target = "group", ignore = true)
    @Mapping(target = "linkedCredentials", ignore = true)
    StudentEntity toStudentEntity(StudentDTO studentDTO);

    List<StudentDTO> toListStudentDTOs(List<StudentEntity> listOfStudentEntities);

    List<StudentEntity> toListStudentEntities(List<StudentDTO> listOfStudentDTOs);

    default Page<StudentDTO> toPageStudentDTO(Page<StudentEntity> studentEntityPage) {
        if (studentEntityPage == null){
            return Page.empty();
        }
        return studentEntityPage.map(this::toStudentDTO);
    };

    default Page<StudentEntity> toPageStudentEntity(Page<StudentDTO> studentDTOPage)
    {
        if (studentDTOPage == null){
            return Page.empty();
        }
        return studentDTOPage.map(this::toStudentEntity);
    };

    default List<StudentDTO> studentEntityListToStudentDTOList(List<StudentEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<StudentDTO> list1 = new ArrayList<StudentDTO>( list.size() );
        for ( StudentEntity studentEntity : list ) {
            list1.add( toStudentDTO( studentEntity ) );
        }

        return list1;
    }

//    default StudentDTO studentEntityToStudentDTO(StudentEntity studentEntity) {
//        if ( studentEntity == null ) {
//            return null;
//        }
//
//        StudentDTO studentDTO = new StudentDTO();
//
//        studentDTO.setId( studentEntity.getId() );
//        studentDTO.setSurname( studentEntity.getSurname() );
//        studentDTO.setName( studentEntity.getName() );
//        studentDTO.setPatronymic( studentEntity.getPatronymic() );
//        studentDTO.setGroupId(studentEntity.getGroup().getId());
//        // TODO пока не очень уверен, что тут нужны credentials
//        studentDTO.setCredentialId(studentEntity.getLinkedCredentials().getId());
//
//        return studentDTO;
//    }

}
