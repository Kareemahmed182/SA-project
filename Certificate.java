public class Certificate {
    private String attendeeName;
    private String attendeeId;

    public Certificate(String attendeeName, String attendeeId) {
        this.attendeeName = attendeeName;
        this.attendeeId = attendeeId;
    }

    public void generate() {
        System.out.println("Certificate of Attendance");
        System.out.println("Name: " + attendeeName);
        System.out.println("ID: " + attendeeId);
        System.out.println("Thank you for attending!");
    }
}
