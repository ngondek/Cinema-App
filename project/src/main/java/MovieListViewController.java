import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;

public class MovieListViewController {
    public ScrollPane scrollPane;
    @FXML
    private VBox content;

    public void initialize() throws Exception {
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        MoviesData library = ReadMoviesFromFile.readMoviesFromFile("Movies.xml");
        for (Movie s : library.getMoviesList()) {
            HBox hBox = new HBox();
            hBox.setSpacing(20);
            hBox.setPadding(new Insets(20));
            Button button = new Button();
            button.setId(s.getTitle());
            ImageView image = new ImageView();
            image.setFitHeight(180);
            image.setFitWidth(120);
            image.setImage(new Image(s.getImage()));
            button.setGraphic(image);
            button.setOnAction((e) -> {
                changeScene(button.getId());
            });
            Label label = new Label();
            label.setText(s.getTitle() + "\n" + s.getDirector() + "\n" + s.getPremiere() +
                    "\n" + s.getType() + "\n" + s.getDesription() + "\n");
            label.setMaxWidth(550);
            label.setWrapText(true);
            label.setTextAlignment(TextAlignment.JUSTIFY);
            hBox.getChildren().addAll(button, label);
            content.getChildren().add(hBox);
        }
    }

    private void changeScene(String buttonId){
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/SeatSelect.fxml"));
        Parent seatSelect = null;
        try {
            seatSelect = loader.load();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        Scene seatSelectView = new Scene(seatSelect);
        SeatSelectController kc = loader.getController();
        String movieFile = buttonId + ".xml";
        kc.movieSelect(movieFile);
        Stage window = (Stage) (content.getScene().getWindow());
        window.setScene(seatSelectView);
        window.show();
    }
}
