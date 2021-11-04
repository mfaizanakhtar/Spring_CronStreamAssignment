package com.AssignmentStream.AssignmentStream.DTO;

import com.AssignmentStream.AssignmentStream.model.BookIssued;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
public class StudentDTO {
//    @Autowired
//    ModelMapper modelMapper;

    private int id;

    private String rollNumber;

    private String firstName;

    private String lastName;

    private boolean activeStatus;

    @JsonIgnore
    private List<BookIssued> bookIssued;

}
