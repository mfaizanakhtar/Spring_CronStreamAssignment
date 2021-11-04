package com.AssignmentStream.AssignmentStream.controller;

import com.AssignmentStream.AssignmentStream.DTO.BookIssuedDTO;
import com.AssignmentStream.AssignmentStream.model.Student;
import com.AssignmentStream.AssignmentStream.service.BookIssuedService;
import com.AssignmentStream.AssignmentStream.service.BookService;
import com.AssignmentStream.AssignmentStream.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/issuebook")
public class BookIssuedController {
    @Autowired
    BookIssuedService bookIssuedService;


    @PostMapping("/issue")
    public BookIssuedDTO issueBook(@Param("studentId") int studentId,@Param("bookId") int bookId) throws Exception {
        return bookIssuedService.issueBook(studentId,bookId);
    }

    @GetMapping("/getAll")
    public List<BookIssuedDTO> getAllIssued(){
        return bookIssuedService.getAllIssued();
    }

    @PutMapping("/return")
    public BookIssuedDTO returnBook(@Param("studentId") int studentId,@Param("bookId") int bookId) throws Exception {
        return bookIssuedService.returnBook(studentId,bookId);
    }

    @GetMapping("/getBooksByDays/{days}")
    public List<BookIssuedDTO> returnBooksIssuedByDate(@PathVariable int days){
        return bookIssuedService.getBooksWithDates(days);
    }

    @GetMapping("/getLateBooks")
    public List<BookIssuedDTO> returnLateBooks(){
        return bookIssuedService.getLateBooks();
    }
}
