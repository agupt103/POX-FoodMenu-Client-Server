package edu.asu.arpit.sdWork.Application;

import java.util.Random;
import java.util.Scanner;

import edu.asu.arpit.sdWork.Model.*;

public class Application 
{
	public static void main(String[] args) 
	{
		try 
		{
			String id = null;
			Scanner scan = new Scanner(System.in);
			System.out.println("Please proceed below with your options");
			System.out.println("1. For adding the food item");
			System.out.println("2. For retrieving the food item");
			int option = Integer.parseInt(scan.next());
			ClientItem foodItemClient = new ClientItem();

			switch (option) 
			{
			case 1:
				try {
					System.out.println("Please enter the country name ");
					String country = scan.next();
					System.out.println("Please enter the food item name");
					String name = scan.next();
					System.out.println("Please enter the category of the food item");
					String category = scan.next();
					System.out.println("Please enter the description of the food item");
					String description = scan.next();
					System.out.println("Please enter the price of the food item");
					String priceString = scan.next();

					Item foodItem = null;
					Random random = new Random();
					if ((country != null && !country.trim().equals(""))
							|| (name != null && !name.trim().equals(""))
							|| (description != null
							&& !description.trim().equals(""))
							|| (category != null && !category.trim().equals(""))
							|| (priceString != null
							&& !priceString.trim().equals(""))) {
						double price = Double.parseDouble(priceString);
						foodItem = new Item();
						foodItem.setCategory(category);
						foodItem.setDescription(description);
						foodItem.setName(name);
						foodItem.setCountry(country);
						foodItem.setPrice(price);
						foodItem.setId(random.nextInt(100) + 400);
					}
					foodItemClient.addFoodItem(foodItem);			
				} 
				catch (Exception e) 
				{
					System.out.println("<InvalidMessage>");
				}
				break;
				
			case 2:
				try 
				{
					System.out.println("Please enter the number of items you want to retrieve");
					String ids = scan.next();
					ItemsSelected selectFood = null;
					foodItemClient = new ClientItem();
					if (Integer.parseInt(ids) > 0) 
					{
						ItemsRetrieved itemsRetrieved = new ItemsRetrieved();
						for (int i = 0; i < Integer.parseInt(ids); i++) 
						{
							System.out.println("Please enter the id of the food item for retrieval");
							int itemId = scan.nextInt();
							selectFood = new ItemsSelected();
							selectFood.setId(itemId);
							foodItemClient.displayFoodItem(selectFood,itemsRetrieved);
						}
						foodItemClient
								.displayFoodItem(itemsRetrieved);
					}
					break;
				} 
				catch (Exception e) {
					System.out.println("<InvalidMessage>");
				}

			}
		} catch (Exception e) 
		{
			System.out.println("<InvalidMessage>");
		}
	}
}
