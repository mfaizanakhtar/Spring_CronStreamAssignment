package com.AssignmentStream.AssignmentStream.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Data
@Table(name="BOOK_ISSUED")
@NoArgsConstructor
public class BookIssued {
    @Id
    @GeneratedValue
    @Column(name="BOOK_ISSUED_ID")
    private int id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="BOOK_CATALOG_ID", referencedColumnName = "BOOK_CATALOG_ID")
    @JsonIgnore
    private BookCatalog bookCatalog;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="STUDENT_ID", referencedColumnName = "STUDENT_ID")
    @JsonIgnore
    private Student student;

    @Column(name="ISSUED_DATE")
    private LocalDate issuedDate=LocalDate.now();

    @Column(name="RETURN_DATE")
    private LocalDate returnDate=LocalDate.now().plusDays(30);

    @Column(name="IS_RETURNED")
    private boolean isReturned=false;

    @Column(name="IS_LATE")
    private boolean isLate=false;

    public BookIssued(Student student,BookCatalog bookCatalog){
        this.student=student;
        this.bookCatalog=bookCatalog;
    }
}
