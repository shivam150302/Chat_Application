import java.io.*;
import java.net.*;

public class Server {

  BufferedReader br;
  PrintWriter out;
  /**
   * 
   */
  public Server() {
    try {
      ServerSocket server =new ServerSocket(4793);
      System.out.println("Server is ready");

      Socket socket = server.accept();

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
        System.out.println("reading started..");

        while(true){
        try {
          String msg=br.readLine();
          System.out.println("client: "+msg);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
      };
      new Thread(r1).start(); 
  }
  public void startwriting(){
    Runnable r2=()->{
      System.out.println("Server is writing");
      while(true){
        try {
          
          BufferedReader br1 = new BufferedReader( new InputStreamReader(System.in));
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
    System.out.println("Hello, World!");
    new Server();
  }
}
