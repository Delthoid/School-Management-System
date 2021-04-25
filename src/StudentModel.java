

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class StudentModel
{
    private String firstName;
    private String lastName;
    private String middleName;
    private int studentId;
    private String section;
    private int yearLevel;
    private int contact;
    
    public StudentModel()
    {
        
    }
    
    public StudentModel(String firstName, String lastName, String middleName, int studentId, String section, int yearLevel, int contact)
    {
        /*this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.studentId = new SimpleIntegerProperty(studentId);
        */  
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.section = section;
        this.yearLevel = yearLevel;
        this.contact = contact;
        this.middleName = middleName;
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
    public String getSection()
    {
        return section;
    }
    public int getYearLevel()
    {
        return yearLevel;
    }
    public int getContact()
    {
        return contact;
    }
    public String getMiddleName()
    {
        return middleName;
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
    public void setSection(String section)
    {
        this.section = section;
    }
    public void setYearLevel(int yearLevel)
    {
        this.yearLevel = yearLevel;
    }
    public void setContact(int contact)
    {
        this.contact = contact;
    }
    public void setMiddleName(String middleName)
    {
        this.middleName = middleName;
    }
}