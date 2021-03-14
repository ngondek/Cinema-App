import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadDataFromFile {
    private ReservationsData reservationsData ;

    ReadDataFromFile(String file) {
        try {
            reservationsData = readReservationsDataFrom(file);
        } catch (JsonProcessingException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    ReservationsData getReservationsData() {
        return reservationsData;
    }

    private ReservationsData readReservationsDataFrom(String fileName) throws JsonProcessingException {

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
        }catch (NullPointerException | IOException ignored){}

        XmlMapper xmlMapper = new XmlMapper();
        try {
            reservationsData = xmlMapper.readValue(input.toString(), ReservationsData.class);
        }catch(NullPointerException e){
            reservationsData = new ReservationsData();
        }
        return reservationsData;
    }
}
