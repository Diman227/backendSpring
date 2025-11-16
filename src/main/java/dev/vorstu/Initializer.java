package dev.vorstu;

import dev.vorstu.entities.StudentEntity;
import dev.vorstu.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Initializer {

    @Autowired
    private StudentRepository studentRepository;

    public void initial(){
        studentRepository.save(new StudentEntity("Никитин", "Никита", "Никитыч", "def_group", "+79"));
        studentRepository.save(new StudentEntity("Иванов", "Иван", "Иванович", "def_group1", "+791"));
        studentRepository.save(new StudentEntity("Сергеев", "Сергей", "Сергеевич", "def_group2", "+792"));
        studentRepository.save(new StudentEntity("Васильев", "Василий", "Васильевич", "def_group3", "+793"));
        studentRepository.save(new StudentEntity("Борисов", "Борис", "Борисович", "def_group4", "+794"));
        studentRepository.save(new StudentEntity("Алексеев", "Алексей", "Алексеевич", "def_group5", "+795"));
    }
}
