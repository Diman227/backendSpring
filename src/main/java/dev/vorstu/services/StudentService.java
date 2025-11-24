package dev.vorstu.services;

import dev.vorstu.dto.GroupDTO;
import dev.vorstu.dto.StudentDTO;
import dev.vorstu.entities.GroupEntity;
import dev.vorstu.entities.StudentEntity;
import dev.vorstu.mappers.StudentMapper;
import dev.vorstu.repositories.GroupRepository;
import dev.vorstu.repositories.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final StudentMapper studentMapper;

    public void addStudent(StudentDTO studentDTO) {

        StudentEntity newStudent = studentMapper.toStudentEntity(studentDTO);
        GroupEntity group = getStudentGroup(studentDTO.getGroupName());
        newStudent.setGroup(group);
        studentRepository.save(newStudent);
    }

    public Page<StudentDTO> getStudentsPerPage(Pageable pageable) {
        return studentMapper.toPageStudentDTO(studentRepository.findAll(pageable));
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {
        StudentEntity newStudent = studentRepository.findById(studentDTO.getId()).orElseThrow();
        GroupEntity group = getStudentGroup(studentDTO.getGroupName());
        newStudent.setGroup(group);
        return studentMapper.toStudentDTO(studentRepository.save(newStudent));
    }

    public GroupEntity getStudentGroup(String groupName){
        return groupRepository.getGroupByName(groupName).orElseThrow();
    }

    public StudentDTO getStudentById(Long id){
        StudentEntity student = studentRepository.findById(id).orElseThrow();
        return studentMapper.toStudentDTO(student);
    }

    public Long deleteStudent(Long id){
        studentRepository.deleteById(id);
        return id;
    }

    public Page<StudentDTO> getFilteredStudents(String filter, Pageable pageable) {
        return studentMapper.toPageStudentDTO(studentRepository.getFilteredStudents(filter, pageable));
    }
}
