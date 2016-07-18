import java.io.*;
import java.sql.Date;
import java.util.List;

public class Person implements Serializable {

  private long id;
  private String firstName;
  private String lastName;
  private Date birthDate;
  private String address;
  private BloodGroup bloodGroup;
  private List<GpsCoordinate> coordinates;

  public Person(){}

  public Person(long id, String firstName, String lastName, Date birthDate, String address, BloodGroup bloodGroup, List<GpsCoordinate> coordinates){

  this.id = id;
  this.firstName = firstName;
  this.lastName = lastName;
  this.birthDate = birthDate;
  this.address = address;
  this.bloodGroup = bloodGroup;
  this.coordinates = coordinates;

}

  public long getId(){
    return this.id;
  }

  public String getFirstName(){
    return this.firstName;
  }

  public String getLastName(){
    return this.lastName;
  }

  public Date getBirthDate(){
    return this.birthDate;
  }

  public String getAddress(){
    return this.address;
  }

  public BloodGroup getBloodGroup(){
    return this.bloodGroup;
  }

  public List<GpsCoordinate> getCoordinates(){
    return this.coordinates;
  }

  public void setId(long id){
    this.id = id;
  }

  public void setFirstName(String name){
    this.firstName = name;
  }

  public void setLastName(String name){
    this.lastName = name;
  }

  public void setBirthDate(Date d){
    this.birthDate = d;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public void setBloodGroup(BloodGroup group){
    this.bloodGroup = group;
  }

  public void setCoordinates(List<GpsCoordinate> coordinates){
    this.coordinates = coordinates;
  }

  public void addCoordinate(GpsCoordinate coord){
    this.coordinates.add(coord);
  }

  public void removeCoordinate(GpsCoordinate coord){
    this.coordinates.remove(coord);
  }


}
