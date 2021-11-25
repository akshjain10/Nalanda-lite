package com.company;

import javax.swing.*;
import java.awt.event.ContainerAdapter;
import java.sql.*;
import java.util.*;


public class Login {
    public static void main() {
        do{
            User user = new User();
            System.out.println("**************************************************************************************");
            System.out.println("                                   NALANDA LITE!!!!!");
            System.out.println("**************************************************************************************");

            System.out.println("**************************************************************************************");
            System.out.println("Login as: \n1.Student \n2.Admin");
//          System.out.println("**************************************************************************************");
            System.out.println("Choose an Option: ");
            System.out.println("**************************************************************************************");

            Scanner sc = new Scanner(System.in);
            int s1 = sc.nextInt();
            if (s1 != 1 && s1 != 2) {
                System.out.println("Invalid Input");
            }

            if (s1 == 1) {
                System.out.println("**************************************************************************************");
                System.out.println("                                   NALANDA LITE!!!!!");
                System.out.println("**************************************************************************************");
                System.out.println("**************************************************************************************");
                System.out.println("1.Register\n2.Login");
                System.out.println("Choose an Option: ");
                System.out.println("**************************************************************************************");
                int s2 = sc.nextInt();
                if (s1 != 1 && s1 != 2) {
                    System.out.println("Invalid Input");
                }
                if (s2 == 1) {
                    User.addUser();
                }
                if (s2 == 2) {
                    Scanner s = new Scanner(System.in);
                    System.out.println("**************************************************************************************");
                    System.out.println("                                   NALANDA LITE!!!!!");
                    System.out.println("**************************************************************************************");
                    System.out.println("**************************************************************************************");
                    System.out.print("Please Login:\nEnter Username: ");
                    String username = s.nextLine();
                    System.out.print("Enter Password: ");
                    String password = s.nextLine();
                    System.out.println("**************************************************************************************");
                    if (User.verifyLogin(username, password)) {
                    StudentService session= new StudentService();
                    session.dashboard(username);
                    }
                }
            }
            if(s1==2){
                Scanner s = new Scanner(System.in);
                System.out.println("**************************************************************************************");
                System.out.println("                                   NALANDA LITE!!!!!");
                System.out.println("**************************************************************************************");
                System.out.println("**************************************************************************************");
                System.out.print("Please Login:\nEnter Username: ");
                String username = s.nextLine();
                System.out.print("Enter Password: ");
                String password=s.nextLine();
                //System.out.println("**************************************************************************************");
                if (username.equals("admin") && password.equals("admin")) {
                    AdminService A=new AdminService();
                    A.Menu();}
            }
            if(s1==0){
                break;
            }
        }while(true);
        // Runtime.getRuntime().exec("cls");
    }
}


