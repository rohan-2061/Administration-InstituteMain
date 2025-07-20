package Institute.administration.system;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//
////public class database {
////
////    Connection connection;
////    Statement statement;
////    database(){
////        try {
////            Class.forName("com.mysql.cj.jdbc.Driver");
////            connection = DriverManager.getConnection("jdbc:mysql://universitymanagement","root","W7301@jqir#");
////            statement = connection.createStatement();
////        }catch (Exception e){
////            e.printStackTrace();
////        }
////    }
////}


import java.sql.*;

public class database {

    Connection connection;

    Statement statement;
    public database(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/college","root","System@123");
            statement = connection.createStatement();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
