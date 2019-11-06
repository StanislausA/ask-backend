package com.revaturelabs.ask.tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class TagConflictException extends RuntimeException {
  /**
   * This exception is intended to be thrown during a Data integrity exception
   */
  private static final long serialVersionUID = 8418321298255656328L;

  public TagConflictException(String message) {
    super(message);
  }
}
