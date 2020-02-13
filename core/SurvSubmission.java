package sth.core;

public class SurvSubmission implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _message;
  private int _hours;

  public SurvSubmission(String message, int hours){
    _message = message;
    _hours = hours;
  }

  public String getMessage(){
    return _message;
  }

  public int getHours(){
    return _hours;
  }
}
