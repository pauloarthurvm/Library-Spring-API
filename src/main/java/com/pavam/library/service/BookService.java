package com.pavam.library.service;

import com.pavam.library.dto.BookRecordDto;
import com.pavam.library.entity.BookEntity;
import com.pavam.library.entity.ReviewEntity;
import com.pavam.library.repository.AuthorRepository;
import com.pavam.library.repository.BookRepository;
import com.pavam.library.repository.PublisherRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookService(BookRepository bookRepository,
                       AuthorRepository authorRepository,
                       PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Transactional
    public BookEntity saveBook(BookRecordDto bookRecordDto) {
        BookEntity bookEntity = new BookEntity();
        bookEntity.setName(bookRecordDto.name());
        bookEntity.setPublisher(publisherRepository.findById(bookRecordDto.publisherId()).get());
        bookEntity.setAuthors(authorRepository.findAllById(
                bookRecordDto.authorsId()).stream().collect(Collectors.toSet()));

        ReviewEntity reviewEntity = new ReviewEntity();
        reviewEntity.setReview(bookRecordDto.reviewContent());
        reviewEntity.setBook(bookEntity);
        bookEntity.setReview(reviewEntity);

        return bookRepository.save(bookEntity);
    }

}
