// Donor class
class Donor {
    private String donorID;
    private String name;
    private int age;
    private String bloodGroup;
    private String contactInfo;
    private boolean eligible;

    public void register() {}
    public void login() {}
    public void acceptRequest() {}
    public void cancelRequest() {}
    public void viewProfile() {}
    public void logDonation() {}
}

// Acceptor class
class Acceptor {
    private String acceptorID;
    private String name;
    private String contactInfo;

    public void register() {}
    public void login() {}
    public void requestBlood() {}
    public void viewStatus() {}
}

// Hospital class
class Hospital {
    private String hospitalID;
    private String name;
    private String location;

    public void login() {}
    public void conductBloodTest() {}
    public void updateRecord() {}
    public void viewRequests() {}
}

// Admin class
class Admin {
    private String adminID;
    private String username;
    private String password;

    public void login() {}
    public void moderateRequests() {}
    public void handleIssues() {}
}

// BloodRequest class
class BloodRequest {
    private String requestID;
    private String bloodGroup;
    private int units;
    private String status;

    public void createRequest() {}
    public void updateStatus() {}
}

// Notification class
class Notification {
    private String notificationID;
    private String message;
    private String timestamp;

    public void send() {}
    public void view() {}
}

