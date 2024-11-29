import java.io.*;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;


public class LoginManager {
    private static final String USERS_FILE = "users.json";
    private List<User> users;

    public LoginManager() {
        users = new ArrayList<>();
        loadUsers();
    }

    // Load users from the JSON file
    private void loadUsers() {
        try (Reader reader = new FileReader(USERS_FILE)) {
            // Parse JSON into a list of User objects
            Gson gson = new Gson();
            Type userListType = new TypeToken<List<User>>(){}.getType();
            users = gson.fromJson(reader, userListType);
            if (users == null) {
                users = new ArrayList<>();
            }
        } catch (IOException e) {
            System.out.println("Error loading users: " + e.getMessage());
        }
    }

    // Login method to check credentials
    public User login(String username, String password) {
        username = username.trim();
        password = password.trim();

        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user; // Return user if credentials match
            }
        }
        return null; // Return null if no matching user found
    }

    // Register method to add new users
    public void register(String username, String password, String role) {
        // Add the new user to the list
        users.add(new User(username.trim(), password.trim(), role.trim()));

        // Save the updated user list to the JSON file
        saveUsers();
    }

    // Save users to the JSON file
    private void saveUsers() {
        try (Writer writer = new FileWriter(USERS_FILE)) {
            Gson gson = new Gson();
            gson.toJson(users, writer); // Convert the users list to JSON and write it to the file
        } catch (IOException e) {
            System.out.println("Error saving users: " + e.getMessage());
        }
    }
}
