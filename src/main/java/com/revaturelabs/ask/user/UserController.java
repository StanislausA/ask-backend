package com.revaturelabs.ask.user;

import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import com.revaturelabs.ask.question.Question;
import com.revaturelabs.ask.tag.TagService;

@RestController
@RequestMapping(path = "/users")

/**
 * 
 * @author Carlos Santos, Chris Allen
 *
 */
public class UserController {

  @Autowired
  UserService userService;
  
  @Autowired
  TagService tagService;

  /**
   * findAll uses the HTTP GET request to retrieve the list of all users currently registered.
   * 
   * @param page
   * @param size
   * @return a ResponseEntity object to render in the front-end.
   */
  @GetMapping
  public ResponseEntity<List<User>> findAll(@RequestParam(required = false) Integer page,
      @RequestParam(required = false) Integer size) {

    if (page == null) {
      page = 0;
    }
    if (size == null) {
      size = 20;
    }
    return ResponseEntity.ok(userService.findAll(page, size).getContent());
  }

  /**
   * "findById" looks for a user by their respective ID
   * If no such user is found, it will return a
   * ResponseStatusException.
   * 
   * @param id
   * @return
   */
  @GetMapping("/{id}")
  public ResponseEntity<User> findById(@PathVariable int id) {
    try {
      return ResponseEntity.ok(userService.findById(id));
    } catch (UserNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  /**
   * "createOrUpdate" looks for the user by id, if they exist, 
   * and creates one if they don't exist.
   * 
   * It will not create a user if the data used to create one matches 
   * a currently existing one in the database, sending the 
   * ResponseStatusException instead.
   * 
   * @param user
   * @param id
   * @return the resulting User object, or 
   */
  @PutMapping("/{id}")
  public User createOrUpdate(@RequestBody User user, @PathVariable int id) {
    user.setId(id);
    try {
      return userService.createOrUpdate(user);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists", e);
    }
  }

  /**
   * "updateUser" gets new User object data, looks for current user
   * using the id, and replaces a user's old data in the database
   * with new data from the User object, retrieved from the front-end.
   * 
   * It will not update any user's data if all the User object's data
   * matches current user's data or if the user doesn't exist.
   * 
   * @param user
   * @param id
   * @return
   */
  @PatchMapping("/{id}")
  public User updateUser(@RequestBody User user, @PathVariable int id) {
    user.setId(id);
    
    user.setExpertTags(tagService.getValidTags(user.getExpertTags()));
    try {
      return userService.update(user);
    } catch (UserConflictException e) {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "User name already exists", e);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  /**
   * "createUser" simply gets the new User object's data and 
   * creates a new row in the Users table in the database
   * using said data.
   * 
   * @param user
   * @return the new user.
   */
  @PostMapping
  public User createUser(@RequestBody User user) {
    return userService.create(user);
  }

  /**
   * "deleteUser" looks for the user by id and
   * deletes the row of the user from the database.
   * 
   * It will not delete anything if it doesn't exist in the first place,
   * throwing a RequestStatusException instead.
   * 
   * @param id
   */
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable int id) {
    try {
      userService.delete(id);
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }

  /**
   * 
   * Takes HTTP GET requests and returns the set of questions associated with the specified user
   * 
   * @param id
   * @return The set of questions associated with the user
   */
  @GetMapping("/{id}/questions")
  public ResponseEntity<Set<Question>> getQuestions(@PathVariable int id) {
    try {
      return ResponseEntity.ok(userService.findById(id).getQuestions());
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }
  
  /**
   * 
   * Takes HTTP PUT requests and returns the updated user after setting the tags to be updated
   * @param user The user object with tags to be changed
   * @return A User JSON after updating
   */
  @PutMapping("/{id}/tags")
  public ResponseEntity<User> updateUserTags(@RequestBody User user, @PathVariable int id){
    user.setExpertTags(tagService.getValidTags(user.getExpertTags()));
    user.setId(id);
    try {
      return ResponseEntity.ok(userService.updateTags(user));
    } catch (UserNotFoundException e) {
      throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", e);
    }
  }
}


