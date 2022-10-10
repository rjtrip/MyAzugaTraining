/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */
import java.io.File;
import java.util.Arrays;

public class lsS{
    public static void main(String[] args) {
        File file = new File(
                System.getProperty("user.dir"));
        File[] files = file.listFiles();
  	Arrays.sort(files);
        for (File f : files) {
            System.out.println( f.getName() );
                    
        }
 
    }
}

/*"users/azuga/documents/demojava"*/