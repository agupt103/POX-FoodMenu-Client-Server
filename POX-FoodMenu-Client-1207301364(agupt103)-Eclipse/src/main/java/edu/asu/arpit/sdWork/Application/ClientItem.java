package edu.asu.arpit.sdWork.Application;

import java.util.ArrayList;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import edu.asu.arpit.sdWork.Model.*;

public class ClientItem 
{

	private WebResource webResource;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/POX-FoodMenu-Server-1207301364-agupt103-1207301364-Eclipse";

	public ClientItem() 
	{
		ClientConfig config = new DefaultClientConfig();
		client = Client.create(config);
	}

	public void addFoodItem(Item foodItem) throws JAXBException 
	{
		webResource = client.resource(BASE_URI).path("ItemService/addItem");

		ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
				.accept(MediaType.APPLICATION_XML)
				.post(ClientResponse.class, foodItem);
         System.out.println(response.getStatus());
		if (response.getStatus() == 200) 
		{
			ExistingItem exists = response.getEntity(ExistingItem.class);
			JAXBContext context = JAXBContext.newInstance(ExistingItem.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(exists, System.out);
		} 
		else if (response.getStatus() == 201) 
		{
			AddedItem added = response.getEntity(AddedItem.class);

			JAXBContext context = JAXBContext.newInstance(AddedItem.class);

			Marshaller m = context.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			m.marshal(added, System.out);

		}
	}

	public void displayFoodItem(ItemsSelected selectFoodItem,ItemsRetrieved retrievedFoodItems) throws JAXBException 
	{
		webResource = client.resource(BASE_URI).path("ItemService/getItem");

	ClientResponse response = webResource.type(MediaType.APPLICATION_XML)
			.accept(MediaType.APPLICATION_XML)
			.post(ClientResponse.class, selectFoodItem);
	if (response.getStatus() == 200) 
	{
		Item exists = response.getEntity(Item.class);
	   if(exists!=null)
	   {
	   ArrayList<Item> foundList=retrievedFoodItems.getFoundFoodItems();
	   foundList.add(exists);
	   retrievedFoodItems.setFoundFoodItems(foundList);
	   }
	}
		else
		{
			ArrayList<InvalidItem> notFoundList=retrievedFoodItems.getNotFoundFoodItems();
			InvalidItem invalidItem=new InvalidItem();
			invalidItem.setId(selectFoodItem.getId());
			notFoundList.add(invalidItem);
			retrievedFoodItems.setNotFoundFoodItems(notFoundList);
		}
	}
	public void displayFoodItem(ItemsRetrieved retrievedFoodItems) throws JAXBException
	{
		System.out.println("inside");
		JAXBContext context = JAXBContext.newInstance(ItemsRetrieved.class);

		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

		m.marshal(retrievedFoodItems, System.out);
	
	}

}

