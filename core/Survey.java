package sth.core;

import java.util.*;

public class Survey implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  private String _state;

  private enum State{
    ABERTO,
    FECHADO,
    CRIADO,
    FINALIZADO,
  }

  private ArrayList<SurvSubmission> _answerList;

  public Survey(){
    _answerList = new ArrayList<SurvSubmission>();
    _state = "criado";
  }

  public int getHours(){
    Iterator<SurvSubmission> k = _answerList.iterator();
    int total = 0;
    int i = 0;
    while(k.hasNext()){
      SurvSubmission tmp = k.next();
      total += tmp.getHours();
      i++;
    }

    return (total/i);
  }

  public int getNumAnswers(){
    return _answerList.size();
  }

  public boolean isNonEmpty(){
    return(!_answerList.isEmpty());
  }

  public String getState(){
    return _state;
  }

  public void open(){
    _state = "aberto";
  }

  public void finish(){
    _state = "finalizado";
  }

  public void close(){
    _state = "fechado";
  }

  public void addAnswer(SurvSubmission submission){
    _answerList.add(submission);
  }

}
