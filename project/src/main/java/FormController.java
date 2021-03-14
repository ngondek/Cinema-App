import com.sun.javafx.binding.SelectBinding;
import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FormController {

    @FXML
    public Button buyButton;
    @FXML
    public Label warningText;
    @FXML
    public TextField phoneNumber;
    @FXML
    public TextField email;
    @FXML
    public TextField lastName;
    @FXML
    public TextField name;
    @FXML
    public CheckBox acceptButton;
    @FXML
    public ChoiceBox reducedTicket;
    @FXML
    public ChoiceBox normalTicket;
    @FXML
    public Label totalAmount;
    @FXML
    public Label wrongTicketAmount;

    private Vector<String> selectedSeats;
    private DoubleProperty reduced;
    private DoubleProperty normal;
    private String fileName;

    void finalization(Vector<String> seats, String fileName){
        this.fileName = fileName;
        selectedSeats=seats;
        enableChoosingTickets();
    }

    public void initialize(){
        reduced = new SimpleDoubleProperty();
        reduced.bind(reducedTicket.valueProperty());
        normal = new SimpleDoubleProperty();
        normal.bind( normalTicket.valueProperty());
        totalAmount.textProperty().bind(Bindings.add(reduced.multiply(14.00),normal.multiply(28.00)).asString());
    }

    private void enableChoosingTickets(){
        List<Integer> values = new ArrayList<>();
        for(int i =0 ; i <=selectedSeats.size(); i++){
            values.add(i);
        }
        reducedTicket.setItems(FXCollections.observableList(values));
        normalTicket.setItems(FXCollections.observableList(values));

    }

    public void buy(ActionEvent actionEvent) {
        if((Bindings.add( reduced,normal).intValue() != (selectedSeats.size())))
            wrongTicketAmount.setText("Podana liczba biletów nie zgadza się z liczbą wybranych miejsc. Liczba wybranych miejsc: " + selectedSeats.size());
        else
            wrongTicketAmount.setText("");
        if((phoneNumber.getText().isEmpty() || email.getText().isEmpty() || lastName.getText().isEmpty() || name.getText().isEmpty()) && !acceptButton.isSelected())
            warningText.setText("Proszę uzupełnić wszystkie dane oraz zaakceptować regulamin.");
        else if(phoneNumber.getText().isEmpty() || email.getText().isEmpty() || lastName.getText().isEmpty() || name.getText().isEmpty())
            warningText.setText("Proszę uzupełnić wszystkie dane.");
        else if(!acceptButton.isSelected())
            warningText.setText("Proszę zaakceptować regulamin.");
        else {
            saveBoughtSeats();
            endTransaction();
        }
    }

    private void saveBoughtSeats(){
       WriteDataToFile writeDataToFile= new WriteDataToFile(fileName);
        for (String selectedSeat : selectedSeats) {
            writeDataToFile.addReservation(new Reservation(selectedSeat, name.getText(), lastName.getText(), email.getText(), phoneNumber.getText()));
        }
       writeDataToFile.reservationsToFile(fileName);
    }

    private void endTransaction(){
        Stage stage = (Stage) buyButton.getScene().getWindow();
        try {
            stage.setScene(new Scene((FXMLLoader.load(getClass().getResource("/ThanksScreen.fxml")))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {
        changeScene();
    }

    private void changeScene(){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SeatSelect.fxml"));
        Parent sceneSelect = null;
        try {
            sceneSelect = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene sceneSelectView = new Scene(sceneSelect);
        SeatSelectController kc = loader.getController();
        kc.movieSelect(fileName);
        Stage window = (Stage) (buyButton.getScene().getWindow());
        window.setScene(sceneSelectView);
        window.show();
    }
}
