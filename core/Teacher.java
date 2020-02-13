package sth.core;

import java.util.ArrayList;
import sth.core.exception.BadEntryException;
import java.util.Comparator;
import java.util.Collections;
import java.util.Iterator;

public class Teacher extends Person implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private ArrayList<Subject> _subjectList;

  public Teacher(int id, int phoneNumber, String name){
    super(id, phoneNumber, name);
    this.setPersonType("TEACHER");
    _subjectList = new ArrayList<Subject>();
  }

  @Override
  public Subject parseSubject(String s){
    Iterator<Subject> i = _subjectList.iterator();

    while(i.hasNext()){
      Subject tmp = i.next();
      if(tmp.getName().equals(s)){
        return tmp;
      }
    }
    return null;
  }

  @Override
  void parseContext(String lineContext, School school) throws BadEntryException {
    String components[] =  lineContext.split("\\|");

    if (components.length != 2)
      throw new BadEntryException("Invalid line context " + lineContext);

    Course course = school.parseCourse(components[0]);
    Subject subject = course.parseSubject(components[1]);

    _subjectList.add(subject);
    subject.addTeacher(this);
  }

  public void createProject(Subject subject, String name, String description){
    subject.createProject(name, description);
  }

  @Override
  public String toString(){
    return("DOCENTE|" + Integer.toString(super.getId()) + "|" + Integer.toString(super.getPhoneNumber()) + "|" + super.getName());
  }

  ArrayList<Submission> getProjectSubmissions(Subject subject, String name){
    Project project = subject.getProject(name);
    ArrayList<Submission> submissions = project.getSubmissions();
    return submissions;
  }

  ArrayList<Student> getStudentsOfSubject(Subject subject){
    return subject.getStudents();
  }

  public class advComparator implements Comparator<Subject> {
    @Override
    public int compare(Subject a, Subject b) {
        int c = a.getCourse().getName().compareToIgnoreCase(b.getCourse().getName());
        return c == 0 ? a.getName().compareToIgnoreCase(b.getName()) : c;
    }
}

  @Override
  public ArrayList<Subject> getSubjectsOrdered(){
    Collections.sort(_subjectList, new advComparator());
    return _subjectList;
  }
}
