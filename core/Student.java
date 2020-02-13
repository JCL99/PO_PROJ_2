package sth.core;

import java.util.*;
import sth.core.exception.BadEntryException;
import java.io.*;


public class Student extends Person implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private boolean _isRepresentative;
  private Course _course;
  private ArrayList<Subject> _subjectList;
  private ArrayList<String> _notifications;

  public Student(int id, int phoneNumber, String name, boolean isRepresentative){
    super(id, phoneNumber, name);
    _isRepresentative = isRepresentative;
    this.setPersonType("STUDENT");
    _subjectList = new ArrayList<Subject>();
    _notifications = new ArrayList<String>();
  }


  public void setNotification(String n){
    _notifications.add(n);
  }


  @Override
  public void wipeNotifications(){
    _notifications.clear();
  }

  @Override
  public ArrayList<String> getNotifications(){
    return _notifications;
  }

  @Override
  void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
      throw new BadEntryException("Invalid line context " + lineContext);

    if (_course == null) {
      _course = school.parseCourse(components[0]);
      _course.addStudent(this);
    }

    Subject subject = _course.parseSubject(components[1]);

    subject.enrollStudent(this);
  }

  @Override
  public Course getCourse(){
    return _course;
  }

  void addSubject(Subject subject){
    _subjectList.add(subject);
  }

  @Override
  boolean isRepresentative(){
    return(_isRepresentative);
  }

  void setRepresentative(boolean tf){
    _isRepresentative = tf;

    if(tf){

    }
  }

  @Override
  public String toString(){
    if(_isRepresentative){
      return("DELEGADO|" + Integer.toString(super.getId()) + "|" + Integer.toString(super.getPhoneNumber()) + "|" + super.getName());
    }
    return("ALUNO|" + Integer.toString(super.getId()) + "|" + Integer.toString(super.getPhoneNumber()) + "|" + super.getName());
  }

  void submitAnswerToSurvey(SurvSubmission submission, Survey survey){
    survey.addAnswer(submission);
  }

  @Override
  public ArrayList<Subject> getSubjectsOrdered(){
    Collections.sort(_subjectList, new Comparator<Subject>() {
      @Override
      public int compare(Subject s1, Subject s2) {
          return s1.getName().compareToIgnoreCase(s2.getName());
      }
    });
    return _subjectList;
  }

  @Override
  public Subject parseSubject(String name){
    Iterator<Subject> i = _subjectList.iterator();

    while(i.hasNext()){
      Subject tmp = i.next();
      if (tmp.getName().equals(name)){
        return tmp;
      }
    }
    return null;
  }

}
