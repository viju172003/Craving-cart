package com.tap.model;

import java.sql.Timestamp;

public class Restaurant {

    private int restaurantId;
    private String name;
    private String address;
    private String cuisineType;
    private String description;
    private double rating;
    private String status;
    private Timestamp createDate;
    private String imagePath;
    private String deliveryTime;
    private int userid;
    public Restaurant() {
    }

    public Restaurant(String name, String address, String cuisineType,
            String description, double rating, String status,
            Timestamp createDate) {

        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.description = description;
        this.rating = rating;
        this.status = status;
        this.createDate = createDate;
    }

    public Restaurant(int restaurantId, String name, String address,
            String cuisineType, String description,
            double rating, String status,
            Timestamp createDate) {

        this.restaurantId = restaurantId;
        this.name = name;
        this.address = address;
        this.cuisineType = cuisineType;
        this.description = description;
        this.rating = rating;
        this.status = status;
        this.createDate = createDate;
    }

    public int getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(int restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Restaurant [restaurantId=" + restaurantId
                + ", name=" + name
                + ", address=" + address
                + ", cuisineType=" + cuisineType
                + ", description=" + description
                + ", rating=" + rating
                + ", status=" + status
                + ", createDate=" + createDate + "]";
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getUserid() {
        return userid;
    }
	public String getImagePath() {
	    return imagePath;
	}

	public void setImagePath(String imagePath) {
	    this.imagePath = imagePath;
	}
	public String getDeliveryTime() {
	    return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
	    this.deliveryTime = deliveryTime;
	}
}