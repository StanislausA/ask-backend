package com.revaturelabs.ask.user;

import java.util.List;
import com.revaturelabs.ask.tag.Tag;


public interface UserService {

  /**
   * "findAll" basically gets all
   * the users from the H2 database.
   * 
   * @return List of all users in database.
   */
  List<User> findAll();

  /**
   * "findById" finds the user by their ID.
   * 
   * @param id
   * @return
   * @throws UserNotFoundException
   */
  User findById(int id) throws UserNotFoundException;

  /**
   * "create" gets the user object and
   * creates a new row for it in the database.
   * 
   * @param user
   * @return
   */
  User create(User user);

  /**
   * "update" checks if the user exists beforehand.
   * If so, it saves changes into the specified row
   * of the database, given it doesn't overwrite the ID.
   * 
   * If not, it throws the exception.
   * 
   * @param user
   * @return
   * @throws UserNotFoundException
   * @throws UserConflictException
   */
  User update(User user) throws UserNotFoundException, UserConflictException;

  /**
   * "createOrUpdate" does the same function as
   * "update" except it also creates the user
   * if the user in question doesn't exist.
   * 
   * @param user
   * @return
   * @throws UserConflictException
   */
  User createOrUpdate(User user) throws UserConflictException;

  /**
   * "delete" basically looks for the ID of the 
   * user and deletes the specified user with 
   * that ID.
   * 
   * @param id
   * @throws UserNotFoundException
   */
  void delete(int id) throws UserNotFoundException;
  
  /**
   * "addUserTag" basically looks for the ID of the 
   * user and adds a set of Tags to the user.
   * 
   * @param id
   * @throws UserNotFoundException
   */
  User addUserTags(User user, Tag[] tags) throws UserNotFoundException, UserConflictException;

}
