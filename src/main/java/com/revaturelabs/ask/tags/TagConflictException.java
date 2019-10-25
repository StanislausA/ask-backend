package com.revaturelabs.ask.tags;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TagConflictException extends RuntimeException {
  public TagConflictException(String message) {
    super(message);
  }
}
