import java.util.HashMap;
import java.util.Map;

public class LoginModule {
    private Map<String, String> userCredentials;

    public LoginModule() {
        this.userCredentials = new HashMap<>();
    }

    public void registerUser(String username, String password) {
        userCredentials.put(username, password);
    }

    public boolean authenticate(String username, String password) {
        return userCredentials.getOrDefault(username, "").equals(password);
    }
}