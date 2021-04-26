
public class SelectedProfile {
    private static String lastName;
    private static String firstName;
    private static String middleInitial;
    private static String email;
    private static String address;
    private static int id;
    private static int age;
    
    public void setLastName(String lname) { lastName = lname; }
    public void setFirstName(String fname) { firstName = fname; }
    public void setMInitial(String initial) { middleInitial = initial; }
    public void setEmail(String email) { this.email = email; }
    public void setAddress(String address) { this.address = address; }
    public void setStudentId(int id) { this.id = id; }
    public void setAge(int age) { this.age = age; }
    
    public String getLastName() { return lastName; }
    public String getFirstName() { return firstName; }
    public String getmInitial() {  return middleInitial; }
    public String getEmail() { return email; }
    public String getAddress() { return address; }
    public int getId() { return id; }
    public int getAge() { return age; }
}
 