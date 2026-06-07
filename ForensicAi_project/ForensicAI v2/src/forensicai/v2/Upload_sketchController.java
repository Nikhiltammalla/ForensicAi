/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package forensicai.v2;

import com.mycompany.forensicai_facematch.face_rekognition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Akash Sahu
 */
public class Upload_sketchController implements Initializable {

    private String sketchPath;

    public void setSketchPath(String path) {
        this.sketchPath = path;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void openFaceRecognition(ActionEvent event) {

        try {

            System.out.println("OPENING FACE RECOGNITION WITH SKETCH PATH: " + sketchPath);

            face_rekognition recognition = new face_rekognition(sketchPath);

            recognition.setVisible(true);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    @FXML
    private void onBack(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("menu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("New Window");
            stage.setScene(scene);
            stage.resizableProperty().setValue(false); //Disable maximize button
            stage.show();
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}