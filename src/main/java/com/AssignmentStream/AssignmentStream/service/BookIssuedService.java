package com.AssignmentStream.AssignmentStream.service;

import com.AssignmentStream.AssignmentStream.DTO.BookCatalogDTO;
import com.AssignmentStream.AssignmentStream.DTO.BookIssuedDTO;
import com.AssignmentStream.AssignmentStream.DTO.StudentDTO;
import com.AssignmentStream.AssignmentStream.exception.BookISsuedException;
import com.AssignmentStream.AssignmentStream.model.BookCatalog;
import com.AssignmentStream.AssignmentStream.model.BookIssued;
import com.AssignmentStream.AssignmentStream.model.Student;
import com.AssignmentStream.AssignmentStream.repository.BookIssuedRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BookIssuedService {
    @Autowired
    StudentService studentService;

    @Autowired
    BookService bookService;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    BookIssuedRepository bookIssuedRepository;

    Logger log = LoggerFactory.getLogger(this.getClass());

    public BookIssuedDTO issueBook(int studentId, int bookId) throws Exception {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        Student student = modelMapper.map(studentDTO, Student.class);

        BookCatalogDTO bookCatalogDTO = bookService.getBookById(bookId);
        BookCatalog bookCatalog = modelMapper.map(bookCatalogDTO,BookCatalog.class);

        BookIssued findBookIssue = bookIssuedRepository.findByStudentAndBookNotReturned(studentId,bookId);
        if(findBookIssue!=null){
            throw new BookISsuedException("Book Already Issued to Student");
        }

        if(bookCatalog.getAvailableCopies()==0){
            throw new BookISsuedException("Book Not Available");
        }

        bookCatalogDTO.setAvailableCopies(bookCatalogDTO.getAvailableCopies()-1);
        bookService.updateBookById(bookCatalogDTO.getId(),bookCatalogDTO);

        BookIssued bookIssued = new BookIssued(student,bookCatalog);
        BookIssued savedBookIssued = bookIssuedRepository.save(bookIssued);

        BookIssuedDTO responseDto = modelMapper.map(savedBookIssued,BookIssuedDTO.class);
        return responseDto;
    }

    public List<BookIssuedDTO> getAllIssued(){
        List<BookIssued> bookIssueds = bookIssuedRepository.findAll();
        List<BookIssuedDTO> bookIssuedDTOS=modelMapper.map(bookIssueds,new TypeToken<List<BookIssuedDTO>>(){}.getType());

        return bookIssuedDTOS;
    }

    public BookIssuedDTO returnBook(int studentId,int bookId){
        BookIssued bookIssued = bookIssuedRepository.findByStudentAndBookNotReturned(studentId,bookId);

        bookIssued.setReturnDate(LocalDate.now());
        bookIssued.setReturned(true);

        BookIssued returnedBook = bookIssuedRepository.save(bookIssued);

        BookIssuedDTO bookIssuedDTO = modelMapper.map(returnedBook,BookIssuedDTO.class);
        return  bookIssuedDTO;

    }

    public List<BookIssuedDTO> getBooksWithDates(int Days){
        List<BookIssued> bookIssuedList = bookIssuedRepository.findAll();
        LocalDate today = LocalDate.now();

        Stream<BookIssued> filteredIssuedStream = bookIssuedList.stream().filter(obj->
                Duration.between(today.atStartOfDay(),obj.getIssuedDate().atStartOfDay()).toDays()<Days);

        List<BookIssued> filterIssuedList = filteredIssuedStream.collect(Collectors.toList());
        return modelMapper.map(filterIssuedList,new TypeToken<List<BookIssuedDTO>>(){}.getType());
    }

    public List<BookIssuedDTO> getLateBooks(){
        List<BookIssued> bookIssuedList = bookIssuedRepository.findAll();

        Stream<BookIssued> filteredIssuedStream = bookIssuedList.stream().filter(obj->
                obj.isLate());

        List<BookIssued> filterIssuedList = filteredIssuedStream.collect(Collectors.toList());
        return modelMapper.map(filterIssuedList,new TypeToken<List<BookIssuedDTO>>(){}.getType());
    }

    @Transactional
    public void MarkAsLate(){
        bookIssuedRepository.updateIssuedBooksAsLate(LocalDate.now());
    }
}
