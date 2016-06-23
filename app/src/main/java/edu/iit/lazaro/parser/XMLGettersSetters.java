package edu.iit.lazaro.parser;

/**
 * Created by Lazaro on 3/1/16.
 */
import java.util.ArrayList;
import android.util.Log;

/**
 *  This class contains all getter and setter methods to set and retrieve data.
 *
 **/
public class XMLGettersSetters {

    private ArrayList<String> title = new ArrayList<String>();
    private ArrayList<String> artist = new ArrayList<String>();
    private ArrayList<String> country = new ArrayList<String>();
    private ArrayList<String> company = new ArrayList<String>();
    private ArrayList<String> price = new ArrayList<String>();
    private ArrayList<String> year = new ArrayList<String>();
    private ArrayList<String> CD = new ArrayList<String>();

    public ArrayList<String> getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company.add(company);
        Log.i("This is the company:", company);
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price.add(price);
        Log.i("This is the price:", price);
    }

    public ArrayList<String> getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year.add(year);
        Log.i("This is the year:", year);
    }

    public ArrayList<String> getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title.add(title);
        Log.i("This is the title:", title);
    }

    public ArrayList<String> getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist.add(artist);
        Log.i("This is the artist:", artist);
    }

    public ArrayList<String> getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country.add(country);
        Log.i("This is the country:", country);
    }

    public void setAttribute(String attr) {
        this.CD.add(attr);
        Log.i("Sold out?: ", attr);
    }

    public ArrayList<String> getAttribute() {
        return CD;
    }

}

