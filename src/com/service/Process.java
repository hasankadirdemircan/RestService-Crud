package com.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.ws.rs.core.Response;

import org.json.JSONArray;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.service.db.ConnectionManager;
import com.service.employee.Employee;

public class Process 
{
	private static final String insertRc="insert into authors(name,age,book) values(?,?,?)";
	private static final String selectRc = "select *from authors";
	private static final String updateRc = "update authors set name=?, age=?, book=? where id=?";
	private static final String deleteRc= "delete from authors where id=?";
	Employee employee = new Employee();
	
	public String insert(JsonObject jsonObject)
	{
		String insertdata = jsonObject.toString();
		parse(insertdata);
		
		
		Connection connection=ConnectionManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
        try {
			statement=connection.prepareStatement(insertRc);
			statement.setString(1,employee.getName());
			statement.setString(2, employee.getAge());
			statement.setString(3, employee.getBook());
			statement.executeUpdate();
			statement.clearBatch();
			
			connection.close();
			statement.close();
		}catch (Exception e) {
			System.out.println(e);
		}
	    
        String sendString = select();
		return sendString;
		
		 
	}
	public String delete()
	{
		//String deletedata = jsonObject.toString();
		//parse(deletedata);
		
		Connection connection = ConnectionManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement=connection.prepareStatement(deleteRc);
			statement.setString(1, "1");
			statement.executeUpdate();
			statement.clearBatch();
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		String sendString = select();
		return sendString;
	}
	/*public String update(JsonObject jsonObject)
	{
		String updatedata = jsonObject.toString();
		parse(updatedata);
		Connection connection=ConnectionManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		try {
			statement=connection.prepareStatement(updateRc);
			statement.setString(1, employee.getName());
			statement.setString(2, employee.getAge());
			statement.setString(3, employee.getBook());
			statement.executeUpdate();
			statement.clearBatch();
			
			connection.close();
			statement.close();
			
			
		}catch(Exception e) {
			System.out.println(e);
		}
		
		String sendString = show();
		return sendString;
	}
	*/
	public String select()
	{
		Connection connection=ConnectionManager.getConnection();
		PreparedStatement statement;
		ResultSet resultSet;
		
		JsonArray jsonArray = null;
		try
		{
			
			statement=connection.prepareStatement(selectRc);
			resultSet=statement.executeQuery();
			jsonArray = new JsonArray();
			while(resultSet.next())
			{
				JsonObject jsonObject = new JsonObject();
				
				jsonObject.addProperty("name", resultSet.getString("name"));
				jsonObject.addProperty("age", resultSet.getString("age"));
				jsonObject.addProperty("book", resultSet.getString("book"));
				
				jsonArray.add(jsonObject);
			}
			connection.close();
			statement.close();
			
		}catch(Exception e){
			System.out.println(e);
		}
		String getdata = jsonArray.toString();
		return getdata;
	}
	
	
	public void parse(String data)
	{
		
		JSONObject object = new JSONObject(data);
		
		employee.setName(object.getString("name"));
		employee.setAge(object.getString("age"));
		employee.setBook(object.getString("book"));
		
		
	}
	
}
