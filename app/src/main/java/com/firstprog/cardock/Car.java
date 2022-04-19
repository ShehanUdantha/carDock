package com.firstprog.cardock;

public class Car {
    private String carBrand, carModel, fuelType, gear, description, imgUrl, ownerName, ownerPhone, ownerAddress, manufactureYear, carPrice, carMileage, carEngine, uploadDate;
    private int carId, userId;

    public Car(int carId, int userId, String carBrand, String carModel, String fuelType, String gear, String description, String imgUrl, String manufactureYear, String carPrice, String carMileage, String carEngine, String ownerName, String ownerPhone, String ownerAddress, String uploadDate) {
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.fuelType = fuelType;
        this.gear = gear;
        this.description = description;
        this.imgUrl = imgUrl;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.ownerAddress = ownerAddress;
        this.carId = carId;
        this.userId = userId;
        this.manufactureYear = manufactureYear;
        this.carPrice = carPrice;
        this.carMileage = carMileage;
        this.carEngine = carEngine;
        this.uploadDate = uploadDate;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getGear() {
        return gear;
    }

    public void setGear(String gear) {
        this.gear = gear;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCarId() {
        return carId;
    }

    public void setCarId(int carId) {
        this.carId = carId;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public void setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOwnerAddress() {
        return ownerAddress;
    }

    public void setOwnerAddress(String ownerAddress) {
        this.ownerAddress = ownerAddress;
    }

    public String getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(String carPrice) {
        this.carPrice = carPrice;
    }

    public String getCarMileage() {
        return carMileage;
    }

    public void setCarMileage(String carMileage) {
        this.carMileage = carMileage;
    }

    public String getCarEngine() {
        return carEngine;
    }

    public void setCarEngine(String carEngine) {
        this.carEngine = carEngine;
    }

    public String getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(String uploadDate) {
        this.uploadDate = uploadDate;
    }

    @Override
    public String toString() {
        return "Car{" +
                "carBrand='" + carBrand + '\'' +
                ", carModel='" + carModel + '\'' +
                ", fuelType='" + fuelType + '\'' +
                ", gear='" + gear + '\'' +
                ", description='" + description + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerPhone='" + ownerPhone + '\'' +
                ", ownerAddress='" + ownerAddress + '\'' +
                ", manufactureYear='" + manufactureYear + '\'' +
                ", carPrice='" + carPrice + '\'' +
                ", carMileage='" + carMileage + '\'' +
                ", carEngine='" + carEngine + '\'' +
                ", uploadDate='" + uploadDate + '\'' +
                ", carId=" + carId +
                ", userId=" + userId +
                '}';
    }
}
