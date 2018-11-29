package by.iba.boot_learning.entity;

public class InsertResult {

    private String message;
    private String insertedObjectName;

    public InsertResult(String insertedObjectName) {
        this.message = insertedObjectName + " was not insert";
        this.insertedObjectName = insertedObjectName;
    }

    public void defineMessage(boolean isAdded) {
        if (isAdded) {
            this.message = insertedObjectName + " was added";
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getInsertedObjectName() {
        return insertedObjectName;
    }

    public void setInsertedObjectName(String insertedObjectName) {
        this.insertedObjectName = insertedObjectName;
    }
}
