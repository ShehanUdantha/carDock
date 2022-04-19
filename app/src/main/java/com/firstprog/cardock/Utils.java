package com.firstprog.cardock;

import static android.content.ContentValues.TAG;

import android.util.Log;

import java.util.ArrayList;

public class Utils {
    private static ArrayList<User> allUsers;
    private static ArrayList<Car> allCars;
    private static Utils instance;
    private static int getID;

    private Utils(){
        if(null == allUsers){
            allUsers = new ArrayList<>();
        }

        if(null == allCars){
            allCars = new ArrayList<>();
        }
    }

    public void initUserData(int userId, String personName, String personEmail, String personLocation, String personPhone, String password) {
        allUsers.add(new User(userId,personName, personEmail, personLocation, personPhone, password));
    }

    public void initCarData(int carId, int userId, String carBrand, String carModel, String fuelType, String gear, String description, String imgUrl, String manufactureYear, String carPrice, String carMileage, String carEngine, String ownerName, String ownerPhone, String ownerAddress, String uploadDate){
        allCars.add(new Car(carId, userId, carBrand, carModel, fuelType, gear, description, imgUrl, manufactureYear, carPrice, carMileage, carEngine, ownerName, ownerPhone, ownerAddress, uploadDate));
    }

    public String getName(int id){
        return allUsers.get(id).getPersonName();
    }

    public String getLocation(int id){
        return allUsers.get(id).getPersonLocation();
    }

    public String getPhone(int id){
        return allUsers.get(id).getPersonPhone();
    }

    public String getEmail(int id){
        return allUsers.get(id).getPersonEmail();
    }

    public String getPassword(int id){
        return allUsers.get(id).getPassword();
    }

    public int getIdFromEmailAndPassword(String email, String password){
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getPersonEmail().equals(email) && allUsers.get(i).getPassword().equals(password)){
                getID = allUsers.get(i).getUserId();
            }
        }
        return getID;
    }

    public boolean loginValidator(String email, String password){
        boolean isChecked = false;
        for (int i = 0; i < allUsers.size(); i++) {
            if(allUsers.get(i).getPersonEmail().equals(email) && allUsers.get(i).getPassword().equals(password)){
                isChecked = true;
            }
        }
        return isChecked;
    }

    public boolean checker(String email){
        boolean isExisted = false;
        for(User u : allUsers){
            if(u.getPersonEmail().equals(email)){
                isExisted = true;
            }
        }
        return isExisted;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        Utils.allUsers = allUsers;
    }

    public static ArrayList<Car> getAllCars() {
        return allCars;
    }

    public static void setAllCars(ArrayList<Car> allCars) {
        Utils.allCars = allCars;
    }

    public static Utils getInstance() {
        if (null == instance) {
            instance = new Utils();
        }
        return instance;
    }

    public static void setInstance(Utils instance) {
        Utils.instance = instance;
    }
}
