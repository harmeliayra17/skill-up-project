package skill.up.project.controllers;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import skill.up.project.config.DbConfig;
import skill.up.project.models.Webinar;

public class WebinarController extends DbConfig{
    //create untuk tambah webinar
    public static boolean addWebinar(String name, String imagePath, String link, String description) {
        query = "INSERT INTO webinars (name, image_path, link, description) VALUES (?, ?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, link);
            preparedStatement.setString(4, description);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    //read untuk diperlihatkan di scene webinar dan pelatihan
    public static List<Webinar> getAllWebinar() {
        List<Webinar> webinars = new ArrayList<>();
        query = "SELECT * FROM webinars";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String imagePath = resultSet.getString("image_path");
                String link = resultSet.getString("link");
                String description = resultSet.getString("description");

                Webinar webinar = new Webinar(id, name, imagePath, link, description);
                webinars.add(webinar);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webinars;
    }

    //read untuk di scene detail webinar
    public static Webinar getWebinarById(int id) {
        Webinar webinar = null;
        query = "SELECT * FROM webinars WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String imagePath = resultSet.getString("image_path");
                String link = resultSet.getString("link");
                String description = resultSet.getString("description");

                webinar = new Webinar(id, name, imagePath, link, description);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return webinar;
    }

    //update
    public static boolean updateWebinar(int id, String name, String imagePath, String link, String description) {
        query = "UPDATE webinars SET name=?, image_path=?, link=?, description=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, link);
            preparedStatement.setString(4, description);
            preparedStatement.setInt(5, id);
            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //delete
    public static boolean deleteWebinar(int id) {
        query = "DELETE FROM webinars WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void addWebinarForAdmin(int adminId, int webinarId) {
        String sql = "INSERT INTO webinars (admin_id, webinar_id) VALUES(?, ?)";
        try {
            DbConfig.getConnection();
            DbConfig.preparedStatement = DbConfig.connection.prepareStatement(sql);
            DbConfig.preparedStatement.setInt(1, adminId);
            DbConfig.preparedStatement.setInt(2, webinarId);
            DbConfig.preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
