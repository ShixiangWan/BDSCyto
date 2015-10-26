package com.test.mrmd;

import java.io.BufferedReader;
import java.io.FileReader;

public class CaculateNum {
	
	public int getInstanceNum (String input)
	{
		try {
			BufferedReader bf = new BufferedReader(new FileReader(input));
			String lineString;
			int Num = 0;
			while (bf.ready()) 
			{
				lineString = bf.readLine();
				if (lineString.substring(0, 1).matches("[0-9]") && lineString != null) 
				{
					Num ++;
				}
			}
			bf.close();
			return Num;
		} 
		catch (Exception e) 
		{
			System.out.println("Can't get Instance Number");
		}
		return 0;
	}
	
	public int getFeatureNum (String input)
	{
		try {
			BufferedReader bf = new BufferedReader(new FileReader(input));
			String lineString;
			int Num = 0;
			while (bf.ready()) 
			{
				lineString = bf.readLine();
				if (lineString.substring(0, 1).matches("[0-9]") && lineString != null) 
				{
					for (int i = 0; i < lineString.length(); i++)
					{
						if (lineString.substring(i, i + 1).matches(",")) 
						{
							Num ++;
						}
					}
					break;
				}
			}
			bf.close();
			return Num;
			
		} 
		catch (Exception e) 
		{
			System.out.println("Can't get Feature Number");
		}
		return 0;
	}
	
}
