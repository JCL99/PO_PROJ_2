package sth.core;

public class Employee extends Person implements java.io.Serializable{

  /** Serial number for serialization. */
  private static final long serialVersionUID = 201810051538L;

  public Employee(int id, int phoneNumber, String name){
    super(id, phoneNumber, name);
    this.setPersonType("EMPLOYEE");
  }

  @Override
  public String toString(){
    return("FUNCIONÁRIO|" + Integer.toString(super.getId()) + "|" + Integer.toString(super.getPhoneNumber()) + "|" + super.getName());
  }
}
