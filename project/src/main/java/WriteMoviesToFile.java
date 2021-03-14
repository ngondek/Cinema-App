import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteMoviesToFile {
    public static void main(String[] args) throws IOException {

        List<Movie> movies = new ArrayList<>();

        movies.add(new Movie("Tylko Sprawiedliwosc","\n" + "Destin Daniel Cretton","\n" +
                "Premiera: \n 26 czerwca 2020 (Polska) / 6 wrzesnia 2019 (swiat)", "Dramat",
                "Swiatowej slawy adwokat Bryan Stevenson wspomina sprawe skazanego na kare smierci mordercy osiemnastolatki" +
                " Waltera McMilliana, ktorego udalo mu sie wybronic.",
                "https://media.multikino.pl/uploads/images/films_and_events/tylko-sprawiedliwosc-plakat_9f7f35ac53.jpg"));
        movies.add(new Movie("Obrazy Bez Autora", "Florian Henckel von Donnersmarck","\n" +
                "Premiera: \n" + "12 czerwca 2020 (Polska) / 4 wrzesnia 2018 (swiat)","Dramat / Thriller",
                "Historia oparta na biografii malarza Kurta Barnerta. Maly Kurt jest bardzo przywiazany do swojej mlodej i " +
                "niezwyklej ciotki Elisabeth. Jej choroba i jej los wywra duzy wplyw na jego pozniejsze, powojenne zycie.",
                "https://www.wroclaw.pl/go/download/img-cbd24ac32df7bd081b6cabce8740cc24/1572406860667-jpeg.jpg"));
        movies.add(new Movie("Zdrajca",  "\n" +
                "Marco Bellocchio","\n" +
                "Premiara: \n 3 lipca 2020 (Polska) / 23 maja 2019 (swiat)","\n" +
                "Biograficzny / Dramat","W najdrozszym ze swoich filmow mistrz Bellocchio bez wahania zdradza romantyczna, " +
                "gangsterska narracje spod znaku \"Ojca chrzestnego\" na rzecz surowej rekonstrukcji brutalnych wydarzen.",
                "https://fwcdn.pl/fpo/75/34/807534/7912757.3.jpg"));
        movies.add(new Movie("Eastern",  "\n" +
                "Piotr Adamski", "Premiara: 26 czerwca 2020 (Polska) / 13 czerwca 2019 (swiat)", "Dramat",
                "W swiecie regulowanym bezwzglednym, patriarchalnym kodeksem dwie mlode dziewczyny, " +
                "w imie honoru swych rodzin, musza dokonczyc krwawa wendete","https://fwcdn.pl/fpo/23/26/832326/7898769.3.jpg"));
        movies.add(new Movie("Endings, Beginings",  "\n" +
                "Drake Doremus", "\n" +
                "Premiara: \n 3 lipca 2020 (Polska) / 8 wrzesnia 2019 (swiat)", "Romans", "Daphne rzuca prace, rozstaje sie z chlopakiem, " +
                "przeprowadza sie do siostry i zupelnie nie wie, co będzie dalej robic w zyciu. Kiedy na jednej imprezie poznaje Jacka i " +
                "Franka, i zaczyna spotykac sie z nimi jednoczesnie, sprawy komplikuja sie jeszcze bardziej.",
                "https://fwcdn.pl/fpo/91/04/829104/7917831.3.jpg"));

        MoviesData moviesData = new MoviesData(movies);
        moviesToFile(moviesData, "Movies.xml");

        //createReservationFile();

    }

    public static void createReservationFile(String fileName){
        List<Reservation> reservations = new ArrayList<>();
        //reservations.add(new Reservation("a11","Jozef","Wrobel","jwrobel@op.pl","2354638201"));
        ReservationsData reservationsData= new ReservationsData(reservations);
        XmlMapper xmlMapper = new XmlMapper();
        FileWriter out;
        try {
            out = new FileWriter(fileName);
            out.write(xmlMapper.writeValueAsString(reservationsData));
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void moviesToFile(MoviesData moviesData, String fileName){
        XmlMapper xmlMapper = new XmlMapper();
        FileWriter out;
        try {
            out = new FileWriter(fileName);
            out.write(xmlMapper.writeValueAsString(moviesData));
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
