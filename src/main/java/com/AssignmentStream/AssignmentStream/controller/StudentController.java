package com.AssignmentStream.AssignmentStream.controller;

import com.AssignmentStream.AssignmentStream.DTO.StudentDTO;
import com.AssignmentStream.AssignmentStream.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/student")
public class StudentController {
    @Autowired
    StudentService studentService;

    @PostMapping("/add")
    public StudentDTO addStudent(@RequestBody StudentDTO studentDTO){
        return studentService.addStudent(studentDTO);
    }

    @GetMapping("/getAll")
    public List<StudentDTO> getAllStudents(){
        return studentService.getAllStudents();
    }

    @GetMapping("/getById/{id}")
    public StudentDTO getStudentById(@PathVariable int id) throws Exception {
        return studentService.getStudentById(id);
    }

    @PutMapping("/update/{id}")
    public StudentDTO updateStudentById(@PathVariable int id,@RequestBody StudentDTO studentDTO) throws Exception {
        return studentService.updateStudentById(id,studentDTO);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStudentById(@PathVariable int id) throws Exception {
        studentService.deleteStudentById(id);
        return "Id="+id+" Deleted Sucessfully";
    }
}
