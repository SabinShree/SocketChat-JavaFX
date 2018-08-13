package LastAppForChatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server extends Thread {
    private int port;
    private ServerSocket server;

    public Server(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        new Server(12345).start();
    }

    @Override
    public void run() {
        try {
            server = new ServerSocket(port);
            System.out.println("Port " + port + " is now opened.");
            do {
                Socket socket = server.accept();
                String nickName = new BufferedReader(new InputStreamReader(socket.getInputStream())).readLine();
                System.out.println("New Member : " + nickName + " From IP address " + socket.getInetAddress().getHostAddress());
                User users = new User(socket, nickName);
                ClientAdder.getInstance().addClientToList(users);
                users.getOutStream().println(users.toString());
                new Thread(new UserHandler(this, users)).start();
            } while (true);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (server != null) {
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeUsers(User users) {
        ClientAdder.getInstance().removeUsers(users);
    }

    public void displayToAllUsers(String msg, User userHandler) {
        for (User users : ClientAdder.getInstance().getAddUsers()) {
            users.getOutStream().println(userHandler.toString() + " : " + msg );
        }
    }


    public void sendMessageToUsers(String msg, User userSender, String user) {
        boolean find = false;
        for (User client : ClientAdder.getInstance().getAddUsers()) {
            if (client.getNickname().equals(user) && client != userSender) {
                find = true;
                userSender.getOutStream().println(userSender.toString() + " -> " + client.toString() + " : " + msg);
                client.getOutStream().println("Private  : " + msg);
            }
        }
        if (!find) {
            userSender.getOutStream().println(userSender.toString() + " -> " + "<b>No One </b> " + msg);
        }
    }
}

