
/*
 * Orlando Camacho
 * CSD-420 Advanced Java Programming
 * Assignment 1.3 - JavaFX Random Playing Cards
 * October 25, 2026
 *
 * Description:
 * Displays four randomly selected cards from a
 * standard 52-card deck. The Refresh button uses
 * a lambda expression to display a new selection.
 *
 * GitHub:
 * https://github.com/AstroidDestroyerAdmin/csd-420
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RandomCards extends Application {

    private final HBox cardBox = new HBox(15);
    private List<Integer> previousCards = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        cardBox.setAlignment(Pos.CENTER);

        Button refreshButton = new Button("Refresh Cards");

        // Lambda expression for the Refresh button
        refreshButton.setOnAction(event -> displayCards());

        VBox root = new VBox(25, cardBox, refreshButton);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(25));

        displayCards();

        Scene scene = new Scene(root, 650, 300);

        primaryStage.setTitle(
            "CSD-420 Assignment 1.3 - Random Playing Cards"
        );
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void displayCards() {

        List<Integer> deck = new ArrayList<>();

        for (int i = 1; i <= 52; i++) {
            deck.add(i);
        }

        Collections.shuffle(deck);

        List<Integer> selectedCards =
            new ArrayList<>(deck.subList(0, 4));

        // Ensure the next display differs from the previous
        while (selectedCards.equals(previousCards)) {
            Collections.shuffle(deck);
            selectedCards =
                new ArrayList<>(deck.subList(0, 4));
        }

        previousCards = new ArrayList<>(selectedCards);

        cardBox.getChildren().clear();

        for (int cardNumber : selectedCards) {

            File cardFile = new File(
                "cards/" + cardNumber + ".png"
            );

            Image image = new Image(
                cardFile.toURI().toString()
            );

            ImageView cardView = new ImageView(image);
            cardView.setFitWidth(120);
            cardView.setFitHeight(175);
            cardView.setPreserveRatio(true);

            cardBox.getChildren().add(cardView);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
