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
        return  "Name = " + name + '\n' +
                "Phone Number = " + phoneNumber + '\n' +
                "Email Address = " + emailAddress + '\n' +
                "Staff ID = " + staffID;
    }
}
