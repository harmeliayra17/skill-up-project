package skill.up.project.models;

public class Article extends Model {
    String title, imagePath, link;

    public Article(int id, String title, String imagePath, String link) {
        super(id);
        this.title = title;
        this.imagePath = imagePath;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getImagePath() {
        return imagePath;
    }
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getLink() {
        return link;
    }
    public void setLink(String link) {
        this.link = link;
    }
}
