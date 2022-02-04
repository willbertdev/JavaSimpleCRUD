package model;

import java.sql.*;
import java.util.*;

public class UserModel {

    private String name;
    private String email;
    public Connection con = DbConnection.connect();

    public  UserModel() {}

    public UserModel(String action, HashMap data) throws SQLException {
        Connection con = DbConnection.connect();

        switch(action) {
            case "insert":
                insert(data);
                break;
        }
    }


    public void readAll(String sql) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        HashMap<Integer, String[]> arrUser = new HashMap<Integer, String[]>();

        try {
            ps = this.con.prepareStatement(sql);
            rs = ps.executeQuery();

            int i = 0;
            while(rs.next()) {
                String name = rs.getString("name");
                String email = rs.getString("email");
                arrUser.put(i, new String[]{name, email});
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        System.out.println(Arrays.toString(arrUser.get(1)));
    }

    public void insert(HashMap data) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(name, email) VALUES(?,?)";
        ps = this.con.prepareStatement(sql);

        PreparedStatement finalPs = ps;

        data.forEach((key, value) -> {
            String val[] = (String[]) value;

            try {
                finalPs.setString(1, val[0]);
                finalPs.setString(2, val[1]);
                finalPs.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    finalPs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        System.out.println("Inserted!");

    }

    public void update(HashMap data, int id) {
        PreparedStatement ps = null;
        String sql = "UPDATE users set name = ?, email = ? WHERE id = ?";

        String updateData[] = (String[]) data.get(0);
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, updateData[0]);
            ps.setString(2, updateData[1]);
            ps.setString(3, String.valueOf(id));
            ps.execute();
            System.out.println("Data updated in ID: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(int id) {
        PreparedStatement ps = null;
        String sql = "DELETE FROM users WHERE id = ?";
        try {
            ps = this.con.prepareStatement(sql);
            ps.setString(1, String.valueOf(id));
            ps.execute();

            System.out.println("Data deleted in ID " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
