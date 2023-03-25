package com.azubike.ellipsis.spring_cache.web.controllers;

import com.azubike.ellipsis.spring_cache.services.BookService;
import com.azubike.ellipsis.spring_cache.web.model.BookDto;
import com.azubike.ellipsis.spring_cache.web.model.BookPageList;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("api/v1/books")
@RequiredArgsConstructor
public class BookController {
  private final BookService bookService;
  private final Integer DEFAULT_PAGE_NUMBER = 0;
  private final Integer DEFAULT_PAGE_SIZE = 10;

  @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BookPageList> getBooks(
      @RequestParam(value = "pageNumber", required = false, defaultValue = "0") Integer pageNumber,
      @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize) {
    if (pageNumber < 0) pageNumber = DEFAULT_PAGE_NUMBER;
    if (pageSize < 1) pageSize = DEFAULT_PAGE_SIZE;
    BookPageList bookPageList = bookService.getBooks(PageRequest.of(pageNumber, pageSize));
    return ResponseEntity.ok(bookPageList);
  }

  @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BookDto> getBookById(@PathVariable("bookId") long bookId) {
    final BookDto book = bookService.getBook(bookId);
    return ResponseEntity.ok(book);
  }

  @PostMapping(
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<BookDto> createBook(@Valid @RequestBody BookDto bookDto) {
    final BookDto savedBook = bookService.addBook(bookDto);
    URI location =
        ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{bookId}")
            .buildAndExpand(savedBook.getId())
            .toUri();
    return ResponseEntity.created(location).body(savedBook);
  }

  @PutMapping(value = "/{bookId}")
  ResponseEntity<BookDto> updateBookName(
      @Validated @NotBlank @RequestParam("name") String bookName,
      @PathVariable("bookId") long bookId) {
    final BookDto updatedBook = bookService.updateName(bookName, bookId);
    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping(value = "/{bookId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteBook(@PathVariable("bookId") long bookId) {
    bookService.deleteBook(bookId);
  }
}
