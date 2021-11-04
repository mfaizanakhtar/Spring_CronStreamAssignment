package com.AssignmentStream.AssignmentStream.service;

import com.AssignmentStream.AssignmentStream.DTO.BookCatalogDTO;
import com.AssignmentStream.AssignmentStream.exception.BookNotFoundException;
import com.AssignmentStream.AssignmentStream.model.BookCatalog;
import com.AssignmentStream.AssignmentStream.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    ModelMapper modelMapper;

    public BookCatalogDTO addBook(BookCatalogDTO bookCatalogDTO){
        BookCatalog bookCatalog = modelMapper.map(bookCatalogDTO,BookCatalog.class);
        bookCatalog = bookRepository.save(bookCatalog);

        BookCatalogDTO responseDto = modelMapper.map(bookCatalog,BookCatalogDTO.class);
        return responseDto;
    }

    public List<BookCatalogDTO> getAllBooks(){
        List<BookCatalog> bookCatalogs = bookRepository.findAll();
        List<BookCatalogDTO> bookCatalogDTOS=modelMapper.map(bookCatalogs,new TypeToken<List<BookCatalogDTO>>(){}.getType());

        return bookCatalogDTOS;
    }

    public BookCatalogDTO getBookById(int id) throws Exception {
        Optional<BookCatalog> bookCatalog = bookRepository.findById(id);

        if(!bookCatalog.isPresent()){
            throw new BookNotFoundException("Book Not Found");
        }

        BookCatalogDTO bookCatalogDTO = modelMapper.map(bookCatalog.get(),BookCatalogDTO.class);
        return bookCatalogDTO;

    }

    public BookCatalogDTO updateBookById(int id,BookCatalogDTO bookCatalogDTO) throws Exception {
        Optional<BookCatalog> bookCatalog = bookRepository.findById(id);
        if(!bookCatalog.isPresent()){
            throw new BookNotFoundException("Book Not Found");
        }

        if(bookCatalogDTO.getBookName()!=null) bookCatalog.get().setBookName(bookCatalogDTO.getBookName());
        if(bookCatalogDTO.getAuthor()!=null) bookCatalog.get().setAuthor(bookCatalogDTO.getAuthor());
        if(bookCatalogDTO.getCode()!=null) bookCatalog.get().setCode(bookCatalogDTO.getCode());
        if(bookCatalogDTO.getAvailableCopies()!=0) bookCatalog.get().setAvailableCopies(bookCatalogDTO.getAvailableCopies());
        if(bookCatalogDTO.getTotalCopies()!=0) bookCatalog.get().setTotalCopies(bookCatalogDTO.getTotalCopies());

        BookCatalogDTO responseDto = modelMapper.map(bookCatalog.get(),BookCatalogDTO.class);
        return responseDto;
    }

}
