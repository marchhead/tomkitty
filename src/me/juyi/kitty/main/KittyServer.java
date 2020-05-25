package me.juyi.kitty.main;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class KittyServer {
    public static void main(String[] args) throws IOException{
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            final Socket socket = server.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {


                        try {
                            InputStream is = socket.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                            String requestStr = "";
                            String line = null;
                            while ((line = reader.readLine())!=null && !"".equals(line)) {
                                requestStr += line+"\n";
                            }
                            System.out.println(requestStr);

                            OutputStream os = socket.getOutputStream();
                            os.write(("HTTP/1.1 200 OK\n" +
                                    "Server: Donyue\n" +
                                    "Content-Type: text/html\n\n").getBytes());

                            os.write("<h1>Hello</h1>".getBytes());

                            Thread.sleep(3000);
                            os.close();
                            is.close();
                            socket.close();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }


                }
            }).start();

        }
    }
}
