package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.app.exception.*;

import java.util.*;
import sth.core.*;

/**
 * 4.5.4. Close survey.
 */
public class DoCloseSurvey extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoCloseSurvey(SchoolManager receiver) {
    super(Label.CLOSE_SURVEY, receiver);
    //FIXME initialize input fields if needed
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws NoSuchProjectIdException, NoSuchDisciplineIdException, DialogException {
    Subject s = _receiver.getLoggedUser().parseSubject(_discipline.toString());
    if(s != null){
      Project p = s.getProject(_project.toString());
      if(p != null){
        Survey surv = p.getSurvey();
        if(surv == null){
          throw new NoSurveyException(_discipline.toString(), _project.toString());
        }

        surv.close();
      }
      else{
          throw new NoSuchProjectIdException(_project.toString());
      }
    }
  else{
    throw new NoSuchDisciplineIdException(_discipline.toString());
    }
  }
}
