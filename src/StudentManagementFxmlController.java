/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.sql.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.*;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 *
 * @author delth
 */
public class StudentManagementFxmlController implements Initializable {
    
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    
    private static ArrayList<String> empNames = new ArrayList<String>();
    private static ArrayList<String> empPassw = new ArrayList<String>();
    
    @FXML
    private Label label;
    
    @FXML
    private TextField usernameField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Button loginButton;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @FXML
    private void login(ActionEvent e)throws Exception
    {
        if(connect_status())
        {
            System.out.println(empNames + "\n" + empPassw);
            if(empNames.contains(usernameField.getText()))
            {
                for(String uname: empNames)
                {
                    if(usernameField.getText().equals(uname))
                    {
                        if(passwordField.getText().equals(empPassw.get(empNames.indexOf(uname))))
                        {
                            Parent root = FXMLLoader.load(getClass().getResource("MainMenu.fxml"));
                            Stage stage = new Stage();
                            Scene scene = new Scene(root);
                            
                            /*
                            Screen screen = Screen.getPrimary();
                            Rectangle2D bounds = screen.getVisualBounds();
                            
                            stage.setX(bounds.getMinX());
                            stage.setY(bounds.getMinY());
                            stage.setWidth(bounds.getWidth());
                            stage.setHeight(bounds.getHeight());
                            */
                            
                            stage.setScene(scene);
                            stage.setMaximized(true);
                            stage.show();
                            
                            Stage curr_stage = (Stage) loginButton.getScene().getWindow();
                            curr_stage.close();
                        }
                        else
                        {
                            Alert alert = new Alert(AlertType.ERROR);
                            alert.setHeaderText("Wrong username or password");

                            alert.showAndWait();
                        }
                    }
                }
            }
            else
            {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setHeaderText("Username doesn't exist");

                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Cant connect to database");
            alert.setHeaderText("asdasd");
            
            alert.showAndWait();
            
            passwordField.setText(""); 
            usernameField.requestFocus();
        }
    }
    
    private boolean connect_status() throws Exception
    {
        boolean con_stat = false;
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM tbl_employee");
            
            while(rs.next())
            {
                if(empNames.contains(rs.getString(2)) && empPassw.contains(rs.getString(3)))
                {
                    
                }
                else
                {
                    empNames.add(rs.getString(2));
                    empPassw.add(rs.getString(3));
                }
                
            }
            //The data on database has been recorded!, closing the connection
            conn.close();
            con_stat = true;
        }
        catch(Exception ex)
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("There was an error");
            alert.setHeaderText("There was an error when connecting to database");
            alert.setContentText("Error message: \n" + ex);
            
            alert.showAndWait();
            
            con_stat = false;
        }
        return con_stat;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
