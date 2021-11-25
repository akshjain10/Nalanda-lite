package com.company;

import java.sql.*;
import java.util.*;

public class StudentService {
    public void dashboard(String username) {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        do {
            try (
                    Connection connection = DriverManager.getConnection(connectionUrl, "root", "Akshat@10");
                    Statement statement = connection.createStatement();) {
                // Create and execute a SELECT SQL statement.
                String selectSql1 = "select * from stud where ID='" + username + "'";
                resultSet = statement.executeQuery(selectSql1);
                resultSet.next();
                String name=resultSet.getString(1);
                System.out.println("**************************************************************************************");
                System.out.println("                                   NALANDA LITE!!!!!");
                System.out.println("**************************************************************************************");
                System.out.println("Hello "+name+"!!!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Scanner sc = new Scanner(System.in);
            System.out.println("Here's your Dashboard\n\n1. Profile\n2. View Marks\n3. View Students\n4. View Course Content\nEnter 0 to logout\n\nChoose an Option: ");
            int option = sc.nextInt();
            if (option == 1) {
                System.out.println("**************************************************************************************");
                try (
                        Connection connection = DriverManager.getConnection(connectionUrl, "root", "Akshat@10");
                        Statement statement = connection.createStatement();) {
                    // Create and execute a SELECT SQL statement.
                    String selectSql1 = "select * from stud where ID='" + username + "'";
                    resultSet = statement.executeQuery(selectSql1);
                    while (resultSet.next()) {
                        String name=resultSet.getString(1);
                        String ID=resultSet.getString(2);
                        String Gender=resultSet.getString(3);
                        System.out.format("Name: %s%20sID: %s%20sGender: %s%20s",name,"",ID,"",Gender,"");
                        System.out.println("");
                    }
                String selectSql2 = "select * from course";
                resultSet = statement.executeQuery(selectSql2);
                    while (resultSet.next()) {
                        String CourseCode=resultSet.getString(1);
                        String CourseName=resultSet.getString(2);
                        String MaxMarks=resultSet.getString(3);
                        System.out.format("CourseCode: %s%30sCourse: %s%30s",CourseCode,"",CourseName,"");
                        System.out.println("");
                    }

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("**************************************************************************************");

            } else if (option == 2) {
                System.out.println("**************************************************************************************");
                try (
                        Connection connection = DriverManager.getConnection(connectionUrl, "root", "Akshat@10");
                        Statement statement = connection.createStatement();) {
                    // Create and execute a SELECT SQL statement.
                    String selectSql1 = "select * from evaluatives where ID='" + username + "'";
                    resultSet = statement.executeQuery(selectSql1);
                    ResultSetMetaData rsmd = resultSet.getMetaData();
                    int col=rsmd.getColumnCount();
                    int i=1;
                    // Print results from select statement
                    while(col>0)
                    {
                        System.out.format("%20s",rsmd.getColumnName(i));
                        i++;
                        col--;
                    }
                    System.out.println("");
                    while (resultSet.next()) {
                        i=1;
                        col=rsmd.getColumnCount();
                        while(col>0){
                            System.out.format("%20s",resultSet.getString(i));
                            i++;
                            col--;
                        }
                        System.out.println("");
                    }
                    // Print results from select statement
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                System.out.println("**************************************************************************************");

            } else if (option == 3) {
                Service service = new Service();
                System.out.println("**************************************************************************************");
                service.ShowAllStudents();
                System.out.println("**************************************************************************************");
            } else if (option == 4) {
              Course course=new Course();
              course.viewContent();
            }
            else if (option == 0) {
                break;
            }
        }while(true);
    }
}