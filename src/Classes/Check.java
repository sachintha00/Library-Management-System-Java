/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import javax.swing.JOptionPane;

/**
 *
 * @author Sachintha
 */
public class Check {
    
    public static boolean IsInteger(String value)
    {
        boolean boolValue = false;
        
        try
        {
            int i = Integer.parseInt(value);
            
            boolValue = true;
        }
        catch(Exception e)
        {
            boolValue = false;
        }
        
        return boolValue;
    }
    
    public static boolean IsString(String value)
    {
        boolean boolValue = false;
        
        try
        {
            int i = Integer.parseInt(value);
            
            boolValue = false;
        }
        catch(Exception e)
        {
            boolValue = true;
        }
        
        return boolValue;
    }
}
