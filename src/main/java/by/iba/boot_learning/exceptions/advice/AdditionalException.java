package by.iba.boot_learning.exceptions.advice;

public class AdditionalException {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AdditionalException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "AdditionalException{" +
                "message='" + message + '\'' +
                '}';
    }
}
