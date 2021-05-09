/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author delth
 */
public class CustomDialogController implements Initializable {

    String message = "";
    @FXML public Label m;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    public void setMessage(String message)
    {
        this.message = message;
        
    }
}
