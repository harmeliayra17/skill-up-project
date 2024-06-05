package skill.up.project.controllers;

import skill.up.project.config.DbConfig;
import skill.up.project.models.Admin;
import skill.up.project.models.Webinar;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminController extends DbConfig {

    public static Admin login(String email, String password) {
        String query = "SELECT * FROM admins WHERE email=? AND password=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet adminResult = preparedStatement.executeQuery()) {
                if (adminResult.next()) {
                    int id = adminResult.getInt("id");
                    String name = adminResult.getString("name");
                    String phoneNumber = adminResult.getString("phone_number");
                    String company = adminResult.getString("company");
                    String passwordRetrieved = adminResult.getString("password");

                    List<Webinar> webinars = WebinarController.getAllWebinar();
                    Admin admin = new Admin(id, name, email, phoneNumber, company, passwordRetrieved);
                    return admin;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean register(String name, String email, String password) {
        String query = "INSERT INTO admins (name, email, password) VALUES (?,?,?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement!= null) preparedStatement.close();
                if (connection!= null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean updateAdmin(int id, String name, String phoneNumber, String company) {
        String query = "UPDATE admins SET name = ?, phone_number = ?, company = ? WHERE id = ?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setString(3, company);
            preparedStatement.setInt(4, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Admin getAdminById(int id) {
        String query = "SELECT * FROM admins WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String phoneNumber = resultSet.getString("phone_number");
                String company = resultSet.getString("company");
    
                Admin admin = new Admin(id, name, email, password, phoneNumber, company);
                return admin;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet!= null) resultSet.close();
                if (preparedStatement!= null) preparedStatement.close();
                if (connection!= null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static boolean isUserExists(String email) {
        String query = "SELECT COUNT(*) AS count FROM admins WHERE email=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
