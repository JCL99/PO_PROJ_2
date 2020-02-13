package sth.app.representative;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.*;
import sth.app.exception.*;
import java.util.*;
import sth.core.exception.*;

/**
 * 4.6.6. Show discipline surveys.
 */
public class DoShowDisciplineSurveys extends Command<SchoolManager> {

  private Input<String> _discipline;

  /**
   * @param receiver
   */
  public DoShowDisciplineSurveys(SchoolManager receiver) {
    super(Label.SHOW_DISCIPLINE_SURVEYS, receiver);
    _discipline = _form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException {
    _form.parse();

    Subject s = _receiver.getLoggedUser().parseSubject(_discipline.toString());
    if(s == null){

    }
    else{
      ArrayList<Project> pl = s.getProjectsByName();

      if(pl == null){

      }
      else{

        Iterator<Project> k = pl.iterator();
        while(k.hasNext()){
          Project tmp = k.next();

          if(tmp.getSurvey().getState().equals("aberto")){
            _display.addLine(_discipline.toString() + " - " + tmp.getName() + " (aberto)");
          }
          else{
            _display.addLine(_discipline.toString() + " - " + tmp.getName() + " - " + Integer.toString(tmp.getSurvey().getNumAnswers()) + " - " + Integer.toString(tmp.getSurvey().getHours()));
          }
        }
      }
    }
    _display.display();
  }

}
