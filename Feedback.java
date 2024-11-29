public class Feedback {
    private String attendeeId;
    private String comments;
    private int rating;

    public Feedback(String attendeeId, String comments, int rating) {
        this.attendeeId = attendeeId;
        this.comments = comments;
        this.rating = rating;
    }

    public String getFeedbackDetails() {
        return "Feedback from " + attendeeId + ": " + comments + " (Rating: " + rating + ")";
    }
}