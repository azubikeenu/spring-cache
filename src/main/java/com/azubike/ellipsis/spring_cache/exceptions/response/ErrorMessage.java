package com.azubike.ellipsis.spring_cache.exceptions.response;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ErrorMessage {
  private Date timeStamp;
  private String message;
  private int status;
  private String path;
}
