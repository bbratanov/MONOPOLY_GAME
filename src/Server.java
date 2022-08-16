import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
class ServerSomthing extends Thread {

    private Socket socket;

    private BufferedReader in;
    private BufferedWriter out;
    boolean fl=false;



    public ServerSomthing(Socket socket) throws IOException {
        this.socket = socket;
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        start();
    }
    @Override
    public void run() {
        try{
        String word;
//        word = in.readLine();
//        Server.msg.add(word);
//        check();
            // первое сообщение отправленное сюда - это никнейм
            try {
                while (true) {

                    word = in.readLine();
                    if(!fl&&word.lastIndexOf("start")!=-1){
                        Server.msg.add(word);
                        check();
                    }else{


                    if(word!=null){
                    System.out.println(word);
                    System.out.println("Echoing: " + word);

                        for (ServerSomthing vr : Server.serverList) {
                            if(this!=vr)
                            vr.send(word); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
                        }
                        if(word.equals("l")) downService();
                    }
}
                }
            } catch (Exception e) {}}catch (Exception e){}

    }
    private void check(){
        if(  Server.msg.size()==2){
            Server.serverList.get(0).send(Server.msg.get(1));
            Server.serverList.get(1).send(Server.msg.get(0));
//            for (ServerSomthing vr : Server.serverList) {
//
//                    vr.send("start"); // отослать принятое сообщение с привязанного клиента всем остальным влючая его
//                fl=true;
//            }
        }
    }

    private void send(String msg) {
        try {
            out.write(msg + "\n");
            out.flush();
        } catch (IOException ignored) {}

    }


    private void downService() {
        try {
            if(!socket.isClosed()) {
                socket.close();
                in.close();
                out.close();
                for (ServerSomthing vr : Server.serverList) {
                    if(vr.equals(this)) vr.interrupt();
                    Server.serverList.remove(this);
                }
            }
        } catch (IOException ignored) {}
    }
}





public class Server {

    public static final int PORT = 3448;
    public static LinkedList<ServerSomthing> serverList = new LinkedList<>(); // список всех нитей - экземпляров

public static ArrayList<String>msg = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(PORT);
        System.out.println("Server Started");
        try {
            while (true) {
                Socket socket = server.accept();

                try {
                    serverList.add(new ServerSomthing(socket)); // добавить новое соединенние в список
                } catch (IOException e) {
                    socket.close();
                }
            }
        } finally {
            server.close();
        }
    }
}