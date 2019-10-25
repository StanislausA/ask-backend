package com.revaturelabs.ask.tags;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Tags class represent a tag. It holds an Integer id and TagName String.
 * 
 */
@Entity
@Table(name = "tags")
public class Tag implements Serializable {

  private static final long serialVersionUID = -7136050878066116590L;

  @Id
  @Column(name = "id")
  @GeneratedValue
  private Integer id;

  @Column(name = "tag_name")
  private String tagName;

  /**
   * Auto generated getter method for Tag Id.
   * 
   * @return an Integer that holds the Tag id.
   */
  public Integer getId() {
    return id;
  }

  /**
   * Auto generated setter method for Tag id.
   * 
   * @param id -an integer that holds the Tag id.
   */
  public void setId(Integer id) {
    this.id = id;
  }

  /**
   * Auto generated getter method for Tag name.
   * 
   * @return an Integer that hold the Tag name.
   */
  public String getTagName() {
    return tagName;
  }

  /**
   * Auto generated setter for Tag Name.
   * 
   * @param tagName - A String that holds the tag name.
   */
  public void setTagName(String tagName) {
    this.tagName = tagName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((id == null) ? 0 : id.hashCode());
    result = prime * result + ((tagName == null) ? 0 : tagName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Tag other = (Tag) obj;
    if (id == null) {
      if (other.id != null)
        return false;
    } else if (!id.equals(other.id)) {
      return false;
    }
    if (tagName == null) {
      if (other.tagName != null) {
        return false;
      }
    } else if (!tagName.equals(other.tagName)) {
      return false;
    }
    return true;
  }

  /**
   * Auto-generated toString method for Tag class.
   * 
   * @return a String to represent the question class.
   */
  @Override
  public String toString() {
    return "Tag [id=" + id + ", tagName=" + tagName + "]";
  }


}
