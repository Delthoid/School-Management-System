/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class StudentsController implements Initializable {

    @FXML
    private ComboBox sectBox;
    
    @FXML
    private Button b;
    
    @FXML
    private Label studentsLabel;
    
    @FXML
    private Label studentsCountLabel;
   
    @FXML
    public TableView<StudentModel> tableView;
    
    @FXML
    public TableColumn<StudentModel, Integer> studentIdCol;
    
    @FXML
    public TableColumn<StudentModel, String> firstNameCol;
    
    @FXML
    public TableColumn<StudentModel, String> lastNameCol;
    
    @FXML private Slider viewSlider;
    
    ObservableList<StudentModel> data = FXCollections.observableArrayList();
    StudentsList sList = new StudentsList();
    
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    
    public ArrayList<String> sections = new ArrayList<String>();

    public StudentsController() {
        //this.tableView = new TableView<>();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadSection(); 
        resetTable();
        
        ConnectDB connect = new ConnectDB();
        connect.connect("SELECT * FROM tbl_students", "LOAD STUDENTS");
        
        loadTable();
    }
    public void resetTable()
    {
        sList.sections.removeAll(sList.sections);
        sList.studentsId.removeAll(sList.studentsId);
        sList.firstNames.removeAll(sList.firstNames);
        sList.lastNames.removeAll(sList.lastNames);
        
        data.clear();
        tableView.getItems().clear();
    }
    public void loadTable()
    {
        EntriesCount eCount = new EntriesCount();
        int c = eCount.totalSelectedStudents;
        //Call connect to db class
        for(int i = 0; i < sList.studentsId.size(); i++)
        {
            data.add(new StudentModel(sList.firstNames.get(i), sList.lastNames.get(i), sList.studentsId.get(i), sList.sections.get(i), sList.yearLevels.get(i), sList.contacts.get(i)));
        }
        //Create columns
        TableColumn studentIdCol = new TableColumn("Student ID");
        studentIdCol.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("StudentId"));
        studentIdCol.prefWidthProperty().bind(tableView.widthProperty().multiply(0.3));
        
        TableColumn firstNameCol = new TableColumn("First Name");
        firstNameCol.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("FirstName"));
        
        TableColumn lastNameCol = new TableColumn("Last Name");
        lastNameCol.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("LastName"));
        
        TableColumn contactCol = new TableColumn("Contact Number");
        contactCol.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("Contact"));
        
        TableColumn sectionCol = new TableColumn("Section");
        sectionCol.setCellValueFactory(new PropertyValueFactory<StudentModel, String>("Section"));
        
        TableColumn yearLevelCol = new TableColumn("Year Level");
        yearLevelCol.setCellValueFactory(new PropertyValueFactory<StudentModel, Integer>("YearLevel"));
        
        studentsCountLabel.setText(c + " Student(s)");
        
        //Adding data to the table
        tableView.getItems().clear();
        tableView.getItems().addAll(data);
        tableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        tableView.getColumns().clear();
        tableView.getColumns().addAll(studentIdCol, firstNameCol, lastNameCol, contactCol, sectionCol, yearLevelCol);
        
        System.out.println(data);
    }
    public void loadSection()
    {
        Connection conn = null;
        String query = "SELECT section FROM tbl_students";
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            //ResultSet rs2 = stmt.executeQuery("SELECT EXISTS(" + query + ")"); //Used for validating query if the item that looking for is existing or not on the selected Table ehe
            ResultSet rs = stmt.executeQuery(query);
            
            while(rs.next())
            {
                if(sections.contains(rs.getString(1))){  
                }
                else
                    sections.add(rs.getString(1));
            }
            conn.close();
        }
        catch(Exception ex)
        {
            System.out.println("Error students controller " + ex);
        }
        for(String i: sections)
        {
            sectBox.getItems().add(i);
        }
        System.out.println(sections);
    }
    public void clearFilter()
    {
        resetTable();
        System.out.println(data);
        System.out.println(tableView);
        sectBox.getSelectionModel().clearSelection();
    }
    @FXML
    public void select(MouseEvent event)
    {
        if(event.getClickCount() == 2)
        {
            //System.out.println(tableView.getSelectionModel().getSelectedItem().getFirstName());
            String i = tableView.getSelectionModel().getSelectedItem().getFirstName();
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText(i);

            alert.showAndWait();
        }
    }
    public void selectSection()
    {
        EntriesCount eCount = new EntriesCount();
        int c = eCount.totalSelectedStudents;
        studentsLabel.setText(sectBox.getValue().toString());
        System.out.println(sectBox.getValue());
        
        resetTable();
        //Call connect to db class
        ConnectDB connect = new ConnectDB();
        connect.connect("SELECT * FROM tbl_students WHERE section = '" + sectBox.getValue().toString() + "'", "LOAD STUDENTS SECTION");
        studentsCountLabel.setText(c + " Student(s)");
        loadTable();
    }
    public void adjustTableView()
    {
        Double v = viewSlider.getValue();
        int sliderValue = (int) Math.round(v);
        tableView.setFixedCellSize(v + 10);
        System.out.println(sliderValue);
    }
}
