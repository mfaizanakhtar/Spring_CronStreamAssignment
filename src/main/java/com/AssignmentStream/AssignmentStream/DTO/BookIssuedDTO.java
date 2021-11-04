package com.AssignmentStream.AssignmentStream.DTO;

import com.AssignmentStream.AssignmentStream.model.BookCatalog;
import com.AssignmentStream.AssignmentStream.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.time.LocalDate;


@Data
public class BookIssuedDTO {

    private int id;

    @JsonIgnore
    private BookCatalog bookCatalog;

    @JsonIgnore
    private Student student;

    private LocalDate issuedDate;

    private LocalDate returnDate;

    private boolean isReturned;

    private boolean isLate;


    public int getBookid() {
        return bookCatalog.getId();
    }

    public int getStudentid() {
        return student.getId();
    }


    public String getBookname() {
        return bookCatalog.getBookName();
    }

    public String getStudentname() {
        return student.getFirstName()+" "+student.getLastName();
    }

}
