package com.azubike.ellipsis.spring_cache.services;

import com.azubike.ellipsis.spring_cache.web.model.BookDto;
import com.azubike.ellipsis.spring_cache.web.model.BookPageList;
import org.springframework.data.domain.PageRequest;

public interface BookService {
  BookDto addBook(BookDto bookDto);

  BookDto updateName(String name, long bookId);

  BookDto getBook(long id);

  void deleteBook(long id);
BookPageList getBooks( PageRequest pageRequest);}
