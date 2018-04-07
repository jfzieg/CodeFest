package application;

import java.util.Scanner;

public class main {

    public static void main(String[] args) {
        boolean running = true;
        boolean userExists = false;
    	Scanner sc = new Scanner(System.in);

        while (running) {
    		System.out.println("Welcom to 5th Year");
    		System.out.println("Please enter your info.");
    		System.out.print("First Name:");
    		String fName = sc.nextLine();
    		System.out.print("Last Name:");
    		String lName = sc.nextLine();
    		System.out.print("Major(s):");
    		String majors = sc.nextLine();
    		System.out.print("Minor(s):");
    		String minors = sc.nextLine();
    		System.out.println("What would you like to do?");
    		System.out.println("(1)Add class\n(2) ");
        }
    }

}
