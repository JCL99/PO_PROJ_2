package sth.app.teaching;

import pt.tecnico.po.ui.DialogException;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;
import sth.core.*;

import sth.core.exception.NoSuchProjectIdException;
import sth.core.exception.NoSuchDisciplineIdException;

import java.util.*;

/**
 * 4.4.2. Close project.
 */
public class DoCloseProject extends sth.app.common.ProjectCommand {

  /**
   * @param receiver
   */
  public DoCloseProject(SchoolManager receiver) {
    super(Label.CLOSE_PROJECT, receiver);
  }

  /** @see sth.app.common.ProjectCommand#myExecute() */
  @Override
  public final void myExecute() throws DialogException, NoSuchDisciplineIdException, NoSuchProjectIdException {
    Subject s = _receiver.getLoggedUser().parseSubject(_discipline.toString());
    if(s != null){
      Project p = s.getProject(_project.toString());
      if(p != null){
        p.close();

        //System.out.println("Entrou");

        ArrayList<Student> sl = s.getStudents();
        if(!sl.isEmpty()){
          Iterator<Student> i = sl.iterator();
          while(i.hasNext()){
            Student tmp = i.next();
            String str1 = "Pode preencher inquérito do projecto " + _project.toString() + " da disciplina " + _discipline.toString();
            //System.out.println(str1);
            tmp.setNotification(str1);
          }
        }

        /*
        ArrayList<Teacher> tl = s.getTeachers();
        if(!tl.isEmpty()){
          Iterator<Teacher> j = tl.iterator();
          while(j.hasNext()){
            Teacher tmp = j.next();
            String str2 = "Resultados do inquérito do projecto " + _project.toString() + " da disciplina " + _discipline.toString();
            tmp.setNotification(str2);
          }
        }
        */
      }
      else{
        throw new NoSuchProjectIdException(_project.toString());
      }
    }
    else{
      throw new NoSuchDisciplineIdException(_discipline.toString());
    }
  }

}
