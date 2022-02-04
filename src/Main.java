import model.UserModel;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        deleteUser();
    }

    public static void addUser() {
        String data1[] = {"Willbert", "willbert.developer@gmail.com"};
        String data2[] = {"Lester", "lester.developer@gmail.com"};

        HashMap<Integer, String[]> data = new HashMap<>();
        data.put(0, data1);
        data.put(1, data2);


//        System.out.println(data.get("names")[0]);
        try {
            new UserModel("insert", data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void readAllData() {
        String sql = "SELECT * FROM users";

        UserModel user = new UserModel();
        user.readAll(sql);
    }

    public static void updateUser() {
        String data1Update[] = {"wilbert", "wilb3rd@gmail.com"};
        HashMap<Integer, String[]> updateData = new HashMap<Integer, String[]>();


        updateData.put(0, data1Update);
        UserModel user = new UserModel();
        user.update(updateData, 1);
    }

    public static void deleteUser() {
        UserModel user = new UserModel();
        user.delete(1);
    }
}
