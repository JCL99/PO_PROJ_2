package sth.core;

import java.util.*;
import sth.core.*;

public class Project implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _name;
  private String _description;
  private boolean _closed;
  private Survey _survey;
  private ArrayList<Submission> _submissionList;

  public Project(String name, String description){
    _name = name;
    _description = description;
    _closed = false;
    _submissionList = new ArrayList<Submission>();
  }

  public boolean isOpened(){
    return  !_closed;
  }

  public void removeSurvey(){
    _survey = null;
  }

  public void createSurvey(){
    _survey = new Survey();
  }

  public String getName(){
    return _name;
  }

  public void close(){
    _closed = true;
    if(_survey == null){
      createSurvey();
    }
    _survey.open();
  }

  public Survey getSurvey(){
    return _survey;
  }

  public void addSubmission(Person student, String message){
    Submission s = new Submission(message, student.getId());

    Iterator<Submission> i = _submissionList.iterator();
    while(i.hasNext()){
      Submission tmp = i.next();
      if(tmp.getStudentId() == student.getId()){
        _submissionList.remove(tmp);
        _submissionList.add(s);
        return;
      }
    }
    _submissionList.add(s);
  }

  public ArrayList<Submission> getSubmissions(){
    Collections.sort(_submissionList, new Comparator<Submission>() {
      @Override
      public int compare(Submission s1, Submission s2) {
          return s1.getStudentId() - s2.getStudentId();
      }
    });
    return _submissionList;
  }
}
