package id.campusin.tanyakampus.model;

public class BaseMessage {

    private String message;
    private User sender;
    private long createdAt;


    public String getMessage() {
        return message;
    }

    public User getSender() {
        return sender;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public BaseMessage(String message, User sender, long createdAt) {
        this.message = message;
        this.sender = sender;
        this.createdAt = createdAt;
    }
}
