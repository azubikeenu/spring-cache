package com.azubike.ellipsis.spring_cache.web.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookDto implements Serializable {
  @Null private long id;
  @NotBlank private String name;
  @NotBlank private String category;
  @NotBlank private String author;
  @NotBlank private String publisher;
  private String edition;
}
