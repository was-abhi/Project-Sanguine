import java.util.*;

// Donor class
class Donor {
    private String donorID;
    private String name;
    private String bloodGroup;
    private boolean eligible = true;

    public Donor(String donorID, String name, String bloodGroup) {
        this.donorID = donorID;
        this.name = name;
        this.bloodGroup = bloodGroup;
    }

    public void viewProfile() {
        System.out.println("Donor: " + name + " | Blood Group: " + bloodGroup);
    }

    public void logDonation() {
        eligible = false;
        System.out.println(name + " has donated blood. Next donation after 6 months.");
    }

    public void notifyDonor(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}

// Acceptor class
class Acceptor {
    private String acceptorID;
    private String name;

    public Acceptor(String acceptorID, String name) {
        this.acceptorID = acceptorID;
        this.name = name;
    }

    public BloodRequest requestBlood(String bloodGroup, int units) {
        System.out.println(name + " requested " + units + " units of " + bloodGroup + " blood.");
        return new BloodRequest(UUID.randomUUID().toString(), bloodGroup, units);
    }
}

// Hospital class
class Hospital {
    private String hospitalID;
    private String name;

    public Hospital(String hospitalID, String name) {
        this.hospitalID = hospitalID;
        this.name = name;
    }

    public void conductBloodTest(Donor donor) {
        System.out.println("Hospital " + name + " conducted blood test for " + donor + ".");
    }

    public void approveRequest(BloodRequest request) {
        request.updateStatus("Approved");
        System.out.println("Hospital " + name + " approved request " + request.getRequestID());
    }
}

// Admin class
class Admin {
    private String adminID;
    private String username;

    public Admin(String adminID, String username) {
        this.adminID = adminID;
        this.username = username;
    }

    public void moderateRequests(BloodRequest request) {
        System.out.println("Admin " + username + " moderated request " + request.getRequestID());
    }
}

// BloodRequest class
class BloodRequest {
    private String requestID;
    private String bloodGroup;
    private int units;
    private String status;

    public BloodRequest(String requestID, String bloodGroup, int units) {
        this.requestID = requestID;
        this.bloodGroup = bloodGroup;
        this.units = units;
        this.status = "Pending";
    }

    public void updateStatus(String status) {
        this.status = status;
    }

    public String getRequestID() {
        return requestID;
    }

    public String getStatus() {
        return status;
    }
}

// Main Simulation
public class BloodDonationSystem {
    public static void main(String[] args) {
        // Create objects
        Donor d1 = new Donor("D001", "Rahul", "A+");
        Acceptor a1 = new Acceptor("A001", "Anjali");
        Hospital h1 = new Hospital("H001", "City Hospital");
        Admin admin = new Admin("ADM001", "SystemAdmin");

        // Workflow: Acceptor requests blood
        BloodRequest req = a1.requestBlood("A+", 2);

        // Hospital approves
        h1.approveRequest(req);

        // Admin moderates
        admin.moderateRequests(req);

        // Notify donor
        d1.notifyDonor("Blood request approved. Please donate if eligible.");

        // Donor logs donation
        d1.logDonation();

        // Donor views profile
        d1.viewProfile();

        System.out.println("Final Request Status: " + req.getStatus());
    }
}
