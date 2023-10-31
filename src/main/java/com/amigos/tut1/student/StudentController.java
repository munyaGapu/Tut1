package com.amigos.tut1.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/student")
public class StudentController {
    private final StudentService studentService;
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/list")
    public List<Student> json(){
        return studentService.json();
    }

    @PostMapping(path = "/add", consumes="application/json")
    public void Register(@RequestBody Student student){
        studentService.addNew(student);

    }
    @DeleteMapping(path = "{studentId}")
    public void delete(@PathVariable("studentId") Long studentId){
        studentService.deleteStudent(studentId);

    }
    @PutMapping(path = "{studentId}")
    public void update(@PathVariable("studentId") Long studentId,
                       @RequestParam(required = false) String name,@RequestParam(required = false) String email){
        studentService.updateStudent(studentId,name,email);
    }


}
