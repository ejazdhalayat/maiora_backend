package com.maiora.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.maiora.model.Student;
import com.maiora.repository.StudentRepository;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student addStudent(Student student) {
        student.setAge(calculateAge(student.getYear(), student.getMonth(), student.getDay()));
        return studentRepository.save(student);
    }

    public int calculateAge(int year, int month, int day) {
        LocalDate birthDate = LocalDate.of(year, month, day);
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public void updateAges() {
        List<Student> students = studentRepository.findAll();
        for (Student student : students) {
            student.setAge(calculateAge(student.getYear(), student.getMonth(), student.getDay()));
        }
        studentRepository.saveAll(students);
    }
}