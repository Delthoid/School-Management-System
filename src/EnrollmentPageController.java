/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class EnrollmentPageController implements Initializable {

    public static int generatedId;
    
    @FXML 
    public Label generatedIdLabel;
    
    @FXML TextField fname;
    @FXML TextField mname;
    @FXML TextField lname;
    @FXML DatePicker datePicker;
    @FXML ComboBox gender;
    @FXML TextField contact;
    @FXML TextField email;
    @FXML TextArea address;
    @FXML TextField guardian;
    @FXML TextField occupation;
    @FXML TextField gcontact;
    @FXML TextField relationship;
    @FXML ComboBox year;
    @FXML ComboBox section;
    @FXML ComboBox availableCourse;
    
    @FXML GridPane form;
    
    @FXML ImageView imageView;
    @FXML Label test;
    
    public static String path;
    
    ArrayList<String> sections = new ArrayList<String>();
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        generateId();
        generatedIdLabel.setText(String.valueOf(generatedId));
        loadSection();
        loadCourses();
        loadYearLevels();
        gender.getItems().add("Male");
        gender.getItems().add("Female");
        
        // set a clip to apply rounded border to the original image.
        Rectangle clip = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        
        //s
       //Snapshop thr rounded image
       SnapshotParameters parameters = new SnapshotParameters();
       parameters.setFill(Color.TRANSPARENT);
       WritableImage image = imageView.snapshot(parameters, null);
       
       //remove the rounding clip so that opur effect can show trough
       imageView.setClip(null);
       
       // store the rounded image in the imageView.
       imageView.setImage(image);
    }    
    //The generated ID will be based on the current record, and will be next to the last record
    public void generateId()
    {
        ConnectDB conn = new ConnectDB();
        conn.connect("SELECT * from tbl_students ORDER by student_id DESC LIMIT 1", "GENERATE ID");
    }
    public void loadSection()
    {
        ConnectDB conn = new ConnectDB();
        ArrayList<String> c = conn.loadSections();
        for(int i = 0; i < c.size(); i++)
        {
            section.getItems().add(c.get(i));
        }
    }
    
    public void loadCourses()
    {
        ConnectDB conn = new ConnectDB();
        ArrayList<String> c = conn.loadCourses();
        for(int i = 0; i < c.size(); i++)
        {
            availableCourse.getItems().add(c.get(i));
        }
    }
    
    public void loadYearLevels()
    {
        ConnectDB conn = new ConnectDB();
        ArrayList<String> c = conn.loadYearLevels();
        for(int i = 0; i < c.size(); i++)
        {
            year.getItems().add(c.get(i));
        }
    }
    
    //method for submitting form/application
    public void submitApplication()
    {
        boolean valid = false;
        for(Object o: form.getChildren())
        {
            //Check if all textfields in GridPane(form) contains value
            if(o.getClass().toString().contains("TextField"))
            {
                TextField tf = (TextField) o;
                if(tf.getText().length() == 0)
                {
                    //Empty field
                    valid = false;
                    break;
                }
                else
                    valid = true;
            }
            else if(o.getClass().toString().contains("ComboBox"))
            {
                ComboBox cb = (ComboBox) o;
                if(cb.getSelectionModel().isEmpty())
                {
                    //Empty option
                    valid = false;
                    break;
                }
                else
                    valid = true;
            }
        }
        if(valid == true)
        {
            ConnectDB db = new ConnectDB();
            long m = System.currentTimeMillis();
            java.sql.Date date = new java.sql.Date(m);
            Date bday = Date.valueOf(datePicker.getValue());
            db.toEnrollmentQueue(
                    fname.getText(), mname.getText(), lname.getText(),
                    bday, gender.getSelectionModel().getSelectedItem().toString(), Integer.parseInt(contact.getText()),
                    email.getText(), guardian.getText(), occupation.getText(),
                    Integer.parseInt(gcontact.getText()), relationship.getText(), year.getSelectionModel().getSelectedIndex(),
                    section.getSelectionModel().getSelectedItem().toString(), address.getText(), path, 
                    date
            );
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("Enrollment for has been submitted");
            alert.setContentText("Kindly wait for the approval of this student's application.");
            alert.showAndWait();
            
            System.out.println((LocalDate)datePicker.getValue());
            System.out.println(datePicker.getValue());
            System.out.println(java.sql.Date.valueOf(datePicker.getValue()));
        }
        else if(valid == false)
        {
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Incomplete information");
            alert.setHeaderText("Please complete all fields");
            alert.showAndWait();
        }
    }
    
    public void uploadPicture()
    {
        try
        {
            FileChooser fil_chooser = new FileChooser();
            fil_chooser.setTitle("Choose picture");
            fil_chooser.getExtensionFilters().add(new ExtensionFilter("Image files", "*.png", "*.jpeg", "*jpg"));
            File f = fil_chooser.showOpenDialog((Stage)form.getScene().getWindow());
            if(f != null)
            {
                path = f.getAbsolutePath().toString();
                test.setText(f.getAbsolutePath() + " selected");
                Image image = new Image(f.toURI().toString());
                imageView.setImage(image);
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
