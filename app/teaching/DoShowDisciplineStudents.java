package sth.app.teaching;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.*;

import sth.core.exception.NoSuchDisciplineIdException;

/**
 * 4.4.4. Show course students.
 */
public class DoShowDisciplineStudents extends Command<SchoolManager> {

  Input<String> _subject;

  /**
   * @param receiver
   */
  public DoShowDisciplineStudents(SchoolManager receiver) {
    super(Label.SHOW_COURSE_STUDENTS, receiver);
    _subject=_form.addStringInput(Message.requestDisciplineName());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() throws DialogException{
    _form.parse();

    Subject s = _receiver.getLoggedUser().parseSubject(_subject.toString());
    if(s!=null){
      if(s.getNumberStudents() > 5){

      }
      else{
        //throw new NoSuchDisciplineIdException(_subject.value());
      }
    }

  }

}
