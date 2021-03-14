import java.util.ArrayList;
import java.util.List;

public class ReservationsData {

    private List<Reservation> reservationList;

    public ReservationsData() {
        reservationList = new ArrayList<>();
    }

    public ReservationsData(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }
}
