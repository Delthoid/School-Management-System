

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class StudentModel
{
    private String firstName;
    private String lastName;
    private String middleName;
    private String section;
    private String email;
    private String address;
    private String enrolledDate;
    private String guardian;
    private int studentId;
    private int age;
    private int yearLevel;
    private int contact;
   
    
    public StudentModel()
    {
        
    }
    
    public StudentModel(String firstName, String lastName, String middleName, 
            int studentId, int age, String section, 
            int yearLevel, int contact, String email, 
            String address, String enrolledDate, String guardian)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentId = studentId;
        this.section = section;
        this.yearLevel = yearLevel;
        this.contact = contact;
        this.middleName = middleName;
        this.age = age;
        this.email = email;
        this.address = address;
        this.enrolledDate = enrolledDate;
        this.guardian = guardian;
    }
    //Getters
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getSection() { return section; } 
    public String getMiddleName() { return middleName; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public String getEnrolledDate() { return enrolledDate; }
    public String getGuardian() { return guardian; }
    public int getStudentId() { return studentId; }
    public int getYearLevel() { return yearLevel; }
    public int getContact() { return contact; }
    public int getAge() { return age; }
    
    //Setters
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setSection(String section) { this.section = section; }
    public void setMiddleName(String middleName) { this.middleName = middleName; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress (String address) { this.address = address; }
    public void setEnrolledDate(String date) { this.enrolledDate = date; }
    public void setGuardian(String guardian) { this.guardian = guardian;}
    public void setStudentId(int studentId) { this.studentId = studentId; }
    public void setYearLevel(int yearLevel) { this.yearLevel = yearLevel; }
    public void setContact(int contact) { this.contact = contact; }
    public void setAge(int age) { this.age = age; }
}