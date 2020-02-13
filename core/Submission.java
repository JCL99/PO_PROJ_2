package sth.core;

public class Submission implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _message;
  private int _id;

  public Submission(String message, int id){
    _message = message;
    _id = id;
  }

  public String getMessage(){
    return _message;
  }

  public int getStudentId(){
    return _id;
  }
}
