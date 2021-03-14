public class Movie {
    private String title,director,type, premiere, desription, image;

    Movie(String title, String director, String type, String premiere, String desription, String image) {
        this.title = title;
        this.director = director;
        this.type = type;
        this.premiere = premiere;
        this.desription = desription;
        this.image = image;
    }

    public Movie() {
    }

    String getDirector() {
        return director;
    }

    String getType() {
        return type;
    }

    String getPremiere() {
        return premiere;
    }

    String getImage() {
        return image;
    }

    String getTitle() {
        return title;
    }

    String getDesription() {
        return desription;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setPremiere(String premiere) {
        this.premiere = premiere;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDesription(String desription) {
        this.desription = desription;
    }
}
