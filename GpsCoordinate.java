import java.io.*;
import java.sql.Date;

class GpsCoordinate implements Serializable {
  private long id;
  private Date time;
  private double latitude;
  private double longitude;
  private double height;

  public GpsCoordinate(){}

  public GpsCoordinate(long id, Date time, double latitude, double longitude, double height){
    this.id = id;
    this.time = time;
    this.latitude = latitude;
    this.longitude = longitude;
    this.height = height;
  }

  public long getId(){
    return this.id;
  }

  public Date getTime(){
    return this.time;
  }

  public double getLatitude(){
    return this.latitude;
  }

  public double getLongitude(){
    return this.longitude;
  }

  public double getHeight(){
    return this.height;
  }

  public void setId(long id){
    this.id = id;
  }

  public void setTime(Date time){
    this.time = time;
  }

  public void setLatitude(double d){
    this.latitude = d;
  }

  public void setLongitude(double d){
    this.longitude = d;
  }

  public void setHeight(double d){
    this.height = d;
  }

} 
