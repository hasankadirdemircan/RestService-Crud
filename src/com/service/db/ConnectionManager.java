package com.service.db;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager 
{
	
	public static Connection getConnection()
	{
		Connection connection=null;
		
		try
        {
		   Class.forName ("com.mysql.cj.jdbc.Driver").newInstance ();
            String userName = "root";
            String password = "";
            String url = "jdbc:MySQL://localhost/yazar?serverTimezone=GMT&useSSL=false";        
            connection = DriverManager.getConnection (url, userName, password);
            System.out.println ("\nsql e baglandi");
        }
       catch (Exception ex)
        {
		       System.err.println ("Cannot connect to database server");
			   ex.printStackTrace();
        } 
		return connection;
	}
}
