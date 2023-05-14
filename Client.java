import java.io.*;
import java.net.*;

public class Client {

  Socket socket;
  BufferedReader br;
  PrintWriter out;

  public Client() {
    try {
      System.out.println("Sending request");
      socket = new Socket("127.0.0.1", 4793);
      System.out.println("connection done");

      br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      out = new PrintWriter(socket.getOutputStream());
      
      startreading();
      startwriting();

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void startreading(){
    Runnable r1= () -> {
      // System.out.println("reading started..");

      while(true){
      try {
        String msg=br.readLine();
        System.out.println("Server: "+msg);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    };
    new Thread(r1).start(); 
}
public void startwriting(){
  Runnable r2=()->{
    System.out.println("client is writing");
    while(true){
      try {
        
        BufferedReader br1 = new BufferedReader( new InputStreamReader(System.in));
        // System.out.println("client:");
        String content=br1.readLine();
        out.println(content);
        out.flush();

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  };
  new Thread(r2).start(); 
}

  public static void main(String[] args) throws Exception {
    System.out.println("Client here");
    new Client();
  }
}
