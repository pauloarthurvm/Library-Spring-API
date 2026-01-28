package com.pavam.library.repository;

import com.pavam.library.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<BookEntity, Long> {

    BookEntity findBookEntityByName(String name);

    @Query(value = "SELECT * FROM BOOK WHERE publisher_id = :id", nativeQuery = true)
    List<BookEntity> getAllBooksByPublisherId(@Param("id") Long id);

}
