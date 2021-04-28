
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class ConnectDB {
    
    
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    public static ArrayList<Integer> student_ids = new ArrayList<Integer>();
    
    //This connect method also used for reaing databases
    public void update(
            int id,
            String lname, String fname, String mname,
            String email, String address, int contact,
            int level, String section, String enDate,
            String guardian
    )
    {
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "UPDATE tbl_students set first_name =?, middle_name=?, last_name=?, email=?, address=?, contact_number=?, year_level=?, section=?  WHERE student_id=?";
        System.out.print(query);
        
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Update");
        alert.setHeaderText("Done updating !" + lname + " " + fname);
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
