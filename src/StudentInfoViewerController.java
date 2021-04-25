/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.*;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author delth
 */
public class StudentInfoViewerController implements Initializable {

    /*
    WHEN CALLING CONNECTDB CALL, WE SHOULD INCLUDE WHAT TYPE OF QUERY IT IS
    
    */
    
    @FXML
    private TextField searchBox;
    
    //Labels
    @FXML public static Label fullnameLabel;
    @FXML public static Label idLabel;
    @FXML public static Label ageLabel;
    @FXML public static Label emailLabel;
    @FXML public static Label addressLabel;
    
    //Student Data
    public String fullName = "";
    
    public static boolean exist = false;
    public static int currentStudentId = 0;
    
    public static ArrayList<String> studentInfo = new ArrayList<String>();
    
    public void enterSearch(KeyEvent e)
    {
        try
        {
            int search_input = Integer.parseInt(searchBox.getText());
            if(e.getCode() == KeyCode.ENTER)
            {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Enter oressed");
                a.showAndWait();
                
                ConnectDB cDB = new ConnectDB();
                cDB.connect("SELECT EXISTS(SELECT * FROM tbl_students WHERE student_id = " + search_input + ")", "CHECK EXIST");
                if(exist == true)
                {
                    cDB.connect("SELECT * FROM tbl_students WHERE student_id = " + search_input, "STUDENT INFO");
                    
                    fullnameLabel.setText(studentInfo.get(3) + ", " + studentInfo.get(1) + " " + studentInfo.get(2).charAt(0) + ".");
                    idLabel.setText(studentInfo.get(0));
                    emailLabel.setText(studentInfo.get(7));
                    addressLabel.setText(studentInfo.get(15));
                }
                else if(exist == false)
                {
                    Alert alert = new Alert(AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Student ID not found!");
                    alert.setContentText("");

                    alert.showAndWait();
                }
                System.out.println();
            }
        }
        catch(NumberFormatException ne)
        {
            if(e.getCode() == KeyCode.ENTER)
            {
                Alert a = new Alert(AlertType.INFORMATION);
                a.setTitle("Invalid input");
                a.setContentText("Not ID detected");
                a.showAndWait();
            }
           
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        idLabel.setText(currentStudentId + "");
    }    
    
}
