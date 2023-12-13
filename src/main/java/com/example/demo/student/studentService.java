package com.example.demo.student;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class studentService {

    private final StudentRepository studentRepository;
    @Autowired
    public studentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public List<Student> getStudents(){
        //return students
        return studentRepository.findAll();

    }

    //POST --- http://localhost:8080/api/v1/student
    //payload -> application/json ->  { "name": "test student 3", "email": "test3@gmail.com", "dob": "1995-12-17" }
    public void addNewStudent(Student student){
        //get the email from the object
        Optional<Student> studentOptionalEmail = studentRepository.findStudentByEmail(student.getEmail());

        //check if the value is truthy
        if(studentOptionalEmail.isPresent()){
            throw new IllegalStateException("Email already taken!!");
        }else{
            //just print it in to the console
            //System.out.println(student);
            //save the student
            studentRepository.save(student);

        }
    }


    //DELETE --- http://localhost:8080/api/v1/student/1
    public void deleteStudent(Long id){
        //check if the student exists first
        boolean studentExists = studentRepository.existsById(id);

        if(!studentExists){
            throw new IllegalStateException("Student with id " + id + " doesn't exist");
        }else{
            studentRepository.deleteById(id);
        }


    }

    //PUT --- http://localhost:8080/api/v1/student/1?name=Oswaldo&email=oswaldo@gmail.com
    @Transactional
    public void updateStudent(Long id, String name, String email){
        Student student = studentRepository.findById(id)
                .orElseThrow(()-> new IllegalStateException(
                   "Student with the id " + id + " does not exist!"
                ));


        //name -- check null value and length above 0 and if the name is not the same as the current student's name
        if(name != null && !name.isEmpty() && !Objects.equals(student.getName(), name)){
           //set the name
            student.setName((name));
        }


        //email
        if(email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
            //find the student by email
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);

            //check if the email already exists
            if(studentOptional.isPresent()){
                throw new IllegalStateException(("Email is already taken!!"));
            }

            //set the email
            student.setEmail(email);

        }

    }

}
