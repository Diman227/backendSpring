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

    public StudentDTO addStudent(StudentDTO studentDTO) {

        if(studentDTO.getId() != null && studentRepository.existsById(studentDTO.getId())) {
            throw new RuntimeException("Студент с id " + studentDTO.getId() + " уже существует!");
        }

        StudentEntity newStudent = studentMapper.toStudentEntity(studentDTO);
        GroupEntity group = groupRepository.findById(studentDTO.getGroupId()).orElseThrow();
        newStudent.setGroup(group);
        newStudent.setId(null);
        return studentMapper.toStudentDTO(studentRepository.save(newStudent));
    }

    public Page<StudentDTO> getStudentsPerPage(Pageable pageable) {
        return studentMapper.toPageStudentDTO(studentRepository.findAll(pageable));
    }

    public StudentDTO updateStudent(StudentDTO studentDTO) {

        if(!studentRepository.existsById(studentDTO.getId())) {
            throw new RuntimeException("Студента с id - " + studentDTO.getId() + " не существует");
        }

        StudentEntity changingStudent = studentMapper.toStudentEntity(studentDTO);
        GroupEntity group = groupRepository.findById(studentDTO.getGroupId()).orElseThrow();
        changingStudent.setGroup(group);
        return studentMapper.toStudentDTO(studentRepository.save(changingStudent));
    }

    public GroupEntity getStudentGroup(Long groupId){
        return groupRepository.findById(groupId).orElseThrow();
    }

    public StudentDTO getStudentById(Long id){

        if(id == null){
            throw new RuntimeException("Id не может быть равно нулю");
        }

        StudentEntity student = studentRepository.findById(id).orElseThrow();
        return studentMapper.toStudentDTO(student);
    }

    public Long deleteStudent(Long id){

        if(!studentRepository.existsById(id)){
            throw new RuntimeException("Студента с id - " + id + " не существует");
        }

        studentRepository.deleteById(id);
        return id;
    }

    public Page<StudentDTO> getFilteredStudents(String filter, Pageable pageable) {
        return studentMapper.toPageStudentDTO(studentRepository.getFilteredStudents(filter, pageable));
    }
}
