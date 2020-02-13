package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import sth.core.Person;
import sth.core.Subject;
import java.util.*;

/**
 * 4.2.1. Show person.
 */
public class DoShowPerson extends Command<SchoolManager> {

  private SchoolManager _receiver;

  /**
   * @param receiver
   */
  public DoShowPerson(SchoolManager receiver) {
    super(Label.SHOW_PERSON, receiver);
    _receiver = receiver;
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    //System.out.println(tmpPerson.toString());
    _display.addLine(_receiver.getLoggedUser().toString());
    if(_receiver.getLoggedUser().getPersonType().equals("STUDENT")){
      ArrayList<Subject> tmpList = _receiver.getLoggedUser().getSubjectsOrdered();

      Iterator<Subject> i = tmpList.iterator();
      while(i.hasNext()){
        Subject tmp2 = i.next();
        _display.addLine("* " + _receiver.getLoggedUser().getCourse().getName() + " - " + tmp2.getName());
      }
    }
    else if(_receiver.getLoggedUser().getPersonType().equals("TEACHER")){
      ArrayList<Subject> tmpList = _receiver.getLoggedUser().getSubjectsOrdered();

      Iterator<Subject> i = tmpList.iterator();
      while(i.hasNext()){
        Subject tmp2 = i.next();
        _display.addLine("* " + tmp2.getCourse().getName() + " - " + tmp2.getName());
      }
    }
    _display.display();
  }

}
