package sth.core;

import sth.core.exception.BadEntryException;
import java.util.*;
import java.io.Serializable;

public abstract class Person implements Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private int _id;
  private String _name;
  private int _phoneNumber;
  private String _personType;

  public Person(int id, int phoneNumber, String name){
    _id = id;
    _name = name;
    _phoneNumber = phoneNumber;
  }

  public void wipeNotifications(){
  
  }

  public ArrayList<String> getNotifications(){
    return null;
  }

  void parseContext(String context, School school) throws BadEntryException {
    throw new BadEntryException("Should not have extra context: " + context);
  }

  public String getName(){
    return _name;
  }

  public int getId(){
    return _id;
  }

  public int getPhoneNumber(){
    return _phoneNumber;
  }

  public String getPersonType(){
    return _personType;
  }

  void setPersonType(String s){
    _personType = s;
  }

  boolean isRepresentative(){
    return false;
  }

  public Course getCourse(){
    return null;
  }

  public void setPhoneNumber(int newPhoneNumber){
    _phoneNumber = newPhoneNumber;
  }

  public ArrayList<Subject> getSubjectsOrdered(){
    return null;
  }

  public Subject parseSubject(String s){
    return null;
  }
}
