package com.AssignmentStream.AssignmentStream.service;

import com.AssignmentStream.AssignmentStream.DTO.StudentDTO;
import com.AssignmentStream.AssignmentStream.exception.StudentNotFoundException;
import com.AssignmentStream.AssignmentStream.model.Student;
import com.AssignmentStream.AssignmentStream.repository.StudentRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    StudentRepository studentRepository;

    public StudentDTO addStudent(StudentDTO studentDTO){
        Student student = modelMapper.map(studentDTO,Student.class);
        student = studentRepository.save(student);

        StudentDTO responseDto = modelMapper.map(student,StudentDTO.class);

        return responseDto;

    }

    public List<StudentDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        List<StudentDTO> studentDTOS = modelMapper.map(students,new TypeToken<List<StudentDTO>>(){}.getType());

        return studentDTOS;
    }

    public StudentDTO getStudentById(int id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent()){
            throw new StudentNotFoundException("Student not Found");
        }

        StudentDTO studentDTO = modelMapper.map(student.get(),StudentDTO.class);

        return studentDTO;
    }

    public StudentDTO updateStudentById(int id,StudentDTO studentDTO) throws Exception {

        Optional<Student> student = studentRepository.findById(id);
        if(!student.isPresent()){
            throw new StudentNotFoundException("Student not Found");
        }

        if(studentDTO.getFirstName()!=null) student.get().setFirstName(studentDTO.getFirstName());
        if(studentDTO.getLastName()!=null) student.get().setLastName(studentDTO.getLastName());
        if(studentDTO.getRollNumber()!=null) student.get().setRollNumber(studentDTO.getRollNumber());
        if(studentDTO.isActiveStatus()!=false && studentDTO.isActiveStatus()!=true) student.get().setActiveStatus(studentDTO.isActiveStatus());

        Student updatedStudent = studentRepository.save(student.get());
        StudentDTO responseDto = modelMapper.map(updatedStudent,StudentDTO.class);

        return responseDto;
    }

    public void deleteStudentById(int id) throws Exception {
        Optional<Student> student = studentRepository.findById(id);

        if(!student.isPresent() || student.get().isActiveStatus()==false){
            throw new StudentNotFoundException("Student not Found");
        }

        student.get().setActiveStatus(false);
        studentRepository.save(student.get());
    }
}
