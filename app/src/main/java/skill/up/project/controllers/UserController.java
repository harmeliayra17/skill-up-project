package skill.up.project.controllers;

import java.sql.ResultSet;
import skill.up.project.config.DbConfig;
import skill.up.project.models.User;
import java.sql.SQLException;


public class UserController extends DbConfig {

    // create login
    public static User login(String email, String password) {
        String query = "SELECT * FROM users WHERE email=? AND password=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            try (ResultSet userResult = preparedStatement.executeQuery()) {
                if (userResult.next()) {
                    int id = userResult.getInt("id");
                    String role = userResult.getString("role");
                    User user = new User(id, role);
                    return user;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //register
    public static boolean register(String name, String email, String password) {
        String query = "INSERT INTO users (name, email, password, role) VALUES (?,?,?, 'user')";
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

    //get user by id yang diperlihatkan di profile scene
    public static User getUserById(int id) {
        String query = "SELECT * FROM users WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                String phoneNumber = resultSet.getString("phone_number");
                String mbtiResult = resultSet.getString("mbti_result");
                String registeredWebinar = resultSet.getString("registered_webinar");
                int age = resultSet.getInt("age");
                String role = resultSet.getString("role");
                String company = resultSet.getString("company");
    
                User user = new User(id, name, email, phoneNumber, mbtiResult, registeredWebinar, age, company, role);
                return user;
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

    //update user
    public static boolean updateUser(int id, String name, String phoneNumber, int age) {
        String query = "UPDATE users SET name=?, phone_number=?, age=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2, phoneNumber);
            preparedStatement.setInt(3, age);
            preparedStatement.setInt(4, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
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
        String query = "UPDATE users SET name = ?, phone_number = ?, company = ? WHERE id = ?";
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

    //update mbti result
    public static boolean updateUserMbtiResult(int id, String mbtiResult) {
        String query = "UPDATE users SET mbti_result=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, mbtiResult);
            preparedStatement.setInt(2, id);

            int affectedRows = preparedStatement.executeUpdate();
            return affectedRows > 0;
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

    // Cek keberadaan email dalam database
    public static boolean isUserExists(String email) {
        String query = "SELECT COUNT(*) AS count FROM users WHERE email=?";
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
        } finally {
            try {
                if (resultSet!= null) resultSet.close();
                if (preparedStatement!= null) preparedStatement.close();
                if (connection!= null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    // update registered webinar
    public static boolean updateUserRegisteredWebinar(int id, String registeredWebinar) {
        String querySelect = "SELECT registered_webinar FROM users WHERE id=?";
        String queryUpdate = "UPDATE users SET registered_webinar = ? WHERE id=?";
        try {
            getConnection();
            
            // Fetch the current registered_webinar value
            preparedStatement = connection.prepareStatement(querySelect);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            
            String currentWebinars = "";
            if (resultSet.next()) {
                currentWebinars = resultSet.getString("registered_webinar");
            }
            
            // Append the new webinar
            if (currentWebinars == null || currentWebinars.isEmpty()) {
                currentWebinars = registeredWebinar;
            } else {
                currentWebinars += ", " + registeredWebinar;
            }

            // Update the database
            preparedStatement = connection.prepareStatement(queryUpdate);
            preparedStatement.setString(1, currentWebinars);
            preparedStatement.setInt(2, id);
            int affectedRows = preparedStatement.executeUpdate();
            
            return affectedRows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}