package com.example.forexamplejohn.stylezv2;

import java.util.List;

/**
 * Created by for example john on 3/12/2016.
 */
public class User {

    //MEMBER VARIABLES
    String name;
    Closet myCloset;
    int rank;
    List <String> friends;
    Latlong latlong;
    Boolean discoverable;


    public Boolean getDiscoverable() {return discoverable;}

    public void setDiscoverable(Boolean discoverable) {this.discoverable = discoverable;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Closet getMyCloset() {
        return myCloset;
    }

    public void setMyCloset(Closet myCloset) {
        this.myCloset = myCloset;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public Latlong getLatlong() {
        return latlong;
    }

    public void setLatlong(Latlong latlong) {
        this.latlong = latlong;
    }



   // Inner Obj classes
   private class Latlong{

       //MEMBER VARIABLES
        int Lat;
        int longitude;

        //DVC
        public Latlong(){

        }

        //EVC
        public Latlong(int lat,int longitude){
            this.Lat = lat;
            this.longitude = longitude;
        }

        //SETTERS
        public int getLat() {
            return Lat;
        }

        public void setLat(int lat) {
            Lat = lat;
        }


        //GETTERS
        public int getlongitude() {
            return longitude;
        }

        public void setlongitude(int aLong) {
            longitude = aLong;
        }
    }// END OF INNER CLASS LATLONG


    private class Closet{
        //MEMBER VARIABLES
        List <String> accessories;
        List <String> tops;
        List <String> bottom;
        List <String> shoes;
        List <String> dresses;
        List <String> jewelry;

        public Closet (){}

        public List<String> getAccessories() {
            return accessories;
        }

        public void setAccessories(List<String> accessories) {
            this.accessories = accessories;
        }

        public List<String> getTops() {
            return tops;
        }

        public void setTops(List<String> tops) {
            this.tops = tops;
        }

        public List<String> getBottom() {
            return bottom;
        }

        public void setBottom(List<String> bottom) {
            this.bottom = bottom;
        }

        public List<String> getShoes() {
            return shoes;
        }

        public void setShoes(List<String> shoes) {
            this.shoes = shoes;
        }

        public List<String> getDresses() {
            return dresses;
        }

        public void setDresses(List<String> dresses) {
            this.dresses = dresses;
        }

        public List<String> getJewelry() {
            return jewelry;
        }

        public void setJewelry(List<String> jewelry) {
            this.jewelry = jewelry;
        }
    }//END OF CLOSET

}//end of User


