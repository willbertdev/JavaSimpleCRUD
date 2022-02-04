package model;

import java.sql.*;
import java.util.*;

public class UserModel {

    private String name;
    private String email;
    public Connection con = DbConnection.connect();

    public  UserModel() {}

    public UserModel(String action, HashMap data, int id, String sql) throws SQLException {

        switch(action) {
            case "insert":
                insert(data);
                break;
            case "update":
                update(data, id);
                break;
            case "delete":
                delete(id);
                break;
            case "read":
                readAll(sql);
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
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        arrUser.forEach((key, value) ->  {
            System.out.println("Data " + (key+1) + ": " + Arrays.toString(value));
        });
    }

    public void insert(HashMap data) throws SQLException {
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(name, email) VALUES(?,?)";
        ps = this.con.prepareStatement(sql);

        System.out.println(data.size());

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
        this.con.close();
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

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                ps.close();
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Data updated in ID: " + id);
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
                this.con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
