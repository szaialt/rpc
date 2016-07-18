
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServerMain {

    private static final int PORT = 1234;

    public static void main(String[] args) {
        try {
            PersonRegistry pr = new PersonRegistry("persons.dat");

            // Open listener socket
            ServerSocket server = new ServerSocket(PORT);

            // Indicate startup
            System.out.println("Server started to listen on port " + PORT);

            // Run the service continously
            while(true) {
                // Look for a client connection, accept when found one
                Socket client = server.accept();

                // Process it on a separate thread
                ServerThread st = new ServerThread(client, pr);
                st.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static class ServerThread extends Thread {

        private Socket client;
        private PersonRegistry pr;

        public ServerThread(Socket client, PersonRegistry pr) {
            this.client = client;
            this.pr = pr;
        }

        @Override
        public void run() {
            try {
                // Receive request: read out the operands
                InputStream is = client.getInputStream();
                ObjectInputStream ois = new ObjectInputStream(is);
                // Send response: write in the result of the invocation
                OutputStream os = client.getOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(os);

                // Process request and create response:
                // invoke the service implementation
                pr.load();
                boolean cl = true;
               // Choose service operation
                while(cl){
                String methodName = ois.readUTF();
                switch (methodName) {
                  case "add": {  
                    Person person = (Person)ois.readObject();    
                    boolean b = pr.add(person);  
                    oos.writeBoolean(b);
                    pr.save();
                    oos.flush(); // important: immediatelly write contents to the socket                    
                    break;
                  }
                  case "find": { 
                    long id = ois.readLong();
                    Person person = pr.find(id);
                    boolean b = (!(person == null));
                    oos.writeBoolean(b);
                    if (b) oos.writeObject(person);
                    oos.flush(); // important: immediatelly write contents to the socket
                    break;
                  }
                  default: {
                    cl = false;
                    break;
                  }
                }
              }
            } catch (IOException ex) {
                Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex); 
                ex.printStackTrace();
            } catch (ClassNotFoundException ex) {
              Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex); 
            } finally {
                // Close connection to the client
                try {
                    client.close();
                } catch (IOException ex) {
                    Logger.getLogger(ServerMain.class.getName()).log(Level.SEVERE, null, ex);
                    ex.printStackTrace();
                }
            }
        }

    }
}
