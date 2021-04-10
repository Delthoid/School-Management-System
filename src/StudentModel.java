

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentModel
{
    private String firstName;
    private String lastName;
    private int studentId;
    
    public StudentModel()
    {
        
    }
    
    public StudentModel(String firstName, String lastName, int studentId)
    {
        /*this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.studentId = new SimpleIntegerProperty(studentId);
        */  
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
    }
    
    public String getFirstName()
    {
        return firstName;
    }
    public String getLastName()
    {
        return lastName;
    }
    public int getStudentId()
    {
        return studentId;
    }
    
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    public void setStudentId(int studentId)
    {
        this.studentId = studentId;
    }
}