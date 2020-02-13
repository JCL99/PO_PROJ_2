package sth.core;

import java.util.List;
import sth.core.Person;

import sth.core.exception.BadEntryException;
import sth.core.exception.NoSuchPersonIdException;


import java.io.IOException;
import java.util.*;
import java.io.Serializable;

/**
 * School implementation.
 */
public class School implements java.io.Serializable {

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _nome;
  private ArrayList<Person> _personList;
  private ArrayList<Course> _courseList;
  private Person _loggedUser;

  public School(){
    _nome = "TagusPark";
    _personList = new ArrayList<Person>();
    _courseList = new ArrayList<Course>();
  }

  /**
   * @param filename
   * @throws BadEntryException
   * @throws IOException
   */
  void importFile(String filename) throws IOException, BadEntryException {
    Parser myParser = new Parser(this);
    myParser.parseFile(filename);
  }

  /**
   * @param id
   * @throws NoSuchPersonIdException
   */
  public Person getPerson(int id) throws NoSuchPersonIdException{
    Iterator<Person> i = _personList.iterator();

    while(i.hasNext()){
      Person tmp = i.next();
      if (tmp.getId() == id){
        return tmp;
      }
    }
    throw new NoSuchPersonIdException(id);
  }

  public ArrayList<Person> getPersonById(){
    Collections.sort(_personList, new Comparator<Person>() {
    @Override
      public int compare(Person p1, Person p2) {
        return p1.getId() - p2.getId();
      }
    });

    return _personList;
  }

  public ArrayList<Person> getPersonByName(String name){
    Iterator<Person> i = _personList.iterator();
    ArrayList<Person> aux = new ArrayList<Person>();

    while(i.hasNext()){
      Person tmp = i.next();
      if (tmp.getName().toLowerCase().contains(name.toLowerCase())){
        aux.add(tmp);
      }
    }

    Collections.sort(aux, new Comparator<Person>() {
    @Override
      public int compare(Person p1, Person p2) {
          return p1.getName().compareToIgnoreCase(p2.getName());
      }
    });

    return aux;
  }

  /**
   * @param person
   */
  void addPerson(Person person){
    _personList.add(person);
  }

  void addCourse(Course c){
    _courseList.add(c);
  }

  /**
   * @param courseName
   */
  public Course parseCourse(String courseName){
    Iterator<Course> i = _courseList.iterator();

    while(i.hasNext()){
      Course tmp = i.next();
      if (tmp.getName().equals(courseName)){
        return tmp;
      }
    }
    Course tmp = new Course(courseName);
    addCourse(tmp);
    return tmp;
  }

  public boolean isValidPerson(int id){
    Iterator<Person> i = _personList.iterator();

    while(i.hasNext()){
      Person tmp = i.next();
      if (tmp.getId() == id){
        return true;
      }
    }
    return false;
  }

  public Person getLoggedUser(){
    return _loggedUser;
  }

  public void setLoggedUser(Person p){
    _loggedUser = p;
  }

}
