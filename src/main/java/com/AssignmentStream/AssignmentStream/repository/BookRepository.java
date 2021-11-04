package com.AssignmentStream.AssignmentStream.repository;

import com.AssignmentStream.AssignmentStream.model.BookCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookCatalog,Integer> {
}
