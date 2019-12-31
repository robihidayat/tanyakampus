package id.campusin.tanyakampus.customexception;

public class HandlingException extends RuntimeException {
    private String responseCode;
    private String responseMessage;

    public HandlingException(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }
}
