package skill.up.project.models;

public class Webinar extends Model{
    String name, imagePath, link, description;

    public Webinar(int id, String name, String imagePath, String link, String description) {
        super(id);
        this.name = name;
        this.imagePath = imagePath;
        this.link = link;
        this.description = description;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
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

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
