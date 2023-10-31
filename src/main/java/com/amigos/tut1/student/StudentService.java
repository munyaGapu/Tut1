package com.amigos.tut1.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Component
public class StudentService {
    private final StudentRepository studentRepository;
@Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
   // private Student student;


    public List<Student> json(){

    return studentRepository.findAll();
    }

    public void addNew(Student student) {
        Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
        if(studentByEmail.isPresent()){
            throw new IllegalStateException("email taken");
        }
        studentRepository.save(student);
        //System.out.println(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exist=studentRepository.existsById(studentId);
        if(!exist){
            throw new IllegalStateException("Student with id "+studentId+" not found");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId,String name,String email) {
        Student student=studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
                "student with id "+studentId+"not found"
        ));
        //boolean exist=studentRepository.existsById(studentId);
       // Student student=new Student();
      /*  if(!exist){
            throw new IllegalStateException("not found");
        }*/
        //Student student = new Student();
        if(name!=null && name.length()>0 && !Objects.equals(name,student.getName())){
            student.setName(name);
        }
        if(email!=null && email.length()>0){
            Optional<Student> studentByEmail = studentRepository.findStudentByEmail(email);
            if(studentByEmail.isPresent()){
                throw new IllegalStateException("you provided the same email");
            }
            student.setEmail(email);
        }
   /* student.setName(student.getName());
    student.setEmail(student.getEmail());
    student.setDob(student.getDob());
    student.setAge(student.getAge());
    studentRepository.save(student);*/


    }
}
