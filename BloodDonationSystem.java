import java.util.*;

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
        System.out.println("Donor ID: " + donorID);
        System.out.println("Name: " + name);
        System.out.println("Blood Group: " + bloodGroup);
        System.out.println("Eligible: " + eligible);
    }

    public void editProfile(String newName, String newBloodGroup) {
        this.name = newName;
        this.bloodGroup = newBloodGroup;
        System.out.println("Profile updated successfully!");
    }

    public void logDonation() {
        eligible = false;
        System.out.println(name + " has donated blood. Next donation after 6 months.");
    }

    public void notifyDonor(String message) {
        System.out.println("Notification for " + name + ": " + message);
    }
}

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

class Hospital {
    private String hospitalID;
    private String name;

    public Hospital(String hospitalID, String name) {
        this.hospitalID = hospitalID;
        this.name = name;
    }

    public void approveRequest(BloodRequest request) {
        request.updateStatus("Approved");
        System.out.println("Hospital " + name + " approved request " + request.getRequestID());
    }

    public void conductBloodTest(Donor donor) {
        System.out.println("Hospital " + name + " conducted blood test for donor.");
    }
}

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

    public void handleIssues() {
        System.out.println("Admin " + username + " is handling reported issues.");
    }
}

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

public class BloodDonationSystem {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Donor donor = new Donor("D001", "Rahul", "A+");
        Acceptor acceptor = new Acceptor("A001", "Anjali");
        Hospital hospital = new Hospital("H001", "City Hospital");
        Admin admin = new Admin("ADM001", "SystemAdmin");
        BloodRequest currentRequest = null;

        while (true) {
            System.out.println("\n--- Blood Donation System ---");
            System.out.println("1. Login as Donor");
            System.out.println("2. Login as Acceptor");
            System.out.println("3. Login as Hospital");
            System.out.println("4. Login as Admin");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: // Donor
                    System.out.println("\n--- Donor Menu ---");
                    System.out.println("1. View Profile");
                    System.out.println("2. Edit Profile");
                    System.out.println("3. Log Donation");
                    System.out.print("Choose: ");
                    int dChoice = sc.nextInt();
                    sc.nextLine(); // consume newline
                    if (dChoice == 1) {
                        donor.viewProfile();
                    } else if (dChoice == 2) {
                        System.out.print("Enter new name: ");
                        String newName = sc.nextLine();
                        System.out.print("Enter new blood group: ");
                        String newBloodGroup = sc.nextLine();
                        donor.editProfile(newName, newBloodGroup);
                    } else if (dChoice == 3) {
                        donor.logDonation();
                    }
                    break;

                case 2: // Acceptor
                    System.out.println("\n--- Acceptor Menu ---");
                    System.out.print("Enter blood group: ");
                    String bg = sc.next();
                    System.out.print("Enter units: ");
                    int units = sc.nextInt();
                    currentRequest = acceptor.requestBlood(bg, units);
                    break;

                case 3: // Hospital
                    System.out.println("\n--- Hospital Menu ---");
                    if (currentRequest != null) {
                        hospital.approveRequest(currentRequest);
                    } else {
                        System.out.println("No active requests to approve.");
                    }
                    break;

                case 4: // Admin
                    System.out.println("\n--- Admin Menu ---");
                    if (currentRequest != null) {
                        admin.moderateRequests(currentRequest);
                    }
                    admin.handleIssues();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
