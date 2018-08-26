package com.twitter.get.statuses;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MainClass {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException 
	{
		Object[] parameters ={new Object()};
		Class<TwitterEndToEndWorkflow> person=null;
		Object obj=null;
		Object[] param ={parameters};
		try
		{
			person=(Class<TwitterEndToEndWorkflow>) Class.forName("com.twitter.get.statuses.TwitterEndToEndWorkflow");		
			obj=person.newInstance();
		} 
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Method[] field=person.getDeclaredMethods();
		
		for (Method field2 : field)
		{
			System.out.println(field2);
			try
			{	
				if (field2.isAnnotationPresent(BeforeClass.class)) 
				{
					field2.invoke(obj, null);
				}
				
				if (field2.isAnnotationPresent(Test.class)) 
				{
					field2.invoke(obj, null);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
