
public class SelectedProfile {
    private static String lastName;
    private static String firstName;
    private static String middleName;
    private static String email;
    private static String address;
    private static String section;
    private static String enrolledDate;
    private static String guardian;
    private static int id;
    private static int age;
    private static int contact;
    private static int year;
    
    public void setLastName(String lname)       { lastName = lname; }
    public void setFirstName(String fname)      { firstName = fname; }
    public void setMiddleName(String initial)   { middleName = initial; }
    public void setEmail(String email)          { this.email = email; }
    public void setAddress(String address)      { this.address = address; }
    public void setSection(String section)      { this.section = section; }
    public void setEnrolledDate(String date)    { enrolledDate = date; }
    public void setGuardian(String guardian)    { this.guardian = guardian; }
    public void setStudentId(int id)            { this.id = id; }
    public void setAge(int age)                 { this.age = age; }
    public void setContact(int contact)         { this.contact = contact; }
    public void setYear(int year)               { this.year = year; }
    
    public String getLastName()                 { return lastName; }
    public String getFirstName()                { return firstName; }
    public String getMiddleName()               { return middleName; }
    public String getEmail()                    { return email; }
    public String getAddress()                  { return address; }
    public String getSection()                  { return section; }
    public String getEnrolledDate()             { return enrolledDate; }
    public String getGuardian()                 { return guardian; }
    public int getId()                          { return id; }
    public int getAge()                         { return age; }
    public int getContact()                     { return contact; }
    public int getYear()                        { return year; }
}
 