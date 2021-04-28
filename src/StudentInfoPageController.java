/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.scene.shape.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author delth
 */
public class StudentInfoPageController implements Initializable {

    @FXML
    Label fullNameLabel;
    @FXML Label idLabel;
    @FXML Label ageLabel;
    @FXML Label emailLabel;
    @FXML Label addressLabel;
    
    @FXML TextField idField;
    @FXML TextField lastNameField;
    @FXML TextField firstNameField;
    @FXML TextField middleNameField;
    @FXML TextField emailField;
    @FXML TextField addressField;
    @FXML TextField contactField;
    @FXML TextField yearLevelField;
    @FXML TextField sectionField;
    @FXML TextField enrolledField;
    @FXML TextField guardianField;
    
    @FXML ImageView imageView;
    
    @FXML Button editButton;
    @FXML Button archiveButton;
    @FXML Button cancelButton;
    @FXML Button updateButton;
    
    @FXML HBox buttonsLoc;
    
    @FXML VBox fieldsHolder;
    @FXML VBox fieldsHolderB;
    @FXML VBox fieldsHolderC;
    
    public static String fullName = "";
    private int targetId;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Adding icons to buttons
        editButton.setGraphic(new ImageView("icons/edit.png"));
        archiveButton.setGraphic(new ImageView("icons/archive.png"));
        updateButton.setGraphic(new ImageView("icons/update.png"));
        
        //Hide these buttons at startup
        buttonsLoc.getChildren().remove(updateButton);
        buttonsLoc.getChildren().remove(cancelButton);
        
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
       
       loadDetails();
    }    
    public void loadDetails()
    {
        SelectedProfile profile = new SelectedProfile();
        
        //Label
        fullNameLabel.setText(profile.getLastName() + ", " + profile.getFirstName() + " " + profile.getMiddleName().charAt(0));
        idLabel.setText(profile.getId() + "");
        ageLabel.setText(profile.getAge() + " years old");
        emailLabel.setText(profile.getEmail());
        addressLabel.setText(profile.getAddress());
        
        //Fields
        idField.setText(profile.getId() + "");
        lastNameField.setText(profile.getLastName());
        firstNameField.setText(profile.getFirstName());
        middleNameField.setText(profile.getMiddleName());
        emailField.setText(profile.getEmail());
        addressField.setText(profile.getAddress());
        contactField.setText(profile.getContact() + "");
        yearLevelField.setText(profile.getYear() + "");
        sectionField.setText(profile.getSection());
        enrolledField.setText(profile.getEnrolledDate());
        guardianField.setText(profile.getGuardian());
    }
    public void handleUpdate()
    {
        ConnectDB conn = new ConnectDB();
        
        SelectedProfile profile = new SelectedProfile();
        targetId = profile.getId();
        
        /*
         public void update(
            int id,
            String lname, String fname, String mname,
            String email, String address, int contact,
            int level, String section, String enDate,
            String guardian
    )
        */
        
        conn.update(
                targetId,
                lastNameField.getText(), firstNameField.getText(), middleNameField.getText(),
                emailField.getText(), addressField.getText(), Integer.parseInt(contactField.getText()),
                Integer.parseInt(yearLevelField.getText()), sectionField.getText(), enrolledField.getText(),
                guardianField.getText()
        );
    }
    public void handleEditBtn()
    {
        buttonsLoc.getChildren().add(cancelButton);
        buttonsLoc.getChildren().add(updateButton);
        
        buttonsLoc.getChildren().remove(editButton);
        buttonsLoc.getChildren().remove(archiveButton);
        
        //Get all the textFields on VBox
        for(Object o: fieldsHolder.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(true);
                tf.getStyleClass().removeAll("textBox");
                tf.getStyleClass().add("textBoxEditMode");
            }
        }
        for(Object o: fieldsHolderB.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(true);
                tf.getStyleClass().removeAll("textBox");
                tf.getStyleClass().add("textBoxEditMode");
            }
        }
        for(Object o: fieldsHolderC.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(true);
                tf.getStyleClass().removeAll("textBox");
                tf.getStyleClass().add("textBoxEditMode");
            }
        }
    }
    public void handleCancelBtn()
    {
        buttonsLoc.getChildren().remove(cancelButton);
        buttonsLoc.getChildren().remove(updateButton);
        
        buttonsLoc.getChildren().add(editButton);
        buttonsLoc.getChildren().add(archiveButton);
        
        //Get all the textFields on VBox
        for(Object o: fieldsHolder.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(false);
                tf.getStyleClass().add("textBox");
                tf.getStyleClass().removeAll("textBoxEditMode");
            }
        }
        for(Object o: fieldsHolderB.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(false);
                tf.getStyleClass().add("textBox");
                tf.getStyleClass().removeAll("textBoxEditMode");
            }
        }
        for(Object o: fieldsHolderC.getChildren())
        {
            if(o.getClass().toString().contains("javafx.scene.control.TextField")){
                TextField tf = (TextField) o;
                tf.setEditable(false);
                tf.getStyleClass().add("textBox");
                tf.getStyleClass().removeAll("textBoxEditMode");
            }
        }
    }
    public void hello()
    {
        System.out.println("Hello World " + fullName);
    }
}
