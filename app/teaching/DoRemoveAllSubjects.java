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

public class DoRemoveAllSubjects extends Command<SchoolManager> {

  private Input<String> _course;

  public DoRemoveAllSubjects(SchoolManager receiver) /*throws NoSuchDisciplineIdException*/{
    super(Label.REMOVE_ALLSUBS, receiver);

    _course=_form.addStringInput("Nome do curso: ");
  }


  public final void execute(){
    _form.parse();

    Course c = _receiver.getSchool().parseCourse(_course.value());
    if(c!=null){
        c.removeEmptySubs();
    }
    else{
      //throw new NoSuchCourseException(_course.value());
    }
  }

}
