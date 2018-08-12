package LastAppForChatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class User {
    private static int nbUser = 0;
    private int userId;
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String nickname;
    private Socket client;
    private String color;

    // constructor
    User(Socket client, String name) throws IOException {
        this.printWriter = new PrintWriter(client.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.client = client;
        this.nickname = name;
        this.userId = nbUser;
//        this.color = ColorInt.getColor(this.userId);
        nbUser += 1;
    }

    PrintWriter getOutStream() {
        return this.printWriter;
    }

    BufferedReader getInputStream() {
        return this.bufferedReader;
    }

    String getNickname() {
        return this.nickname;
    }

    public String toString() {
        return this.getNickname();
    }

}
