package sth.app.person;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Display;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import java.util.*;
import sth.core.Subject;
import sth.core.Course;


/**
 * 4.2.2. Change phone number.
 */
public class DoChangePhoneNumber extends Command<SchoolManager> {

  Input<Integer> _phoneNumber;

  /**
   * @param receiver
   */
  public DoChangePhoneNumber(SchoolManager receiver) {
    super(Label.CHANGE_PHONE_NUMBER, receiver);
    _phoneNumber = _form.addIntegerInput(Message.requestPhoneNumber());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    _form.parse();

    _receiver.getLoggedUser().setPhoneNumber(_phoneNumber.value());
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
