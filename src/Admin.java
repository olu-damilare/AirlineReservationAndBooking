public class Admin {
    private String name;
    private String phoneNumber;
    private String emailAddress;
    private String staffID;

    public Admin(String name, String phoneNumber, String emailAddress, String staffID) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.staffID = staffID;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\n' +
                "phoneNumber='" + phoneNumber + '\n' +
                ",emailAddress='" + emailAddress + '\n' +
                '}';
    }
}
