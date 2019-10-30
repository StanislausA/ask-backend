package com.revaturelabs.ask.tags;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * A JPA repository for Tags. It has the default methods of a JPA repository.
 *
 */
@Repository
public interface TagRepository extends CrudRepository<Tag, Integer> {
  Tag findByTagName(String tagName);
}
