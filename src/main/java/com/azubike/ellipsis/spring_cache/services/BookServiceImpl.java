package com.azubike.ellipsis.spring_cache.services;

import com.azubike.ellipsis.spring_cache.domain.Book;
import com.azubike.ellipsis.spring_cache.errors.BookNotFoundException;
import com.azubike.ellipsis.spring_cache.repository.BookRepository;
import com.azubike.ellipsis.spring_cache.web.mappers.BookMapper;
import com.azubike.ellipsis.spring_cache.web.model.BookDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
  private final BookRepository bookRepository;
  private final BookMapper bookMapper;

  @Override
  public BookDto addBook(final BookDto bookDto) {
    final Book savedBook = bookRepository.save(bookMapper.dtoToBook(bookDto));
    return bookMapper.bookToDto(savedBook);
  }

  @Override
  @CachePut(cacheNames = "books" , key = "#bookId")
  public BookDto updateName(String name, final long bookId) {
    final int updatedRows = bookRepository.updateName(bookId, name);
    if (updatedRows == 0) {
      log.info("Could not update book with id {}", bookId);
      throw new BookNotFoundException("Book with id " + bookId + " Not found");
    }
    return bookMapper.bookToDto(getBookById(bookId));
  }

  @Override
  @Cacheable(cacheNames = "books", key = "#id")
  public BookDto getBook(final long id) {
    return bookMapper.bookToDto(this.getBookById(id));
  }

  @Override
  @CacheEvict(cacheNames = "books", key = "#id")
  public void deleteBook(final long id) {
    final Book foundBook = getBookById(id);
    log.info("Deleting book with id  {}", foundBook.getId());
    bookRepository.delete(foundBook);
  }

  private Book getBookById(long bookId) {
    return bookRepository
        .findById(bookId)
        .orElseThrow(
            () -> {
              log.info("Could not find book with id {}", bookId);
              throw new BookNotFoundException("Book with id : " + bookId + " not found");
            });
  }
}
