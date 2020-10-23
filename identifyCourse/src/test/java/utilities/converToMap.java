package utilities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.WebElement;

public class converToMap {
	public static int total;
	
	/*
	 * Method to convert 2 Lists into a Map
	 */
	public static LinkedHashMap<String, String> convert(List<WebElement> filterList, List<WebElement>filterListCount )
	{	//public ArrayList<String> tabsGUId;

		//Initializing a linked hash map
		LinkedHashMap<String , String > elementMap = new LinkedHashMap<String, String>();
		total =0;
		
		// String iterators to iterate the lists
		Iterator<String> q1=null;
		Iterator<String> q2=null;
		
		// Creating a String type of list
		List<String> list = new ArrayList<String>();
		for(WebElement i: filterList)
		{
			// Getting String values from WebElement type list
			list.add(i.getAttribute("value"));
			q1 = list.iterator();
		}
		List<String> listCount=new ArrayList<String>();
		for(WebElement j: filterListCount)
		{
			listCount.add(j.getText().replace("(", "").replace(")", ""));
			q2 = listCount.iterator();
		}
		
		// Calling stream class's map method to convert String type list into integer list
		List<Integer> listAdd = listCount.stream().map(Integer::parseInt).collect(Collectors.toList());
		
		// Adding all elements of the list to calculate total courses
		for(int i:listAdd)
		{
			total += i;
		}
		
		//Iterating both lists and adding them into map
		while (q1.hasNext() || q2.hasNext()) elementMap.put(q1.next(), q2.next());
		
		System.out.println(elementMap);
		System.out.println("Total Courses: "+total);
		elementMap.put("TOTAL COURSES",String.valueOf(total));

		//Return map to store into excel sheet
		return elementMap;
	}

}
