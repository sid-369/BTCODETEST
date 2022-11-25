package com.FairBilling.project;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class FairBilling {
	
	public static void process(String s1, Map<String, String> users)
	{
		String[] dataval = s1.split(" ");
		
		if(null!=dataval && dataval.length==3)
		{
			String firstPart = dataval[0];
			String secondPart = dataval[1];
			String thirdPart = dataval[2];
			
			if(isTimeValid(firstPart))
			{
				if(null!=thirdPart && (thirdPart.equalsIgnoreCase("Start")
						|| thirdPart.equalsIgnoreCase("End")))
				{
					if(users.containsKey(secondPart))
					{
						String value = users.get(secondPart);
						users.put(secondPart, value + "|" + firstPart + " " + thirdPart);
					}
				}
			}
		}
		
	}
	
	public static boolean isTimeValid(String value)
	{
		try {
			String[] time = value.split(":");
			if(null!=time && time.length==3)
			{
				return Integer.parseInt(time[0]) < 24 && Integer.parseInt(time[1]) < 60 &&
						Integer.parseInt(time[2]) < 60;
			}
			else
			{
				return false;
			}
		}
		catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) {
		
//		System.out.println("Hi");
		
		Map<String, String> users = new HashMap<>();

		try (Stream<String> lines = Files.lines(Paths.get("C:\\Users\\Admin\\FairBillingProject\\FairBilling\\src\\logFile.log"))) 
				{
				lines.forEachOrdered(line -> process(line, users));
				
				for(Map.Entry<String, String> entry : users.entrySet())
				{
					System.out.println(entry.getKey() +  " --> : " + entry.getValue());
				}
			}
		catch(Exception e){
			e.printStackTrace();
		}

	}	
}
