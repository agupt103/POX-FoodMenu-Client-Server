package edu.asu.arpit.sdWork.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="InvalidFoodItem")
public class InvalidItem {
private int id;

public int getId() {
	return id;
}

@XmlElement(name="FoodItemId")
public void setId(int id) {
	this.id = id;
}
}
