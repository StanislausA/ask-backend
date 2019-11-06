package com.revaturelabs.ask.tag;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * This exception will be thrown when a tag is not found in a database
 *
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class TagNotFoundException extends RuntimeException {

  private static final long serialVersionUID = 7749497388328047729L;

  public TagNotFoundException(String message) {
    super(message);
  }
}
