package skill.up.project.controllers;

import java.util.ArrayList;
import java.util.List;

import skill.up.project.config.DbConfig;
import skill.up.project.models.Article;

public class ArticleController extends DbConfig{
    //create
    public static boolean addArticle(String title, String imagePath, String link) {
        query = "INSERT INTO articles (title, image_path, url) VALUES (?, ?, ?)";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, link);
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //read untuk homescene
    public static List<Article> getAllArticles() {
        List<Article> articles = new ArrayList<>();
        query = "SELECT * FROM articles";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String imagePath = resultSet.getString("image_path");
                String link = resultSet.getString("url");

                Article article = new Article(id, title, imagePath, link);
                articles.add(article);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return articles;
    }

    //TODO (diperlukan atau tidak?) read untuk lihat detail artikel
    public static Article getArticleById(int id) {
        Article article = null;
        query = "SELECT * FROM articles WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String imagePath = resultSet.getString("image_path");
                String link = resultSet.getString("url");
                // Fill the book
                article = new Article(id, title, imagePath, link);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return article;
    }

    //update
    public static boolean updateArticle(int id, String title, String imagePath, String link) {
        query = "UPDATE articles SET title=?, image_path=?, url=? WHERE id=?";
        try {
            getConnection();
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, imagePath);
            preparedStatement.setString(3, link);
            preparedStatement.setInt(4, id);
            int rowsUpdated = preparedStatement.executeUpdate(); 
            return rowsUpdated > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //delete
    public static boolean deleteArticle(int id) {
        query = "DELETE FROM articles WHERE id=?";
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
}
