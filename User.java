package LastAppForChatting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User {
    private PrintWriter printWriter;
    private BufferedReader bufferedReader;
    private String nickname;
    private Socket client;

    // constructor
    protected User(Socket client, String name) throws IOException {
        this.printWriter = new PrintWriter(client.getOutputStream(), true);
        this.bufferedReader = new BufferedReader(new InputStreamReader(client.getInputStream()));
        this.client = client;
        this.nickname = name;
    }

    public PrintWriter getOutStream() {
        return this.printWriter;
    }

   public BufferedReader getInputStream() {
        return this.bufferedReader;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String toString() {
        return this.getNickname();
    }

}
