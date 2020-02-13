package sth.core;

import java.util.*;
import java.io.Serializable;

public class Course implements Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _name;
  private ArrayList<Subject> _subjectList;
  private ArrayList<Student> _studentList;
  private ArrayList<Student> _representativeList;

  public Course(String name){
    _name = name;
    _subjectList = new ArrayList<Subject>();
    _studentList = new ArrayList<Student>();
    _representativeList = new ArrayList<Student>();
  }

  public void removeEmptySubs(){
    ArrayList<Subject> copy = _subjectList;

    Iterator<Subject> i = copy.iterator();

    while(i.hasNext()){
      Subject tmp = i.next();
      if (tmp.getNumberStudents() == 0){
        _subjectList.remove(tmp);
      }
    }
  }

  public String getName(){
    return _name;
  }

  void addSubject(Subject subject){
    _subjectList.add(subject);
  }

  void addStudent(Student student){
    _studentList.add(student);
  }

  void addRepresentative(Student student){
    _representativeList.add(student);
  }

  public Subject getSubject(String subjectName){
    Iterator<Subject> i = _subjectList.iterator();

    while(i.hasNext()){
      Subject tmp = i.next();
      if (tmp.getName().equals(subjectName)){
        return tmp;
      }
    }
    return null;
  }

  Subject parseSubject(String subjectName){
    Iterator<Subject> i = _subjectList.iterator();

    while(i.hasNext()){
      Subject tmp = i.next();
      if (tmp.getName().equals(subjectName)){
        return tmp;
      }
    }
    Subject tmp = new Subject(subjectName, this);
    this.addSubject(tmp);
    return tmp;
  }
}
