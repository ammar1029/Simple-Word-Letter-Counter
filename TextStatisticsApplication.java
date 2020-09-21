package textstatistics;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import java.util.Arrays;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.layout.*;

public class TextStatisticsApplication extends Application {

    @Override
    public void start(Stage window) {
        TextArea textArea = new TextArea();
        Label numberOfCharacters = new Label("Characters: 0");
        Label numberOfWords = new Label("Words: 0");
        Label longestWord = new Label("The longest word is: ");

        textArea.textProperty().addListener(new ChangeListener<String>() {
            public void changed(ObservableValue<? extends String> change,
                    String oldValue, String newValue) {

                String[] parts = newValue.split(" ");
                int words;
                if (parts[0].equals("")) {
                    words = 0;
                } else {
                    words = parts.length;
                }
                int characters = Arrays.stream(parts)
                        .map(value -> value.length())
                        .reduce(0, (subtotal, element) -> subtotal + element);

                String longest = Arrays.stream(parts)
                        .sorted((s1, s2) -> s2.length() - s1.length())
                        .findFirst()
                        .get();

                numberOfCharacters.setText("Characters: " + String.valueOf(characters));
                numberOfWords.setText("Words: " + String.valueOf(words));
                longestWord.setText("The longest word is: " + longest);
            }
        });

        HBox bottomComponentGroup = new HBox();
        bottomComponentGroup.getChildren().addAll(numberOfCharacters, numberOfWords, longestWord);
        bottomComponentGroup.setSpacing(10);

        BorderPane ComponentGroup = new BorderPane();

        ComponentGroup.centerProperty().setValue(textArea);
        ComponentGroup.bottomProperty().setValue(bottomComponentGroup);

        Scene view = new Scene(ComponentGroup);

        window.setScene(view);
        window.show();
    }

    public static void main(String[] args) {
        launch(TextStatisticsApplication.class);
    }

}
