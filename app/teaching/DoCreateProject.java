package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Project;
import sth.core.Subject;
import sth.core.Teacher;

import sth.app.exception.NoSuchDisciplineException;
import sth.app.exception.NoSuchProjectException;
import sth.app.exception.DuplicateProjectException;;

/**
 * 4.4.1. Create project.
 */
public class DoCreateProject extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCreateProject(SchoolManager receiver) {
    super(Label.CREATE_PROJECT, receiver);
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineException, NoSuchProjectException{
    if (_receiver.getLoggedUser().parseSubject(_discipline.toString()) != null){
      Project p = _receiver.getLoggedUser().parseSubject(_discipline.toString()).createProject(_project.toString(), "");
      if(p == null){
        throw new DuplicateProjectException(_discipline.toString(), _project.toString());
      }
    }
    else{
      throw new NoSuchDisciplineException(_discipline.toString());
    }
  }
}
