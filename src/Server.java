/*Клиент посылает слово серверу,
сервер возвращает назад в обратном
порядке следования букв это слово клиенту.
 */


import java.net.*;
import java.io.*;

public class Server {
    public static void main(String[] args) {
        int port = 1024;
            try {
                ServerSocket ss = new ServerSocket(port); //создание сокета
                System.out.println("Waiting for client...");
                Socket socket = ss.accept(); //подключение клиента
                System.out.println("Client connected.");
                InputStream sin = socket.getInputStream(); //создание входного потока
                OutputStream sout = socket.getOutputStream(); //создние выходного потока
                DataInputStream in = new DataInputStream(sin);
                DataOutputStream out = new DataOutputStream(sout) {
                };

                String word1 = null;
                try {
                    word1 = in.readUTF(); //читает от клиента инфу
                } catch (Exception x) {
                    x.printStackTrace();
                }

               String answer=new StringBuffer(word1).reverse().toString() ;
                System.out.println(word1);
                out.writeUTF(answer); //посылает ответ
               ss.close(); //закрытие сокета клиента
                socket.close(); //закрытие сокета сервера
                out.flush();
            } catch (Exception x) {
                x.printStackTrace();
            }
    }
}





