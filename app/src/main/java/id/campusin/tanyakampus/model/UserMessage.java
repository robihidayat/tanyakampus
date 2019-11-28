package id.campusin.tanyakampus.model;

public class UserMessage extends BaseMessage {
    private String message;

    public UserMessage(String message, User sender, long createdAt) {
        super(message, sender, createdAt);
    }
}
