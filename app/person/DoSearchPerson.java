package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Person;
import sth.core.exception.NoSuchPersonIdException;
import sth.app.exception.NoSuchPersonException;
import java.util.*;
import sth.core.Subject;


/**
 * 4.2.4. Search person.
 */
public class DoSearchPerson extends Command<SchoolManager> {

  Input<String> _personName;

  /**
   * @param receiver
   */
  public DoSearchPerson(SchoolManager receiver) {
    super(Label.SEARCH_PERSON, receiver);
    _personName = _form.addStringInput(Message.requestPersonName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws NoSuchPersonException{
    _form.parse();

    ArrayList<Person> aux = _receiver.getSchool().getPersonByName(_personName.toString());
    Iterator<Person> i = aux.iterator();

    while(i.hasNext()){
      Person tmp = i.next();
      _display.addLine(tmp.toString());
      if(tmp.getPersonType().equals("STUDENT")){
        ArrayList<Subject> tmpList =tmp.getSubjectsOrdered();
        Iterator<Subject> j = tmpList.iterator();
        while(j.hasNext()){
          Subject tmp2 = j.next();
          _display.addLine("* " + tmp2.getCourse().getName() + " - " + tmp2.getName());
        }
      }
      else if(tmp.getPersonType().equals("TEACHER")){
        ArrayList<Subject> tmpList = tmp.getSubjectsOrdered();
        Iterator<Subject> j = tmpList.iterator();
        while(j.hasNext()){
          Subject tmp2 = j.next();
          _display.addLine("* " + tmp2.getCourse().getName() + " - " + tmp2.getName());
        }
      }
    }
    _display.display();
  }

}
