package com.AssignmentStream.AssignmentStream.repository;

import com.AssignmentStream.AssignmentStream.model.BookIssued;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface BookIssuedRepository extends JpaRepository<BookIssued,Integer> {
    @Query(value = "SELECT * FROM book_issued WHERE book_catalog_id=:BookId AND student_id=:studentId AND is_returned=0",nativeQuery = true)
    public BookIssued findByStudentAndBookNotReturned(int studentId, int BookId);

    @Modifying(clearAutomatically = true)
    @Query(value = "UPDATE book_issued SET is_late=1 WHERE is_returned=0 AND DATEDIFF(:currentDate,issued_date)>30",nativeQuery = true)
    public void updateIssuedBooksAsLate(LocalDate currentDate);

}
