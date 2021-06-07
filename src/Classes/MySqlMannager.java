/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.exceptions.MySQLDataException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author Sachintha
 */
public class MySqlMannager {
    
    public static Connection connect()
    {
        Connection mySqlCon = null;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            mySqlCon = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library_management_system","root","");
            return mySqlCon; 
            
        }catch(Exception e){
            
            JOptionPane.showMessageDialog(null, "Error");
        } 
        
        return null;     
    }
    
    public static int insertUpdateDelete(String query)
    {
        try
        {
            Connection mysqlCon = connect();
            PreparedStatement pst = mysqlCon.prepareStatement(query);
            int count = pst.executeUpdate();
            
            mysqlCon.close();
            pst.close();
            return count;
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Insert Error");
        }
        return 0;
    }
    
    public static ResultSet viewData(String query)
    {
        try
        {
            Connection mysqlCon = connect();
            Statement st = mysqlCon.createStatement();
            ResultSet rs = st.executeQuery(query);
            return rs;
//            mysqlCon.close();
//            st.close();
            
            
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }
    
}


//class BookDAO
//{
//    
//}
//
//class Book
//{
//    
//}
//
//class MemberDAO
//{
//    public static Member InsertUpdateDelete(String firstName, String SecondName, String lastName, String MAddress, String Id, String tpNumber)
//    {
//        Member mObj = new Member();
//        mObj.fname = firstName;
//        mObj.sname = SecondName;
//        mObj.lname = lastName;
//        mObj.address = MAddress;
//        mObj.nic = Id;
//        mObj.tpNumber = tpNumber;
//        
//        return mObj;
//    }
//    
//}
//
//class Member
//{
//    String fname,sname,lname,address,nic,tpNumber;
//}