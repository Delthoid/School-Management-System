import java.sql.Date;

public class QueueModel {
    private java.sql.Date applicationDate;
    private String firstname;
    private String lastname;
    
    public QueueModel(java.sql.Date applicationDate, String firstname, String lastname)
    {
        this.applicationDate = applicationDate;
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    public java.sql.Date getApplicationDate()
    {
        return applicationDate;
    }
    public String getFirstName()
    {
        return firstname;
    }
    public String getLastName()
    {
        return lastname;
    }
    
    public void setApplicationDate(java.sql.Date applicationDate)
    {
        this.applicationDate = applicationDate;
    }
    public void setFirstName()
    {
        this.firstname = firstname;
    }
    public void setLastName()
    {
        this.lastname = lastname;
    }
}
