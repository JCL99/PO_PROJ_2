package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import sth.core.SchoolManager;
import java.util.*;
import sth.core.Subject;
import sth.core.Person;

/**
 * 4.2.3. Show all persons.
 */
public class DoShowAllPersons extends Command<SchoolManager> {

  /**
   * @param receiver
   */
  public DoShowAllPersons(SchoolManager receiver) {
    super(Label.SHOW_ALL_PERSONS, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {

    Iterator<Person> k = _receiver.getSchool().getPersonById().iterator();
    while(k.hasNext()){
      Person tmp3 = k.next();
      _display.addLine(tmp3.toString());

      if(tmp3.getPersonType().equals("STUDENT")){
        ArrayList<Subject> tmpList = tmp3.getSubjectsOrdered();
        Iterator<Subject> i = tmpList.iterator();
        while(i.hasNext()){
          Subject tmp2 = i.next();
          _display.addLine("* " + tmp2.getCourse().getName() + " - " + tmp2.getName());
        }
      }
      else if(tmp3.getPersonType().equals("TEACHER")){
        ArrayList<Subject> tmpList = tmp3.getSubjectsOrdered();
        Iterator<Subject> i = tmpList.iterator();
        while(i.hasNext()){
          Subject tmp2 = i.next();
          _display.addLine("* " + tmp2.getCourse().getName() + " - " + tmp2.getName());
        }
      }
    }
    _display.display();
  }

}
