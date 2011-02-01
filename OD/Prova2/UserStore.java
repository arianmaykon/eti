import java.util.HashMap;

public class UserStore {

    private HashMap<String, IPushClient> userStore = new HashMap<String, IPushClient>();

    public IPushClient getClientByName(String name) {
        return this.userStore.get(name);
    }

    public String[] getAllUsers() {
        return this.userStore.keySet().toArray(new String[0]);
    }

    public void addUser(String user, IPushClient client) {
        this.userStore.put(user, client);
    }
}
