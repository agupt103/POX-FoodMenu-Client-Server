package edu.asu.arpit.sdWork.Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "RetrievedFoodItems")
public class ItemsRetrieved implements Serializable{

private ArrayList<Item> foundItems;
private ArrayList<InvalidItem> notFoundItems;

public ItemsRetrieved()
{
	foundItems=new ArrayList<Item>();
	notFoundItems=new ArrayList<InvalidItem>();
}
public ArrayList<Item> getFoundFoodItems() {
	return foundItems;
}
@XmlElement(name="FoodItem")
public void setFoundFoodItems(ArrayList<Item> foundFoodItems) {
	this.foundItems = foundFoodItems;
}
public ArrayList<InvalidItem> getNotFoundFoodItems() {
	return notFoundItems;
}
@XmlElement(name="InvalidFoodItem")
public void setNotFoundFoodItems(ArrayList<InvalidItem> notFoundFoodItems) {
	this.notFoundItems = notFoundFoodItems;
}
}

