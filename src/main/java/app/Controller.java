package app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import transpiler.Transpiler;

import javax.xml.soap.Text;

public class Controller {

    private Transpiler transpiler;

    public Controller() {
        this.transpiler = new Transpiler();
    }

    ObservableList<String> unknownExpressionsList = FXCollections.observableArrayList("Ignore", "Copy");

    @FXML
    private ChoiceBox<String> unknownExpressions;

    @FXML
    private TextArea cCode;

    @FXML
    private TextArea rustCode;

    @FXML
    private Button transpileButton;

    public static String rustCodeStaticString = "";

    @FXML
    private void initialize(){
        unknownExpressions.setValue("Unknown Expresions");
        unknownExpressions.setItems(unknownExpressionsList);
        unknownExpressions.getSelectionModel().selectFirst();

    }

    public void handleTranspileAction(ActionEvent actionEvent) {
        String rustCodeString = transpiler.transpile(cCode.getText());
        rustCode.setText(rustCodeStaticString);
    }
}
