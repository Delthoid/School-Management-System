
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Control;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class ConnectDB {
    
    
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    public static ArrayList<Integer> student_ids = new ArrayList<Integer>();
    
    //This method is for inserting data to table
    public void enrollStudent(
            String fname, String mname, String lname,
            String bday, String gender, int contactNumber,
            String email, String guardian, String occupation,
            int gcontactNumber, String relationship, int level,
            String section, String dateEnrolled, String address
    ){
        Calendar calendar = Calendar.getInstance();
        java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "INSERT INTO tbl_students(first_name, middle_name, last_name, birthday, gender, contact_number, email, guardian, occupation, guardian_contact_number, relationship, year_level, section, date_enrolled, address) "
                     + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            ps = conn.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, mname);
            ps.setString(3, lname);
            ps.setDate(4, startDate);
            ps.setString(5, gender);
            ps.setInt(6, contactNumber);
            ps.setString(7, email);
            ps.setString(8, guardian);
            ps.setString(9, occupation);
            ps.setInt(10, gcontactNumber);
            ps.setString(11, relationship);
            ps.setInt(12, level);
            ps.setString(13, section);
            ps.setDate(14, startDate);
            ps.setString(15, address);
            
            ps.execute();
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    //This connect method also used for reaing databases
    public void update(
            int id,
            String lname, String fname, String mname,   
            String email, String address, int contact,
            int level, String section, String enDate,
            String guardian
    ){
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE tbl_students set first_name =?, middle_name=?, last_name=?, email=?, address=?, contact_number=?, year_level=?, section=?  WHERE student_id=?";
        System.out.print(query);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Sucess");
        alert.setHeaderText("Done updating" + lname + " " + fname+ "!");
        try 
        {
            conn = DriverManager.getConnection(connection, "root", "");
            ps = conn.prepareStatement(query);
            ps.setString(1, fname);
            ps.setString(2, mname);
            ps.setString(3, lname);
            ps.setString(4, email);
            ps.setString(5, address);
            ps.setInt(6, contact);
            ps.setInt(7, level);
            ps.setString(8, section);
            ps.setInt(9, id);
            ps.executeUpdate();
            System.out.println("Updates saved!");
            alert.showAndWait();
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
       
    }
        
    //Save enrollment form to enrollment queue database
    public void toEnrollmentQueue(
            String fname, String mname, String lname,
            java.util.Date birthday, String gender, int contact,
            String email, String guardian, String gOccupation, 
            int gContact, String gRelationship, int ylevel,
            String section, String address,String image_path,
            java.util.Date applicationDate
            )
    {
        Connection conn = null;
        PreparedStatement ps;
        String q = "INSERT INTO tbl_enrollmentqueue" +
                   "(first_name, middle_name, last_name, " +
                   "birthday, gender, contact_number, " +
                   "email, guardian, occupation, " +
                   "guardian_contact_number, relationship, year_level, " +
                   "section, address, image_path, " +
                   "application_date)" +
                   "VALUES (" +
                   "?, ?, ?, " +
                   "?, ?, ?, " +
                   "?, ?, ?, " +
                   "?, ?, ?, " +
                   "?, ?, ?, " +
                   "?)";
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            ps = conn.prepareStatement(q);
            
            ps.setString(1, fname);
            ps.setString(2, mname);
            ps.setString(3, lname);
            ps.setDate(4, (Date) birthday);
            ps.setString(5, gender);
            ps.setInt(6, contact);
            ps.setString(7, email);
            ps.setString(8, guardian);
            ps.setString(9, gOccupation);
            ps.setInt(10, gContact);
            ps.setString(11, gRelationship);
            ps.setInt(12, ylevel);
            ps.setString(13, section);
            ps.setString(14, address);
            ps.setString(15, image_path);
            ps.setDate(16, (Date)applicationDate);
            ps.executeUpdate();
            
            Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText("sql chefck");
            alert.showAndWait();
            
            System.out.println(q);
            
            conn.close();
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
        
    //Load enrollment queue table
    public TableView loadEnrollmentQueue()
    {
        Connection conn = null;
        String query = "SELECT * FROM tbl_enrollmentqueue";
        ObservableList<QueueModel> data = FXCollections.observableArrayList();
        
        TableView table = new TableView();
        TableColumn appDateCol = new TableColumn("Application Date");
        appDateCol.setPrefWidth(150);
        appDateCol.setCellValueFactory(
        new PropertyValueFactory<QueueModel,Date>("applicationDate"));
        
        TableColumn fnameCol = new TableColumn("First Name");
        fnameCol.setCellValueFactory(
        new PropertyValueFactory<QueueModel,String>("FirstName"));
        
        TableColumn lnameCol = new TableColumn("Last Name");
        lnameCol.setCellValueFactory(
        new PropertyValueFactory<QueueModel,String>("LastName"));
        
        
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                Date a = (Date)rs.getDate(17);
                String b = rs.getString(2);
                String c = rs.getString(4);
                System.out.println(a.toString());
                System.out.println(b.toString());
                System.out.println(c.toString());
                data.add(new QueueModel(a, b, c));
            }
            table.getItems().addAll(data);
            conn.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        
        table.getColumns().addAll(appDateCol, fnameCol, lnameCol);
        table.setPrefHeight(300.0);
        
        return table;
    }
    
    //Load sections
    public ArrayList loadSections()
    {
        Connection conn = null;
        String query = "SELECT * FROM tbl_sections";
        ArrayList<String> sections = new ArrayList<String>();
        
        
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                sections.add(rs.getString(2));
                if(sections.contains(rs.getString(2)))
                {
                    //Nothing to do
                }
                else
                    sections.add(rs.getString(2));

            }
            conn.close();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return sections;
    }
    
    //Load courses
    public ArrayList loadCourses()
    {
        Connection conn = null;
        String query = "SELECT * FROM tbl_course";
        ArrayList<String> courses = new ArrayList<String>();
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                courses.add(rs.getString(2));
                if(courses.contains(rs.getString(2)))
                {
                    //Nothing to do
                }
                else
                    courses.add(rs.getString(2));

            }
            conn.close();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return courses;
    }
    
    //Load year levels
    public ArrayList loadYearLevels()
    {
        Connection conn = null;
        String query = "SELECT * FROM tbl_yearlevel";
        ArrayList<String> yearlevel = new ArrayList<String>();
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while(rs.next())
            {
                yearlevel.add(rs.getString(2));
            }
            conn.close();
        }
        
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return yearlevel;
    }

    
    public boolean connect(String connectionQuery, String y)
    {
        boolean con_stat = false;
        Connection conn = null;
        String query = connectionQuery;
        
        System.out.println(query);
        
        try
        {
            conn = DriverManager.getConnection(connection, "root", "");
            Statement stmt = conn.createStatement();
            //ResultSet rs2 = stmt.executeQuery("SELECT EXISTS(" + query + ")"); //Used for validating query if the item that looking for is existing or not on the selected Table ehe
            ResultSet rs = stmt.executeQuery(query);
            
            if(y == "CHECK EXIST")
            {
                StudentInfoViewerController info = new StudentInfoViewerController();
                while(rs.next())
                {
                    System.out.println("CHECK EXIST " + rs.getInt(1));
                    if(rs.getInt(1) == 1)
                        info.exist = true;
                    else if(rs.getInt(1) == 0)
                        info.exist = false;
                }
            }
            //Genereate ID
            else if(y == "GENERATE ID")
            {
                EnrollmentPageController enrollmentPage = new EnrollmentPageController();
                int lastId = 0;
                while(rs.next())
                {
                    lastId = rs.getInt(1);
                }                
                enrollmentPage.generatedId = (lastId += 1);
            }
            else if(y == "LOAD STUDENTS")
            {
                StudentsList sList = new StudentsList();
                EntriesCount eCount = new EntriesCount();
                while(rs.next())
                {
                    sList.sections.add(rs.getString(14));
                    sList.studentsId.add(rs.getInt(1));
                    sList.firstNames.add(rs.getString(2));
                    sList.lastNames.add(rs.getString(4));
                    sList.mNames.add(rs.getString(3));
                    sList.contacts.add(rs.getInt(7));
                    sList.yearLevels.add(rs.getInt(13));
                    sList.emails.add(rs.getString(8));
                    sList.addresses.add(rs.getString(16));
                    sList.enrolledDates.add(rs.getDate(15).toString());
                    sList.guardians.add(rs.getString(9));
                    //Age is sample only
                    sList.ages.add(20);
                }
                eCount.totalSelectedStudents = sList.studentsId.size();
            }
            else if(y == "LOAD STUDENTS SECTION")
            {
                StudentsList sList = new StudentsList();
                EntriesCount eCount = new EntriesCount();
                while(rs.next())
                {
                    sList.sections.add(rs.getString(14));
                    sList.studentsId.add(rs.getInt(1));
                    sList.firstNames.add(rs.getString(2));
                    sList.lastNames.add(rs.getString(4));
                    sList.mNames.add(rs.getString(3));
                    sList.contacts.add(rs.getInt(7));
                    sList.yearLevels.add(rs.getInt(13));
                    sList.emails.add(rs.getString(8));
                    sList.addresses.add(rs.getString(16));
                    sList.enrolledDates.add(rs.getDate(15).toString());
                    sList.guardians.add(rs.getString(9));
                    //Age is sample only
                    sList.ages.add(20);
                }
                eCount.totalSelectedStudents = sList.studentsId.size();
            }
            else if(y == "STUDENT INFO")
            {
                StudentInfoViewerController studnt = new StudentInfoViewerController();
                while(rs.next())
                {
                    student_ids.add(rs.getInt(1));
                    studnt.studentInfo.add(String.valueOf(rs.getInt(1)));       //Student ID
                    studnt.studentInfo.add(rs.getString(2));                    // First Name
                    studnt.studentInfo.add(rs.getString(3));                    // Middle Name
                    studnt.studentInfo.add(rs.getString(4));                    // Last Name
                    studnt.studentInfo.add(rs.getString(5));                    // Birthday
                    studnt.studentInfo.add(rs.getString(6));                    // Gender
                    studnt.studentInfo.add(String.valueOf(rs.getInt(7)));       // Contact Number
                    studnt.studentInfo.add(rs.getString(8));                    // Email
                    studnt.studentInfo.add(rs.getString(9));                    // Guardian
                    studnt.studentInfo.add(rs.getString(10));                   // Occupation
                    studnt.studentInfo.add(String.valueOf(rs.getInt(11)));      // Guardian Contact
                    studnt.studentInfo.add(rs.getString(12));                   // Relationship
                    studnt.studentInfo.add(String.valueOf(rs.getInt(13)));      // Year Level
                    studnt.studentInfo.add(rs.getString(14));                   // Section
                    studnt.studentInfo.add(rs.getString(15));                   // Date Enrolled
                    studnt.studentInfo.add(rs.getString(16));                   // Address
                }   
            }
            //The data on database has been recorded!, closing the connection
            conn.close();
            con_stat = true;
        }
        catch(Exception ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("There was an error");
            alert.setHeaderText("There was an error when connecting to database");
            alert.setContentText("Error message: \n" + ex);
            
            alert.showAndWait();
            
            con_stat = false;
        }
        return con_stat;
    }
}
