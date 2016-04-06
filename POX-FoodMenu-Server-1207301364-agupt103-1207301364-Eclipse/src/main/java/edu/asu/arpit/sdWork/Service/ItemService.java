package edu.asu.arpit.sdWork.Service;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.asu.arpit.sdWork.DAO.ItemDAO;
import edu.asu.arpit.sdWork.Model.*;

@Path("/ItemService")
public class ItemService {
	private static final Logger LOG = LoggerFactory
			.getLogger(ItemService.class);

	@POST
	@Path("/addItem")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response addFoodItem(Item foodItem) {
		try {
			LOG.info("Creating the html greeting message");
			LOG.debug("POST request");

			ItemDAO foodItemDAO = new ItemDAO();
          System.out.println("abc");
			if (foodItem != null) {

				String result = foodItemDAO.addFoodItem(foodItem);
				if (result.equals("true")) {
					AddedItem added = new AddedItem();
					added.setId(foodItem.getId());
					return Response.status(201).entity(added).build();
				} else if (!result.equals("false") && result != null) {
					ExistingItem exists = new ExistingItem();
					exists.setId(Integer.parseInt(result));
					return Response.status(200).entity(exists).build();
				} else {
					throw new IllegalArgumentException();
				}
			} else {
				throw new IllegalArgumentException();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	@POST
	@Path("/getItem")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_XML)
	public Response displayFoodItem(SelectedItems selectFoodItem) {
		try 
		{
			ItemDAO foodItemDAO = new ItemDAO();
			Item result = foodItemDAO.displayFoodItem(selectFoodItem);
			if (result != null) 
			{
				return Response.status(200).entity(result).build();
			} else 
			{
				return Response.status(404).build();
			}

		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return null;
	}
}

