package com.revaturelabs.ask.user;

public class UserNotFoundException extends RuntimeException {
  
  private static final long serialVersionUID = 7935151869324561571L;

  /**
   * This basically throws the exception, as is.
   */
  public UserNotFoundException() {
    super("User not found");
  }

  /**
   * Throws exception with an additional message.
   * @param m
   */
  public UserNotFoundException(String m) {
    super("User not found " + m);
  }
}
