package com.trilogy.cloudgamestoreadmin.util.password;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtility {

    private static PasswordEncoder encoder = new BCryptPasswordEncoder();
    private static String adminPassword = "admin";
    private static String managerPassword = "manager";
    private static String teamLeadPassword = "team lead";
    private static String employeePassword = "employee";

    private static String adminPassEnc = encoder.encode(adminPassword);
    private static String managerPassEnc = encoder.encode(managerPassword);
    private static String teamLeadPassEnc = encoder.encode(teamLeadPassword);
    private static String employeePassEnc = encoder.encode(employeePassword);


    public static void main(String[] args) {
        System.out.println("Admin password hash: " + adminPassEnc);
        System.out.println("Manager password hash: " + managerPassEnc);
        System.out.println("Team lead password hash: " + teamLeadPassEnc);
        System.out.println("Employee password hash: " + employeePassEnc);
    }


}
