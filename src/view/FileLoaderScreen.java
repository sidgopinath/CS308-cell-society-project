package view;

import java.io.File;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class FileLoaderScreen {

    final FileChooser fileChooser;
    Stage myStage;
    Scene myScene;

    public FileLoaderScreen(Stage stage){
        fileChooser = new FileChooser();
        myStage = stage;

    }

    public Node getNode() {
        // Modeled after JavaFX doc example 
        // http://docs.oracle.com/javafx/2/ui_controls/file-chooser.htm

        final Button fileOpenButton = new Button("Load XML file");
        fileOpenButton.setOnAction(e->getFile());
        final GridPane chooserGridPane = new GridPane();

        GridPane.setConstraints(fileOpenButton, 0, 1);
        chooserGridPane.setHgap(6);
        chooserGridPane.setVgap(6);
        chooserGridPane.getChildren().add(fileOpenButton);

        final Pane root = new VBox(12);
        root.setPadding(new Insets(12, 12, 12, 12));
        return root;
    }

    public File getFile() {
        configureFileChooser(fileChooser);
        File file = fileChooser.showOpenDialog(myStage);
        return file;
    }

    private static void configureFileChooser(final FileChooser fileChooser){                           
        fileChooser.setTitle("Choose XML settings file");
        fileChooser.setInitialDirectory(
                                        new File(System.getProperty("user.dir"))
                ); 
        fileChooser.getExtensionFilters().addAll(
                                                 new FileChooser.ExtensionFilter("XML", "*.xml"),
                                                 new FileChooser.ExtensionFilter("All Files ", "*.*")
                );

    }
}
