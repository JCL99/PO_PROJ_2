package sth.app.main;

import java.io.IOException;

import pt.tecnico.po.ui.Command;
import pt.tecnico.po.ui.Input;
import sth.core.SchoolManager;

import java.io.*;

/**
 * 4.1.1. Save to file under current name (if unnamed, query for name).
 */
public class DoSave extends Command<SchoolManager> {
  private Input<String> _filename;

  /**
   * @param receiver
   */
  public DoSave(SchoolManager receiver) {
    super(Label.SAVE, receiver);
    _filename=_form.addStringInput(Message.newSaveAs());
  }

  /** @see pt.tecnico.po.ui.Command#execute() */
  @Override
  public final void execute() {
    String filename = _receiver.getFilename();
    if(filename==null){
      _form.parse();
      filename = _filename.value();
    }
    try {
    _receiver.save(filename);
    }
    catch (FileNotFoundException fnfe) {
      _display.addLine(Message.fileNotFound(filename));
    } catch (IOException ioe) {
      _display.addLine("IOException");
    }
    _display.display();
   }
}
