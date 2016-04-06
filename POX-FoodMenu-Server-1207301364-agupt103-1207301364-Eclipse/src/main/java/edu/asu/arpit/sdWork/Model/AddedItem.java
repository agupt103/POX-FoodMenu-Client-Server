package edu.asu.arpit.sdWork.Model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="FoodItemAdded")
public class AddedItem {
private int id;

public int getId() {
	return id;
}
@XmlElement(name="FoodItemId")
public void setId(int id) {
	this.id = id;
}
}
