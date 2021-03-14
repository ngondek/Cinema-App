import java.util.ArrayList;
import java.util.List;

public class MoviesData {
    private List<Movie> moviesList = new ArrayList<>();

    public MoviesData(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public MoviesData() {
        moviesList = new ArrayList<>();
    }

    public void setMoviesList(List<Movie> moviesList) {
        this.moviesList = moviesList;
    }

    public List<Movie> getMoviesList() {
        return moviesList;
    }
}
