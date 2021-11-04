package com.AssignmentStream.AssignmentStream.DTO;

import com.AssignmentStream.AssignmentStream.model.BookIssued;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class BookCatalogDTO {

    private int id;

    private String code;

    private String author;

    private String bookName;

    private int totalCopies;

    private int availableCopies;

    @JsonIgnore
    private List<BookIssued> bookIssued;
}
