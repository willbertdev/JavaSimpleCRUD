import model.UserModel;

import java.lang.reflect.Array;
import java.sql.SQLException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        readAllData();
//        addUser();
//        updateUser();
//        deleteUser();
    }


    public static void readAllData() {
        String sql = "SELECT * FROM users";

        try {
            new UserModel("read",new HashMap<>(), 0, sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addUser() {
        String data1[] = {"Willbert", "willbert.developer@gmail.com"};
        String data2[] = {"Lester", "lester.developer@gmail.com"};

        try {
            HashMap<Integer, String[]> data = new HashMap<>();
            data.put(0, data1);
            new UserModel("insert", data, 0, "");
            data.put(0, data2);
            new UserModel("insert", data, 0, "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUser() {
        String data1Update[] = {"wilbert", "wilb3rd@gmail.com"};
        HashMap<Integer, String[]> updateData = new HashMap<Integer, String[]>();

        updateData.put(0, data1Update);

        try {
            new UserModel("update", updateData, 14, "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser() {
        try {
            new UserModel("delete", new HashMap<>(), 14, "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
