
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javafx.scene.control.Alert;


public class ConnectDB {
    
    private static final String connection = "jdbc:mysql://127.0.0.1:3306/student_management";
    public static ArrayList<Integer> student_ids = new ArrayList<Integer>();
    
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
