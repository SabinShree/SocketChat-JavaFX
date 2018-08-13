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
        try{
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
                    String messagePrivate = message.substring(firstSpace + 1);
                    server.sendMessageToUsers(messagePrivate, user, userPrivate);
                }
                else {
                server.displayToAllUsers(message, user);
            }
        }
        server.removeUsers(user);
        scanner.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}

