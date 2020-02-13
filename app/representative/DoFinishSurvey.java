package sth.app.representative;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import java.util.*;
import sth.core.*;
import sth.app.exception.*;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;


/**
 * 4.6.5. Finish survey.
 */
public class DoFinishSurvey extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoFinishSurvey(SchoolManager receiver) {
    super(Label.FINISH_SURVEY, receiver);
    //FIXME initialize input fields if needed
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
      throw new NoSurveyException(_discipline.toString(), _project.toString());
    }
    surv.finish();
  }

}
