/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.Optional;
import javafx.fxml.FXML;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;


public class MainMenuController implements Initializable {

    @FXML
    public Pane mainPanel;
    
    @FXML public Label actLabel;
    @FXML public Button dashboardBtn;
    @FXML public Button studentsBtn;
    @FXML public Button enrollmentBtn;
    @FXML public Button adminSettingsBtn;
    @FXML public Button logoutBtn;
    @FXML public Button attendanceBtn;
    @FXML public Button enrolleeQueueBtn;
    
    @FXML VBox buttonsPane;
    
    Pane studentsPane;
    Pane dashboardPane;
    Pane enrollmentPane;
    Pane enrollmentQueue;
    
    private boolean clickedEnrollment = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dashboardBtn.setGraphic(new ImageView("icons/dashboard.png"));
        studentsBtn.setGraphic(new ImageView("icons/students.png"));
        enrollmentBtn.setGraphic(new ImageView("icons/enrollment.png"));
        adminSettingsBtn.setGraphic(new ImageView("icons/admin.png"));
        logoutBtn.setGraphic(new ImageView("icons/logout.png"));
        attendanceBtn.setGraphic(new ImageView("icons/calenda.png"));
        enrolleeQueueBtn.setGraphic(new ImageView("icons/queue_temp.png"));
        
        dashboardBtn.getStyleClass().add("buttonSidebarActive");
        try
        {
            dashboardPane =  FXMLLoader.load(getClass().getResource("DashboardPage.fxml"));
            studentsPane =  FXMLLoader.load(getClass().getResource("Students.fxml"));
            enrollmentPane = FXMLLoader.load(getClass().getResource("EnrollmentPage.fxml"));
            enrollmentQueue = FXMLLoader.load(getClass().getResource("EnrollmentQueue.fxml"));
            
            loadEnrollmentQ();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
            
    }
    public void loadDashboard() throws Exception
    {
        if(clickedEnrollment == true)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Leave?");
            alert.setHeaderText("Unsaved changes will be lost. Are you sure you want to leave?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                mainPanel.getChildren().setAll(dashboardPane);
                for(Object o: buttonsPane.getChildren())
                {
                    if(o.getClass().toString().contains("Button")){
                        Button btn = (Button) o;
                        btn.getStyleClass().removeAll("buttonSidebarActive");
                        btn.getStyleClass().add("buttonInactive");
                    }
                }
                dashboardBtn.getStyleClass().add("-fx-font-weight: bold");
                dashboardBtn.getStyleClass().add("buttonSidebarActive");
                clickedEnrollment = false;
            } 
            else {
                // ... user chose CANCEL or closed the dialog
            }
        }
        else
        {
            mainPanel.getChildren().setAll(dashboardPane);
            for(Object o: buttonsPane.getChildren())
            {
                if(o.getClass().toString().contains("Button")){
                    Button btn = (Button) o;
                    btn.getStyleClass().removeAll("buttonSidebarActive");
                    btn.getStyleClass().add("buttonInactive");
                }
            }
            dashboardBtn.getStyleClass().add("-fx-font-weight: bold");
            dashboardBtn.getStyleClass().add("buttonSidebarActive");   
        }
    }

    public void loadStudents()throws Exception
    {
        if(clickedEnrollment == true)
        {
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Leave?");
            alert.setHeaderText("Unsaved changes will be lost. Are you sure you want to leave?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK)
            {
                mainPanel.getChildren().setAll(studentsPane);
                for(Object o: buttonsPane.getChildren())
                {
                    if(o.getClass().toString().contains("Button")){
                        Button btn = (Button) o;
                        btn.getStyleClass().removeAll("buttonSidebarActive");
                        btn.getStyleClass().add("buttonInactive");
                    }
                }
                studentsBtn.getStyleClass().add("-fx-font-weight: bold");
                studentsBtn.getStyleClass().add("buttonSidebarActive");
                clickedEnrollment = false;
            } 
            else {
            }
        }
        else
        {
            mainPanel.getChildren().setAll(studentsPane);
            for(Object o: buttonsPane.getChildren())
            {
                if(o.getClass().toString().contains("Button")){
                    Button btn = (Button) o;
                    btn.getStyleClass().removeAll("buttonSidebarActive");
                    btn.getStyleClass().add("buttonInactive");
                }
            }

            studentsBtn.getStyleClass().add("-fx-font-weight: bold");
            studentsBtn.getStyleClass().add("buttonSidebarActive");   
        }
    }

    public void loadEnrollment()
    {
        try
        {
            EnrollmentPageController enPane = new EnrollmentPageController();
            enPane.generateId();
            mainPanel.getChildren().setAll(enrollmentPane);
            clickedEnrollment = true;
        
        for(Object o: buttonsPane.getChildren())
        {
            if(o.getClass().toString().contains("Button")){
                Button btn = (Button) o;
                btn.getStyleClass().removeAll("buttonSidebarActive");
                btn.getStyleClass().add("buttonInactive");
            }
        }
        enrollmentBtn.getStyleClass().addAll("-fx-font-weight: bold");
        enrollmentBtn.getStyleClass().add("buttonSidebarActive");   
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    //Load enrollment queue
    public void loadEnrollmentQ()
    {
        try
        {
            if(clickedEnrollment == true)
            {
                Alert alert = new Alert(AlertType.CONFIRMATION);
                alert.setTitle("Leave?");
                alert.setHeaderText("Unsaved changes will be lost. Are you sure you want to leave?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK)
                {
                    mainPanel.getChildren().setAll(enrollmentQueue);
                    for(Object o: buttonsPane.getChildren())
                    {
                        if(o.getClass().toString().contains("Button")){
                            Button btn = (Button) o;
                            btn.getStyleClass().removeAll("buttonSidebarActive");
                            btn.getStyleClass().add("buttonInactive");
                        }
                    }
                    studentsBtn.getStyleClass().add("-fx-font-weight: bold");
                    studentsBtn.getStyleClass().add("buttonSidebarActive");
                    clickedEnrollment = false;
                } 
                else {
                }
            }
            else
            {
                mainPanel.getChildren().setAll(enrollmentQueue);
                for(Object o: buttonsPane.getChildren())
                {
                    if(o.getClass().toString().contains("Button")){
                        Button btn = (Button) o;
                        btn.getStyleClass().removeAll("buttonSidebarActive");
                        btn.getStyleClass().add("buttonInactive");
                    }
                }

                enrolleeQueueBtn.getStyleClass().add("-fx-font-weight: bold");
                enrolleeQueueBtn.getStyleClass().add("buttonSidebarActive");   
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void loadAdminSettings()
    {
        //actLabel.setText("Admin Settings");
    }
}
