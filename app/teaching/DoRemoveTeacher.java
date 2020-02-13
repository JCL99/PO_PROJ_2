package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import pt.tecnico.po.ui.Command;
import sth.core.SchoolManager;
import sth.core.Project;
import sth.core.Subject;
import sth.core.Teacher;
import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import sth.core.*;
import sth.app.exception.*;

import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.DuplicateProjectException;;

public class DoRemoveTeacher extends Command<SchoolManager> {

  private Input<String> _subject;
  private Input<Integer> _teacher;

  public DoRemoveTeacher(SchoolManager receiver) /*throws NoSuchDisciplineIdException*/{
    super(Label.REMOVE_TEACHER, receiver);

    _subject=_form.addStringInput(Message.requestDisciplineName());
    _teacher=_form.addIntegerInput("Id docente: ");
  }


  public final void execute(){
    _form.parse();

    Subject s = _receiver.getLoggedUser().parseSubject(_subject.toString());
    if(s!=null){
      if(s.getNumberStudents() > 5){
        s.removeTeacher(_teacher.value());
      }
      else{
        //throw new NoSuchDisciplineIdException(_subject.value());
      }
    }
  }

}
