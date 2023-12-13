package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/student")
public class studentController {

    //student service
    private final studentService studentService;

    @Autowired
    public studentController(studentService studentService){
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents(){
        //call the service method
        return studentService.getStudents();
    }
    @PostMapping
    public void registerNewStudent(@RequestBody Student student){
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id){
        studentService.deleteStudent(id);
    }

    //In this case the PUT verb will update only the name and email
    @PutMapping(path = "{studentId}")
    public void updateStudent(@PathVariable("studentId") Long id,
                              @RequestParam(required = false) String name,
                              @RequestParam(required = false) String email)
    {
            studentService.updateStudent(id, name, email);
    }
}
