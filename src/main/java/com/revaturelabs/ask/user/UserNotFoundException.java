package com.revaturelabs.ask.user;

public class UserNotFoundException extends RuntimeException {
  public UserNotFoundException() {
    super("User not found");
  }

  public UserNotFoundException(String m) {
    super("User not found " + m);
  }
}
