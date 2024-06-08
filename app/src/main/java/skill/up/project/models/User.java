package skill.up.project.models;

public class User extends Model{
    private String name, email, role, phoneNumber, mbtiResult, registeredWebinar, company;
    private int age;

    //untuk login
    public User(int id, String role) {
        super(id);
        this.role = role;
    }

    //untuk getUserById()
    public User(int id, String name, String email, String phoneNumber, String mbtiResult,
            String registeredWebinar, int age, String company, String role) {
        super(id);
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.mbtiResult = mbtiResult;
        this.registeredWebinar = registeredWebinar;
        this.age = age;
        this.company = company;
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMbtiResult() {
        return mbtiResult;
    }

    public void setMbtiResult(String mbtiResult) {
        this.mbtiResult = mbtiResult;
    }

    public String getRegisteredWebinar() {
        return registeredWebinar;
    }

    public void setRegisteredWebinar(String registeredWebinar) {
        this.registeredWebinar = registeredWebinar;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
    
}
