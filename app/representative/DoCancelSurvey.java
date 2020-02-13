package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.app.exception.*;

import java.util.*;
import sth.core.*;

/**
 * 4.5.2. Cancel survey.
 */
public class DoCancelSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCancelSurvey(SchoolManager receiver) {
    super(Label.CANCEL_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
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
    if(surv.isNonEmpty()){
      throw new NonEmptySurveyException(_discipline.toString(), _project.toString());
    }
    if(surv.getState().equals("finalizado")){
      throw new SurveyFinishedException(_discipline.toString(), _project.toString());
    }
    p.removeSurvey();
  }

}
