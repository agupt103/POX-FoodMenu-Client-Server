package edu.asu.arpit.sdWork.Model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "FoodItem")
public class Item implements Serializable{

	  private static final long serialVersionUID = 1L;
	   private int id;
	   private String name;
	   private String description;
	   private String category;
	   private double price;
	   private String country;
	   
	   
	   
	public int getId() {
		return id;
	}
	
	@XmlElement(name="id")
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	@XmlElement(name="name")
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	
	@XmlElement(name="description")
	public void setDescription(String description) {
		this.description = description;
	}
	public String getCategory() {
		return category;
	}
	
	@XmlElement(name="category")
	public void setCategory(String category) {
		this.category = category;
	}
	public double getPrice() {
		return price;
	}
	
	@XmlElement(name="price")
	public void setPrice(double price) {
		this.price = price;
	}

	public String getCountry() {
		return country;
	}

	
	@XmlAttribute(name="country")
	public void setCountry(String country) {
		this.country = country;
	}
	   
	
	
	   

}

