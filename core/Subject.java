package sth.core;

import java.util.*;

public class Subject implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _name;
  private int _capacity;
  private Course _course;
  private ArrayList<Project> _projectList;
  private ArrayList<Teacher> _teacherList;
  private ArrayList<Student> _studentList;

    public Subject(String name, Course course){
    _name = name;
    _course = course;
    _teacherList = new ArrayList<Teacher>();
    _studentList = new ArrayList<Student>();
    _projectList = new ArrayList<Project>();
  }

  public void removeTeacher(int id){
    Iterator<Teacher> i = _teacherList.iterator();
    Teacher teacherToRemove = null;
    while(i.hasNext()){
      Teacher tmp = i.next();
      if(tmp.getId() == id){
        teacherToRemove = tmp;
        break;
      }
    }
    _teacherList.remove(teacherToRemove);

  }

  public int getNumberStudents(){
    return _studentList.size();
  }

  public String getName(){
    return _name;
  }

  public Course getCourse(){
    return _course;
  }

  void addTeacher(Teacher teacher){
    _teacherList.add(teacher);
  }

  public ArrayList<Teacher> getTeachers(){
    return _teacherList;
  }

  void enrollStudent(Student student){
    student.addSubject(this);
    _studentList.add(student);
  }

  public ArrayList<Student> getStudents(){
    return _studentList;
  }

  public Project createProject(String name, String description){
    Project p = null;
    p = getProject(name);
    Iterator<Project> i = _projectList.iterator();
    while(i.hasNext()){
      Project tmp = i.next();
      if(p != null){
        return null;
      }
    }
    p = new Project(name, "");
    _projectList.add(p);
    return p;
  }

  public Project getProject(String name){
    Iterator<Project> i = _projectList.iterator();

    while(i.hasNext()){
      Project tmp = i.next();
      if(tmp.getName().equals(name)){
        return tmp;
      }
    }
    return null;
  }

  public ArrayList<Project> getProjectsByName(){
    ArrayList<Project> aux = _projectList;

    Collections.sort(aux, new Comparator<Project>() {
    @Override
      public int compare(Project p1, Project p2) {
          return p1.getName().compareToIgnoreCase(p2.getName());
      }
    });

    return aux;
  }
}
