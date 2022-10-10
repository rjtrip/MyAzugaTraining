/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import java.util.*;
import java.io.*;
import java.io.File;


public class lsh

{


    public static void main(String[] args) 
{
        
        String[] pathnames;

      
        File f = new File("/users/azuga");

        
        pathnames = f.list();

      
        for (String pathname : pathnames) {
            
            System.out.print(pathname); 
             System.out.print("   "); 

        }
    }
}
       
  
    
