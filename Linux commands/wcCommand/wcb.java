/**
 * Copyright (c) All rights reserved
 * Unauthorized copying or redistribution of this file in source and binary forms via any medium is strictly prohibited
 * @Author Rajatt( Rajat tripathi )
 */

import java.io.*;

public class wcb{
	public static void main(String[] args)
	{
		try {
			
			// creates object of FileOutputStream by passing
			// file Bytes.txt
			FileOutputStream FileOS
				= new FileOutputStream("/users/azuga/documents/wcTest/data.txt");

			// creates object of DataOutputStream by
			// passing object of FileOutputStream i.e.
			// FileOS
			DataOutputStream DataOS = new DataOutputStream(FileOS);

			DataOS.writeBytes("I  work in Azuga");
			// DataOutputStream class
			int total_bytes = DataOS.size();

			// Showing the number of bytes as output in
			// console
			DataOS.close();

			System.out.println(total_bytes+" "+ "data.txt");
		}
		catch (Exception e)
		{
			System.out.println("Exception: " + e.toString());
		}
	}
}
