package sth.app.student;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import java.util.*;
import sth.app.exception.*;
import sth.core.*;
/**
 * 4.5.2. Answer survey.
 */
public class DoAnswerSurvey extends sth.app.common.ProjectCommand {

  private Input<Integer> _hours;
  private Input<String> _message;

  /**
   * @param receiver
   */
  public DoAnswerSurvey(SchoolManager receiver) {
    super(Label.ANSWER_SURVEY, receiver);
    _hours = _form.addIntegerInput(Message.requestProjectHours());
    _message = _form.addStringInput(Message.requestComment());
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    _form.parse();

    Subject s = _receiver.getLoggedUser().getCourse().getSubject(_discipline.toString());
    if(s == null){
      throw new NoSuchDisciplineIdException(_discipline.toString());
    }
    Project p = s.getProject(_project.toString());
    if(p == null){
      throw new NoSuchProjectIdException(_project.toString());
    }

    Survey surv = p.getSurvey();
    if(surv == null){
      throw new NoSurveyException(_discipline.toString(), _project.toString());
    }
    if(surv.getState().equals("finalizado")){
      throw new SurveyFinishedException(_discipline.toString(), _project.toString());
    }

    SurvSubmission ss = new SurvSubmission(_message.toString(), _hours.value());
    surv.addAnswer(ss);
  }

}
