package com.AssignmentStream.AssignmentStream.controller;

import com.AssignmentStream.AssignmentStream.DTO.BookCatalogDTO;
import com.AssignmentStream.AssignmentStream.model.BookCatalog;
import com.AssignmentStream.AssignmentStream.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/book")
public class BookController {

    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public BookCatalogDTO addBook(@RequestBody BookCatalogDTO bookCatalogDTO){
        return bookService.addBook(bookCatalogDTO);
    }

    @GetMapping("/getAll")
    public List<BookCatalogDTO> getAllBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/getById/{id}")
    public BookCatalogDTO getBookById(@PathVariable int id) throws Exception {
        return bookService.getBookById(id);
    }

    @PutMapping("update/{id}")
    public BookCatalogDTO updateBookById(@PathVariable int id,@RequestBody BookCatalogDTO bookCatalogDTO) throws Exception {
        return bookService.updateBookById(id,bookCatalogDTO);
    }
}
