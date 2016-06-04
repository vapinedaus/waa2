/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import model.Person;

/**
 *
 * @author 984865
 */
public class Database {
    
    private  static List<Person> users = new ArrayList<Person>();
    static
    {
        users.add(new Person("Victor Angelo" ,"a", "a",true));
        
    
    }
    
    
    public static void  add(Person user)
    {
            users.add(user);
    }
    
    public  static void  remove(Person user)
    {
        
            users.remove(user);
    }
    
    public  static List<Person>  getAllUsers()
    {
            return users;
    }
    
    public static Person  AuthUser(String user, String password)
    {
            for (Person s: users)
            {
               if (s.getUsername().equals(user) &&
                       s.getPassword().equals(password))
               {
                   return s;
               }
            
            }
            
            return null;
   
    }
    
    
}
