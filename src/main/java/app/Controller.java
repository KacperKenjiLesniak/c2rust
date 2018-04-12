package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

public class Controller {

    ObservableList<String> unknownExpressionsList = FXCollections.observableArrayList("Ignore", "Copy");

    @FXML
    private ChoiceBox<String> unknownExpressions;

    @FXML
    private void initialize(){
        unknownExpressions.setValue("Unknown Expresions");
        unknownExpressions.setItems(unknownExpressionsList);
        unknownExpressions.getSelectionModel().selectFirst();
    }

}
