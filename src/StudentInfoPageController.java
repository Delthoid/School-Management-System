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
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
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
    @FXML Button closeButton;
    
    public static String fullName = "";
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Adding icons to buttons
        editButton.setGraphic(new ImageView("icons/edit.png"));
        archiveButton.setGraphic(new ImageView("icons/archive.png"));
        closeButton.setGraphic(new ImageView("icons/clear.png"));
        
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
        
        fullNameLabel.setText(profile.getLastName() + ", " + profile.getFirstName() + " " + profile.getmInitial());
        idLabel.setText(profile.getId() + "");
        ageLabel.setText(profile.getAge() + " years old");
        emailLabel.setText(profile.getEmail());
        addressLabel.setText(profile.getAddress());
    }
    public void hello()
    {
        System.out.println("Hello World " + fullName);
    }
}
