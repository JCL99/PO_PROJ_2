package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.*;
import sth.app.exception.*;

/**
 * 4.4.5. Show survey results.
 */
public class DoShowSurveyResults extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoShowSurveyResults(SchoolManager receiver) {
    super(Label.SHOW_SURVEY_RESULTS, receiver);

  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    Subject s = _receiver.getLoggedUser().parseSubject(_discipline.toString());
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

    String tmp = surv.getState();

    if(tmp.equals("criado")){
      _display.addLine(_discipline.toString() + " - " + _project.toString() + "(por abrir)");
    }
    else if(tmp.equals("aberto")){
      _display.addLine(_discipline.toString() + " - " + _project.toString() + "(aberto)");
    }
    else if(tmp.equals("fechado")){
      _display.addLine(_discipline.toString() + " - " + _project.toString() + "(fechado)");
    }
    _display.display();

  }

}
