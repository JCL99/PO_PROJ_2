package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.Teacher;
import sth.core.Subject;
import sth.core.Submission;
import sth.core.Project;

import java.util.*;

import sth.core.exception.NoSuchDisciplineIdException;
import sth.core.exception.NoSuchProjectIdException;

/**
 * 4.4.3. Show project submissions.
 */
public class DoShowProjectSubmissions extends sth.app.common.ProjectCommand {
  /**
   * @param receiver
   */
  public DoShowProjectSubmissions(SchoolManager receiver) {
    super(Label.SHOW_PROJECT_SUBMISSIONS, receiver);

  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    _display.addLine(_discipline.toString() + " - " + _project.toString());
    Subject s = _receiver.getLoggedUser().parseSubject(_discipline.toString());
    if(s == null){
      throw new NoSuchDisciplineIdException(_discipline.toString());
    }

    Project p = s.getProject(_project.toString());

    if(p == null){
      throw new NoSuchProjectIdException(_project.toString());
    }

    ArrayList<Submission> sublist = p.getSubmissions();

    Iterator<Submission> i = sublist.iterator();
    while(i.hasNext()){
      Submission tmp = i.next();
      _display.addLine("* " + String.valueOf(tmp.getStudentId()) + " - " + tmp.getMessage());
    }
    _display.display();
  }

}
