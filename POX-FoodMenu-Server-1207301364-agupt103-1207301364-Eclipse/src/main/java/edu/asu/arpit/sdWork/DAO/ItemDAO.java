package edu.asu.arpit.sdWork.DAO;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.Result;
import javax.xml.transform.Source;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import edu.asu.arpit.sdWork.Model.*;

public class ItemDAO {

	public String addFoodItem(Item foodItem) {
		try {
			String found=null;
			boolean flag = false;
			final String dir = System.getProperty("user.dir");
			String fullPath = dir + "/FoodItemData.xml";
			File file = new File(fullPath);
			if (file.exists()) 
			{
 				InputStream inputStream= new FileInputStream(file);
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");   
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(is);
				
				NodeList nodeList = doc.getElementsByTagName("FoodItem");

				for (int temp = 0; temp < nodeList.getLength(); temp++) 
				{
					Node nNode = nodeList.item(temp);
					Element eElement = (Element) nNode;
					String category = eElement.getElementsByTagName("category")
							.item(0).getTextContent();
					String name = eElement.getElementsByTagName("name").item(0)
							.getTextContent();
					if (foodItem.getCategory().equals(category)
							&& foodItem.getName().equals(name)) 
					{
						System.out.println("found");
						found=eElement.getElementsByTagName("id").item(0)
								.getTextContent();
						flag = true;
						break;
					}
				}
				if (flag)
				{
				    	
					return found;
				}
					
				else 
				{
					Element eElement=doc.createElement("FoodItem");
					eElement.setAttribute("country", foodItem.getCountry());
					Element eId=doc.createElement("id");
					eId.setTextContent(String.valueOf(foodItem.getId()));
					Element eName=doc.createElement("name");
                    eName.setTextContent(foodItem.getName());
					Element eDescription=doc.createElement("description");
					eDescription.setTextContent(foodItem.getDescription());
					Element eCategory=doc.createElement("category");
					eCategory.setTextContent(foodItem.getCategory());
					Element ePrice=doc.createElement("price");
					ePrice.setTextContent(String.valueOf(foodItem.getPrice()));

					eElement.appendChild(eId);
					eElement.appendChild(eName);
					eElement.appendChild(eDescription);
					eElement.appendChild(eCategory);
					eElement.appendChild(ePrice);
										
					Element rootTag=(Element) doc.getElementsByTagName("FoodItemData").item(0);
					rootTag.appendChild(eElement);
					
					System.out.println(saveDoc(doc,fullPath));
				return "true";
				}

			}

		} catch (Exception e) 
		{
			e.printStackTrace();
			return "false";
		}
		//return false;
		return null;	
	}
	
	public Item displayFoodItem(SelectedItems selectFoodItem) 
	{
		try 
		{
			final String dir = System.getProperty("user.dir");
			String fullPath = dir + "/FoodItemData.xml";
			File file = new File(fullPath);
			System.out.println("eneterd");
			String foundId=null;

			boolean flag = false;
			if (file.exists()) {
				System.out.println("file exists");
 				InputStream inputStream= new FileInputStream(file);
	            Reader reader = new InputStreamReader(inputStream,"UTF-8");
	            InputSource is = new InputSource(reader);
	            is.setEncoding("UTF-8");
	            
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
				Document doc = dBuilder.parse(is);
				
				NodeList nList = doc.getElementsByTagName("FoodItem");
				Item item=new Item();

				for (int temp = 0; temp < nList.getLength(); temp++) {
					Node nNode = nList.item(temp);
					Element eElement = (Element) nNode;
					String id = eElement.getElementsByTagName("id")
							.item(0).getTextContent();
	
					if (selectFoodItem.getId() == Integer.parseInt(id))
					 {
						System.out.println("found");
						foundId=eElement.getElementsByTagName("id").item(0)
								.getTextContent();
						flag = true;
						
						item.setId(Integer.parseInt(id));
						
						String country = eElement.getAttribute("country");
						String category = eElement.getElementsByTagName("category")
								.item(0).getTextContent();
						String description = eElement.getElementsByTagName("description")
								.item(0).getTextContent();
						String price = eElement.getElementsByTagName("price")
								.item(0).getTextContent();
						String name = eElement.getElementsByTagName("name")
								.item(0).getTextContent();

						
						item.setCategory(category);
						item.setCountry(country);
						item.setDescription(description);
						item.setName(name);
						item.setPrice(Double.parseDouble(price));
						flag=true;
						break;
						
					}
					
				}
				if(flag)
				{
					return item;
				}
				else
				{
					System.out.println("m kaha hu");
					return null;
				}
				}
			

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		//return false;
		return null;
		
	}
	
	public static String saveDoc(Node node, String fullPath) throws TransformerFactoryConfigurationError, TransformerException
	{
		Transformer xform = TransformerFactory.newInstance().newTransformer();
	    Result output=new StreamResult(new File(fullPath));
	    Source input=new DOMSource(node);
	    xform.transform(input, output);
	    return output.toString();
	}
}

