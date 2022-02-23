import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;


import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller {
    @FXML
    VBox leftPanel, rightPanel;


    public void btnExit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void btnCopy(ActionEvent actionEvent) {
        PanelControl leftPC = (PanelControl) leftPanel.getProperties().get("ctrl");
        PanelControl rightPC = (PanelControl) rightPanel.getProperties().get("ctrl");

        if(leftPC.getSelectedFileName() == null && rightPC.getSelectedFileName() == null){
            Alert alert = new Alert(Alert.AlertType.ERROR, "File has been no choose ", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        PanelControl srcPC = null,
                     distPC = null;

        if(leftPC.getSelectedFileName() != null){
            srcPC = leftPC;
            distPC = rightPC;
        }
        if(rightPC.getSelectedFileName() != null){
            srcPC = rightPC;
            distPC = leftPC;
        }


        Path srcPath = Paths.get(srcPC.getCurrentPath(), srcPC.getSelectedFileName());
        Path distPath = Paths.get(distPC.getCurrentPath()).resolve(srcPath.getFileName().toString());
        try {
            Files.copy(srcPath, distPath);
            distPC.updateList(Paths.get(distPC.getCurrentPath()));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Can't copy this is file", ButtonType.OK);
            alert.showAndWait();
        }

    }
}
