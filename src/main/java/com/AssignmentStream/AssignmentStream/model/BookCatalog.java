package com.AssignmentStream.AssignmentStream.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="BOOK_CATALOG")
public class BookCatalog {
    @GeneratedValue
    @Id
    @Column(name = "BOOK_CATALOG_ID")
    private int id;
    @Column(name="CODE")
    private String code;
    @Column(name="AUTHOR")
    private String author;
    @Column(name="BOOK_NAME")
    private String bookName;
    @Column(name="TOTAL_COPIES")
    private int totalCopies;
    @Column(name="AVAILABLE_COPIES")
    private int availableCopies;
    @OneToMany(mappedBy = "bookCatalog")
    private List<BookIssued> bookIssued;
}
