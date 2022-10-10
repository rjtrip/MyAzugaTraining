/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import java.util.*;
import java.io.*;
import java.io.File;


public class lsnh

{


    public static void main(String[] args) 
{
        
       

      
      File f = new File("/users/azuga");
 FileFilter fileFilter = new FileFilter(){
         public boolean accept(File dir) {          
            if (dir.isHidden()) {
               return false;
            } else {
               return true;
            }
         }
      };        
      File[] list = f.listFiles(fileFilter);


      for(File fileName : list) {

         System.out.println(fileName.getName());

      } 
    }
}
       
  
    
