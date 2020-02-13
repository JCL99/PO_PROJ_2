package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;
import sth.core.*;

import sth.app.exception.*;


/**
 * 4.5.1. Create survey.
 */
public class DoCreateSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCreateSurvey(SchoolManager receiver) {
    super(Label.CREATE_SURVEY, receiver);

  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
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
      p.createSurvey();
    }
    else{
      throw new DuplicateSurveyException(_discipline.toString(), _project.toString());
    }
  }

}
