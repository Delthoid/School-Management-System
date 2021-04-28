/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;


public class MainMenuController implements Initializable {

    @FXML
    public Pane mainPanel;
    
    @FXML public Label actLabel;
    @FXML public Button dashboardBtn;
    @FXML public Button studentsBtn;
    @FXML public Button enrollmentBtn;
    @FXML public Button adminSettingsBtn;
    @FXML public Button logoutBtn;
    
    Pane studentsPane;
    Pane dashboardPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardBtn.setGraphic(new ImageView("icons/dashboard.png"));
        studentsBtn.setGraphic(new ImageView("icons/students.png"));
        enrollmentBtn.setGraphic(new ImageView("icons/enrollment.png"));
        adminSettingsBtn.setGraphic(new ImageView("icons/admin.png"));
        logoutBtn.setGraphic(new ImageView("icons/logout.png"));
        
        dashboardBtn.getStyleClass().add("buttonSidebarActive");
        try
        {
            studentsPane =  FXMLLoader.load(getClass().getResource("Students.fxml"));
            dashboardPane =  FXMLLoader.load(getClass().getResource("DashboardPage.fxml"));
            
            loadStudents();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
    }
    public void loadDashboard() throws Exception
    {
        mainPanel.getChildren().setAll(dashboardPane);
        studentsBtn.getStyleClass().removeAll("buttonSidebarActive");
        studentsBtn.getStyleClass().add("buttonInactive");
        dashboardBtn.getStyleClass().add("-fx-font-weight: bold");
        dashboardBtn.getStyleClass().add("buttonSidebarActive");
        
    }

    public void loadStudents()throws Exception
    {
        mainPanel.getChildren().setAll(studentsPane);
        dashboardBtn.getStyleClass().removeAll("buttonSidebarActive");
        dashboardBtn.getStyleClass().add("buttonInactive");
        studentsBtn.getStyleClass().add("-fx-font-weight: bold");
        studentsBtn.getStyleClass().add("buttonSidebarActive");
        
    }
    
    public void loadAdminSettings()
    {
        //actLabel.setText("Admin Settings");
    }
    
    public void loadEnrollment()
    {
        //actLabel.setText("Enrollment");
    }
}
