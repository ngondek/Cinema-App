import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WriteDataToFile {
    private List<Reservation> reservations;
    private ReservationsData reservationsData;


    WriteDataToFile(String file){
        reservations = new ArrayList<>();

        ReadDataFromFile readDataFromFile = new ReadDataFromFile(file);
        if(!readDataFromFile.getReservationsData().getReservationList().isEmpty()) {
            for (Reservation reservation : readDataFromFile.getReservationsData().getReservationList()) {
                reservations.add(new Reservation(reservation.getSeat(),reservation.getName(),reservation.getLastName(),reservation.getEmail(),reservation.getPhoneNumber()));
            }
        }
    }

    void addReservation(Reservation reservation){
        reservations.add(reservation);
    }

    List<Reservation> getReservations() {
        return reservations;
    }

    void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    void reservationsToFile(String fileName) {

        reservationsData = new ReservationsData(reservations);

        XmlMapper xmlMapper = new XmlMapper();
        FileWriter out;
        try {
            out = new FileWriter(fileName);
            out.write(xmlMapper.writeValueAsString(reservationsData));
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
