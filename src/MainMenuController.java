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
import javafx.scene.layout.Pane;


public class MainMenuController implements Initializable {

    @FXML
    public Pane mainPanel;
    
    @FXML public Label actLabel;
    @FXML public Button dashboardBtn;
    @FXML public Button studentInfoBtn;
    @FXML public Button studentsBtn;
    @FXML public Button enrollmentBtn;
    @FXML public Button adminSettingsBtn;
    
    Pane studentsPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardBtn.getStyleClass().add("buttonSidebarActive");
        try
        {
            studentsPane =  FXMLLoader.load(getClass().getResource("Students.fxml"));
            
            loadDashboard();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
    }
    public void loadDashboard() throws Exception
    {
        Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("DashboardPage.fxml"));
        mainPanel.getChildren().setAll(newLoadedPane);
        
        dashboardBtn.getStyleClass().add("buttonSidebarActive");
        
        studentsBtn.getStyleClass().remove("buttonSidebarActive");
        studentInfoBtn.getStyleClass().remove("buttonSidebarActive");
    }
    
    public void loadStudentInfoViwer()
    {
        try
        {
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("StudentInfoViewer.fxml"));
            mainPanel.getChildren().setAll(newLoadedPane);

            studentInfoBtn.getStyleClass().add("buttonSidebarActive");

            studentsBtn.getStyleClass().remove("buttonSidebarActive");
            dashboardBtn.getStyleClass().remove("buttonSidebarActive");
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    public void loadStudents()throws Exception
    {
        mainPanel.getChildren().setAll(studentsPane);
        
        studentsBtn.getStyleClass().add("buttonSidebarActive");
        
        studentInfoBtn.getStyleClass().remove("buttonSidebarActive");
        dashboardBtn.getStyleClass().remove("buttonSidebarActive");
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
