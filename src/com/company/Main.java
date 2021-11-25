package com.company;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.lang.*;

class Person{
    protected String name;
    protected String Gender;
    public String getGender(){
        return Gender;
    }
    public String getName()
    {
        return name;
    }
}
class Student extends Person{
    private String studentID;
    String getID()
    {return studentID;}
    Student(String name,String studentID,String Gender)
    {
        this.name=name;
        this.studentID=studentID;
        this.Gender=Gender;
    }
}
class Course
{
    private String courseCode;
    private String courseName;
    private int MaxMarks;
    public String getCourseCode()
    {
        return courseCode;
    }
    public String getCourseName()
    {
    return courseName;
    }
    public int getMaxMarks()
    {
        return MaxMarks;
    }
    public int CourseStatus()
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT count(*) from course";
            resultSet = statement.executeQuery(selectSql);
            // Print results from select statement
            int count=0;
            while(resultSet.next())
            {count=resultSet.getInt(1); }
        return count;
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    return 0;
    }
    public void addContent(String content)
    {
        PrintWriter outputStreamName = null;
        try{
            outputStreamName = new PrintWriter(new FileOutputStream("CourseContent.txt", true));
            outputStreamName.println(content);
        }
        catch(FileNotFoundException e){
            System.err.println("Error opening the file CourseContent.txt");
            System.exit(0);
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }
        finally{
            outputStreamName.close();
        }
    }
    public void addCourse()
    {
        System.out.println("Create A Course: ");
        System.out.print("Enter CourseCode: ");
        Scanner sc=new Scanner(System.in);
        courseCode=sc.nextLine();
        System.out.print("Enter CourseName: ");
        courseName=sc.nextLine();
        Scanner s1=new Scanner(System.in);
        System.out.print("Enter MaxMarks: ");
        MaxMarks= s1.nextInt();
    }
    public void viewContent()
    {
        System.out.println("Create A Course: ");
        System.out.print("Enter CourseCode: ");
        Scanner sc=new Scanner(System.in);
        courseCode=sc.nextLine();
        System.out.print("Enter CourseName: ");
        courseName=sc.nextLine();
        Scanner s1=new Scanner(System.in);
        System.out.print("Enter MaxMarks: ");
        MaxMarks= s1.nextInt();
    }

}
class Service{
    public static void ShowAllStudents()
    {
        System.out.println("\n\nHere's the list of all students\n");
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from stud";
            resultSet = statement.executeQuery(selectSql);
            // Print results from select statement
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int col=rsmd.getColumnCount();
            int i=1;
            while(col>0)
            {
                System.out.format("%20s",rsmd.getColumnName(i));
                i++;
                col--;
            }
            System.out.println("");
            while (resultSet.next()) {
                System.out.format("%20s%20s%20s",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                System.out.println("");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void ShowInfo()
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from stud";
            resultSet = statement.executeQuery(selectSql);
            // Print results from select statement
            ResultSetMetaData rsmd = resultSet.getMetaData();
            int col=rsmd.getColumnCount();
            int i=1;
            while(col>0)
            {
                System.out.format("%20s",rsmd.getColumnName(i));
                i++;
                col--;
            }
            System.out.println("");
            while (resultSet.next()) {
                System.out.format("%20s%20s%20s",resultSet.getString(1),resultSet.getString(2),resultSet.getString(3));
                System.out.println("");
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
class AdminService extends Service{
    public void Menu()
    {
        Scanner s=new Scanner(System.in);
        int choice;
        Course course=new Course();
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        String courseCode=course.getCourseCode();
        String courseName=course.getCourseName();
        int MaxMarks=course.getMaxMarks();
        if(course.CourseStatus()==0)
        {
            course.addCourse();
            try (
                    Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                    Statement statement = connection.createStatement()) {
                // Create and execute a SELECT SQL statement.
                String selectSql1 = "insert into course values ('"+courseCode+"','"+courseName+"',"+MaxMarks+")";
                int result1 = statement.executeUpdate(selectSql1);
                if(result1==1)
                {
                    System.out.println("Course Created Successfully!!");
                }
                // Print results from select statement
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        do {
                System.out.println("**************************************************************************************");
                System.out.println("Hello Admin, here's what you can do:");
                System.out.println("1. Add Evaluative\n2. View Enrolled Students\n3. Course Content\n4. Update Marks\n5. Remove Student\nEnter 0 to Logout\nChoose an Option: ");
                System.out.println("**************************************************************************************");
                choice = s.nextInt();
                if (choice == 1) {
                    addEvaluative();
                }
                if (choice == 2) {
                    viewMarks();
                }
                if(choice==3)
                {
                    System.out.println("**************************************************************************************");
                    System.out.println("1. Add Content\n2. View Content\nChoose an Option: ");
                    System.out.println("**************************************************************************************");
                    Scanner s2=new Scanner(System.in);
                    int ch=s2.nextInt();
                    if(ch==1)
                    {
                        System.out.print("Enter Name of Topic: ");
                        Scanner t=new Scanner(System.in);
                        String topic=t.nextLine();
                        course.addContent(topic);
                    }
                    if(ch==2)
                    {
                        course.viewContent();
                    }
                }
                if (choice == 4)
                {
                    System.out.print("Enter ID of Student: ");
                    Scanner s2=new Scanner(System.in);
                    String ID=s2.nextLine();
                    System.out.print("Enter Evaluative Name: ");
                    Scanner s3=new Scanner(System.in);
                    String evaluative=s3.nextLine();
                    System.out.print("Enter Marks: ");
                    Scanner s4=new Scanner(System.in);
                    int marks =s4.nextInt();
                    SetMarks(ID,evaluative,marks);
                }
                if (choice == 5) {
                    System.out.println("**************************************************************************************");
                    System.out.print("Enter ID of Student to be removed:");
                    Scanner s1=new Scanner(System.in);
                    String studentID = s1.nextLine();
                    System.out.println("**************************************************************************************");
                    Delete(studentID);
                }

                if (choice==0){
                    break;
                }
        }while(true);
    }
   /* public void AddStudent()
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql1 = "INSERT INTO evaluatives (Name,ID) SELECT Name,ID FROM stud";
            int result = statement.executeUpdate(selectSql1);
            //int result1 = statement.executeUpdate(selectSql2);
            // Print results from select statement
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("\n\n\nRecord entered successfully!!!! Press Enter to go to Main Menu");
    }*/
    public void SetMarks(String name, String Eval, int marks)
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        //ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql1 = "update evaluatives set "+Eval+"="+marks+" where ID='"+name+"'";
            int result1=statement.executeUpdate(selectSql1);
            // Print results from select statement
            if(result1==0)
            {System.out.println("Record not found");}
            else
            {System.out.println("Record successfully Updated!!");}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Delete(String s)
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        //ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql1 = "delete from stud where ID='"+s+"'";
            String selectSql2 = "delete from evaluatives where ID='"+s+"'";
            String selectSql3 = "delete from login where username='"+s+"'";
            int result1=statement.executeUpdate(selectSql1);
            int result2=statement.executeUpdate(selectSql2);
            int result3=statement.executeUpdate(selectSql3);
            // Print results from select statement
            if(result1==0 || result2==0 || result3==0)
            {System.out.println("Record not found");}
            else
            {System.out.println("Record successfully Deleted!!");}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addEvaluative()
    {
        Scanner sc1= new Scanner(System.in);
        System.out.print("Enter Name of Evaluative: ");
        String s=sc1.nextLine();
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql = "ALTER TABLE evaluatives add "+s+" int(10) default 0";
            int result=statement.executeUpdate(selectSql);
            // Print results from select statement
            if(result==0)
            {System.out.println("Record successfully Added!!");}
            if(result==1)
            {System.out.println("Error Occurred!!");}
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewMarks()
    {
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;

        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql = "select * from evaluatives";
            resultSet= statement.executeQuery(selectSql);
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
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
class User{
    public static void addUser()
    {
        Scanner s=new Scanner(System.in);
        System.out.println("**************************************************************************************");
        System.out.print("Enter BITS Student ID:");
            String str2 = s.nextLine();
            System.out.print("Enter Your Name:");
            String str1 = s.nextLine();
            System.out.print("Enter Your Gender(M or F):");
            String str3 = s.nextLine();
        System.out.println("**************************************************************************************");
        System.out.print("Create Password:");
            String password1 = s.nextLine();
            System.out.print("Confirm Password:");
        String password2 = s.nextLine();
        //System.out.println("**************************************************************************************");
        Student st=new Student(str1,str2,str3);
        while(!password1.equals(password2)){
                System.out.println("**************************************************************************************");

                System.out.println("Please create your password again!");
                System.out.print("Create Password:");
                password1 = s.nextLine();
                System.out.print("Confirm Password:");
                password2 = s.nextLine();
            }
            System.out.println("...Your password has been set...\n...Your Username will be your BITS ID...\n");
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        String name=st.getName();
        String studentID=st.getID();
        String gender=st.getGender();
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql1 = "insert into stud values ('"+name+"','"+studentID+"','"+gender+"')";
            String selectSql2 = "insert into login values ('"+studentID+"','"+password1+"')";
            String selectSql3 = "insert into evaluatives(Name,ID) values ('"+name+"','"+studentID+"')";
            int result1 = statement.executeUpdate(selectSql1);
            int result2 = statement.executeUpdate(selectSql2);
            int result3 = statement.executeUpdate(selectSql3);
            // Print results from select statement
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean verifyLogin(String studentId, String password1)
    {
        boolean f=false;
        String connectionUrl = "jdbc:mysql://localhost:3307/student";
        ResultSet resultSet = null;
        try (
                Connection connection = DriverManager.getConnection(connectionUrl,"root","Akshat@10");
                Statement statement = connection.createStatement()) {
            // Create and execute a SELECT SQL statement.
            String selectSql1 = "select password from login where username='"+studentId+"'";
            resultSet=statement.executeQuery(selectSql1);
            resultSet.next();
            String password = resultSet.getString(1);
            //Print results from select statement
            if(password.equals(password1))
            {
                f=true;
            }
        }
        catch (SQLException e) {
            System.out.println("Invalid Username or Password!!");
        }
        return f;
       // System.out.println("\n\n\nRecord entered successfully!!!! Press Enter to go to Main Menu");
    }
}
public class Main {
    public static void main(String[] args) throws IOException {
	// write your code here
        System.out.println("WELCOME TO NALANDA LITE!!!\n\n\n");
        Login Login=new Login();
        Login.main();

    }
}
