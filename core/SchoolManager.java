package sth.core;

import java.io.Serializable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sth.core.exception.BadEntryException;
import sth.core.exception.ImportFileException;
import sth.core.exception.NoSuchPersonIdException;


import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;


/**
 * The façade class.
 */
public class SchoolManager {

  private School _school;
  private Person _loggedUser;
  private String _filename;

  public SchoolManager(){
    _school = new School();
  }

  public Person getLoggedUser(){
    return _loggedUser;
  }

  public void setLoggedUser(Person p){
    _loggedUser = p;
  }

  public String getFilename(){
    return _filename;
  }

  public void save(String filename) throws IOException {
      _filename = filename;

      ObjectOutputStream obOut = null;
      try{
        FileOutputStream fpout = new FileOutputStream(_filename);
        obOut = new ObjectOutputStream(fpout);
        obOut.writeObject(_school);
      } catch (FileNotFoundException e) {
      } catch (IOException e) {
          throw new IOException();
      }finally{
        if(obOut != null){
          obOut.close();
        }
      }
 	 }

  public void load(String filename) throws FileNotFoundException, IOException, ClassNotFoundException, NoSuchPersonIdException{
    _filename = filename;

    ObjectInputStream obIn = null;
    try{
      FileInputStream fpin = new FileInputStream(_filename);
      obIn = new ObjectInputStream(fpin);
      School s = (School) obIn.readObject();

      if(!s.isValidPerson(_loggedUser.getId())){
        throw new NoSuchPersonIdException(_loggedUser.getId());
      }
      else{
        Person p = s.getPerson(_loggedUser.getId());
        _school = s;
        _loggedUser = p;
      }

    }catch (FileNotFoundException e) {
      throw new FileNotFoundException(filename);
    } catch (IOException e) {
      throw new IOException();
    }finally{
      if(obIn != null){
        obIn.close();
      }
    }

  }

  public School getSchool(){
    return _school;
  }

  public void setSchool(Object s){
    _school = (School) s;
  }

  /**
   * @param datafile
   * @throws ImportFileException
   * @throws InvalidCourseSelectionException
   */
  public void importFile(String datafile) throws ImportFileException {
    try {
      _school.importFile(datafile);
    } catch (IOException | BadEntryException e) {
      throw new ImportFileException(e);
    }
  }

  /**
   * Do the login of the user with the given identifier.

   * @param id identifier of the user to login
   * @throws NoSuchPersonIdException if there is no uers with the given identifier
   */
  public void login(int id) throws NoSuchPersonIdException {
    _loggedUser = _school.getPerson(id);
  }

  /**
   * @return true when the currently logged in person is an administrative
   */
  public boolean isLoggedUserAdministrative() {
    return(_loggedUser.getPersonType().equals("EMPLOYEE"));
  }

  /**
   * @return true when the currently logged in person is a professor
   */
  public boolean isLoggedUserProfessor() {

    return(_loggedUser.getPersonType().equals("TEACHER"));
  }

  /**
   * @return true when the currently logged in person is a student
   */
  public boolean isLoggedUserStudent() {

    return(_loggedUser.getPersonType().equals("STUDENT"));
  }

  /**
   * @return true when the currently logged in person is a representative
   */
  public boolean isLoggedUserRepresentative() {

    return(_loggedUser.isRepresentative());
  }

}
