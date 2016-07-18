
import java.util.HashMap;
import java.util.Iterator;
import java.io.*;

public class PersonRegistry implements Serializable {

  private HashMap<Long, Person> people;
  private String filePath;

  public PersonRegistry(){
    this.people = new HashMap<Long, Person>();
    this.filePath = "";
  }

  public PersonRegistry(HashMap<Long, Person> people){
    this.people = people;
    this.filePath = "";
  }

  public PersonRegistry(String filePath){
    this.people = new HashMap<Long, Person>();
    this.filePath = filePath;
  }

  public PersonRegistry(HashMap<Long, Person> people, String filePath){
    this.people = people;
    this.filePath = filePath;
  }

  // Beolvassa a people map-ba a filePath útvonalon tárolt személyeket.
  // Hiba esetén false, egyébként true értéket ad vissza.
  public boolean load(){

    try (FileInputStream istream = new FileInputStream(filePath);
    ObjectInputStream ois = new ObjectInputStream(istream);) {
    
    int len = ois.readInt();

    for (int i = 0; i < len; i++) {
      // Explicit cast needed
      try {
        Person person = (Person)ois.readObject();
        people.put(person.getId(), person);
      }
      catch(Exception ex){
        System.out.println(ex);
        return false;
      }
    }
  }
  catch(Exception ex){
    System.out.println(ex);
    return false;
  }
  return true;
  }

  // Lementi a people map-ben tárolt személyeket a filePath útvonalra.
  // Hiba esetén false, egyébként true értéket ad vissza.
  public boolean save(){
    
    try (FileOutputStream outstream = new FileOutputStream(filePath);
         ObjectOutputStream oos = new ObjectOutputStream(outstream);
    ) {
      Iterator<Long> keySetIterator = people.keySet().iterator();

      oos.writeInt(people.size());
      while(keySetIterator.hasNext()){
        Long key = keySetIterator.next();
        oos.writeObject(people.get(key));
      }
    return true;
    }
    catch (Exception ex){
      System.out.println(ex);
      return false;
    }
  }

  // Hozzáadja a people map-hez a megadott személyt az azonosítója alapján,
  // ha még nincs benne. Akkor ad true értéket, ha a megadott személy még nem
  // szerepelt a map-ben, egyébként false-t. 
  public boolean add(Person newPerson){
    long id = newPerson.getId();
    Person person = people.get(id);
    if (person == null){
      people.put(id, newPerson);
      return true;
    }
    else {
      return false;
    }
  }

  // Kikeres egy személyt az azonosítója alapján a people map-ből.
  // Ha a keresett személy nem található, null értéket ad vissza.
  public Person find(long id){
    return people.get(id);
  }


  //Kiíratja a könyveket
/*  public void print(){
    Iterator<String> keySetIterator = people.keySet().iterator();

    while(keySetIterator.hasNext()){
      String key = keySetIterator.next();
      (people.get(key)).print();
    }
  }*/

}
