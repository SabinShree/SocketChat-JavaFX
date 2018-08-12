package LastAppForChatting;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Controller {

    @FXML // fx:id="chatText"
    private TextField chatText; // Value injected by FXMLLoader

    @FXML // fx:id="sendButton"
    private JFXButton sendButton; // Value injected by FXMLLoader

    @FXML // fx:id="chatMessage"
    private TextArea chatMessage; // Value injected by FXMLLoader

    @FXML // fx:id="ip"
    private TextField ip; // Value injected by FXMLLoader

    @FXML // fx:id="port"
    private TextField port; // Value injected by FXMLLoader
    @FXML // fx:id="userName"
    private TextField userName; // Value injected by FXMLLoader
    private BufferedReader bufferedReader;
    private PrintWriter printWriter;
    private Socket socket;
    @FXML
    private JFXButton connectButton;

    @FXML
    public void initialize() {
        chatMessage.appendText("Welcome to the sabin Chatting application.\n");
        chatMessage.setDisable(true);
        chatText.setDisable(true);
        sendButton.setDisable(true);
        ip.setText("localhost");
        port.setText(String.valueOf(12345));
    }

    @FXML
    public void sendMessage() {
        String message = chatText.getText().trim();
        if (message.isEmpty()) {
            return;
        }
        printWriter.println(message);
        chatText.setText("");
    }

    @FXML
    public void connectToServer() {
        try {
            String ips = ip.getText().trim();
            int ports = Integer.parseInt(port.getText().trim());
            String userNames = userName.getText().trim();
            if (userNames.isEmpty()) return;
            System.out.println(userNames);
            socket = new Socket(ips, ports);
            chatMessage.appendText("Welcome " + userNames + " to chat room." + "\n");
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println(userNames);
            chatMessage.setDisable(false);
            chatText.setDisable(false);
            sendButton.setDisable(false);
            ip.setDisable(true);
            port.setDisable(true);
            connectButton.setDisable(true);
            userName.setDisable(true);
            new Thread(() -> {
                while (true) {
                    try {
                        String message = bufferedReader.readLine();
                        if (message.isEmpty()) {
                            return;
                        }
                        chatMessage.appendText(message + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void OnKeyTyped(ActionEvent actionEvent) {
        sendMessage();
    }
}