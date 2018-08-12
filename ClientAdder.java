package LastAppForChatting;

import java.util.ArrayList;
import java.util.List;

public class ClientAdder {
    private static ClientAdder a = new ClientAdder();
    private List<User> addUsers = new ArrayList<>();

    public static ClientAdder getInstance() {
        return a;
    }

    private ClientAdder() {

    }

    public void addClientToList(User user) {
        this.addUsers.add(user);
    }

    public List<User> getAddUsers() {
        return addUsers;
    }

    public void removeUsers(User user) {
        this.addUsers.remove(user);
    }
}
