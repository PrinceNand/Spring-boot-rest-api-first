package com.spring.springboot_rest_api.controller;

import com.spring.springboot_rest_api.bean.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    // http://localhost:8080/student
    @GetMapping("student")
    public ResponseEntity<Student> getStudent(){
        Student student = new Student(
                1,
                "Ashley",
                "Nand"
        );
        return ResponseEntity.ok()
                .header("custom-header","Ashley")
                .body(student);
    }

    // http://localhost:8080/students
    @GetMapping()
    public ResponseEntity<List<Student>> getStudents(){
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Ashley", "Nand"));
        students.add(new Student(2, "King", "Dollar"));
        return ResponseEntity.ok(students);
    }

    // Spring BOOT REST API with Path Variable
    // {id} - URI template variable
    // http://localhost:8080/students/1/ashley/nand
    @GetMapping("{id}/{first-name}/{last-name}")
    public ResponseEntity<Student> studentPathVariable(@PathVariable("id") int studentId,
                                       @PathVariable("first-name") String firstName,
                                       @PathVariable("last-name") String lastName) {
        Student student = new Student(studentId, firstName, lastName);
        return ResponseEntity.ok(student);
    }

    // Spring boot REST API with Request Param
    //  http://localhost:8080/students/query?id=1&firstName=Ashley&lastName=Nand
    @GetMapping("query")
    public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,
                                          @RequestParam String firstName,
                                          @RequestParam String lastName){
        Student student = new Student(id, firstName, lastName);
        return ResponseEntity.ok(student);
    }


    // Spring boot REST API that handle HTTP post request -- For Creating Data
    // @PostMapping and @RequestBody
    @PostMapping("create")
//    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Student> getPostStudent(@RequestBody Student student){
        System.out.println(student.getId());
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return new ResponseEntity<>(student,HttpStatus.CREATED);
    }


    // Spring boot REST API that handle HTTP post request -- For updating Data
    // @PutMapping, @PathVariable and @RequestBody
    @PutMapping ("{id}/update")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<Student> getPutStudent(@RequestBody Student student,
                                 @PathVariable("id") int studentId){
        System.out.println(studentId);
        System.out.println(student.getFirstName());
        System.out.println(student.getLastName());
        return ResponseEntity.ok(student);
    }


    // Spring boot REST API that handle HTTP post request -- For deleting Data
    // @DeleteMapping, @PathVariable
    @DeleteMapping ("{id}/delete")
    public ResponseEntity<String> getPutStudent(@PathVariable("id") int studentId){
        System.out.println(studentId);
        return ResponseEntity.ok("Student Deleted Successfully!");
    }

}
