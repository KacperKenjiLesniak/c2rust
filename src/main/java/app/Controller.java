package app;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import transpiler.Transpiler;

import java.util.HashSet;
import java.util.Set;

public class Controller {

    private Transpiler transpiler;

    public Controller() {
        this.transpiler = new Transpiler();
    }


    @FXML
    private CCodeEditor cCode;

    @FXML
    private RustCodeEditor rustCode;

    @FXML
    private Button transpileButton;

    public static Set<String> rustCodeImportString = new HashSet<>();

    public static String rustCodeStaticException = "";

    @FXML
    private void initialize() {
    }

    public void handleTranspileAction(ActionEvent actionEvent) {
        rustCodeStaticException = "";
        rustCodeImportString.clear();
        String rustCodeString = "";
        try {
            rustCodeString = transpiler.transpile(cCode.getCode());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (rustCodeStaticException.equals("")) rustCode.setCode(createImports() + "\n" + rustCodeString);
        else rustCode.setCode(rustCodeStaticException);

    }

    public String createImports() {
        StringBuilder rustImports = new StringBuilder();
        for (String rustImport : rustCodeImportString) {
            rustImports.append(rustImport);
        }
        return rustImports.toString();
    }
}
