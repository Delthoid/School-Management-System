/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;



public class EnrollmentQueueController implements Initializable {

    @FXML VBox queuePane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadQueue();
    }
    
    public void loadQueue()
    {
        ConnectDB db = new ConnectDB();
        TableView table = db.loadEnrollmentQueue();
        
        queuePane.getChildren().removeAll(table);
        queuePane.getChildren().addAll(table);
    }
}
