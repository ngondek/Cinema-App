import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadMoviesFromFile {
    public static void main(String[] args) throws IOException {
        MoviesData moviesData = readMoviesFromFile("src/Movies.xml");
        try{
            for (Movie s : moviesData.getMoviesList()) {
                System.out.println(s.getTitle());
            }
        }catch (NullPointerException ignored){
        }
    }

    static MoviesData readMoviesFromFile(String fileName) throws JsonProcessingException {

        StringBuilder input = new StringBuilder();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(fileName));
            String string = reader.readLine();
            while (string != null) {
                input.append(string);
                string = reader.readLine();
            }
            reader.close();
        }catch (Exception e){e.printStackTrace();}


        XmlMapper xmlMapper = new XmlMapper();
        MoviesData moviesData;
        try {
            moviesData = xmlMapper.readValue(input.toString(), MoviesData.class);
        }
        catch (NullPointerException e ) {
            moviesData = new MoviesData();
        }

        return moviesData;
    }
}
