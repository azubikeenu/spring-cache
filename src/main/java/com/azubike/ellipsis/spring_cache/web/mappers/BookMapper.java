package com.azubike.ellipsis.spring_cache.web.mappers;

import com.azubike.ellipsis.spring_cache.domain.Book;
import com.azubike.ellipsis.spring_cache.web.model.BookDto;
import org.mapstruct.Mapper;import org.mapstruct.Mapping;

@Mapper
public interface BookMapper {

  Book dtoToBook(BookDto bookDto);

  BookDto bookToDto(Book book);
}
