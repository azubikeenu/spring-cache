package com.azubike.ellipsis.spring_cache.web.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public class BookPageList extends PageImpl<BookDto> {

  @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
  public BookPageList(
      @JsonProperty("content") List<BookDto> content,
      @JsonProperty("number") int number,
      @JsonProperty("size") int size,
      @JsonProperty("totalElements") Long totalElements,
      @JsonProperty("pageable") JsonNode pageable,
      @JsonProperty("last") boolean last,
      @JsonProperty("totalPages") int totalPages,
      @JsonProperty("sort") JsonNode sort,
      @JsonProperty("first") boolean first,
      @JsonProperty("numberOfElements") int numberOfElements) {

    super(content, PageRequest.of(number, size), totalElements);
  }

  public BookPageList(final List<BookDto> content, final Pageable pageable, final long total) {
    super(content, pageable, total);
  }

  public BookPageList(final List<BookDto> content) {
    super(content);
  }
}
