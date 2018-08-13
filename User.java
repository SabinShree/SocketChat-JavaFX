package LastAppForChatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class User {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String nickname;
    private Socket client;

    // constructor
    User(Socket client, String name) throws IOException {
        this.printWriter = new PrintWriter(client.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.client = client;
        this.nickname = name;
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
