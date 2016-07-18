
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Date;
import java.util.List;

public class ClientMain {

    private static final String HOST = "localhost";
    private static final int PORT = 1234;

    public static void main(String[] args) {
        Socket s = null;

        try {
            // Open connection to the server
            s = new Socket(HOST, PORT);

            // Create request: define operands
            long a = 10, b = 20;

            // Send request: write in the operands
            OutputStream os = s.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
           // Receive response: read out the addition result
            InputStream is = s.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            
            Person p1 = new Person(a, "Anna", "Szekér", new Date(1982, 9, 24), "Böszörményi út 3", null, null);
            Person p2 = new Person(b, "Annamária", "Fekete", new Date(1950, 2, 17), "Böszörményi út 3", null, null);

            oos.writeUTF("add");
            oos.writeObject(p1);
            oos.flush(); 

            oos.writeUTF("add");
            oos.writeObject(p2);
            oos.flush(); 

            oos.writeUTF("find");
            oos.writeLong(a);
            oos.flush(); 

            long c = 15;
            oos.writeUTF("find");
            oos.writeLong(c);
            oos.flush(); // important: immediatelly write contents to the socket

            boolean b1 = ois.readBoolean();
            System.out.println(b1); //Innentől kezdve nem lehet olvasni
            boolean b2 = ois.readBoolean();
            System.out.println(b2);
            boolean b3 = ois.readBoolean();
            System.out.println(b3);
            if (b3) {
              Person anna = (Person)ois.readObject();
              String lastName = anna.getLastName();
              System.out.println("Vezetéknév: "+lastName);
              String firstName = anna.getFirstName();
              System.out.println("Keresztnév: "+firstName);
              Date birthDate = anna.getBirthDate();
              int year = birthDate.getYear();
              int month = birthDate.getMonth();
              int day = birthDate.getDay();
              System.out.println("Születési idő: "+year + " " + (month+1) + " " + day);
              String address = anna.getAddress();
              System.out.println("Lakcím: "+address);
              BloodGroup bloodGroup = anna.getBloodGroup();
              if (bloodGroup != null){
                System.out.print("Vércsoportja: ");
                if (bloodGroup == BloodGroup.A){
                  System.out.println("A");
                }
                else if (bloodGroup == BloodGroup.B){
                  System.out.println("B");
                }
                else if (bloodGroup == BloodGroup.AB){
                  System.out.println("AB");
                }
                else if (bloodGroup == BloodGroup.Zero){
                  System.out.println("0");
                }
              }
              List<GpsCoordinate> coordinates = anna.getCoordinates();
              if (coordinates != null){
                for (int i = 0; i < coordinates.size(); i++) {
                  GpsCoordinate coord = coordinates.get(i);
                  System.out.println("Szélesség: " + coord.getLatitude());
                  System.out.println("Hosszúság: " + coord.getLongitude());
                  System.out.println("Magasság: " + coord.getHeight());
                  Date date = coord.getTime();
                  int year2 = date.getYear();
                  int month2 = date.getMonth();
                  int day2 = date.getDay();
                  System.out.println("Idő: "+year + " " + (month+1) + " " + day);
                }
              }
              
            }
            boolean b4 = ois.readBoolean();
            System.out.println(b4);
            if (b4) {
              Person somebody = (Person)ois.readObject();
              String lastName = somebody.getLastName();
              System.out.println("Vezetéknév: "+lastName);
              String firstName = somebody.getFirstName();
              System.out.println("Keresztnév: "+firstName);
              Date birthDate = somebody.getBirthDate();
              int year = birthDate.getYear();
              int month = birthDate.getMonth();
              int day = birthDate.getDay();
              System.out.println("Születési idő: "+year + " " + (month+1) + " " + day);
              String address = somebody.getAddress();
              System.out.println("Lakcím: "+address);
              BloodGroup bloodGroup = somebody.getBloodGroup();
              if (bloodGroup != null){
                System.out.print("Vércsoportja: ");
                if (bloodGroup == BloodGroup.A){
                  System.out.println("A");
                }
                else if (bloodGroup == BloodGroup.B){
                  System.out.println("B");
                }
                else if (bloodGroup == BloodGroup.AB){
                  System.out.println("AB");
                }
                else if (bloodGroup == BloodGroup.Zero){
                  System.out.println("0");
                }
              }
              List<GpsCoordinate> coordinates = somebody.getCoordinates();
              if (coordinates != null){
                for (int i = 0; i < coordinates.size(); i++) {
                  GpsCoordinate coord = coordinates.get(i);
                  System.out.println("Szélesség: " + coord.getLatitude());
                  System.out.println("Hosszúság: " + coord.getLongitude());
                  System.out.println("Magasság: " + coord.getHeight());
                  Date date = coord.getTime();
                  int year2 = date.getYear();
                  int month2 = date.getMonth();
                  int day2 = date.getDay();
                  System.out.println("Idő: "+year + " " + (month+1) + " " + day); 
                }
              }
            
            }

            oos.writeUTF("close");
            oos.flush();

        } catch (UnknownHostException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
          catch (ClassNotFoundException ex) {
            Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally {
            // Close connection to the server
            if(s != null) {
                try {
                    s.close();
                } catch (IOException ex) {
                    Logger.getLogger(ClientMain.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
