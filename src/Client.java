/*Клиент посылает слово серверу,
сервер возвращает назад в обратном
порядке следования букв это слово клиенту.
 */

import java.io.*;
import java.net.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Client extends Application {
    int i = 1;
    Socket sock = null;
    InputStream is = null;
    OutputStream os = null;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Client");
        GridPane root = new GridPane();
        root.setHgap(10);
        root.setVgap(10);
        Button btn = new Button("Connect");
        Button btn1= new Button ("Send");
        TextField tf = new TextField("127.0.0.1");
        TextField tf1 = new TextField("1024");
        TextField tf2 = new TextField();
        TextArea ta = new TextArea();
        Label sendingData=new Label("Sending data");
        Label result=new Label("Result");
        root.add(sendingData,5,19);
        sendingData.setStyle("-fx-background-color: #FFCC00");
        result.setStyle("-fx-background-color: #FFCC00");
        ta.setStyle("-fx-background-color: #FFCC00");
        root.add(result,5,24);
        root.add(btn, 0,2);
        root.add(tf, 2,2);
        root.add(tf1,6,2);
        root.add(btn1, 0,20);
        root.add(ta, 5, 25);
        root.add(tf2,5,20);
        btn1.setOnAction(e -> {
            try {
                    InputStream sin = sock.getInputStream();
                    OutputStream sout = sock.getOutputStream();
                    DataInputStream in = new DataInputStream(sin);
                    DataOutputStream out = new DataOutputStream(sout);
                    String str = "";
                    str += tf2.getText();
                    out.writeUTF(str);
                    out.flush();
                    str = in.readUTF();
                    ta.appendText(str);

                sock.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        btn.setOnAction(e -> {
            try {
                sock = new Socket(InetAddress.getByName(tf.getText()), Integer.parseInt(tf1.getText()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        ta.setPrefHeight(70);
        ta.setPrefWidth(200);
        final Scene scene = new Scene(root, 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }}
