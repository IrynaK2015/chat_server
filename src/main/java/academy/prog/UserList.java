package academy.prog;

import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserList {
    private static UserList userListInstance = new UserList();

    private ArrayList<String> activeUserList = null;
    private final Gson gson;

    public static UserList getInstance() {
        if (userListInstance == null)
            userListInstance = new UserList();

        return userListInstance;
    }

    private UserList() {
        activeUserList = new ArrayList<>();
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
    }

    public void addUser(String userLogin)
    {
        if (!activeUserList.contains(userLogin)) {
            activeUserList.add(userLogin);
        }
    }

    public void deleteUser(String userLogin) {
        for (int i = 0; i < activeUserList.size(); i++) {
            if (activeUserList.get(i).equals(userLogin)) {
                activeUserList.remove(i);
                break;
            }
        }
    }


    public synchronized String toJSON() {
        return gson.toJson(activeUserList);
    }
}
