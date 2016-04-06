package edu.asu.arpit.sdWork.Model;

import java.io.Serializable;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "RetrievedFoodItems")
public class ItemsRetrieve implements Serializable{

private ArrayList<Item> foundItems;
private ArrayList<InvalidItem> notFoundItems;
public ArrayList<Item> getFoundFoodItems() {
	return foundItems;
}
public void setFoundFoodItems(ArrayList<Item> foundFoodItems) {
	this.foundItems = foundFoodItems;
}
public ArrayList<InvalidItem> getNotFoundFoodItems() {
	return notFoundItems;
}
public void setNotFoundFoodItems(ArrayList<InvalidItem> notFoundFoodItems) {
	this.notFoundItems = notFoundFoodItems;
}
}

