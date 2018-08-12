package LastAppForChatting;

import java.util.Scanner;

class UserHandler implements Runnable {
    private Server server;
    private User user;

    public UserHandler(Server server, User user) {
        this.server = server;
        this.user = user;
    }

    @Override
    public void run() {
        String message;
//        BufferedReader bufferedReader = new BufferedReader((this.user.getInputStream()));
        Scanner scanner = new Scanner(this.user.getInputStream());
        while (scanner.hasNextLine()) {
            message = scanner.nextLine();
            if (message.startsWith("@")) {
                if (message.contains(" ")) {
                    System.out.println("Private Message : " + message);
                    int firstSpace = message.indexOf(" ");
                    String userPrivate = message.substring(1, firstSpace);
                    server.sendMessageToUsers(message.substring(firstSpace + 1), user, userPrivate);
                }
            } else if (message.startsWith("#")) {
            } else {
                server.displayToAllUsers(message, user);
            }
        }
        server.removeUsers(user);
//        this.server.SendMessageToAllUsers();
        scanner.close();
    }
}

